package com.antazri.main.code;

import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * CodeSearch.java est la classe principale du jeu CodeSearch. Elle charge tous
 * les paramètres et classes nécessaires au fonctionnement du jeu lors de son
 * initialisation dans Main.java et dont le but est de trouver un code secret
 * à X chiffres.
 * 
 * @author AnthonyT
 * @version 1.0
 */

public class CodeSearch {

	private ResourceBundle bundle = ResourceBundle.getBundle("com.antazri.main.resources.config");
	private int maxLoop = Integer.parseInt(bundle.getString("codesearch.maxloop"));
	private int codeLength = Integer.parseInt(bundle.getString("codesearch.codelength"));
	private AbstractCodeInteger proposition;
	private AbstractCodeInteger answer;
	private int loop = 0;
	private Scanner scan = new Scanner(System.in);
	private boolean developper;
	private boolean running = true;
	private int game = -1;
	private int userScore = 0;
	private int npcScore = 0;
	private static Logger logger = LogManager.getLogger(CodeSearch.class);

	/**
	 * Constructeur de l'objet CodeSearch.
	 * 
	 * @param developper
	 *            permet d'activer le mode "developper" (affichage des résultats).
	 */
	public CodeSearch(boolean developper) {
		super();

		this.developper = developper;

		run();
	}

	/**
	 * Méthode d'initialisation du jeu, appelée dès la création d'un objet
	 * CodeSearch dans Main.java.
	 * 
	 * @throws InputMismatchException
	 *             une erreur peut apparaître si l'utilsateur de saisit pas un
	 *             entier.
	 */
	public void run() {
		logger.info("Connexion à CodeSearch");
		while (running) {
			do {
				System.out.println(
						"========================================\nBienvenue dans le jeu Code Search\n========================================\n"
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
								.println("=========================================\nC'est parti pour le mode Duel !");
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
					logger.trace("L'utilisateur a rentré une donnée inconnue dans le choix de mode de jeu");
					scan.next();
				}
			} while (game != 0);
			logger.info("Sortie du jeu CodeSearch");
			running = false;
			break;
		}
	}

