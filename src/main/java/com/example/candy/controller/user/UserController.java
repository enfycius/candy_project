package com.example.candy.controller.user;

import com.example.candy.controller.ApiResult;
import com.example.candy.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/exist")
    public ApiResult<Boolean> checkEmail(@RequestBody Map<String, String> request) {
        return ApiResult.OK(userService.findByEmail(request.get("email")).isPresent());
    }
}
