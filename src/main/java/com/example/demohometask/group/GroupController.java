package com.example.demohometask.group;


import com.example.demohometask.group.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GroupResponseDto> createGroup(@RequestBody @Valid GroupCreateDto createDTo )
    {
        GroupResponseDto responseDto = groupService.create( createDTo );
        return ResponseEntity.status( HttpStatus.CREATED ).body( responseDto );
    }

    @PostMapping("/add-student")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addStudent(@RequestParam Integer studentId, @RequestParam Integer groupId ){
         GroupResponseDto responseDto = groupService.addStudent(studentId,groupId);
        return ResponseEntity.status( HttpStatus.OK ).body( responseDto );
    }

    @GetMapping
    public ResponseEntity<Page<GroupResponseDto>> getAllGroup(Pageable pageable, @RequestParam( required = false ) String predicate )
    {
        Page<GroupResponseDto> all = groupService.getGroupsAccordingToRole( pageable, predicate );
        return ResponseEntity.ok( all );
    }

    @GetMapping( "/{id}" )
    @PreAuthorize("#id == authentication.principal.id or hasRole('ADMIN')")
    public ResponseEntity<GroupResponseDto> getGroup( @PathVariable Integer id )
    {
        GroupResponseDto responseDto = groupService.getById( id );
        return ResponseEntity.ok( responseDto );
    }

    @PutMapping( "/{id}" )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GroupResponseDto> updateGroup( @PathVariable Integer id, @RequestBody @Valid GroupUpdateDto updateDto )
    {
        GroupResponseDto responseDto = groupService.update( id, updateDto );
        return ResponseEntity.ok( responseDto );
    }

    @PatchMapping( "/{id}" )
    @PreAuthorize("hasRole('ADMIN')   ")
    public ResponseEntity<GroupResponseDto> patchGroup( @PathVariable Integer id, @RequestBody GroupPatchDto patchDto ) throws NoSuchFieldException, IllegalAccessException
    {
        GroupResponseDto responseDto = groupService.patch( id, patchDto );
        return ResponseEntity.ok( responseDto );
    }

    @DeleteMapping( "/{id}" )
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<?> delete( @PathVariable Integer id )
    {
        groupService.delete( id );
        return ResponseEntity.status( HttpStatus.NO_CONTENT ).build();
    }
}
