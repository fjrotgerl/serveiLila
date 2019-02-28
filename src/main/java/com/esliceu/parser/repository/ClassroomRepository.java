package com.esliceu.parser.repository;

import com.esliceu.parser.model.database.Aula;
import org.springframework.data.repository.CrudRepository;

public interface ClassroomRepository extends CrudRepository <Aula,Integer> {

}
