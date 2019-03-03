package com.esliceu.parser.repository;

import com.esliceu.parser.model.database.Student;
import com.esliceu.parser.model.database.StudentSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface SessionStudentRepository extends CrudRepository<StudentSession, Long> {

}
