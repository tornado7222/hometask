package com.example.demohometask.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBaseDto {


//    @NotBlank(message = "auth.user.firstName.required")
    private String firstName;

//    @NotBlank(message = "auth.user.lastName.required")
    private String lastName;

    @NotBlank(message = "auth.user.phoneNumber.required")
    @Pattern(regexp = "^998\\d{9}$", message = "pattern.phone.number")
    private String phoneNumber;

    private LocalDate birthDate;
}
