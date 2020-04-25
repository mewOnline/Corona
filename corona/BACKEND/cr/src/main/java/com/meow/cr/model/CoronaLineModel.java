package com.meow.cr.model;

import java.util.ArrayList;

public class CoronaLineModel {

	private String type = "spline";
	private String name = "";
	private boolean showInLegend = true;
	private ArrayList<DataPoint> dataPoints = new ArrayList();

	
	
	
	public CoronaLineModel(String name) {
		super();
		this.name = name;
	}

	public CoronaLineModel() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isShowInLegend() {
		return showInLegend;
	}

	public void setShowInLegend(boolean showInLegend) {
		this.showInLegend = showInLegend;
	}

	public ArrayList<DataPoint> getDataPoints() {
		return dataPoints;
	}

	public void setDataPoints(ArrayList<DataPoint> dataPoints) {
		this.dataPoints = dataPoints;
	}

}
