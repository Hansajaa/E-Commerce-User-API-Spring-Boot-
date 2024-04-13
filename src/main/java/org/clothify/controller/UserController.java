package org.clothify.controller;

import lombok.RequiredArgsConstructor;
import org.clothify.entity.UserEntity;
import org.clothify.model.Login;
import org.clothify.model.User;
import org.clothify.service.LoginService;
import org.clothify.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
@CrossOrigin
public class UserController {

    final UserService userService;
    final LoginService loginService;

    @PostMapping("/add")
    public ResponseEntity<UserEntity> createUser(@RequestBody User user){

        Boolean isSaved = loginService.saveLoginDetails(new Login(user.getUsername(), user.getPassword()));

        if (isSaved){
            UserEntity userEntity = userService.saveUserDetails(user);
            return ResponseEntity.ok().body(userEntity);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @PostMapping("/check-username/{username}")
    public Boolean checkUserName(@PathVariable String username){
        return userService.existsByUserName(username);
    }
}
