package com.example.demohometask.group;


import com.example.demohometask.common.service.GenericCrudService;
import com.example.demohometask.group.dto.*;
import com.example.demohometask.group.entity.Group;
import com.example.demohometask.jwt.JwtService;
import com.example.demohometask.user.UserRepository;
import com.example.demohometask.user.entity.Role;
import com.example.demohometask.user.entity.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@RequiredArgsConstructor
public class GroupService extends GenericCrudService<Group, Integer, GroupCreateDto, GroupUpdateDto, GroupPatchDto, GroupResponseDto> {

    private final GroupRepository repository;
    private final GroupDtoMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final Class<Group> entityClass = Group.class;
    private final UserRepository userRepository;


    @Override
    protected Group save(GroupCreateDto groupCreateDto) {
        return repository.save(mapper.toEntity(groupCreateDto));
    }

    @Override
    protected Group updateEntity(GroupUpdateDto groupUpdateDto, Group group) {
        mapper.update(groupUpdateDto, group);
        return repository.save(group);
    }

    public Page<GroupResponseDto> getGroupsAccordingToRole(Pageable pageable, String predicate){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication.getPrincipal() instanceof User)){
            throw new AccessDeniedException("You dont have proper access to enter the platform");
        }

        Integer userId = ((User) authentication.getPrincipal()).getId();
        User user = findUserById(userId);
        boolean isAdmin = user.getRoles().stream().anyMatch(role -> role.equals(Role.ADMIN));

        if (isAdmin){
            return getAll(pageable,predicate);
        }
        List<Group> groups = user.getGroups();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), groups.size());

        List<Group> userGroups = groups.subList(start, end);
        List<GroupResponseDto> list = userGroups.stream().map(mapper::toResponseDto).toList();
        return new PageImpl<>(list, pageable, groups.size());

    }

    public GroupResponseDto addStudent(Integer studentId, Integer groupId) {
        Group group = findGroupById(groupId);
        User student = findUserById(studentId);
        group.getUsers().add(student);
        Group saved = repository.save(group);
        return mapper.toResponseDto(saved);
    }

    private Group findGroupById(Integer groupId){
        return repository
                .findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException("group with id = %d not found".formatted(groupId)));
    }
    private User findUserById(Integer userId){
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("user with id = %d not found".formatted(userId)));
    }
}
