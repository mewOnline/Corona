package com.meow.cr.service;

import java.util.ArrayList;

import com.meow.cr.model.CoronaCaseModel;
import com.meow.cr.model.CoronaLineModel;

public interface CoronaService {

	ArrayList<CoronaCaseModel> getAll();

	boolean add(CoronaCaseModel model);

	ArrayList<CoronaLineModel> findCaseOfCity(Integer plaka);

	ArrayList<CoronaLineModel> findCaseOfTurkey();

}
