package com.esliceu.parser.repository;

import com.esliceu.parser.model.Student;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, String> {

}