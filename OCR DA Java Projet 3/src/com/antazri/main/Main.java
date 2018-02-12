package com.antazri.main;

import java.util.Scanner;

import com.antazri.main.code.CodeSearch;

public class Main {

	private static boolean running = true;
	private static Scanner scan = new Scanner(System.in);
	private static int game = -1;

	public Main() {

	}

	public static void main(String[] args) {
		while (running) {
			do {
				System.out.println(
						"=========================================\nBienvenue dans l'OpenClassrooms Game Center\n=========================================\n"
								+ "1 - Code Search\n" + "2 - Mastermind\n" + "0 - Quitter le Game Center\n"
								+ "A quel jeu voulez-vous jouer ?");
				try {
					game = scan.nextInt();
					switch (game) {

					case 1:
						CodeSearch codeGame = new CodeSearch(false);
						codeGame.run();
						break;

					case 2:
						System.out.println("Mastermind : Coming soon");
						break;

					case 0:
						break;

					default:
						System.out.println("Désolé ! Ce jeu n'existe pas !");
						continue;
					}

				} catch (NumberFormatException e) {
					System.out.println("Je n'ai pas compris votre réponse");
					continue;
				}

			} while (game != 0);

			running = false;
			break;
		}

		System.out.println("** Bye ! **");
	}

}
