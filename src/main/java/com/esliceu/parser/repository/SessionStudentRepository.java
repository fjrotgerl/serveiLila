package com.esliceu.parser.repository;

import com.esliceu.parser.model.database.StudentSession;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionStudentRepository extends CrudRepository<StudentSession, Long> {

    List<StudentSession> findAllByOrderById(Pageable pageable);

}
