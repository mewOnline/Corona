package com.meow.cr.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.internal.util.privilegedactions.NewSchema;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meow.cr.document.City;
import com.meow.cr.document.CoronaCase;
import com.meow.cr.model.CoronaCaseModel;
import com.meow.cr.model.CoronaLineModel;
import com.meow.cr.model.DataPoint;
import com.meow.cr.model.INFECTED;
import com.meow.cr.repository.CityRepository;
import com.meow.cr.repository.CoronaRepository;
import com.meow.cr.service.CoronaService;

@Service
public class CoronaServiceImpl implements CoronaService {

	@Autowired
	private CoronaRepository corRep;
	@Autowired
	private CityRepository cityRep;

	@Override
	public ArrayList<CoronaCaseModel> getAll() {
		ArrayList<CoronaCaseModel> cases = new ArrayList<>();
		List<CoronaCase> findAll = corRep.findAll();
		for (CoronaCase coronaCase : findAll) {
			CoronaCaseModel coronaCaseModel = new CoronaCaseModel();
			BeanUtils.copyProperties(coronaCase, coronaCaseModel);
			cases.add(coronaCaseModel);
		}
		return cases;
	}

	@Override
	public boolean add(CoronaCaseModel model) {

		// Örnek1 “ 20.04.2020 tarihinde Ankara da Korona virüs salgınında yapılan
		// testlerde 15 yeni vaka oldu. 1 kişi korona dan vefat etti. 5 kişide
		// iyileştir.”
		// Örnek2 “Korona virüs salgınında yapılan testlerde 19.04.2020 tarihinde 
		// İstanbul da 30 yeni vaka tespit edil. İstanbul da 7 kişi iyileşti.  3 kişide
		// vefat etti. ”
		//
		CoronaCase coronaCase = new CoronaCase();

		// date
		String entry = model.getEntry().toUpperCase();
		entry = entry.replaceAll("Ş", "S").replaceAll("Ü", "U").replaceAll("İ", "I").replaceAll("Ğ", "G").replaceAll("Ç", "C").replaceAll("Ö", "O");
		String[] entryTarih = entry.split("TARIH");
		if (entryTarih.length >= 2) {
			String dirtyTarih = entryTarih[0].trim();
			if (dirtyTarih.length() >= 10) {
				String date = dirtyTarih.substring(dirtyTarih.length() - 10, dirtyTarih.length());
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
				coronaCase.setDate(LocalDate.parse(date, formatter));
			}
		}

		// city
		String cityName = new String();
		List<City> findAll = cityRep.findAll();
		for (City city : findAll) {
			if (entry.contains(city.getName().toUpperCase())) {
				cityName = city.getName();
				city.setInfected(INFECTED.YES.getValue());
				cityRep.save(city);
				break;
			}
		}
		coronaCase.setCity(cityName);

		// death
		Integer death = 0;
		String[] entryDeath = entry.split("VEFAT");
		String dirtDeath = entryDeath[0];
		String[] dirtyDeathList = dirtDeath.split(" ");
		for (int i = dirtyDeathList.length - 1; i > 0; i--) {
			String string = dirtyDeathList[i];
			if (StringUtils.isNumeric(string)) {
				death = Integer.valueOf(string);
				break;
			}
		}
		coronaCase.setDeath(death);

		// Recovered
		Integer rcover = 0;
		String[] entryrCover = entry.split("IYILE");
		String dirtyrCover = entryrCover[0];
		String[] dirtyrCoverList = dirtyrCover.split(" ");
		for (int i = dirtyrCoverList.length - 1; i > 0; i--) {
			String string = dirtyrCoverList[i];
			if (StringUtils.isNumeric(string)) {
				rcover = Integer.valueOf(string);
				break;
			}
		}
		coronaCase.setRecovered(rcover);

		// Confirmed
		Integer confirm = 0;
		String[] entryConfirm = entry.split("YENI VAKA");
		String dirtyConfirm = entryConfirm[0];
		String[] dirtyConfirmList = dirtyConfirm.split(" ");
		for (int i = dirtyConfirmList.length - 1; i > 0; i--) {
			String string = dirtyConfirmList[i];
			if (StringUtils.isNumeric(string)) {
				confirm = Integer.valueOf(string);
				break;
			}
		}
		coronaCase.setConfirmed(confirm);

		List<CoronaCase> findByCityAndDate = corRep.findByCityAndDate(coronaCase.getCity(), coronaCase.getDate());
		if (!findByCityAndDate.isEmpty()) {
			corRep.deleteAll(findByCityAndDate);
		}
		
		if(coronaCase.getDate()==null||coronaCase.getCity().trim().equals("")) {
			return false;
		}
		
		corRep.save(coronaCase);

		return true;

	}

