package org.clothify.controller;

import lombok.RequiredArgsConstructor;
import org.clothify.entity.UserEntity;
import org.clothify.model.Login;
import org.clothify.model.User;
import org.clothify.service.LoginService;
import org.clothify.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    final UserService userService;
    final LoginService loginService;

    @PostMapping("/add")
    public UserEntity createUser(@RequestBody User user){

        loginService.saveLoginDetails(new Login(user.getUsername(), user.getPassword()));

        UserEntity userEntity = userService.saveUserDetails(user);
        return userEntity;

    }
}
