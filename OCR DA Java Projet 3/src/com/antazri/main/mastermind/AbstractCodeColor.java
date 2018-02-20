package com.antazri.main.mastermind;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import com.antazri.main.utils.Code;
import com.antazri.main.utils.CoinColor;

public abstract class AbstractCodeColor implements Code<CoinColor> {

	protected List<CoinColor> elements;
	protected int length;
	protected Map<Integer, List<CoinColor>> wrongColors;

	public AbstractCodeColor() {
		super();
	}

	public List<CoinColor> getElements() {
		return this.elements;
	}
	
	public int getLength() {
		return this.length;
	}

	public void addWrongColor(int index, CoinColor color) {
		List<CoinColor> colors = this.wrongColors.get(index);
		colors.add(color);
		this.wrongColors.put(index, colors);
	}
	
	public void addAllWrongColor(int index, CoinColor color) {
		List<CoinColor> colors = this.allColors();
		colors.remove(color);
		this.wrongColors.put(index, colors);
	}
	
	public Map<Integer, List<CoinColor>> getWrongColors() {
		return wrongColors;
	}

	@Override
	public void addElement(CoinColor element) {
		this.elements.add(element);
	}

	@Override
	public void resetCode() {
		this.getElements().clear();
	}
	
	public List<CoinColor> allColors() {
		List<CoinColor> allColors = new ArrayList<CoinColor>(EnumSet.allOf(CoinColor.class));
		return allColors;
	}

	@Override
	public String toString() {
		String description = "";
		for (CoinColor element : elements) {
			description += element + " ";
		}
		return description;
	}

}
