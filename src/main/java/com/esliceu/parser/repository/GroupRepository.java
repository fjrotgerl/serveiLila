package com.esliceu.parser.repository;

import com.esliceu.parser.model.database.Group;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends CrudRepository<Group, Integer> {

}