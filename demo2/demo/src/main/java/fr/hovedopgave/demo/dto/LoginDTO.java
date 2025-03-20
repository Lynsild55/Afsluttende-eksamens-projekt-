package fr.hovedopgave.demo.dto;


import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginDTO {
    @NotBlank(message = "Username cannot be empty")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    private String password;
}