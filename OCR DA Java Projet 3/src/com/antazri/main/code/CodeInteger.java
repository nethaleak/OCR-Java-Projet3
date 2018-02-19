package com.antazri.main.code;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.antazri.main.utils.Code;

public class CodeInteger extends Code<Integer> {
	
	int[][] bornes = {{0,9},{0,9},{0,9},{0,9}};

	public CodeInteger() {
		super();
		
		this.elements = new ArrayList<Integer>();
	}
	
	@Override
	public List<Integer> generateUserProposition() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		for (int i = 1; i < 5; i++) {
			try {
				System.out.println("Chiffre " + i + " :");
				int temp = scan.nextInt();
				this.addElement(temp);
			} catch (InputMismatchException e) {
				System.out.println("Ce caractère n'est pas un chiffre !");
				scan.next();
			}
		}
		
		return this.elements;		
	}

	/**
	 * Generate automatically a combination of 4 figures
	 **/
	public List<Integer> automaticProposition() {
		this.resetCode();
		for (int i = 0; i < 4; i++) {
			int x = (int) Math.floor(Math.random() * 10);
			this.addElement(new Integer(x));
		}
		return this.elements;
	}

	/**
	 * Generate a new combination of 4 figures after compare();
	 **/
	/*@Override
	public void generateNewProposition(List<Integer> answer) {
		int[][] bornes = {{0,9},{0,9},{0,9},{0,9}};
		List<Integer> temp = new ArrayList<>();
		Collections.copy(temp, this.getElements());
		this.resetCode();
		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).equals(answer.get(i))) {
				this.addElement(temp.get(i));
			}
			else if (temp.get(i) > answer.get(i)) {
				bornes[i][1] = temp.get(i);
				int x = (int) Math.floor(Math.random() * bornes[i][1]);
				this.addElement(new Integer(x));
			} else if (temp.get(i) < answer.get(i)) {
				bornes[i][0] = temp.get(i);
				int x = (int) Math.floor(Math.random() * bornes[i][1]);
				this.addElement(new Integer(x));
			} 
		}
	}*/
	
	@Override
	public void generateNpcProposition(List<Integer> answer) {
		Random random = new Random();
		
		for(int i = 0; i < 4; i++) {
			this.addElement(new Integer(random.nextInt(bornes[i][1] + 1 - bornes[i][0]) + bornes[i][0]));
		}
		
		for (int i = 0; i < this.getElements().size(); i++) {
			if (this.getElements().get(i).equals(answer.get(i))) {
				continue;
			} else if (this.getElements().get(i) > answer.get(i)) {
				bornes[i][1] = this.getElements().get(i);
			} else if (this.getElements().get(i) < answer.get(i)) {
				bornes[i][0] = this.getElements().get(i);
			} 
		}
		
		this.resetCode();
	}

	/*
	 * Compare 2 figures and return the statement
	 */
	@Override
	public String compare(List<Integer> answer) {
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
		this.elements.clear();
	}
	
	public void resetBornes() {
		for(int i = 0; i < bornes.length; i++) {
			bornes[i][0] = 0;
			bornes[i][1] = 9;
		}
	}
}
