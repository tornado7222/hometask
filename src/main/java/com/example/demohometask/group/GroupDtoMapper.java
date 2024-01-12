package com.example.demohometask.group;


import com.example.demohometask.common.service.GenericDtoMapper;
import com.example.demohometask.group.dto.*;
import com.example.demohometask.group.entity.Group;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupDtoMapper extends GenericDtoMapper<Group, GroupCreateDto, GroupUpdateDto, GroupResponseDto> {

    private final ModelMapper mapper;
    @Override
    public Group toEntity(GroupCreateDto groupCreateDto) {
        return mapper.map(groupCreateDto, Group.class);
    }

    @Override
    public GroupResponseDto toResponseDto(Group group) {
        return mapper.map(group, GroupResponseDto.class);
    }

    @Override
    public void update(GroupUpdateDto groupUpdateDto, Group group) {
         mapper.map(groupUpdateDto,group);
    }
}
