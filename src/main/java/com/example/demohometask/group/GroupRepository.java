package com.example.demohometask.group;


import com.example.demohometask.common.repository.GenericSpecificationRepository;
import com.example.demohometask.group.entity.Group;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends GenericSpecificationRepository<Group,Integer> {

}
