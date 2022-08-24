package com.matthew.recrutation.exception;

import com.matthew.recrutation.exception.general.GeneralException;
import com.matthew.recrutation.exception.movie.MovieIsAlreadyFavouriteException;
import com.matthew.recrutation.exception.user.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GeneralException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String generalException(GeneralException e) {
        return e.getMessage();
    }

    @ExceptionHandler(MovieIsAlreadyFavouriteException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String movieIsAlreadyFavouriteException(MovieIsAlreadyFavouriteException e) {
        return e.getMessage();
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String userAlreadyExistsException(UserAlreadyExistsException e) {
        return e.getMessage();
    }
}
