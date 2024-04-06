package org.example.example.filter;

import io.jsonwebtoken.ExpiredJwtException;
import org.example.example.service.JwtUserDetailsService;
import org.example.example.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, ServletException, IOException {
        final String tokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        // if token is present then extract it
        if(tokenHeader != null && tokenHeader.startsWith("Bearer")){
            jwtToken = tokenHeader.substring(7);

            // fetch username from token
            try{
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            }
            catch (IllegalArgumentException e){
                System.out.println("Unable to get JWT Token");
            }
            catch (ExpiredJwtException e){
                System.out.println("JWT Token is expired");
            }
        }else {
            logger.warn("JWT Token does not begin with Bearer String");
        }

        // validate the token
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if(jwtTokenUtil.validateToken(jwtToken, userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
