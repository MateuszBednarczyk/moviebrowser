package com.matthew.recrutation.service.movie;

import com.matthew.recrutation.domain.MovieEntity;
import com.matthew.recrutation.dto.movie.MovieDTO;
import com.matthew.recrutation.mapper.DTOModelMapper;
import com.matthew.recrutation.mapper.MovieModelMapper;
import com.matthew.recrutation.model.MovieModel;
import com.matthew.recrutation.repository.MovieRepository;
import com.matthew.recrutation.service.user.UserFindingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
class MovieFindingServiceImpl implements MovieFindingService {
    private final MovieModelMapper movieModelMapper;
    private final DTOModelMapper dtoModelMapper;
    private final MovieRepository movieRepository;
    private final UserFindingService userFindingService;
    private final String apiKeyParameter = "&apikey=9cfc604e";
    RestTemplate restTemplate = new RestTemplate();
    private String url = "http://www.omdbapi.com/?t=";

    @Override
    public MovieDTO findByTitleAndReleaseYear(String title, String released) {
        MovieModel movieModel = getMovieModelFromApiThenCreateMovieModelObject(title, released);
        MovieEntity movieEntity = movieModelMapper.mapMovieModelToMovieEntity(movieModel);
        checkIfMovieIsAlreadySavedElseSaveIfNotSavedSetLikesToZero(movieEntity);

        return dtoModelMapper.mapMovieEntityToMovieDTO(movieEntity);
    }

    @Override
    public MovieEntity findEntityInDatabase(String title) {
        return movieRepository.findByTitle(title).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<MovieDTO> findMostLikedMovies() {
        List<MovieDTO> movieDTOList = new ArrayList<>();
        List<MovieEntity> movieEntitiesOrderedByLikesDescList = movieRepository.findAllAndOrderByLikes();
        createListOfTopLikedMovies(movieDTOList, movieEntitiesOrderedByLikesDescList);

        return movieDTOList;
    }

    @Override
    public List<MovieDTO> findUsersLikedMovies(Principal loggedUser) {
        List<MovieDTO> movieDTOList = new ArrayList<>();
        List<MovieEntity> favouriteMovies = getUsersLikedMovies(loggedUser);
        mapLikedMovieEntitiesToDTOAndAddThemToMovieDTOList(movieDTOList, favouriteMovies);

        return movieDTOList;
    }

    private void mapLikedMovieEntitiesToDTOAndAddThemToMovieDTOList(List<MovieDTO> movieDTOList, List<MovieEntity> favouriteMovies) {
        favouriteMovies.forEach(movieEntity -> movieDTOList.add(dtoModelMapper.mapMovieEntityToMovieDTO(movieEntity)));
    }

    private List<MovieEntity> getUsersLikedMovies(Principal loggedUser) {
        return userFindingService.findUserByUsername(loggedUser).getFavouriteMovies();
    }

    private void createListOfTopLikedMovies(List<MovieDTO> movieDTOList, List<MovieEntity> movieEntitiesOrderedByLikesDescList) {
        for (int i = 0; i <= getIterationTimes(movieEntitiesOrderedByLikesDescList); i++) {
            movieDTOList.add(dtoModelMapper.mapMovieEntityToMovieDTO(movieEntitiesOrderedByLikesDescList.get(i)));
        }
    }

    private int getIterationTimes(List<MovieEntity> movieEntitiesOrderedByLikesDescList) {
        int iterationTimes;
        if (isMovieEntitiesListBiggerThanThreeEntities(movieEntitiesOrderedByLikesDescList)) {
            iterationTimes = movieEntitiesOrderedByLikesDescList.size()-1;
        } else if (movieEntitiesOrderedByLikesDescList.isEmpty()) {
            iterationTimes = 0;
        } else {
            iterationTimes = 2-1;
        }

        return iterationTimes;
    }

    private boolean isMovieEntitiesListBiggerThanThreeEntities(List<MovieEntity> movieEntitiesOrderedByLikesDescList) {
        return movieEntitiesOrderedByLikesDescList.size() <= 3;
    }

    private void checkIfMovieIsAlreadySavedElseSaveIfNotSavedSetLikesToZero(MovieEntity movieEntity) {
        if (movieRepository.findByTitle(movieEntity.getTitle()).isEmpty()) {
            setMovieLikesToZero(movieEntity);
            movieRepository.save(movieEntity);
        }
    }

    private void setMovieLikesToZero(MovieEntity movieEntity) {
        movieEntity.setLikes(0);
    }

    private MovieModel getMovieModelFromApiThenCreateMovieModelObject(String title, String released) {
        String completeURL = createCompleteUrl(title, released);
        ResponseEntity<MovieModel> response = restTemplate.getForEntity(completeURL, MovieModel.class);

        return response.getBody();
    }

    private String createCompleteUrl(String title, String released) {
        return url + title + "&y=" + released + apiKeyParameter;
    }
}
