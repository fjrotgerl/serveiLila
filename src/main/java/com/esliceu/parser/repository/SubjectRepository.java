package com.esliceu.parser.repository;

import com.esliceu.parser.model.database.Subject;
import org.springframework.data.repository.CrudRepository;

public interface SubjectRepository extends CrudRepository<Subject,Integer> {
}
