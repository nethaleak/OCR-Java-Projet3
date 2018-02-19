package com.antazri.main.mastermind;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.antazri.main.utils.Code;
import com.antazri.main.utils.PieceColor;

public class CodePieces implements Code<PieceColor> {

	private int rightPieces = 0;
	private int presentPieces = 0;
	private List<PieceColor> elements;

	public CodePieces() {
		super();

		this.elements = new ArrayList<PieceColor>();
	}
	
	public List<PieceColor> getElements() {
		return elements;
	}

	public int getRightPieces() {
		return rightPieces;
	}

	public int getPresentPieces() {
		return presentPieces;
	}

	public List<PieceColor> allColors() {
		List<PieceColor> allColors = new ArrayList<PieceColor>(EnumSet.allOf(PieceColor.class));
		return allColors;
	}
	
	@Override
	public void addElement(PieceColor element) {
		this.elements.add(element);
	}

	@Override
	public List<PieceColor> generateUserProposition() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);

		for (int i = 1; i < 5; i++) {
			try {
				System.out.println("Pion " + i + " : Noir / Blanc / Jaune / Rouge / Vert / Bleu");
				String color = scan.nextLine();
				color.toLowerCase();

				switch (color) {
				case "noir":
					this.addElement(PieceColor.BLACK);
					break;

				case "blanc":
					this.addElement(PieceColor.WHITE);
					break;

				case "bleu":
					this.addElement(PieceColor.BLUE);
					break;

				case "jaune":
					this.addElement(PieceColor.YELLOW);
					break;

				case "rouge":
					this.addElement(PieceColor.RED);
					break;

				case "vert":
					this.addElement(PieceColor.GREEN);
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
		
		return this.elements;
	}

	@Override
	public List<PieceColor> automaticProposition() {
		for (int i = 0; i < 4; i++) {
			PieceColor color = allColors().get((int) Math.floor(Math.random() * allColors().size()));
			this.addElement(color);
		}
		return this.elements;
	}

	@Override
	public void generateNpcProposition(List<PieceColor> answer) {
		List<PieceColor> temp = this.getElements();
		this.resetCode();
		int max = allColors().size() - 1;
		PieceColor newColor;
		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).equals(answer.get(i))) {
				this.addElement(temp.get(i));
			} else {
				do {
					int x = (int) Math.floor(Math.random() * max);
					newColor = allColors().get(x);
				} while (temp.get(i) == newColor);
				this.addElement(newColor);
			} 
		}
	}

	@Override
	public String compare(List<PieceColor> answer) {
	this.presentPieces = 0;
	this.rightPieces = 0;
	List<PieceColor> temp = new ArrayList<PieceColor>(answer);
	
		for (int i = 0; i < this.elements.size(); i++) {
			for(int j = 0; j < temp.size(); j++) {
				if(this.elements.get(i).equals(temp.get(j))) {
					this.presentPieces++;
					temp.set(j, null);
					break;
				}
			}
		}
		
		for (int i = 0; i < this.elements.size(); i++) {
			if (this.elements.get(i).equals(answer.get(i))) {
				this.rightPieces++;
			}
		}

		return "Pions présents : " + this.getPresentPieces() + "/4 | Pions bien placés : " + this.getRightPieces()
				+ "/4";
	}

	@Override
	public void resetCode() {
		this.getElements().clear();
	}

	@Override
	public String toString() {
		String description = "";
		for (PieceColor element : elements) {
			description += element.toString() + " ";
		}
		return description;
	}

}
