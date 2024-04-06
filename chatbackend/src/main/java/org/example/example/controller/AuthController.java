package org.example.example.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.example.service.JwtUserDetailsService;
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
import ru.chatapi.api.LoginApi;
import ru.chatapi.model.UserDto;


@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "*")
public class AuthController implements LoginApi {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;

    @Override
    public ResponseEntity<String> loginPost(UserDto userDto) {

        try {
            authenticate(userDto.getUsername(), userDto.getPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(token);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("User is disabled!", e);
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password!", e);
        }
    }
}
