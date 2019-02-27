package com.esliceu.parser.repository;

import com.esliceu.parser.model.database.Professor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor, String> {
    Professor findByCode(String code);

    Iterable<Professor> findAll();
}
