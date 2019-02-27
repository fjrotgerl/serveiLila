package com.esliceu.parser.repository;

import com.esliceu.parser.model.database.StudentSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionStudentRepository extends CrudRepository<StudentSession, Long> {

}
