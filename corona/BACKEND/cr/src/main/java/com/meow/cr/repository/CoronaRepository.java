package com.meow.cr.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.meow.cr.document.CoronaCase;

public interface CoronaRepository extends MongoRepository<CoronaCase, String> {

	List<CoronaCase> findByCityAndDate(String city, LocalDate date);

	List<CoronaCase> findByCity(String name);

	List<CoronaCase> findByCityOrderByDate(String name);

	List<CoronaCase> findAllByOrderByDate();

}
