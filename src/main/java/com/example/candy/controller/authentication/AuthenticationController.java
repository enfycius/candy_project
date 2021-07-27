package com.example.candy.controller.authentication;

import com.example.candy.controller.ApiResult;
import com.example.candy.security.AuthenticationRequest;
import com.example.candy.security.AuthenticationResult;
import com.example.candy.security.JwtAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.candy.controller.ApiResult.OK;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public ApiResult<AuthenticationResponseDto> authentication(@RequestBody AuthenticationRequest authRequest) {
        try {
            JwtAuthenticationToken authToken = new JwtAuthenticationToken(authRequest.getPrincipal(), authRequest.getCredentials());
            Authentication authentication = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return OK(
                    new AuthenticationResponseDto((AuthenticationResult) authentication.getDetails())
            );
        } catch (AuthenticationException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }
}
