package org.clothify.controller;

import lombok.RequiredArgsConstructor;
import org.clothify.model.Login;
import org.clothify.service.LoginService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/login")
@CrossOrigin
@RequiredArgsConstructor
public class LoginController {

    final LoginService loginService;

    @PostMapping("/authenticate")
    public Boolean authenticate(@RequestBody Login login){

        try {
            return loginService.authenticateUser(login);
        } catch (Exception e) {
            return false;
        }
    }
}
