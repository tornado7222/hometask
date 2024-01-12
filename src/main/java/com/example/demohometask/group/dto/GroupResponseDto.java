package com.example.demohometask.group.dto;

import com.example.demohometask.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupResponseDto {

    private Integer id;

    private String name;

    private List<User>users;
}
