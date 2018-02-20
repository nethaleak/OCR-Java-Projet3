package com.antazri.main.mastermind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.antazri.main.utils.CoinColor;
import com.antazri.main.utils.Comparator;

public class ComparatorColor implements Comparator<AbstractCodeColor> {
	
	private AbstractCodeColor code;
	private int rightPieces = 0;
	private int presentPieces = 0;
	private Map<Integer, List<CoinColor>> wrongColors;

	public ComparatorColor(AbstractCodeColor code) {
		super();
		
		this.code = code;
		this.wrongColors = new HashMap<>();
		
		for (int i = 0; i < code.length; i++) {
			List<CoinColor> colors = new ArrayList<>();
			Integer x = new Integer(i);
			wrongColors.put(x, colors);
		}
	}
	
	public int getRightPieces() {
		return this.rightPieces;
	}

	public int getPresentPieces() {
		return this.presentPieces;
	}

	@Override
	public String compareTo(AbstractCodeColor answer) {
		this.presentPieces = 0;
		this.rightPieces = 0;
		
			for (int i = 0; i < code.getLength(); i++) {
				for(int j = 0; j < answer.getLength(); j++) {
					if(code.getElements().get(i).equals(answer.getElements().get(j))) {
						this.presentPieces++;
						break;
					}
				}
			}
			
			for (int i = 0; i < code.length; i++) {
				if(code.getElements().get(i).equals(answer.getElements().get(i))) {
					this.rightPieces++;
					code.addAllWrongColor(i, code.getElements().get(i));
				}
				else {
					code.addWrongColor(i, code.getElements().get(i));
				}
			}
		
			return "Pions présents : " + this.getPresentPieces() + "/4 | Pions bien placés : " + this.getRightPieces()
					+ "/4";
	}
}
