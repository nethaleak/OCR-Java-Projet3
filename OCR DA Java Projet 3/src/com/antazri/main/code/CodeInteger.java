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
		this.resetCode();
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
		List<Integer> temp = this.getElements();
		this.resetCode();
		int min = 0;
		int max = 10;
		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).equals(answer.get(i))) {
				this.addElement(temp.get(i));
			}
			else if (temp.get(i) > answer.get(i)) {
				max = temp.get(i);
				int x = (int) Math.floor(Math.random() * max + min);
				this.addElement(new Integer(x));
			} else if (temp.get(i) < answer.get(i)) {
				min = temp.get(i);
				int x = (int) Math.floor(Math.random() * max);
				this.addElement(new Integer(x));
			} 
		}
	}

	/*
	 * Compare 2 figures and return the statement
	 */
	@Override
	public String correction(List<Integer> answer) {
		String result = "";

		for (int i = 0; i < 4; i++) {
			if (this.getElements().get(i).equals(answer.get(i))) {
				result += "=";
			} else if (this.getElements().get(i) < answer.get(i)) {
				result += "+";
			} else {
				result += "-";
			}
		}
		return result;
	}

	@Override
	public void resetCode() {
		this.getElements().clear();
	}
}
