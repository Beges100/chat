package org.example.example.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import ru.chatapi.api.LoginApi;
import ru.chatapi.model.UserDto;


@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(value = "*")
public class AuthController implements LoginApi {


    @Override
    public ResponseEntity<String> loginPost(UserDto userDto) {
        System.out.println(userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
