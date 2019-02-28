package com.esliceu.parser.repository;

import com.esliceu.parser.model.database.SchoolRoom;
import org.springframework.data.repository.CrudRepository;

public interface AulaRepository extends CrudRepository<SchoolRoom,Integer> {
}
