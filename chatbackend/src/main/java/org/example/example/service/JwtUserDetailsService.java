package org.example.example.service;

import lombok.RequiredArgsConstructor;
import org.example.example.model.UserEntity;
import org.example.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUserName(username);
        if(userEntity == null){
            throw new RuntimeException("User Not Found");
        }
        return new User(userEntity.getUsername(), userEntity.getPassword(), new ArrayList<>());
    }
}
