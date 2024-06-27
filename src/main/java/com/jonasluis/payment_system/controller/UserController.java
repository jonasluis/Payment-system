package com.jonasluis.payment_system.controller;

import com.jonasluis.payment_system.dto.UserRequest;
import com.jonasluis.payment_system.entity.User;
import com.jonasluis.payment_system.service.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserSevice userSevice;

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody UserRequest userRequest){
        User user = userRequest.toModel();
        User userSaved = userSevice.registerUser(user);
        return ResponseEntity.ok().body(userSaved);
    }


}
