package com.esliceu.parser.repository;

import com.esliceu.parser.model.database.ProfessorSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionProfessorRepository extends CrudRepository<ProfessorSession, Long> {

    Optional<ProfessorSession> findById(Long professorId);

    Iterable<ProfessorSession> findAll();

}
