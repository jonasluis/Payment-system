package com.jonasluis.payment_system.controller;

import com.jonasluis.payment_system.dto.UserRequest;
import com.jonasluis.payment_system.dto.UserResponse;
import com.jonasluis.payment_system.entity.User;
import com.jonasluis.payment_system.service.UserSevice;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserSevice userSevice;

    @PostMapping
    public ResponseEntity<UserResponse> registerUser(@RequestBody @Valid UserRequest userRequest) throws MessagingException, UnsupportedEncodingException {
        User user = userRequest.toModel();
        UserResponse userSaved = userSevice.registerUser(user);
        return ResponseEntity.ok().body(userSaved);
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code){
        if (userSevice.verify(code)){
            return "verify_success";
        }else {
            return "verify_fail";
        }
    }

}
