package com.meow.cr.model;

public enum Infected {
	YES(1), NO(0);
	private final int value;

	private Infected(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
