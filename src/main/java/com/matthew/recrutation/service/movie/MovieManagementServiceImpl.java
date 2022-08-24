package com.matthew.recrutation.service.movie;

import com.matthew.recrutation.domain.MovieEntity;
import com.matthew.recrutation.domain.UserEntity;
import com.matthew.recrutation.dto.movie.AddMovieAsFavouriteRequestDTO;
import com.matthew.recrutation.dto.movie.MovieDTO;
import com.matthew.recrutation.exception.movie.MovieIsAlreadyFavouriteException;
import com.matthew.recrutation.service.user.UserFindingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;

@Service
@RequiredArgsConstructor
@Transactional
class MovieManagementServiceImpl implements MovieManagementService {

    private final MovieFindingService movieFindingService;
    private final UserFindingService userFindingService;

    @Override
    public void addMovieAsFavourite(AddMovieAsFavouriteRequestDTO requestDTO, Principal loggedUser) {
        UserEntity userEntity = userFindingService.findUserByUsername(loggedUser);
        MovieDTO movieDTO = getMovieDTO(requestDTO);
        MovieEntity movieEntityFoundInDatabase = getMovieEntity(movieDTO);
        checkIfMovieIsAlreadyAddedAsFavouriteElseAddToFavouriteListAndAddLikeToMovie(userEntity, movieEntityFoundInDatabase);
    }

    private void checkIfMovieIsAlreadyAddedAsFavouriteElseAddToFavouriteListAndAddLikeToMovie(UserEntity userEntity, MovieEntity movieEntityFoundInDatabase) {
        if (userEntity.getFavouriteMovies().contains(movieEntityFoundInDatabase)) {
            throw new MovieIsAlreadyFavouriteException(movieEntityFoundInDatabase.getTitle(), movieEntityFoundInDatabase.getReleased());
        } else {
            setMovieEntityLikesToZeroValueIfNotExists(movieEntityFoundInDatabase);
            userEntity.getFavouriteMovies().add(movieEntityFoundInDatabase);
        }
    }

    private MovieEntity getMovieEntity(MovieDTO movieDTO) {
        String titleOfFoundMovie = movieDTO.getTitle();

        return movieFindingService.findEntityInDatabase(titleOfFoundMovie);
    }

    private MovieDTO getMovieDTO(AddMovieAsFavouriteRequestDTO requestDTO) {
        return movieFindingService.findByTitleAndReleaseYear(requestDTO.getMovieTitle(), requestDTO.getReleased());
    }

    private void setMovieEntityLikesToZeroValueIfNotExists(MovieEntity movieEntityFoundInDatabase) {
        movieEntityFoundInDatabase.setLikes(movieEntityFoundInDatabase.getLikes() + 1);
    }
}
