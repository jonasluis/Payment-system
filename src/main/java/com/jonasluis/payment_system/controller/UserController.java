package com.jonasluis.payment_system.controller;

import com.jonasluis.payment_system.dto.UserCreateRequest;
import com.jonasluis.payment_system.dto.UserResponse;
import com.jonasluis.payment_system.entity.User;
import com.jonasluis.payment_system.service.TokenService;
import com.jonasluis.payment_system.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody @Valid UserCreateRequest userCreateRequest) throws MessagingException, UnsupportedEncodingException {
        User user = userCreateRequest.toModel();
        UserResponse userSaved = userService.registerUser(user);
        return ResponseEntity.ok().body(userSaved);
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code){
        if(userService.verify(code)){
            return "verify_success";
        } else {
            return "verify_fail";
        }
    }

    @GetMapping("/teste")
    public String teste(){
        return "você está logado";
    }

}
