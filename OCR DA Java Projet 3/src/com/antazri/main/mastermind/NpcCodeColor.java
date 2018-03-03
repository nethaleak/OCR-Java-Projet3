package com.antazri.main.mastermind;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.antazri.main.utils.CoinColor;

/**
 * NpcCodeColor est une classe fille de AbstractCodeColor, elle permet de créer
 * des objets NpcCodeColor dédié à la création de code par l'adversaire non-joueur (NPC).
 * 
 * @author AnthonyT
 * @version 1.0
 */

public class NpcCodeColor extends AbstractCodeColor {

	/**
	 * Le contructeur de l'objet initialise ses attributs List "elements", qui
	 * contient le code, et LinkedHashMap "wrongColors".
	 * 
	 * @param length
	 *            Length défini la longueur du code (ce paramètre est spécifié dans
	 *            le fichier config.propertes)
	 */
	public NpcCodeColor(int length) {
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
	 * La méthode generateCode() permet, dans le cas du personnage non-joueur, de
	 * générer automatiquement un code dont le nombre d'éléments dépend de "length".
	 * La méthode récupère d'abord la liste des éléments qui ont été refusés puis
	 * définit une nouvelle couleur grâce à la collection générée par allColors et
	 * Math.random. Si la couleur n'est pas présente dans l'ArrayList correspondante
	 * à l'index de sa position dans le code, alors cette couleur est ajoutée au
	 * code à la position i, sinon la sélection recommence.
	 */
	@Override
	public CoinColor generateCode(int index) {
			List<CoinColor> listWrongColors = this.wrongColors.get(index);
			CoinColor color;

			do {
				color = allColors().get((int) Math.floor(Math.random() * allColors().size()));
			} while (listWrongColors.contains(color));

			return color;
	}
}
