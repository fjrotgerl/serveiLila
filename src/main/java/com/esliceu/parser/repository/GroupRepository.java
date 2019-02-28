package com.esliceu.parser.repository;

import com.esliceu.parser.model.database.Course;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends CrudRepository<Course, Integer> {

}