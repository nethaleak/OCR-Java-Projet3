package com.antazri.main.mastermind;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import com.antazri.main.utils.Code;
import com.antazri.main.utils.CoinColor;

/**
 * AbstractCodeColor est une classe abstraite définissant les objets permettant
 * de créer des codes pour le jeu Mastermind. Elle implément l'interface
 * Code qui définit l'ensemble des comportements des objets de type Code.
 * 
 * @author AnthonyT
 * @version 1.0
 */

public abstract class AbstractCodeColor implements Code<CoinColor> {

	protected List<CoinColor> elements;
	protected int length;
	protected Map<Integer, List<CoinColor>> wrongColors;

	public AbstractCodeColor() {
		super();
	}

	/**
	 * Méthode d'accession à la collection Elements dans laquelle sont stockées les
	 * données.
	 * 
	 * @return la collection/list comprenant le code.
	 */
	public List<CoinColor> getElements() {
		return this.elements;
	}

	/**
	 * Méthode d'accession à la longueur de la liste.
	 * 
	 * @return la longueur de la liste
	 */
	public int getLength() {
		return this.length;
	}

	/**
	 * La méthode addWrongColor permet d'ajouter un élément CoinColor à une
	 * Collection contenue dans une Map wrongColors, qui
	 * elle-même contient l'ensemble des mauvaises réponses données pour chaque
	 * élément à un index.
	 * 
	 * @param index
	 *            L'index est l'ID/la position de la Collection concernée
	 * @param color
	 *            Le paramètre color correspond à la couleur refusée
	 */
	public void addWrongColor(int index, CoinColor color) {
		List<CoinColor> colors = this.wrongColors.get(index);
		colors.add(color);
		this.wrongColors.put(index, colors);
	}

	/**
	 * La méthode addAllWrongColor permet d'ajouter directement l'ensemble des
	 * couleurs, excepté une (qui a été une couleur trouvée pendant la comparaison
	 * de l'objet avec un autre du même type).
	 * 
	 * @param index
	 *            L'index est l'ID/la position de la Collection concernée
	 * @param color
	 *            Ce paramètre correspond à la couleur trouvée
	 */
	public void addAllWrongColor(int index, CoinColor color) {
		List<CoinColor> colors = this.allColors();
		colors.remove(color);
		this.wrongColors.put(index, colors);
	}

	/**
	 * Cette méthode permet d'accéder l'ensemble des listes contenant les mauvaises
	 * réponses pour chaue élément du code.
	 * 
	 * @return Une Map contenant l'ensemble des listes contenant les mauvaises
	 * réponses pour chaue élément du code.
	 */
	public Map<Integer, List<CoinColor>> getWrongColors() {
		return wrongColors;
	}

	/**
	 * Méthode définie dans l'interface Code et permet d'ajouter un élément dans
	 * la Collection.
	 * 
	 * @param element
	 *            Element à ajouter dans la collection
	 */
	@Override
	public void addElement(CoinColor element) {
		this.elements.add(element);
	}

	/**
	 * La méthode resetCode permet de réinitialiser la collection pour éviter
	 * qu'elle ne dépasse la valeur définie pour la longueur des collections/codes.
	 */
	@Override
	public void resetCode() {
		this.getElements().clear();
	}

	/**
	 * La méthode allColors permet de créer une collection de type ArrayList
	 * contenant l'ensemble des élements de l'énumération CoinColor.
	 * 
	 * @return Une ArrayList avec l'ensemble des éléments de CoinColor.java
	 */
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
