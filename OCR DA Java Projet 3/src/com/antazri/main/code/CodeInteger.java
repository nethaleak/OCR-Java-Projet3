package com.antazri.main.code;

public class CodeInteger {

	private int[] elements = { 0, 0, 0, 0 };

	public CodeInteger() {
		super();
	}

	public int[] getElements() {
		return elements;
	}

	public void setElements(int[] elements) {
		this.elements = elements;
	}

	/**
	 * Generate a combination of 4 figures
	 **/
	public int[] generateProposition() {
		for (int i = 0; i < 4; i++) {
			this.elements[i] = (int) Math.floor(Math.random() * 10);
		}
		return this.elements;
	}

	/**
	 * Generate a new combination of 4 figures after correction();
	 **/
	public void generateNewProposition(CodeInteger answer) {
		int min = 0;
		int max = 10;
		for (int i = 0; i < this.getElements().length; i++) {
			if (this.getElements()[i] > answer.getElements()[i]) {
				max = this.getElements()[i];
				this.getElements()[i] = (int) Math.floor(Math.random() * max + min);
			} else if (this.getElements()[i] < answer.getElements()[i]) {
				min = this.getElements()[i];
				this.getElements()[i] = (int) Math.floor(Math.random() * max);
			} else {
				continue;
			}
		}
	}

	/*
	 * Compare 2 figures and return the statement
	 */
	public String correction(int[] answer) {
		String result = "";

		for (int i = 0; i < elements.length; i++) {
			if (elements[i] == answer[i]) {
				result += "=";
			} else if (elements[i] < answer[i]) {
				result += "+";
			} else {
				result += "-";
			}
		}
		return result;
	}

	@Override
	public String toString() {
		String description = "";
		for (int element : elements) {
			description += element;
		}
		return description;
	}
}