	/**
	 * Méthode du mode de jeu Challenger : le joueur doit trouver le code secret de
	 * l'adversaire non-joueur (NPC) généré automatiquement via une méthode dans
	 * NpcCodeInteger.java. Les valeurs sont ensuite comparées via la méthode
	 * compareTo de l'objet ComparatorInteger.
	 * 
	 * @throws InputMismatchException
	 *             une erreur peut apparaître si l'utilisateur de saisit pas un
	 *             entier.
	 */
	public void runChallenge() {
		logger.info("Lancement du mode Challenger");
		proposition = new UserCodeInteger(codeLength);
		answer = new NpcCodeInteger(codeLength);
		loop = 1;
		System.out.println("Votre mission est de trouver le code secret généré par votre adversaire ! \n"
				+ "Vous aurez en tout "  + maxLoop + " essais. Bonne chance !\n=========================================");
		
		for (int i = 0; i < answer.getLength(); i++) {
			answer.addElement(answer.generateCode(i));
		}
		
		if (developper) {
			System.out.println("(Le code généré est : " + answer.toString() + ")");
		}

		do {
			proposition.resetCode();
			System.out.println("\nProposition n°" + loop);

			for (int i = 0; i < proposition.getLength(); i++) {
				System.out.println("Chiffre n°" + (i + 1));
				Integer element = proposition.generateCode(i);
				
				while (element < 0 || element > 9) {
					System.out.println("Entrée invalide, veuillez entrer un chiffre entre 0 et 9 :");
					element = proposition.generateCode(i);
				}
				
				proposition.addElement(element);
			}

			if (proposition.toString().equals(answer.toString())) {
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
					ComparatorInteger userComparator = new ComparatorInteger(proposition);
					System.out.println("Le code n'est pas bon !\nProposition : " + proposition.toString()
							+ " -> Réponse : " + userComparator.compareTo(answer));
					loop++;
				}
			}
		} while (loop < (maxLoop + 1));
	}

	/**
	 * Méthode du mode de jeu Defender : l'adversaire non-joueur (NPC) doit trouver le code
	 * défini par le joueur dans une boucle appelant la méthode de génération de
	 * code dans UserCodeInteger.java. Les valeurs sont ensuite comparés via la
	 * méthode compareTo de l'objet ComparatorInteger.
	 * 
	 * @throws InputMismatchException
	 *             une erreur peut apparaître si l'utilsateur de saisit pas un
	 *             entier.
	 */
	public void runDefense() {
		logger.info("Lancement du mode Defender");
		loop = 1;
		proposition = new NpcCodeInteger(codeLength);
		answer = new UserCodeInteger(codeLength);
		System.out
				.println("Votre mission est de définir un code secret que votre adversaire l'ordinateur devra trouver !"
						+ "\n=========================================");

		System.out.println("\nQuel sera votre code secret ?");

		for (int i = 0; i < answer.getLength(); i++) {
			System.out.println("Chiffre n°" + (i + 1));
			Integer element = answer.generateCode(i);
			
			while (element < 0 || element > 9) {
				System.out.println("Entrée invalide, veuillez entrer un chiffre entre 0 et 9 :");
				element = answer.generateCode(i);
			}
			
			answer.addElement(element);
		}

		System.out.println("Vous avez défini le code : " + answer.toString());

		do {
			proposition.resetCode();
			for (int i = 0; i < proposition.getLength(); i++) {
				proposition.addElement(proposition.generateCode(i));
			}
			System.out.println("\nL'ordinateur propose le code : " + proposition.toString());

			if (proposition.toString().equals(answer.toString())) {
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
					ComparatorInteger npcComparator = new ComparatorInteger(proposition);
					System.out.println("Le code n'est pas bon ! Proposition : " + proposition.toString()
							+ " -> Réponse : " + npcComparator.compareTo(answer));

				}

				loop++;
			}
		} while (loop < (maxLoop + 1));
	}

	/**
	 * Méthode du mode de jeu Duel : le joueur et l'adversaire non-joueur (NPC) définissent
	 * chacun leur code et doivent trouver le code adverse en premier. Les codes
	 * sont générés par des méthodes définies dans UserCodeInteger.java et
	 * NpcCodeInteger.java. A chaque tour les valeurs sont comparées via des
	 * méthodes compareTo d'objets ComparatorInteger.
	 * 
	 * @throws InputMismatchException
	 *             une erreur peut apparaître si l'utilsateur de saisit pas un
	 *             entier.
	 */
	public void runDuel() {
		logger.info("Lancement du mode Duel");
		loop = 1;
		AbstractCodeInteger userProp = new UserCodeInteger(codeLength);
		AbstractCodeInteger userAnswer = new UserCodeInteger(codeLength);
		AbstractCodeInteger npcProp = new NpcCodeInteger(codeLength);
		AbstractCodeInteger npcAnswer = new NpcCodeInteger(codeLength);

		System.out.println("Vous entrez en mode duel : le premier joueur à trouver le code adverse gagne !\n"
				+ "Vous disposez de 4 tours, chaque joueur répond l'un après l'autre."
				+ "\n=========================================");

		System.out.println("\nQuel sera votre code secret ?");

		for (int i = 0; i < userAnswer.getLength(); i++) {
			System.out.println("Chiffre n°" + (i + 1));
			Integer element = userAnswer.generateCode(i);
			
			while (element < 0 || element > 9) {
				System.out.println("Entrée invalide, veuillez entrer un chiffre entre 0 et 9 :");
				element = userAnswer.generateCode(i);
			}
			
			userAnswer.addElement(element);
		}

		for (int i = 0; i < npcAnswer.getLength(); i++) {
			npcAnswer.addElement(npcAnswer.generateCode(i));
		}

		if (developper) {
			System.out.println("( + votre code est : " + userAnswer.toString() + ")");
			System.out.println("( + le code du NPC est : " + npcAnswer.toString() + ")");
		}

		do {
			System.out.println("\nTour n°" + loop);

			for (int i = 0; i < userProp.getLength(); i++) {
				System.out.println("Chiffre n°" + (i + 1));
				Integer element = userProp.generateCode(i);
				
				while (element < 0 || element > 9) {
					System.out.println("Entrée invalide, veuillez entrer un chiffre entre 0 et 9 :");
					element = userProp.generateCode(i);
				}
				
				userProp.addElement(element);
			}

			if (loop <= maxLoop) {
				if (userProp.getElements().equals(npcAnswer.getElements())) {
					System.out.println(
							"Vous avez trouvé le code secret !\nLa réponse était bien : " + userProp.toString());
					break;
				} else {
					try {
						ComparatorInteger userComparator = new ComparatorInteger(userProp);
						System.out.println("Votre code n'est pas bon ! Proposition : " + userProp.toString() + "\n"
								+ userComparator.compareTo(npcAnswer));
					} catch (Exception e) {
						System.out.println("Il y a eu un problème dans votre code, la jeu va redémarrer");
						logger.error("Erreur utilisateur : redémarrage du jeu");
						break;
					}
				}

				npcProp.resetCode();
				for (int i = 0; i < npcProp.getLength(); i++) {
					npcProp.addElement(npcProp.generateCode(i));
				}

				if (npcProp.getElements().equals(userAnswer.getElements())) {
					System.out.println(
							"L'ordinateur a trouvé le code secret !\nLa réponse était bien : " + npcProp.toString());
					break;
				} else {
					try {
						ComparatorInteger npcComparator = new ComparatorInteger(npcProp);
						System.out.println("Le code du NPC n'est pas bon ! Proposition : " + npcProp.toString() + "\n"
								+ npcComparator.compareTo(userAnswer));
					} catch (Exception e) {
						System.out.println("Il y a eu un problème dans votre code, la jeu va redémarrer");
						logger.error("Erreur utilisateur : redémarrage du jeu");
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
		logger.info("Lancement du mode Match");
		userScore = 0;
		npcScore = 0;
		loop = 1;

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
			System.out.println("Il y a eu un problème dans le résultat final");
			logger.error("Erreur dans la compatiblisation des scores");
		}

		return;
	}

}
