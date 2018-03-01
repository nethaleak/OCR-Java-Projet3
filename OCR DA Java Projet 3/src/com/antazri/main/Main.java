package com.antazri.main;

import java.io.File;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import com.antazri.main.code.CodeSearch;
import com.antazri.main.mastermind.Mastermind;

/**
 * Classe principale du programme, elle permet de lancer les différents jeux
 * présents dans l'application : CodeSearch et Mastermind.
 * 
 * @author AnthonyT
 * @version 1.0
 */

public class Main {

	private static ResourceBundle bundle = ResourceBundle.getBundle("com.antazri.main.resources.config");
	private static String devMode = bundle.getString("mode.developper");
	private static boolean running = true;
	private static Scanner scan = new Scanner(System.in);
	private static int game = -1;
	private static boolean developper = Boolean.parseBoolean(devMode);
	private static Logger logger = LogManager.getLogger(Main.class);

	private Main() {
	}

	public static void main(String[] args) {
		while (running) {
			do {
				logger.trace("Lancement du programme");
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
					logger.error("L'utilisateur a entré une donnée non reconnue");
					scan.next();
				}

			} while (game != 0);

			running = false;
			break;
		}

		System.out.println("** Bye ! **");
		logger.trace("Sortie du programme");
	}

}
