package com.example.candy.controller.authentication;

import com.example.candy.controller.ApiResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @PostMapping("/authenticate")
    public ApiResult<AuthenticationResponseDto> authentication() {
        return ApiResult.OK(
                new AuthenticationResponseDto(());
        );
    }
}
