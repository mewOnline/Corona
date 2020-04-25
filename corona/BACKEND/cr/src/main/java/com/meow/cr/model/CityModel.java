package com.meow.cr.model;

public class CityModel {
	private String name;
	private int plaka;

	public CityModel(String name, int plaka) {
		super();
		this.name = name;
		this.plaka = plaka;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPlaka() {
		return plaka;
	}

	public void setPlaka(int plaka) {
		this.plaka = plaka;
	}

}
