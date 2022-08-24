package com.matthew.recrutation.service.movie;

import com.matthew.recrutation.dto.movie.AddMovieAsFavouriteRequestDTO;

import java.security.Principal;

public interface MovieManagementService {
    void addMovieAsFavourite(AddMovieAsFavouriteRequestDTO requestDTO, Principal loggedUser);
}
