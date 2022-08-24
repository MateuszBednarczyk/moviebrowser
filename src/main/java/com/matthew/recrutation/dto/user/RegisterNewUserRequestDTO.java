package com.matthew.recrutation.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Immutable
@Getter
@AllArgsConstructor
public class RegisterNewUserRequestDTO {
    String username;
    String password;
}
