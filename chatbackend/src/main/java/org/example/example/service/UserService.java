package org.example.example.service;

import lombok.RequiredArgsConstructor;
import org.example.example.model.UserEntity;
import org.example.example.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.chatapi.model.UserDto;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void saveUser(UserDto userDto) {
        var user = new UserEntity();
        user.setId(UUID.randomUUID());
        user.setUserName(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
    }
}
