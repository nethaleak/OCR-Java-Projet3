package com.antazri.main.mastermind;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Mastermind {

	private CodePieces proposition = new CodePieces();
	private CodePieces answer = new CodePieces();
	private int loop = 1;
	private String result = "";
	private Scanner scan = new Scanner(System.in);
	private boolean developper;
	private boolean running = true;
	private int game = -1;
	private int userScore;
	private int npcScore;

	public Mastermind(boolean developper) {
		super();

		this.developper = developper;
	}

	/*
	 * Run : user has to chose the type (Defense or Challenge or Duel)
	 */
	public void run() {
		while (running) {
			do {
				System.out.println(
						"========================================\nBienvenue dans le jeu Mastermind\n========================================\n"
								+ "1 - Mode Challenger\n" + "2 - Mode Defender\n" + "3 - Mode Duel\n"
								+ "0 - Quitter le jeu\n" + "A quel mode voulez-vous jouer ?");
				try {
					game = scan.nextInt();
					switch (game) {

					case 1:
						System.out.println(
								"=========================================\nC'est parti pour le mode Challenge !");
						this.runChallenge();
						break;

					case 2:
						System.out.println(
								"=========================================\nC'est parti pour le mode Defense !");
						this.runDefense();
						break;

					case 3:
						System.out
								.println("=========================================\nC'est parti pour le mode Duel !");
						this.runDuel();
						break;

					case 0:
						System.out.println("** Retour au menu principal **");
						this.running = false;
						break;

					default:
						System.out.println("Ce jeu n'existe pas");
						continue;
					}

				} catch (InputMismatchException e) {
					System.out.println("Je n'ai pas compris votre réponse");
					continue;
				}
			} while (game != 0);

			running = false;
			break;
		}
	}

	/*
	 * Challenge Mode : the user has to find the right combination
	 */
	public void runChallenge() {
		proposition.resetCode();
		answer.resetCode();
		loop = 1;
		System.out.println("Votre mission est de trouver le code secret généré par votre adversaire ! \n"
				+ "Vous aurez en tout 4 essais. Bonne chance !\n=========================================");
		answer.automaticProposition();
		if (developper) {
			System.out.println("(Le code généré est : " + answer.toString() + ")");
		}

		do {
			proposition.resetCode();
			System.out.println("\nProposition n°" + loop);

			proposition.generateProposition();

			if (proposition.toString().equals(answer.toString())) {
				System.out.println("Bravo ! Vous avez trouvé le code secret !\nLa réponse était bien : "
						+ proposition.toString() + "\n");
				userScore++;
				loop = 5;
				break;
			} else {
				if (loop == 4) {
					System.out.println("Raté !\nLe code était : " + answer.toString() + "\n");
					npcScore++;
					loop = 5;
					break;
				} else {
					result = proposition.correction(answer.getElements());
					System.out.println("Le code n'est pas bon !\nProposition : " + proposition.toString()
							+ " -> Réponse : " + result);
					loop++;
				}
			}
		} while (loop < 5);
		return;
	}

	/*
	 * Defense Mode : the npc has to find the right combination
	 */
	public void runDefense() {
		loop = 1;
		proposition.resetCode();
		answer.resetCode();
		System.out
				.println("Votre mission est de définir un code secret que votre adversaire l'ordinateur devra trouver !"
						+ "\n=========================================");

		System.out.println("\nQuel sera votre code secret ?");

		answer.generateProposition();

		System.out.println("Vous avez défini le code : " + answer.toString());

		do {
			proposition.automaticProposition();
			System.out.println("\nL'ordinateur propose le code : " + proposition.toString());

			if (proposition.toString().equals(answer.toString())) {
				System.out.println(
						"L'ordinateur a trouvé le code secret !\nLa réponse était bien : " + proposition.toString());
				npcScore++;
				loop = 5;
				break;
			} else {
				if (loop > 3) {
					System.out.println("L'ordinateur n'a pas trouvé votre code : " + answer.toString() + "\n");
					userScore++;
					loop = 5;
					break;
				} else {
					result = proposition.correction(answer.getElements());
					System.out.println("Le code n'est pas bon ! Proposition : " + proposition.toString()
							+ " -> Réponse : " + result);
					proposition.generateNewProposition(answer.getElements());
				}

				loop++;
			}
		} while (loop < 5);
		return;
	}

	/*
	 * Duel Mode : the user challenges the npc
	 */
	public void runDuel() {
		userScore = 0;
		npcScore = 0;
		loop = 1;
		proposition.resetCode();
		answer.resetCode();

		System.out.println("Vous avez 5 manches pour vous départager, le choix du mode se fera aléatoirement !"
				+ "\n=========================================");

		do {
			System.out.println("Le score est de :\nJoueur " + this.userScore + " / " + this.npcScore + " Ordinateur");

			if (Math.random() < 0.5) {
				System.out.println("\n=========================================\n" + "Manche n°" + this.loop + ""
						+ "Mode Challenge !");
				this.runChallenge();
			} else {
				System.out.println("\n=========================================\n" + "Manche n°" + this.loop + ""
						+ "Mode Defense !");
				this.runDefense();
			}

		} while (userScore < 3 || npcScore < 3);

		if (userScore == 3) {
			System.out.println("Vous avez gagné contre l'ordinateur !\nScore final : Joueur " + this.userScore + " / "
					+ this.npcScore + " Ordinateur");
		} else if (npcScore == 3) {
			System.out.println("Vous avez perdu contre l'ordinateur !\nScore final : Joueur " + this.userScore + " / "
					+ this.npcScore + " Ordinateur");
		} else {
			System.out.println("Houston, we have a problem");
		}

		return;
	}

}
