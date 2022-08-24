package com.matthew.recrutation.service.user;

import com.matthew.recrutation.config.SuffixConfiguration;
import com.matthew.recrutation.domain.UserEntity;
import com.matthew.recrutation.dto.user.RegisterNewUserRequestDTO;
import com.matthew.recrutation.exception.user.UserAlreadyExistsException;
import com.matthew.recrutation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
@Slf4j
public class UserManagementServiceImpl implements UserManagementService {

    private final UserRepository userRepository;
    private final SuffixConfiguration suffixConfiguration;

    @Override
    public void registerNewUser(RegisterNewUserRequestDTO requestDTO) {
        String encodedPassword = suffixConfiguration.passwordEncoder().encode(requestDTO.getPassword());
        UserEntity userEntity = new UserEntity(requestDTO.getUsername(), encodedPassword);
        if (userRepository.findByUsername(requestDTO.getUsername()).isEmpty()) {
            userRepository.save(userEntity);
        } else {
            throw new UserAlreadyExistsException(requestDTO.getUsername());
        }
    }
}
