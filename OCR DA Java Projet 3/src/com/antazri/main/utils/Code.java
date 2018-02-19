package com.antazri.main.utils;

import java.util.List;

public abstract class Code<T> {
	
	protected List<T> elements;

	public Code() {
	}
	
	public List<T> getElements() {
		return elements;
	}

	public void setElements(List<T> elements) {
		this.elements = elements;
	}
	
	public void addElement(T element) {
		this.elements.add(element);
	}
	
	public abstract List<T> generateUserProposition();
	public abstract List<T> automaticProposition();
	public abstract void generateNpcProposition(List<T> answer);
	public abstract String compare(List<T> answer);
	public abstract void resetCode();

	@Override
	public String toString() {
		String description = "";
		for (T element : elements) {
			description += element.toString();
		}
		return description;
	}
}