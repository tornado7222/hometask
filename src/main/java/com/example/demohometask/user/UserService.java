package com.example.demohometask.user;

import com.example.demohometask.rsql.SpecificationBuilder;
import com.example.demohometask.user.dto.UserCreateDto;
import com.example.demohometask.user.dto.UserResponseDto;
import com.example.demohometask.user.dto.UserUpdateDto;
import com.example.demohometask.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final ModelMapper modelMapper = new ModelMapper();
    private final UserRepository userRepository;

    public UserResponseDto create(UserCreateDto userCreateDto) {
        User user = modelMapper.map(userCreateDto, User.class);
        User save = userRepository.save(user);
        return modelMapper.map(save,UserResponseDto.class);
    }

    public Page<UserResponseDto> getAll(Pageable pageable, String predicate) {
        Specification<User> specification = SpecificationBuilder.build(predicate);
        if (specification == null){
            return userRepository.findAll(pageable)
                    .map(user -> modelMapper.map(user, UserResponseDto.class));
        }
        return userRepository.findAll(specification,pageable)
                .map(user -> modelMapper.map(user, UserResponseDto.class));
    }

    public UserResponseDto getById(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        return modelMapper.map(user, UserResponseDto.class);
    }

    public UserResponseDto update(Integer id, UserUpdateDto userUpdateDto) {
        User user = userRepository.findById(id).orElseThrow();
        modelMapper.map(userUpdateDto, user);
        userRepository.save(user);
        return modelMapper.map(user, UserResponseDto.class);
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
