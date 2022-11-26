package jeu;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
/**
 * Définie une carte chance, sa tache et son tirage au sort dans un tas de CarteChance
 */

public class CarteComm {
	/**
	 * Mot sur la carte
	 */
	private String phrase;
	/**
	 * Ou un nom de case ou un chiffre pour payer/encaisser
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
	 * @param deplacementConsequenses Ou un nom de case ou un chiffre pour payer/encaisser
	 */
	public CarteComm(String type, String phrase, String deplacementConsequenses) {
		this.setType(type);
		this.setPhrase(phrase);
		this.setdeplacementConsequenses(deplacementConsequenses);
	}

	/** Selon le type de la carte, fait l'action de la carte
	 * 
	 * @param joueur Joueur qui tire la carte
	 * @param cartes Tas de carte
	 * @param p Les autres joueurs du plateau, pour l'anniversaire par exemple
	 * @throws jeuException
	 */
	public static void tacheCarte(Joueur joueur, Stack<CarteComm> cartes, ArrayList<Joueur> p) throws jeuException {
		CarteComm carte = cartes.get(0); 
		cartes.remove(0);
		cartes.add(carte);
		int i = 0;
		switch (carte.getType()) {
		case "PAYER":
			i = Integer.parseInt(carte.getdeplacementConsequenses());
		case "ENCAISSER":
			i = Integer.parseInt(carte.getdeplacementConsequenses());
		}
		switch (carte.getType()) {
		case "PAYER":
			joueur.setArgent(joueur.getArgent() - i);
			break;
		case "ENCAISSER":
			joueur.setArgent(joueur.getArgent() + i);
			break;
		case "CHANCE":
			joueur.setArgent(joueur.getArgent() - 10);
			break;
		case "ANNIVERSAIRE":
			for (Joueur value : p) {
				value.setArgent(value.getArgent() - 10);
				joueur.setArgent(joueur.getArgent() + 10);
			}
			break;
		case "LIBERATION":
			joueur.setLiberation(true);
			break;
		case "DEPLACEMENT":
			switch (carte.getdeplacementConsequenses()) {
				case "CASE DEPART" -> {
					joueur.setNumCaseActuelleADMIN(0);
					joueur.setArgent(joueur.getArgent() + 200);
				}
				case "BOULEVARD DE BELLEVILLE" -> {
					joueur.setNumCaseActuelleADMIN(1);
					joueur.setArgent(joueur.getArgent() + 200);
				}
				case "PRISON" -> joueur.setNumCaseActuelleADMIN(30);
			}
			break;
		}
	}

	/** Simule un tirage d'une carte (aléatoire)
	 * Inutilisée
	 * @param c Toutes les cartes
	 * @return Une carte
	 */
	public static CarteComm tirageCarteComm(ArrayList<CarteComm> c) {
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
