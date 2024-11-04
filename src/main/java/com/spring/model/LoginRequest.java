package com.spring.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginRequest {

    @NotEmpty
    private String login;
    @NotEmpty
    private String password;
}
