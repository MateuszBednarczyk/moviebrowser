package com.matthew.recrutation.service.user;

import com.matthew.recrutation.domain.UserEntity;
import com.matthew.recrutation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserFindingServiceImpl implements UserFindingService {

    private final UserRepository userRepository;

    @Override
    public UserEntity findUserByUsername(Principal loggedUser) {
        return userRepository.findByUsername(loggedUser.getName())
                .orElseThrow(() -> new UsernameNotFoundException(loggedUser.getName()));
    }
}
