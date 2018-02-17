package com.antazri.main.utils;

public enum PieceColor {
	WHITE ("Blanc"),
	BLACK ("Noir"),
	BLUE ("Bleu"),
	GREEN ("Vert"),
	YELLOW ("Jaune"),
	RED ("Rouge");
	
	private String name;
	
	PieceColor(String name) {
		this.setName(name);
	}
	
	public String toString() {
		return this.name();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
