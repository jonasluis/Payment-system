package com.jonasluis.payment_system.dto;

import com.jonasluis.payment_system.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserCreateRequest(

        @NotNull(message = "O nome nao pode ser nulo")
        @NotBlank(message = "O nome nao pode ser vazio")
        String name,

        @NotNull(message = "Email nao pode ser nulo")
        @NotBlank(message = "Email nao pode ser vazio")
        @Email
        String email,

        @NotNull(message = "Senha nao pode ser nulo")
        @NotBlank(message = "Senha nao pode ser vazio")
        @Size(min = 8, message = "A senha deve conter no minimo 8 caracteres")
        String password) {

    public User toModel(){
       return new User(name, email, password);
    }
}
