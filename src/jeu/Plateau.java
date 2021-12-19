package jeu;

import fichiers.Fichier;
import parser.Parser;
import parser.ParserAllerEnPrison;
import parser.ParserCaisseCommunaute;
import parser.ParserCarteChance;
import parser.ParserCarteComm;
import parser.ParserCaseDepart;
import parser.ParserChance;
import parser.ParserCompagnie;
import parser.ParserGare;
import parser.ParserImpot;
import parser.ParserParkingGratuit;
import parser.ParserPrison;
import parser.ParserSimpleVisite;
import parser.ParserTaxeDeLuxe;
import parser.ParserTerrain;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Initialise le plateau, parse dans les fichiers
 */
public class Plateau {
	/**
	 * Les cases du plateau
	 * 
	 */
	private static ArrayList<CasePlateau> plateau = new ArrayList<CasePlateau>();
	/**
	 * Les joueurs du plateau
	 * 
	 */
	private static ArrayList<Joueur> participants = new ArrayList<Joueur>();
	/**
	 * Les cartes chances
	 * 
	 */
	private static Stack<CarteChance> chance = new Stack<CarteChance>();
	/**
	 * Les cartes de communauté
	 * 
	 */
	private static Stack<CarteComm> communaute = new Stack<CarteComm>();

	/**
	 * Constructeur
	 * 
	 * @param listeJoueurs provenant de la class Monopoly
	 */
	public Plateau(ArrayList<Joueur> listeJoueurs) {
		String nomDuFichier = "Parametre/Terrains.csv";
		Parser premierParser = null;
		premierParser = new ParserCaseDepart(premierParser);
		premierParser = new ParserGare(premierParser);
		premierParser = new ParserTerrain(premierParser);
		premierParser = new ParserChance(premierParser);
		premierParser = new ParserCaisseCommunaute(premierParser);
		premierParser = new ParserImpot(premierParser);
		premierParser = new ParserSimpleVisite(premierParser);
		premierParser = new ParserParkingGratuit(premierParser);
		premierParser = new ParserAllerEnPrison(premierParser);
		premierParser = new ParserTaxeDeLuxe(premierParser);
		premierParser = new ParserPrison(premierParser);
		premierParser = new ParserCompagnie(premierParser);
		for (int i = 0; i < 41; i++) {
			getPlateau().add(new CaseDepart("ALLOC", -1));
		}
		Fichier.lire(nomDuFichier, premierParser);

		nomDuFichier = "Parametre/CartesCommunaute.csv";
		Parser secondParser = null;
		secondParser = new ParserCarteComm(secondParser);
		Fichier.lire(nomDuFichier, secondParser);

		nomDuFichier = "Parametre/CartesChance.csv";
		Parser troisiemeParser = null;
		troisiemeParser = new ParserCarteChance(troisiemeParser);
		Fichier.lire(nomDuFichier, troisiemeParser);

		if (!(listeJoueurs.isEmpty()))
			setParticipants(listeJoueurs);
	}
	
	/**
	 * Constructeur
	 * 
	 * Version sans joueurs, utilisé pour les tests
	 */
	public Plateau() {
		String nomDuFichier = "Parametre/Terrains.csv";
		Parser premierParser = null;
		premierParser = new ParserCaseDepart(premierParser);
		premierParser = new ParserGare(premierParser);
		premierParser = new ParserTerrain(premierParser);
		premierParser = new ParserChance(premierParser);
		premierParser = new ParserCaisseCommunaute(premierParser);
		premierParser = new ParserImpot(premierParser);
		premierParser = new ParserSimpleVisite(premierParser);
		premierParser = new ParserParkingGratuit(premierParser);
		premierParser = new ParserAllerEnPrison(premierParser);
		premierParser = new ParserTaxeDeLuxe(premierParser);
		premierParser = new ParserPrison(premierParser);
		premierParser = new ParserCompagnie(premierParser);
		for (int i = 0; i < 41; i++) {
			getPlateau().add(new CaseDepart("ALLOC", -1));
		}
		Fichier.lire(nomDuFichier, premierParser);

		nomDuFichier = "Parametre/CartesCommunaute.csv";
		Parser secondParser = null;
		secondParser = new ParserCarteComm(secondParser);
		Fichier.lire(nomDuFichier, secondParser);

		nomDuFichier = "Parametre/CartesChance.csv";
		Parser troisiemeParser = null;
		troisiemeParser = new ParserCarteChance(troisiemeParser);
		Fichier.lire(nomDuFichier, troisiemeParser);
	}

	/**
	 * Getters et setters
	 * 
	 * 
	 */
	public static ArrayList<Joueur> getParticipants() {
		return participants;
	}

	public static void setParticipants(ArrayList<Joueur> participants) {
		Plateau.participants = participants;
	}

	public static ArrayList<CasePlateau> getPlateau() {
		return plateau;
	}

	public static void setPlateau(ArrayList<CasePlateau> plateau) {
		Plateau.plateau = plateau;
	}

	public static Stack<CarteChance> getChance() {
		return chance;
	}

	public static void setChance(Stack<CarteChance> chance) {
		Plateau.chance = chance;
	}

	public static Stack<CarteComm> getCommunaute() {
		return communaute;
	}

	public static void setCommunaute(Stack<CarteComm> communaute) {
		Plateau.communaute = communaute;
	}

	/**
	 * Montre les cases du plateau dans la console
	 * 
	 * @param plateau
	 */
	public void showPlateau(Plateau plateau) {
		for (int i = 0; i < 41; i++) {
			System.out.println(Plateau.getPlateau().get(i).getPlace() + " " + Plateau.getPlateau().get(i).getNom());
		}
	}

	/**
	 * Montre les cartes communauté dans la console
	 * 
	 * @param plateau
	 */
	public void showCartesComm(Plateau plateau) {
		for (int i = 0; i < Plateau.getCommunaute().size(); i++) {
			System.out.println(
					Plateau.getCommunaute().get(i).getType() + " | " + Plateau.getCommunaute().get(i).getPhrase()
							+ " | " + Plateau.getCommunaute().get(i).getdeplacementConsequenses() + ".");
		}
	}

	/**
	 * Montre les cartes chance dans la console
	 * 
	 * @param plateau
	 */
	public void showCartesChance(Plateau plateau) {
		for (int i = 0; i < Plateau.getCommunaute().size(); i++) {
			System.out.println(Plateau.getChance().get(i).getType() + " | " + Plateau.getChance().get(i).getPhrase()
					+ " | " + Plateau.getChance().get(i).getdeplacementConsequenses() + ".");
		}
	}
}