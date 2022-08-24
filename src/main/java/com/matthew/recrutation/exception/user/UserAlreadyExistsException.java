package com.matthew.recrutation.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends IllegalArgumentException {
    public UserAlreadyExistsException(String username) {
        super("User " + username + " already exists");
    }
}
