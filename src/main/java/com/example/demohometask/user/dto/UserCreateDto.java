package com.example.demohometask.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserCreateDto extends UserBaseDto {

    @NotBlank(message = "auth.user.password.required")
    private String password;

}
