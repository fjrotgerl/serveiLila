package com.esliceu.parser.repository;

import com.esliceu.parser.model.database.Student;
import com.esliceu.parser.model.database.StudentSession;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface SessionStudentRepository extends CrudRepository<StudentSession, Long> {

    @Query(nativeQuery = true , value = "select * from student_session ss LIMIT 1000")
    Iterable<StudentSession> findAllLimit();
}
