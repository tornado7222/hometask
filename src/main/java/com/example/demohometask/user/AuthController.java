package com.example.demohometask.user;


import com.example.demohometask.user.dto.UserCreateDto;
import com.example.demohometask.user.dto.UserResponseDto;
import com.example.demohometask.user.dto.UserSignInDto;
import com.example.demohometask.user.dto.UserSignInResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final ModelMapper mapper;

    @PostMapping( "/login" )
    public ResponseEntity<UserSignInResponseDto> signIn(@RequestBody @Valid UserSignInDto signInDto )
    {
        UserSignInResponseDto userResponseDto = userService.signIn( signInDto );
        return ResponseEntity
                .ok( userResponseDto );
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserCreateDto userCreateDto )
    {
        UserResponseDto userResponseDto = userService.create(userCreateDto);
        System.out.println("userResponseDto = " + userResponseDto);
        return ResponseEntity
                .status( HttpStatus.CREATED )
                .body( userResponseDto );
    }

}
