package com.antazri.main.mastermind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.antazri.main.utils.CoinColor;

public class NpcCodeColor extends AbstractCodeColor {

	public NpcCodeColor(int length) {
		this.length = length;
		this.elements = new ArrayList<CoinColor>();
		this.wrongColors = new HashMap<>();

		for (int i = 0; i < this.length; i++) {
			List<CoinColor> colors = new ArrayList<>();
			Integer x = new Integer(i);
			wrongColors.put(x, colors);
		}
	}

	@Override
	public void generateCode() {
		for (int i = 0; i < this.getLength(); i++) {
			List<CoinColor> listWrongColors = this.wrongColors.get(i);
			CoinColor color;

			do {
				color = allColors().get((int) Math.floor(Math.random() * allColors().size()));
			} while (listWrongColors.contains(color));

			this.addElement(color);
		}
	}
}
