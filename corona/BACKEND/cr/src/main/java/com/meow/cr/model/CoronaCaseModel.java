package com.meow.cr.model;

import java.time.LocalDate;

public class CoronaCaseModel {

	private String city;
	private String entry;
	private LocalDate date;
	private Integer death;
	private Integer recovered;
	private Integer confirmed;

	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getDeath() {
		if (this.death == null) {
			this.death = 0;
		}
		return death;
	}

	public void setDeath(Integer death) {
		this.death = death;
	}

	public Integer getRecovered() {
		if (this.recovered == null) {
			this.recovered = 0;
		}
		return recovered;
	}

	public void setRecovered(Integer recovered) {
	
		this.recovered = recovered;
	}

	public Integer getConfirmed() {
		if (this.confirmed == null) {
			this.confirmed = 0;
		}

		return confirmed;
	}

	public void setConfirmed(Integer confirmed) {
		this.confirmed = confirmed;
	}

}
