package com.matthew.recrutation.api;

import com.matthew.recrutation.dto.user.RegisterNewUserRequestDTO;
import com.matthew.recrutation.service.user.UserManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users/")
@RequiredArgsConstructor
public class UserController {

    private final UserManagementService userManagementService;

    @PostMapping("register")
    public ResponseEntity<String> registerNewUser(@RequestBody RegisterNewUserRequestDTO requestDTO) {
        userManagementService.registerNewUser(requestDTO);
        return new ResponseEntity<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK);
    }
}
