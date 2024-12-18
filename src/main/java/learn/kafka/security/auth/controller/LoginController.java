package learn.kafka.security.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import learn.kafka.security.auth.dto.UserDto;
import learn.kafka.security.auth.model.User;
import learn.kafka.security.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping(path = "/user/registration")
    public User registerUserAccount(@Valid @RequestBody UserDto dto, HttpServletRequest request) {
        return userService.registerNewUserAccount(dto);
    }

    @PostMapping(path = "/user/confirm")
    public String confirmUser(@RequestParam Long code) {
        return userService.generateToken(code);
    }
}
