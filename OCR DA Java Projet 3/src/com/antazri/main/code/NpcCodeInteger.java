package com.antazri.main.code;

import java.util.ArrayList;
import java.util.Random;

/**
 * NpcCodeInteger est une classe fille de AbstractCodeInteger, elle permet de
 * créer des objets NpcCodeInteger dédié à la création de code par l'adversaire
 * (NPC).
 * 
 * @author AnthonyT
 * @version 1.0
 */

public class NpcCodeInteger extends AbstractCodeInteger {

	/**
	 * Le contructeur de l'objet initialise son attribut List "elements" qui
	 * contient le code.
	 * 
	 * @param length
	 *            Length défini la longueur du code (ce paramètre est spécifié dans
	 *            le fichier config.propertes)
	 */
	public NpcCodeInteger(int length) {
		super();

		this.length = length;
		this.elements = new ArrayList<Integer>();
	}

	/**
	 * La méthode generateCode() est défini dans l'interface Code mais décrite dans
	 * les classes filles. Cette permet de générer un élément du code autmatiquement
	 * pour le personnage non-joueur (NPC) selon les bornes définies dans la tableau
	 * bornes[][] initialisé dans la superclasse AbstractCodeInteger.
	 */
	@Override
	public Integer generateCode(int index) {
		Random random = new Random();
		
		return new Integer(random.nextInt(this.bornes[index][1] + 1 - this.bornes[index][0]) + this.bornes[index][0]);
	}
}
