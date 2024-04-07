package org.example.example.config;

import lombok.RequiredArgsConstructor;
import org.example.example.filter.JwtRequestFilter;
import org.example.example.filter.UserNameAuthorizationFilter;
import org.example.example.security.JwtAuthenticationEntryPoint;
import org.example.example.service.JwtUserDetailsService;
import org.example.example.util.JwtTokenUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtUserDetailsService userDetailsService;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtRequestFilter jwtRequestFilter;

    private static final String TOKEN_BASED_AUTH_ENTRY_POINT = "/api/**";
    private static final String GLOBAL_API = "/api/global/**";
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        var customFilter = new UserNameAuthorizationFilter(authenticationManager(), jwtTokenUtil);
        httpSecurity
                .csrf().disable()
                .exceptionHandling()
                .and()
                .addFilter(customFilter)
                .authorizeRequests().antMatchers("/sign-up").permitAll()
                .and()
                .authorizeRequests().antMatchers("/login").permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers(GLOBAL_API).permitAll()

                .and()
                .authorizeRequests()
                .antMatchers(TOKEN_BASED_AUTH_ENTRY_POINT).authenticated()

                .and()
                .authorizeRequests().anyRequest().authenticated();
    }
}
