package com.meow.cr.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meow.cr.document.City;
import com.meow.cr.model.CityModel;
import com.meow.cr.model.Infected;
import com.meow.cr.repository.CityRepository;
import com.meow.cr.service.CityService;
@Service

public class CityServiceImpl implements CityService {
	@Autowired
	private CityRepository cityRep;

	@Override
	//enfekte olmuş şehir listesi combobox için oluşturulur.
	public ArrayList<CityModel> getInfectedCities() {
		ArrayList<City> finbyInfected = cityRep.findByInfected(Infected.YES.getValue());
		ArrayList<CityModel>infectedList = new ArrayList<>();
		infectedList.add(new CityModel("TURKEY", 0));
		for (City c : finbyInfected) {
			infectedList.add(new CityModel(c.getName(), c.getPlaka()));
		}
		return infectedList;
	}

}
