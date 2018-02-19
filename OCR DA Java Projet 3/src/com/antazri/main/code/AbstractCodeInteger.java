package com.antazri.main.code;

import java.util.List;

import com.antazri.main.utils.Code;

public abstract class AbstractCodeInteger implements Code<Integer> {
	
	protected List<Integer> elements;
	protected int length;
	protected int[][] bornes = {{0,9},{0,9},{0,9},{0,9}};

	protected AbstractCodeInteger() {
		super();
	}
	
	public List<Integer> getElements() {
		return elements;
	}
	
	public int getLength() {
		return length;
	}
	
	public int[][] getBornes() {
		return bornes;
	}
	
	public void setBornes(int[][] bornes) {
		this.bornes = bornes;
	}
	
	@Override
	public void addElement(Integer element) {
		this.elements.add(element);
	}

	@Override
	public void resetCode() {
		this.elements.clear();
	}
	
	public void resetBornes() {
		for(int i = 0; i < bornes.length; i++) {
			bornes[i][0] = 0;
			bornes[i][1] = 9;
		}
	}
	
	@Override
	public String toString() {
		String description = "";
		for (Integer element : elements) {
			description += element.toString() + "";
		}
		return description;
	}
}
