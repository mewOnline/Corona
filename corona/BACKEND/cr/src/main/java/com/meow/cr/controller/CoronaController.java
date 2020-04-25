package com.meow.cr.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meow.cr.model.CityModel;
import com.meow.cr.model.CoronaCaseModel;
import com.meow.cr.model.CoronaLineModel;
import com.meow.cr.model.ResponsePayload;
import com.meow.cr.service.CityService;
import com.meow.cr.service.CoronaService;

@RestController
@RequestMapping("/cor")
@CrossOrigin
public class CoronaController {
	@Autowired
	private CoronaService CornServ;
	@Autowired
	private CityService CityServ;

	@GetMapping(value = "/get-all",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponsePayload getAll() {

		try {
			ArrayList<CoronaCaseModel> all = CornServ.getAll();
			return new ResponsePayload(HttpStatus.OK.value(), "Basarili", Boolean.TRUE, all);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponsePayload(HttpStatus.BAD_REQUEST.value(), "Basarisiz", Boolean.FALSE);

		}
	}

	@GetMapping(value = "/get-infected-cities",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponsePayload getInfectedCities() {

		try {
			ArrayList<CityModel> all = CityServ.getInfectedCities();
			return new ResponsePayload(HttpStatus.OK.value(), "Basarili", Boolean.TRUE, all);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponsePayload(HttpStatus.BAD_REQUEST.value(), "Basarisiz", Boolean.FALSE);

		}
	}

	@GetMapping(value = "/get-case/{plaka}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponsePayload findCaseOfCity(@PathVariable("plaka") Integer plaka) {

		try {
			ArrayList<CoronaLineModel> caseOfCity = new ArrayList<>();
			if (plaka == 0) {
				caseOfCity = CornServ.findCaseOfTurkey();
			} else {

				caseOfCity = CornServ.findCaseOfCity(plaka);
			}
			if (caseOfCity == null) {
				return new ResponsePayload(HttpStatus.NOT_FOUND.value(), "Basarisiz", Boolean.FALSE);

			} else
				return new ResponsePayload(HttpStatus.OK.value(), "Basarili", Boolean.TRUE, caseOfCity);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponsePayload(HttpStatus.BAD_REQUEST.value(), "Basarisiz", Boolean.FALSE);

		}
	}

	@GetMapping(value = "/get-case-turkey",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponsePayload findCaseOfTurkey() {

		try {
			ArrayList<CoronaLineModel> caseOfCity = CornServ.findCaseOfTurkey();
			return new ResponsePayload(HttpStatus.OK.value(), "Basarili", Boolean.TRUE, caseOfCity);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponsePayload(HttpStatus.BAD_REQUEST.value(), "Basarisiz", Boolean.FALSE);

		}
	}

	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponsePayload add(@RequestBody CoronaCaseModel model) {

		try {
			boolean add = CornServ.add(model);
			if (add == true)
				return new ResponsePayload(HttpStatus.OK.value(), "Basarili", Boolean.TRUE);
			else
				return new ResponsePayload(HttpStatus.NOT_FOUND.value(), "Basarisiz", Boolean.TRUE);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponsePayload(HttpStatus.BAD_REQUEST.value(), "Basarisiz", Boolean.FALSE);

		}
	}

}
