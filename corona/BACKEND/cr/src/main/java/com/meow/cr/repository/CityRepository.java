package com.meow.cr.repository;

import java.util.ArrayList;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.meow.cr.document.City;
import com.meow.cr.document.CoronaCase;
import com.meow.cr.model.Infected;

	public interface CityRepository extends MongoRepository<City, Integer>{

		ArrayList<City> findByInfected(int i);
		


}
