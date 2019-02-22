package com.esliceu.parser.repository;

import com.esliceu.parser.model.database.Student;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, String> {

}