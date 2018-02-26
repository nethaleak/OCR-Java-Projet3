package com.antazri.main.mastermind;

import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Mastermind.java est la classe principale du jeu Mastermind. Elle charge tous
 * les paramètres et classes nécessaires au fonctionnement du jeu lors de son
 * initialisation dans Main.java et dont le but est de trouver un code secret à
 * X couleurs.
 * 
 * @author AnthonyT
 * @version 1.0
 */

public class Mastermind {

	private ResourceBundle bundle = ResourceBundle.getBundle("com.antazri.main.ressources.config");
	private int maxLoop = Integer.parseInt(bundle.getString("mastermind.maxloop"));
	private int codeLength = Integer.parseInt(bundle.getString("mastermind.codelength"));
	private AbstractCodeColor proposition;
	private AbstractCodeColor answer;
	private int loop = 1;
	private Scanner scan = new Scanner(System.in);
	private boolean developper;
	private boolean running = true;
	private int game = -1;
	private int userScore;
	private int npcScore;

	/**
	 * Constructeur de l'objet Mastermind.
	 * 
	 * @param developper
	 *            permet d'activer le mode "developper" (affichage des résultats)
	 */
	public Mastermind(boolean developper) {
		super();

		this.developper = developper;
	}

	/**
	 * Méthode d'initialisation du jeu, appelée dès la création d'un objet
	 * CodeSearch dans Main.java.
	 * 
	 * @throws InputMismatchException
	 *             une erreur peut apparaître si l'utilsateur de saisit pas un
	 *             entier
	 */
	public void run() {
		while (running) {
			do {
				System.out.println(
						"========================================\nBienvenue dans le jeu Mastermind\n========================================\n"
								+ "1 - Mode Challenger\n" + "2 - Mode Defender\n" + "3 - Mode Duel\n"
								+ "4 - Mode Match\n" + "0 - Quitter le jeu\n" + "A quel mode voulez-vous jouer ?");
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

					case 4:
						System.out
								.println("=========================================\nC'est parti pour le mode Match !");
						this.runMatch();
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

	/**
	 * Méthode du mode de jeu Challenger : le joueur doit trouver le code secret de
	 * l'adversaire non-joueur (NPC) généré automatiquement via une méthode dans
	 * NpcCodeColor.java. Les valeurs sont ensuite comparées via la méthode
	 * compareTo de l'objet ComparatorColor.
	 * 
	 * @throws InputMismatchException
	 *             une erreur peut apparaître si l'utilisateur de saisit pas un
	 *             entier
	 */
	public void runChallenge() {
		proposition = new UserCodeColor(codeLength);
		answer = new NpcCodeColor(codeLength);
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
				break;
			} else {
				if (loop == maxLoop) {
					System.out.println("Raté !\nLe code était : " + answer.toString() + "\n");
					npcScore++;
					break;
				} else {
					ComparatorColor userComparator = new ComparatorColor(proposition);
					try {
						System.out.println("Le code n'est pas bon ! Proposition : " + proposition.toString() + "\n"
								+ userComparator.compareTo(answer));
					} catch (Exception e) {
						System.out.println(
								"Il y a eu un problème dans votre réponse, votre proposition n'a pas été prise en compte");
					}
					loop++;
				}

				proposition.resetCode();
			}
		} while (loop < (maxLoop + 1));
		return;
	}

	/**
	 * Méthode du mode de jeu Defender : l'adversaire non-joueur (NPC) doit trouver le code
	 * défini par le joueur dans une boucle appelant la méthode de génération de
	 * code dans UserCodeColor.java. Les valeurs sont ensuite comparés via la
	 * méthode compareTo de l'objet ComparatorColor.
	 * 
	 * @throws InputMismatchException
	 *             une erreur peut apparaître si l'utilsateur de saisit pas un
	 *             entier
	 */
	public void runDefense() {
		loop = 1;
		proposition = new NpcCodeColor(codeLength);
		answer = new UserCodeColor(codeLength);
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
			System.out
					.println("\nProposition n°" + loop + "\nL'ordinateur propose le code : " + proposition.toString());

			if (proposition.getElements().equals(answer.getElements())) {
				System.out.println(
						"L'ordinateur a trouvé le code secret !\nLa réponse était bien : " + proposition.toString());
				npcScore++;
				break;
			} else {
				if (loop == maxLoop) {
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
		} while (loop < (maxLoop + 1));
		return;
	}

	/**
	 * Méthode du mode de jeu Duel : le joueur et l'adversaire non-joueur (NPC) définissent
	 * chacun leur code et doivent trouver le code adverse en premier. Les codes
	 * sont générés par des méthodes generateCode définies dans UserCodeColor.java et
	 * NpcCodeColor.java. A chaque tour les valeurs sont comparées via des
	 * méthodes compareTo d'objets ComparatorColor.
	 * 
	 * @throws InputMismatchException
	 *             une erreur peut apparaître si l'utilsateur de saisit pas un
	 *             entier.
	 */
	public void runDuel() {
		loop = 1;
		AbstractCodeColor userProp = new UserCodeColor(codeLength);
		AbstractCodeColor userAnswer = new UserCodeColor(codeLength);
		AbstractCodeColor npcProp = new NpcCodeColor(codeLength);
		AbstractCodeColor npcAnswer = new NpcCodeColor(codeLength);

		System.out.println("Vous entrez en mode duel : le premier joueur à trouver le code adverse gagne !\n"
				+ "Vous disposez de 4 tours, chaque joueur répond l'un après l'autre."
				+ "\n=========================================");

		System.out.println("\nQuel sera votre code secret ?");

		for (int i = 0; i < userAnswer.getLength(); i++) {
			System.out.println("Pion " + (i + 1) + " : Noir / Blanc / Jaune / Rouge / Vert / Bleu");
			userAnswer.generateCode();
		}

		npcAnswer.generateCode();

		if (developper) {
			System.out.println("( + votre code est : " + userAnswer.toString() + ")");
			System.out.println("( + le code du NPC est : " + npcAnswer.toString() + ")");
		}

		do {
			System.out.println("Tour n°" + loop);

			for (int i = 0; i < userProp.getLength(); i++) {
				System.out.println("Pion " + (i + 1) + " : Noir / Blanc / Jaune / Rouge / Vert / Bleu");
				userProp.generateCode();
			}

			if (loop <= maxLoop) {
				if (userProp.getElements().equals(npcAnswer.getElements())) {
					System.out.println(
							"Vous avez trouvé le code secret !\nLa réponse était bien : " + userProp.toString());
					break;
				} else {
					try {
						ComparatorColor userComparator = new ComparatorColor(userProp);
						System.out.println("Votre code n'est pas bon ! Proposition : " + userProp.toString() + "\n"
								+ userComparator.compareTo(npcAnswer));
					} catch (Exception e) {
						System.out.println("Il y a eu un problème dans votre code, la jeu va redémarrer");
						break;
					}
				}

				npcProp.generateCode();

				if (npcProp.getElements().equals(userAnswer.getElements())) {
					System.out.println(
							"L'ordinateur a trouvé le code secret !\nLa réponse était bien : " + npcProp.toString());
					break;
				} else {
					try {
						ComparatorColor npcComparator = new ComparatorColor(npcProp);
						System.out.println("Le code du NPC n'est pas bon ! Proposition : " + npcProp.toString() + "\n"
								+ npcComparator.compareTo(userAnswer));
					} catch (Exception e) {
						System.out.println("Il y a eu un problème dans votre code, la jeu va redémarrer");
						break;
					}
				}
			}

			userProp.resetCode();
			npcProp.resetCode();
			loop++;

		} while (loop < (maxLoop + 1));

		if (!userProp.getElements().equals(npcAnswer.getElements())
				&& !npcProp.getElements().equals(userAnswer.getElements())) {
			System.out.println("Personne n'a trouvé le code, c'est un match nul !");
		}
	}

	/**
	 * Méthode du mode de jeu Match : le joueur et l'adversaire non-joueur non-joueur (NPC) jouent à l'un
	 * de smodes Defender ou Challenger selon le résultat de Math.random().
	 * 
	 * @throws InputMismatchException
	 *             une erreur peut apparaître si l'utilsateur de saisit pas un
	 *             entier.
	 */
	public void runMatch() {
		userScore = 0;
		npcScore = 0;
		loop = 1;
		proposition.resetCode();
		answer.resetCode();

		System.out.println("Vous avez 5 manches pour vous départager, le choix du mode se fera aléatoirement !"
				+ "\n=========================================");

		do {
			System.out.println("Le score est de :\nJoueur " + this.userScore + " / " + this.npcScore + " Ordinateur");

			if (loop % 2 == 0) {
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
	}

}
