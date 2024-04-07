package org.example.example.controller;


import lombok.RequiredArgsConstructor;
import org.example.example.repository.UserRepository;
import org.example.example.service.JwtUserDetailsService;
import org.example.example.service.UserService;
import org.example.example.util.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import ru.chatapi.api.SignUpApi;
import ru.chatapi.model.JwtResponseDto;
import ru.chatapi.model.UserDto;


@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "*")
public class AuthController implements SignUpApi {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;
    private final UserService userService;



//    private void authenticate(String username, String password) throws Exception {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        } catch (DisabledException e) {
//            throw new Exception("User is disabled!", e);
//        } catch (BadCredentialsException e) {
//            throw new Exception("Incorrect username or password!", e);
//        }
//    }


    @Override
    public ResponseEntity<JwtResponseDto> signUp(UserDto userDto) {
        userService.saveUser(userDto);
        return new ResponseEntity<>(new JwtResponseDto().token(jwtTokenUtil.generateToken(userDto.getUsername())), HttpStatus.OK);
    }
}
