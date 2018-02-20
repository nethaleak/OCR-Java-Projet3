package com.antazri.main.mastermind;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Mastermind {

	private AbstractCodeColor proposition;
	private AbstractCodeColor answer;
	private int loop = 1;
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
						System.out.println("Ce mode n'existe pas");
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
	}

	/*
	 * Challenge Mode : the user has to find the right combination
	 */
	public void runChallenge() {
		proposition = new UserCodeColor(4);
		answer = new NpcCodeColor(4);
		loop = 1;
		System.out.println("Votre mission est de trouver le code secret généré par votre adversaire ! \n"
				+ "Vous aurez en tout 4 essais. Bonne chance !\n=========================================");
		answer.generateCode();
		if (developper) {
			System.out.println("(Le code généré est : " + answer.toString() + ")");
		}

		do {
			System.out.println("\nProposition n°" + loop);

			for (int i = 0; i < proposition.getLength(); i++) {
				System.out.println("Pion " + (i + 1) + " : Noir / Blanc / Jaune / Rouge / Vert / Bleu");
				proposition.generateCode();
			}

			if (proposition.getElements().equals(answer.getElements())) {
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
					ComparatorColor userComparator = new ComparatorColor(proposition);
					try {
						System.out.println("Le code n'est pas bon ! Proposition : " + proposition.toString() + "\n" 
								+ userComparator.compareTo(answer));
					} catch (Exception e) {
						System.out.println("Il y a eu un problème dans votre réponse, votre proposition n'a pas été prise en compte");
					}
					loop++;
				}
				
				proposition.resetCode();
			}
		} while (loop < 5);
		return;
	}

	/*
	 * Defense Mode : the npc has to find the right combination
	 */
	public void runDefense() {
		loop = 1;
		proposition = new NpcCodeColor(4);
		answer = new UserCodeColor(4);
		System.out
				.println("Votre mission est de définir un code secret que votre adversaire l'ordinateur devra trouver !"
						+ "\n=========================================");

		System.out.println("\nQuel sera votre code secret ?");

		for (int i = 0; i < answer.getLength(); i++) {
			System.out.println("Pion " + (i + 1) + " : Noir / Blanc / Jaune / Rouge / Vert / Bleu");
			answer.generateCode();
		}

		System.out.println("\nVous avez défini le code : " + answer.toString());

		do {
			proposition.resetCode();
			proposition.generateCode();
			System.out.println("\nProposition n°" + loop + "\nL'ordinateur propose le code : " + proposition.toString());

			if (proposition.getElements().equals(answer.getElements())) {
				System.out.println(
						"L'ordinateur a trouvé le code secret !\nLa réponse était bien : " + proposition.toString());
				npcScore++;
				break;
			} else {
				if (loop == 4) {
					System.out.println("L'ordinateur n'a pas trouvé votre code : " + answer.toString() + "\n");
					userScore++;
					break;
				} else {
					ComparatorColor npcComparator = new ComparatorColor(proposition);
					try {
						System.out.println("Le code n'est pas bon ! Proposition : " + proposition.toString() + "\n" 
								+ npcComparator.compareTo(answer));
					} catch (Exception e) {
						System.out.println("Il y a eu un problème dans votre code, la jeu va redémarrer");
						break;
					}
					loop++;
				}
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
