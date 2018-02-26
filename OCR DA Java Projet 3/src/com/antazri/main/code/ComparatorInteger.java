package com.antazri.main.code;

import com.antazri.main.utils.IComparator;

/**
 * ComparatorInteger est la classe définissant les objets servant à comparer les
 * objets issus de AbstractCodeInteger.
 * 
 * @author AnthonyT
 * @version 1.0
 */

public class ComparatorInteger implements IComparator<AbstractCodeInteger> {

	private AbstractCodeInteger code;

	/**
	 * Le contructeur de la classe permet de créer un comparateur unique.
	 * 
	 * @param code
	 *            Le code défini en paramètre va servir de référence dans la
	 *            comparaison
	 */
	public ComparatorInteger(AbstractCodeInteger code) {
		this.code = code;
	}

	/**
	 * La méthode compareTo permet de comparer 2 codes (l'un en paramètre à la
	 * construction de l'objet et l'autre en paramètre de cette méthode). Celle-ci
	 * permet de connaître, pour chaque élément de la collection, si la valeur est
	 * supérieure, également ou inférieure à la référence.
	 * 
	 * @param answer
	 *            Cet objet permet d'être comparé à la référence de l'objet
	 * @return Un objet String est retourné comprenant la comparaison des méthodes
	 */
	@Override
	public String compareTo(AbstractCodeInteger answer) {
		String description = "";
		for (int i = 0; i < answer.getLength(); i++) {
			if (answer.getElements().get(i).equals(code.getElements().get(i))) {
				description += "=";
				code.setBornes(i, 0, code.getElements().get(i));
				code.setBornes(i, 1, code.getElements().get(i));
			} else if (answer.getElements().get(i) > code.getElements().get(i)) {
				description += "+";
				code.setBornes(i, 0, code.getElements().get(i));
			} else {
				description += "-";
				code.setBornes(i, 1, code.getElements().get(i));
			}
		}

		return description;
	}
}