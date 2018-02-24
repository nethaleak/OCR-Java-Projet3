package com.antazri.main.mastermind;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

import com.antazri.main.utils.CoinColor;

/**
 * NpcCodeColor est une classe fille de AbstractCodeColor, elle permet de créer
 * des objets NpcCodeColor dédié à la création de code par l'adversaire (NPC).
 * 
 * @author AnthonyT
 * @version 1.0
 */

public class UserCodeColor extends AbstractCodeColor {

	/**
	 * Le contructeur de l'objet initialise ses attributs List "elements", qui
	 * contient le code, et LinkedHashMap "wrongColors".
	 * 
	 * @param length
	 *            Length défini la longueur du code (ce paramètre est spécifié dans
	 *            le fichier config.propertes)
	 */
	public UserCodeColor(int length) {
		super();

		this.length = length;
		this.elements = new ArrayList<CoinColor>();
		this.wrongColors = new LinkedHashMap<>();

		for (int i = 0; i < this.length; i++) {
			List<CoinColor> colors = new ArrayList<>();
			Integer x = new Integer(i);
			wrongColors.put(x, colors);
		}
	}

	/**
	 * La méthode generateCode() est défini dans l'interface Code mais décrite dans
	 * les classes filles. Cette permet de générer un élément du code du joueur via
	 * un objet Scanner dans une boucle switch.
	 * @throws InputMismatchException Une exception peut être levée en cas de mauvaise entrée dans l'objet Scanner scan
	 */
	@Override
	public void generateCode() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);

		try {
			String color = scan.nextLine();
			color.toLowerCase();

			switch (color) {
			case "noir":
				this.addElement(CoinColor.BLACK);
				break;

			case "blanc":
				this.addElement(CoinColor.WHITE);
				break;

			case "bleu":
				this.addElement(CoinColor.BLUE);
				break;

			case "jaune":
				this.addElement(CoinColor.YELLOW);
				break;

			case "rouge":
				this.addElement(CoinColor.RED);
				break;

			case "vert":
				this.addElement(CoinColor.GREEN);
				break;

			default:
				System.out.println("Ceci n'est pas une couleur connue");
				break;
			}

		} catch (InputMismatchException e) {
			System.out.println("Houston we have a problem : cette entrée n'est pas reconnue par Skynet !");
			scan.next();
		}
	}
}
