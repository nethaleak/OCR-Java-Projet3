package com.antazri.main.code;

import java.util.ArrayList;
import java.util.List;

import com.antazri.main.utils.Code;

public class CodeInteger extends Code<Integer> {

	public CodeInteger() {
		super();
		
		this.elements = new ArrayList<Integer>();
	}

	/**
	 * Generate a combination of 4 figures
	 **/
	public List<Integer> generateProposition() {
		for (int i = 0; i < 4; i++) {
			int x = (int) Math.floor(Math.random() * 10);
			this.addElement(new Integer(x));
		}
		return this.elements;
	}

	/**
	 * Generate a new combination of 4 figures after correction();
	 **/
	@Override
	public void generateNewProposition(List<Integer> answer) {
		int min = 0;
		int max = 10;
		for (int i = 0; i < this.elements.size(); i++) {
			if (this.elements.get(i) > answer.get(i)) {
				max = this.elements.get(i);
				int x = (int) Math.floor(Math.random() * max + min);
				this.elements.add(i, new Integer(x));
			} else if (this.elements.get(i) < answer.get(i)) {
				min = this.elements.get(i);
				int x = (int) Math.floor(Math.random() * max);
				this.elements.add(i, new Integer(x));
			} else {
				continue;
			}
		}
	}

	/*
	 * Compare 2 figures and return the statement
	 */
	@Override
	public String correction(List<Integer> answer) {
		String result = "";

		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i) == answer.get(i)) {
				result += "=";
			} else if (elements.get(i) < answer.get(i)) {
				result += "+";
			} else {
				result += "-";
			}
		}
		return result;
	}
}
