package com.antazri.main.mastermind;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.antazri.main.utils.CoinColor;
import com.antazri.main.utils.Comparator;

/**
 * ComparatorColor est la classe définissant les objets servant à comparer les
 * objets issus de AbstractCodeColor.
 * 
 * @author AnthonyT
 * @version 1.0
 */

public class ComparatorColor implements Comparator<AbstractCodeColor> {

	private AbstractCodeColor code;
	private int rightPieces = 0;
	private int presentPieces = 0;
	private Map<Integer, List<CoinColor>> wrongColors;

	/**
	 * Le contructeur de la classe permet de créer un comparateur unique. A sa
	 * création, une HashMap contenant l'ensemble des mauavsies réponses est
	 * initialisée.
	 * 
	 * @param code
	 *            Le code défini en paramètre va servir de référence dans la
	 *            comparaison
	 */
	public ComparatorColor(AbstractCodeColor code) {
		super();

		this.code = code;
		this.wrongColors = new LinkedHashMap<>();

		for (int i = 0; i < code.length; i++) {
			List<CoinColor> colors = new ArrayList<>();
			Integer x = new Integer(i);
			wrongColors.put(x, colors);
		}
	}

	/**
	 * Accesseur de la variable rightPieces (les pions bien placés)
	 * 
	 * @return Un entier correspondant au nombre de pions biens placés dans la
	 *         proposition par rapport la réponse
	 */
	public int getRightPieces() {
		return this.rightPieces;
	}

	/**
	 * Accesseur de la variable presentPieces (les pions présents)
	 * 
	 * @return Un entier correspondant au nombre de pions présents dans le code
	 *         proposé et dans la réponse
	 */
	public int getPresentPieces() {
		return this.presentPieces;
	}

	/**
	 * La méthode compareTo permet de comparer 2 codes (l'un en paramètre à la
	 * construction de l'objet et l'autre en paramètre de cette méthode). Celle-ci
	 * permet de connaître, pour chaque élément de la collection, si l'élément est
	 * équivalent entre la proposition et la réponse. Si c'est équivalent alors
	 * l'ensemble des couleurs, exceptée la bonne, sont ajoutés à la Collection
	 * wrongColors. Dans le cas contraire, c'est uniquement la mauvaise couleur qui
	 * est ajoutée.
	 * 
	 * @param answer
	 *            Cet objet permet d'être comparé à la référence de l'objet
	 * @return Un objet String est retourné comprenant le résultat de la comparaison
	 *         des 2 codes
	 */
	@Override
	public String compareTo(AbstractCodeColor answer) {
		this.presentPieces = 0;
		this.rightPieces = 0;

		for (int i = 0; i < code.getLength(); i++) {
			for (int j = 0; j < answer.getLength(); j++) {
				if (code.getElements().get(i).equals(answer.getElements().get(j))) {
					this.presentPieces++;
					break;
				}
			}
		}

		for (int i = 0; i < code.length; i++) {
			if (code.getElements().get(i).equals(answer.getElements().get(i))) {
				this.rightPieces++;
				code.addAllWrongColor(i, code.getElements().get(i));
			} else {
				code.addWrongColor(i, code.getElements().get(i));
			}
		}

		return "Pions présents : " + this.getPresentPieces() + "/4 | Pions bien placés : " + this.getRightPieces()
				+ "/4";
	}
}
