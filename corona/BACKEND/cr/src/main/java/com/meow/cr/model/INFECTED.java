package com.meow.cr.model;

public enum INFECTED {
	YES(1), NO(0);
	private final int value;

	private INFECTED(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
