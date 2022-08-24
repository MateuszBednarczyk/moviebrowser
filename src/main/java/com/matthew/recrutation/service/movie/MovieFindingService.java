package com.matthew.recrutation.service.movie;

import com.matthew.recrutation.domain.MovieEntity;
import com.matthew.recrutation.dto.movie.MovieDTO;

import java.security.Principal;
import java.util.List;

public interface MovieFindingService {
    MovieDTO findByTitleAndReleaseYear(String title, String released);

    MovieEntity findEntityInDatabase(String title);

    List<MovieDTO> findMostLikedMovies();

    List<MovieDTO> findUsersLikedMovies(Principal loggedUser);
}
