package com.antazri.main.utils;

/**
 * L'interface générique Code permet d'implémenter le comportement d'objets
 * de type Code et manipulant des type T.
 * 
 * @author AnthonyT
 * @version 1.0
 */

public abstract interface ICode<T> {

	/**
	 * Cette méthode permet l'ajout d'élément.
	 * @param element Le paramètre élément représente l'objet à ajouter.
	 */
	public abstract void addElement(T element);

	/**
	 * La méthode generateCode définit la manière dont le code est généré.
	 */
	public abstract void generateCode();

	/**
	 * La méthode resetCode définit la manière dont le code est réinitialisé.
	 */
	public abstract void resetCode();

}