package com.meow.cr.model;

public class DataPoint {
private  int y;
private String label;



public DataPoint(int y, String label) {
	super();
	this.y = y;
	this.label = label;
}
public DataPoint() {
}
public int getY() {
	return y;
}
public void setY(int y) {
	this.y = y;
}
public String getLabel() {
	return label;
}
public void setLabel(String label) {
	this.label = label;
}



}
