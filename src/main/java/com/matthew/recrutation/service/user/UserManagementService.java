package com.matthew.recrutation.service.user;

import com.matthew.recrutation.dto.user.RegisterNewUserRequestDTO;

public interface UserManagementService {
    void registerNewUser(RegisterNewUserRequestDTO requestDTO);
}
