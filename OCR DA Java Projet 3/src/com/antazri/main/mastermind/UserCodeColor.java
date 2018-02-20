package com.antazri.main.mastermind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.antazri.main.utils.CoinColor;

public class UserCodeColor extends AbstractCodeColor {

	public UserCodeColor(int length) {
		super();

		this.length = length;
		this.elements = new ArrayList<CoinColor>();
		this.wrongColors = new HashMap<>();

		for (int i = 0; i < this.length; i++) {
			List<CoinColor> colors = new ArrayList<>();
			Integer x = new Integer(i);
			wrongColors.put(x, colors);
		}
	}

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
