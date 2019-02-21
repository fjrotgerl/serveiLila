package com.esliceu.parser.repository;

import com.esliceu.parser.model.database.Group;

import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<Group, String> {

}