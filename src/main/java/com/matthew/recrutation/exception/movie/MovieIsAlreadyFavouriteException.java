package com.matthew.recrutation.exception.movie;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class MovieIsAlreadyFavouriteException extends IllegalArgumentException {
    public MovieIsAlreadyFavouriteException(String title, String released) {
        super(title + " " + released + " is already favourite");
    }
}
