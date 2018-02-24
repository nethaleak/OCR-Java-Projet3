package com.antazri.main.utils;

/**
 * L'interface générique fonctionnelle Comparator permet de définir la
 * manière dont des objets de type T sont comparés.
 * 
 * @author AnthonyT
 * @version 1.0
 */

@FunctionalInterface
public interface Comparator<T> {

	/**
	 * La méthode compareTo permet de définir la manière dont les objets de type T sont comparés.
	 * @param answer Le paramètre answer est un élément de comparaison/de référence utilisé dans la méthode.
	 * @return Cette méthode retourne un objet String décrivant le résultat de l'opération de comparaison.
	 */
	String compareTo(T answer);
}
