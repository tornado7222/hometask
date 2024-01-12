package com.example.demohometask.user;


import com.example.demohometask.user.dto.UserPatchDto;
import com.example.demohometask.user.dto.UserResponseDto;
import com.example.demohometask.user.dto.UserUpdateDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('GET_ALL')")
    public ResponseEntity<Page<UserResponseDto>> getAllUser(Pageable pageable, @RequestParam( required = false ) String predicate )
    {
        Page<UserResponseDto> all = userService.getAll( pageable, predicate );
        return ResponseEntity.ok( all );
    }

    @GetMapping( "/{id}" )
    @PreAuthorize("#id == authentication.principal.id or hasRole('ADMIN')")
    public ResponseEntity<UserResponseDto> getUser( @PathVariable Integer id )
    {
        UserResponseDto responseDto = userService.getById( id );
        return ResponseEntity.ok( responseDto );
    }

    @PutMapping( "/{id}" )
    @PreAuthorize("#id == authentication.principal.id or hasRole('ADMIN')")
    public ResponseEntity<UserResponseDto> updateUser( @PathVariable Integer id, @RequestBody @Valid UserUpdateDto updateDto )
    {
        UserResponseDto responseDto = userService.update( id, updateDto );
        return ResponseEntity.ok( responseDto );
    }

    @PatchMapping( "/{id}" )
    @PreAuthorize("#id == authentication.principal.id or hasRole('ADMIN')   ")
    public ResponseEntity<UserResponseDto> patchUser( @PathVariable Integer id, @RequestBody UserPatchDto patchDto ) throws NoSuchFieldException, IllegalAccessException
    {
        UserResponseDto responseDto = userService.patch( id, patchDto );
        return ResponseEntity.ok( responseDto );
    }

    @DeleteMapping( "/{id}" )
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<?> delete( @PathVariable Integer id )
    {
        userService.delete( id );
        return ResponseEntity.status( HttpStatus.NO_CONTENT ).build();
    }
}