	@Override
	public ArrayList<CoronaLineModel> findCaseOfCity(Integer plaka) {

		Optional<City> optcity = cityRep.findById(plaka);
		if (!optcity.isPresent()) {
			return null;
		}
		City city = optcity.get();
		List<CoronaCase> findByCity = corRep.findByCityOrderByDate(city.getName().toUpperCase());

		ArrayList<CoronaLineModel> lines = caseToLineModel(findByCity);

		return lines;
	}

	private ArrayList<CoronaLineModel> caseToLineModel(List<CoronaCase> findByCity) {
		CoronaLineModel death = new CoronaLineModel("OLUM");
		CoronaLineModel confirmed = new CoronaLineModel("VAKA");
		CoronaLineModel recovered = new CoronaLineModel("IYILESEN");
		ArrayList<DataPoint> death_dataPoints = new ArrayList<>();
		ArrayList<DataPoint> confirmed_dataPoints = new ArrayList<>();
		ArrayList<DataPoint> recovered_dataPoints = new ArrayList<>();

		for (CoronaCase coronaCase : findByCity) {

			death_dataPoints.add(new DataPoint(coronaCase.getDeath(), coronaCase.getDate().toString()));
			confirmed_dataPoints.add(new DataPoint(coronaCase.getConfirmed(), coronaCase.getDate().toString()));
			recovered_dataPoints.add(new DataPoint(coronaCase.getRecovered(), coronaCase.getDate().toString()));
		}
		
		death_dataPoints.sort(Comparator.comparing(shop -> shop.getLabel()));
		confirmed_dataPoints.sort(Comparator.comparing(shop -> shop.getLabel()));
		recovered_dataPoints.sort(Comparator.comparing(shop -> shop.getLabel()));

		
		death.setDataPoints(death_dataPoints);
		confirmed.setDataPoints(confirmed_dataPoints);
		recovered.setDataPoints(recovered_dataPoints);
		ArrayList<CoronaLineModel> lines = new ArrayList<>();
		lines.add(confirmed);
		lines.add(recovered);
		lines.add(death);
		return lines;
	}

	@Override
	public ArrayList<CoronaLineModel> findCaseOfTurkey() {
		List<CoronaCase> allcases = corRep.findAllByOrderByDate();
		ArrayList<CoronaCase> cases = new ArrayList<>();

		Map<LocalDate, List<CoronaCase>> allcasesGrouped = allcases.stream().collect(Collectors.groupingBy(w -> w.getDate()));
		for (Entry<LocalDate, List<CoronaCase>> entry : allcasesGrouped.entrySet()) {
			CoronaCase coronaCaseModel = new CoronaCase();
			coronaCaseModel.setCity("TURKEY");
			coronaCaseModel.setDate(entry.getKey());
			List<CoronaCase> caseOfdate = entry.getValue();
			for (CoronaCase case_ : caseOfdate) {
				coronaCaseModel.setConfirmed(coronaCaseModel.getConfirmed() + case_.getConfirmed());
				coronaCaseModel.setDeath(coronaCaseModel.getDeath() + case_.getDeath());
				coronaCaseModel.setRecovered(coronaCaseModel.getRecovered() + case_.getRecovered());
			}
			cases.add(coronaCaseModel);
		}

		ArrayList<CoronaLineModel> lines = caseToLineModel(cases);


		return lines;
	}

}
