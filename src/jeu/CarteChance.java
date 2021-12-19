package jeu;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
/**
 * Définie une carte Communauté, sa tache et son tirage au sort dans un tas de CarteComm
 */
public class CarteChance {
	/**
	 * Mot sur la carte
	 */
	private String phrase;
	/**
	 * Ou un nom de case, ou un chiffre pour payer/encaisser
	 */
	private String deplacementConsequenses;
	/**
	 * Type de la carte : PAYER, DEPLACEMENT, ENCAISSER, LIBERATION, IMPOTS ou REPARATIONS
	 */
	private String type;

	/** Constructeur
	 * 
	 * @param type Type de la carte
	 * @param phrase Mot sur la carte
	 * @param deplacementConsequenses Ou un nom de case, ou un chiffre pour payer/encaisser
	 */
	public CarteChance(String type, String phrase, String deplacementConsequenses) {
		this.setType(type);
		this.setPhrase(phrase);
		this.setdeplacementConsequenses(deplacementConsequenses);
	}

	/** Selon le type de la carte, fait l'action de la carte
	 * 
	 * @param joueur
	 * @param carte
	 * @param p Inutilisée ici
	 * @throws jeuException
	 */
	public static void tacheCarte(Joueur joueur, Stack<CarteChance> cartes, ArrayList<Joueur> p) throws jeuException {
		CarteChance carte = cartes.get(0); 
		cartes.remove(0);
		cartes.add(carte);
		int i = 0;
		switch (carte.getType()) {
		case "PAYER":
			i = Integer.parseInt(carte.getdeplacementConsequenses());
			break;
		case "ENCAISSER":
			i = Integer.parseInt(carte.getdeplacementConsequenses());
			break;
		}
		System.out.println(carte.getPhrase());
		switch (carte.getType()) {
		case "PAYER":
			joueur.setArgent(joueur.getArgent() - i);
			break;
		case "ENCAISSER":
			joueur.setArgent(joueur.getArgent() + i);
			break;
		case "IMPOTS":
			int totalI = 0;
			for (int y = 0; y < joueur.getTerrainsPossede().size(); y++) {
				totalI = totalI + 40 * joueur.getTerrainsPossede().get(y).getNbmaison();
				if (joueur.getTerrainsPossede().get(y).isNbhotel())
					totalI = totalI + 115;
			}
			break;
		case "REPARATIONS":
			int totalR = 0;
			for (int y = 0; y < joueur.getTerrainsPossede().size(); y++) {
				totalR = totalR + 25 * joueur.getTerrainsPossede().get(y).getNbmaison();
				if (joueur.getTerrainsPossede().get(y).isNbhotel())
					totalR = totalR + 100;
			}
			break;
		case "LIBERATION":
			joueur.setLiberation(true);
			break;
		case "DEPLACEMENT":
			if (carte.getdeplacementConsequenses().equals("CASE DEPART")) {
				joueur.setNumCaseActuelleADMIN(0);
				joueur.setArgent(joueur.getArgent()+200);
			}
			if (carte.getdeplacementConsequenses().equals("BOULEVARD DE LA VILLETTE")) {
				if(joueur.getNumCaseActuelle()>11)
					joueur.setArgent(joueur.getArgent()+200);
				joueur.setNumCaseActuelleADMIN(11);
			}
			if (carte.getdeplacementConsequenses().equals("PRISON")) {
				joueur.setNumCaseActuelleADMIN(30);
			}
			if (carte.getdeplacementConsequenses().equals("RUE DE LA PAIX")) {
				if(joueur.getNumCaseActuelle()>39)
					joueur.setArgent(joueur.getArgent()+200);
				joueur.setNumCaseActuelleADMIN(39);
			}
			if (carte.getdeplacementConsequenses().equals("AVENUE HENRI-MARTIN")) {
				if(joueur.getNumCaseActuelle()>24)
					joueur.setArgent(joueur.getArgent()+200);
				joueur.setNumCaseActuelleADMIN(24);
			}
			if (carte.getdeplacementConsequenses().equals("GARE DE LYON")) {
				if(joueur.getNumCaseActuelle()>15)
					joueur.setArgent(joueur.getArgent()+200);
				joueur.setNumCaseActuelleADMIN(15);
			}
			break;
		}
	}

	/** Simule un tirage d'une carte (aléatoire)
	 * Inutilisée
	 * @param c Toutes les cartes
	 * @return Une carte
	 */
	public static CarteChance tirageCarteChance(ArrayList<CarteChance> c) {
		Random random = new Random();
		return c.get(random.nextInt(c.size()));
	}

	public String getPhrase() {
		return phrase;
	}

	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}

	public String getdeplacementConsequenses() {
		return deplacementConsequenses;
	}

	public void setdeplacementConsequenses(String deplacementConsequenses) {
		this.deplacementConsequenses = deplacementConsequenses;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
