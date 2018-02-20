package com.antazri.main.utils;

public enum CoinColor {
	WHITE ("Blanc"),
	BLACK ("Noir"),
	BLUE ("Bleu"),
	GREEN ("Vert"),
	YELLOW ("Jaune"),
	RED ("Rouge");
	
	private String name;
	
	CoinColor(String name) {
		this.name = name;
	}
	
	public String toString() {
		return this.name();
	}

	public String getName() {
		return name;
	}
}
