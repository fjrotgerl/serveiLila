package com.esliceu.parser.repository;

import com.esliceu.parser.model.database.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course,Integer> {
}
