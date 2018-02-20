package com.antazri.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.antazri.main.code.CodeSearch;
import com.antazri.main.mastermind.Mastermind;

public class Main {

	private static boolean running = true;
	private static Scanner scan = new Scanner(System.in);
	private static int game = -1;
	private static boolean developper = true;

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
						CodeSearch codeGame = new CodeSearch(developper);
						codeGame.run();
						break;

					case 2:
						Mastermind mastermind = new Mastermind(developper);
						mastermind.run();
						break;

					case 0:
						break;

					default:
						System.out.println("Désolé ! Ce jeu n'existe pas !");
						break;
					}

				} catch (InputMismatchException e) {
					System.out.println("Je n'ai pas compris votre réponse");
					scan.next();
				}

			} while (game != 0);

			running = false;
			break;
		}

		System.out.println("** Bye ! **");
	}

}
