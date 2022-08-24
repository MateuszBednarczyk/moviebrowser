package com.matthew.recrutation.service.user;

import com.matthew.recrutation.domain.UserEntity;

import java.security.Principal;

public interface UserFindingService {
    UserEntity findUserByUsername(Principal loggedUser);
}
