package com.meow.cr.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.meow.cr.model.INFECTED;
@Document

public class City {
	@Id
	private Integer plaka;
	private String name;
	private Integer infected;

	public City( String name,Integer plaka) {
		super();
		this.plaka = plaka;
		this.name = name;
		this.infected=INFECTED.NO.getValue();
	}
	public City( String name,Integer plaka,int infected) {
		super();
		this.plaka = plaka;
		this.name = name;
		this.infected=infected;
	}
	

	public City() {
		super();
	}
	public Integer getPlaka() {
		return plaka;
	}

	public void setPlaka(Integer plaka) {
		this.plaka = plaka;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getInfected() {
		return infected;
	}

	public void setInfected(Integer infected) {
		this.infected = infected;
	}

}
