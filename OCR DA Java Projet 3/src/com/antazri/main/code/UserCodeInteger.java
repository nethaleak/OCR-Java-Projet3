package com.antazri.main.code;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * NpcCodeInteger est une classe fille de AbstractCodeInteger, elle permet de
 * créer des objets NpcCodeInteger dédié à la création de code par l'adversaire
 * non-joueur (NPC).
 * 
 * @author AnthonyT
 * @version 1.0
 */

public class UserCodeInteger extends AbstractCodeInteger {
	
	private static Logger logger = LogManager.getLogger(UserCodeInteger.class);

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
	public Integer generateCode(int index) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		while (!scan.hasNextInt()) {
			System.out.println("Réponse invalide. Veuillez rentrer un chiffre entre 0 et 9 :");
			logger.error("Donnée invalide. Demande de resaisie");
			scan.next();
		}
		
		return new Integer(scan.nextInt());
	}
}
