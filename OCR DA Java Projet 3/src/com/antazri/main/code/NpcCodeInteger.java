package com.antazri.main.code;

import java.util.ArrayList;
import java.util.Random;

public class NpcCodeInteger extends AbstractCodeInteger{
	
	public NpcCodeInteger(int length) {
		super();
		
		this.length = length;
		this.elements = new ArrayList<Integer>();
	}
	
	@Override
	public void generateCode() {
		Random random = new Random();
		this.resetCode();
		for(int i = 0; i < this.length; i++) {
			this.addElement(new Integer(random.nextInt(this.bornes[i][1] + 1 - this.bornes[i][0]) + this.bornes[i][0]));
		}
	}
}
