package com.example.demohometask.user.dto;

import com.example.demohometask.user.entity.Role;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponseDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private LocalDate birthDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String extraMessage;

    private List<Role> roles;

}
