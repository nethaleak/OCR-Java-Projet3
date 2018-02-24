package com.antazri.main.code;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * NpcCodeInteger est une classe fille de AbstractCodeInteger, elle permet de
 * créer des objets NpcCodeInteger dédié à la création de code par l'adversaire
 * non-joueur (NPC).
 * 
 * @author AnthonyT
 * @version 1.0
 */

public class UserCodeInteger extends AbstractCodeInteger {

	/**
	 * Le contructeur de l'objet initialise son attribut List "elements" qui
	 * contient le code.
	 * 
	 * @param length
	 *            Length défini la longueur du code (ce paramètre est spécifié dans
	 *            le fichier config.propertes)
	 */
	public UserCodeInteger(int length) {
		super();

		this.length = length;
		this.elements = new ArrayList<Integer>();
	}

	/**
	 * La méthode generateCode() est défini dans l'interface Code mais décrite dans
	 * les classes filles. Cette permet de générer un élément du code du joueur via
	 * un objet Scanner.
	 */
	@Override
	public void generateCode() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		this.addElement(new Integer(scan.nextInt()));
	}
}
