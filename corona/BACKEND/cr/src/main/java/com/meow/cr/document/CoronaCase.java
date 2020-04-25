package com.meow.cr.document;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CoronaCase {
	// @DBRef
	private String city;
	@Id
	private String id;
	private LocalDate date;
	private Integer death;
	private Integer recovered;
	private Integer confirmed;

	public CoronaCase(String city, LocalDate date, Integer death, Integer recovered, Integer confirmed) {
		super();
		this.city = city;
		this.id = UUID.randomUUID().toString().replaceAll("-", "");
		this.date = date;
		this.death = death;
		this.recovered = recovered;
		this.confirmed = confirmed;
	}

	public CoronaCase() {
		this.id = UUID.randomUUID().toString().replaceAll("-", "");
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getDeath() {
		if (this.death == null)
			this.death = 0;
		return death;
	}

	public void setDeath(Integer death) {
		this.death = death;
	}

	public Integer getRecovered() {
		if (this.recovered == null)
			this.recovered = 0;

		return recovered;
	}

	public void setRecovered(Integer recovered) {
		this.recovered = recovered;
	}

	public Integer getConfirmed() {
		if (this.confirmed == null)
			this.confirmed = 0;
		return confirmed;
	}

	public void setConfirmed(Integer confirmed) {
		this.confirmed = confirmed;
	}

}
