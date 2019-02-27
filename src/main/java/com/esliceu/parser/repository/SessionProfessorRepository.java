package com.esliceu.parser.repository;

import com.esliceu.parser.model.database.ProfessorSession;
import com.esliceu.parser.model.xml.TeachersSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionProfessorRepository extends CrudRepository<ProfessorSession, Long> {

    ProfessorSession findOne(String teacherCode);

    Iterable<ProfessorSession> findAll();

}
