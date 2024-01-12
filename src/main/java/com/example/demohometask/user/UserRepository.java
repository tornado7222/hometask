package com.example.demohometask.user;


import com.example.demohometask.common.repository.GenericSpecificationRepository;
import com.example.demohometask.user.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends GenericSpecificationRepository<User,Integer> {
    Boolean existsByPhoneNumber(String phone);
    Optional<User> findUserByPhoneNumber(String phone);
}
