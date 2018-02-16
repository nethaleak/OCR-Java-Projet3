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
	
	public abstract List<T> generateProposition();
	public abstract void generateNewProposition(List<T> answer);
	public abstract String correction(List<T> answer);

	@Override
	public String toString() {
		String description = "";
		for (T element : elements) {
			description += element.toString();
		}
		return description;
	}
}