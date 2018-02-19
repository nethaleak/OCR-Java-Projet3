package com.antazri.main.code;

import java.util.ArrayList;
import java.util.Scanner;

public class UserCodeInteger extends AbstractCodeInteger {

	public UserCodeInteger(int length) {
		super();

		this.length = length;
		this.elements = new ArrayList<Integer>();
	}

	@Override
	public void generateCode() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		this.addElement(new Integer(scan.nextInt()));
	}
}
