package com.antazri.main.code;

import com.antazri.main.utils.Comparator;

public class ComparatorInteger implements Comparator<AbstractCodeInteger> {

	private AbstractCodeInteger code;
	private int[][] bornes = {{0,9},{0,9},{0,9},{0,9}};

	public ComparatorInteger(AbstractCodeInteger code) {
		this.code = code;
	}

	@Override
	public String compareTo(AbstractCodeInteger answer) {
		String description = "";
		for (int i = 0; i < answer.getLength(); i++) {
			if (answer.getElements().get(i).equals(code.getElements().get(i))) {
				description += "=";
				this.bornes[i][0] = code.getElements().get(i);
				this.bornes[i][1] = code.getElements().get(i);
			} else if (answer.getElements().get(i) > code.getElements().get(i)) {
				description += "+";
				this.bornes[i][0] = code.getElements().get(i);
			} else {
				description += "-";
				this.bornes[i][1] = code.getElements().get(i);
			}
		}
		
		code.setBornes(this.bornes);
		return description;
	}
}