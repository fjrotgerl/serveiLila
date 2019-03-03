package com.esliceu.parser.repository;

import com.esliceu.parser.model.database.Student;

import com.esliceu.parser.model.database.StudentSession;
import com.esliceu.parser.model.database.Subject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface  StudentRepository extends CrudRepository<Student, String> {

    @Query("SELECT p FROM Student p JOIN FETCH p.studentSessions WHERE p.code = '944BA5381AB6E45FE040D70A59055935'")
    public Student findByIdAndFetchSessionsEagerly(@Param("id") String id);

}