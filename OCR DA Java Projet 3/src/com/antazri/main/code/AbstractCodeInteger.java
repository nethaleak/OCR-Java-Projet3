package com.antazri.main.code;

import java.util.List;

import com.antazri.main.utils.Code;

/**
 * AbstractCodeInteger est la classe abstraite définissant les objets
 * définissant les codes du jeu CodeSearch. Cette classe est utilisée
 * pour construire les différentes versions de codes (joueur ou npc). Elle
 * implémente une interface générique Code spécifiant les comportements des types
 * de codes de l'application.
 * 
 * @author AnthonyT
 * @version 1.0
 */

public abstract class AbstractCodeInteger implements Code<Integer> {

	protected List<Integer> elements;
	protected int length;
	protected int[][] bornes = { { 0, 9 }, { 0, 9 }, { 0, 9 }, { 0, 9 } };

	protected AbstractCodeInteger() {
		super();
	}

	/**
	 * Méthode d'accession à la collection Elements dans laquelle sont stockées les
	 * données.
	 * 
	 * @return la collection/list comprenant le code.
	 */
	public List<Integer> getElements() {
		return elements;
	}

	/**
	 * Méthode d'accession à la longueur de la liste.
	 * 
	 * @return la longueur de la liste
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Méthode de définition des extrémités (bornes) de l'intervalle dans lequel se
	 * situe la réponse. Ces entiers sont initialisés dans l'objet
	 * AbstractCodeInteger mais sont utilisés dans la méthode compareTo des classes
	 * implémentant Comparator.
	 * 
	 * @param index
	 *            l'index est la position de l'élement dans CodeInteger et la
	 *            colonne correspante dans le tableau bornes[][]
	 * @param borne
	 *            la borne est l'une des extrémités (stockées dans bornes[][]) où 0
	 *            est le minimum et 1 le maximum
	 * @param valeur
	 *            la valeur est la nouvelle valeur à stocker dans le tableau
	 */
	public void setBornes(int index, int borne, Integer valeur) {
		this.bornes[index][borne] = valeur;
	}

	/**
	 * Méthode définie dans l'interface Code et permet d'ajouter un élément dans
	 * la Collection.
	 * 
	 * @param element
	 *            Element à ajouter dans la collection
	 */
	@Override
	public void addElement(Integer element) {
		this.elements.add(element);
	}

	/**
	 * La méthode resetCode permet de réinitialiser la collection pour éviter
	 * qu'elle ne dépasse la valeur définie pour la longueur des collections/codes.
	 */
	@Override
	public void resetCode() {
		this.elements.clear();
	}

	/**
	 * La méthode resetBornes() remet les bornes à leurs positions initiales [0,9]
	 * via une boucle qui reprend la longueur du tableau.
	 */
	public void resetBornes() {
		for (int i = 0; i < bornes.length; i++) {
			bornes[i][0] = 0;
			bornes[i][1] = 9;
		}
	}

	@Override
	public String toString() {
		String description = "";
		for (Integer element : elements) {
			description += element.toString() + "";
		}
		return description;
	}
}
