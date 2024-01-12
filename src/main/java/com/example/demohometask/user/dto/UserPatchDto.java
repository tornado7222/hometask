package com.example.demohometask.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPatchDto{

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private LocalDate birthDate;
}
