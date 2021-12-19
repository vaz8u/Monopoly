package jeu;

import java.util.ArrayList;

import ui.Pion;

/**
 * Définie un joueur avec des fonctions par rapport aux actions
 * 	qu'il peut effectuer pendant la partie
 */
public class Joueur {

	/**
	 * son nom
	 * 
	 */
	private String nom;
	/**
	 * position du joueur
	 * 
	 */
	private int numCaseActuelle;
	/**
	 * son argent
	 * 
	 */
	private int argent;
	/**
	 * tous les terrains du joueur
	 * 
	 */
	private ArrayList<CasePlateau> terrainsPossede;
	/**
	 * nombre de gares possédés par le joueur
	 * 
	 */
	private int gares;
	/**
	 * si il est en prison
	 * 
	 */
	private boolean enPrison;
	/**
	 * si il possède les deux services publiques=true; false sinon
	 * 
	 */
	private boolean servicePublic;
	/**
	 * enregistre son dernier déplacement, utile seulement pour calcul loyer si il
	 * tombe sur un service public
	 * 
	 */
	private int deplacement;
	/**
	 * =true si il possède une carte de libération de prison
	 * 
	 */
	private boolean liberation;
	/**
	 * son pion
	 * 
	 */
	private Pion pion;

	/**
	 * Constructeur avec nom
	 *
	 * @param nom Le nom du joueur
	 */
	public Joueur(String nom) {
		this.nom = nom;
		this.numCaseActuelle = 0;
		this.argent = 1500;
		this.setTerrainsPossede(new ArrayList<CasePlateau>());
		this.gares = 0;
		this.enPrison = false;
		this.servicePublic = false;
		this.deplacement = 0;
		this.pion = null;
	}

	/**
	 * Constructeur sans nom
	 * 
	 */
	public Joueur() {
		this.nom = "Joueur";
		this.numCaseActuelle = 0;
		this.argent = 1500;
		this.setTerrainsPossede(new ArrayList<CasePlateau>());
		this.gares = 0;
		this.enPrison = false;
		this.servicePublic = false;
		this.deplacement = 0;
		this.pion = null;
	}

	/*
	 * getters et setters
	 * 
	 */
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNumCaseActuelle() {
		return numCaseActuelle;
	}

	/**
	 * additionne un nombre à la position actuelle du joueur
	 * 
	 * @param numCaseActuelle Position actuelle du joueur sur le plateau
	 * @param joueur          Le joueur que l'on implique
	 * @throws jeuException Si le déplacement est trop petit ou trop grand pour deux
	 *                      dés de 6 faces
	 */

	public void setNumCaseActuelle(int numCaseActuelle, Joueur joueur) throws jeuException {
		if (numCaseActuelle < 2 || numCaseActuelle > 12)
			throw new jeuException("Erreur setCase.");
		this.numCaseActuelle = numCaseActuelle + joueur.getNumCaseActuelle();
		joueur.testDepassementPlateau(joueur);
		joueur.setDeplacement(numCaseActuelle);
	}

	/**
	 * met le joueur à la case donnée sans prendre en compte sa case actuelle
	 * 
	 * @param nouvelleCase Nouvelle case du joueur
	 * @throws jeuException La nouvelle case ne doit pas dépasser la taille du
	 *                      plateau
	 */
	public void setNumCaseActuelleADMIN(int nouvelleCase) throws jeuException {
		if (nouvelleCase < 0 || nouvelleCase > 41)
			throw new jeuException("Erreur setCase.");
		this.numCaseActuelle = nouvelleCase;
		setDeplacement(nouvelleCase);
	}

	public int getArgent() {
		return argent;
	}

	public void setArgent(int argent) {
		this.argent = argent;
	}

	public int getGares() {
		return gares;
	}

	public void setGares(int gares) {
		this.gares = gares;
	}

	public boolean isEnPrison() {
		return enPrison;
	}

	public void enPrison(boolean enPrison) {
		this.enPrison = enPrison;
	}

	public ArrayList<CasePlateau> getTerrainsPossede() {
		return terrainsPossede;
	}

	public void setTerrainsPossede(ArrayList<CasePlateau> terrainsPossede) {
		this.terrainsPossede = terrainsPossede;
	}

	public boolean isServicePublic() {
		return servicePublic;
	}

	public void setServicePublic(boolean servicePublic) {
		this.servicePublic = servicePublic;
	}

	public int getDeplacement() {
		return deplacement;
	}

	public void setDeplacement(int deplacement) {
		this.deplacement = deplacement;
	}

	public boolean isLiberation() {
		return liberation;
	}

	public void setLiberation(boolean liberation) {
		this.liberation = liberation;
	}

	public Pion getPion() {
		return pion;
	}

	public void setPion(Pion pion) {
		this.pion = pion;
	}

	// méthodes métiers

	/**
	 * Action d'acheter un hotel à la case T par le Joueur joueur
	 * 
	 * @param T      Case qui va etre achetée
	 * @param joueur Joueur qui veut acheter
	 * @throws jeuException Manque de fonds, si on ne possède pas le terrain, si on
	 *                      a pas de monopole, si on a déjà un hotel.
	 */
	public void AcheterHotel(CasePlateau T, Joueur joueur) throws jeuException {
		int y = 0;
		int x;
		boolean respmaison = false;
		CasePlateau TB = T;
		CasePlateau TC = T;
		if (getArgent() < T.getPrixMaison())
			throw new jeuException("Fonds insuffisants pour acheter l'hotel.");
		if (!(T.isEstAchete()))
			throw new jeuException(
					"Vous ne pouvez pas acheter d'hotel sur " + T.getNom() + " car vous ne le possedez pas.");
		if (!(T.getProprietaire().getTerrainsPossede().get(getNumCaseActuelle()).isMonopole()))
			throw new jeuException("Vous ne pouvez pas acheter d'hotel car vous n'avez pas de monopole.");
		if (T.isNbhotel())
			throw new jeuException("Vous avez deja un hotel sur ce terrain.");

		switch (T.getCouleur()) {
		case "MARRON":
			respmaison = false;
			break;
		case "BLEU FONCE":
			respmaison = false;
			break;
		case "VERT":
			respmaison = true;
			break;
		case "JAUNE":
			respmaison = true;
			break;
		case "ROUGE":
			respmaison = true;
			break;
		case "ORANGE":
			respmaison = true;
			break;
		case "VIOLET":
			respmaison = true;
			break;
		case "BLEU CIEL":
			respmaison = true;
			break;
		}
		if (respmaison == false) {
			for (y = 0; y < joueur.getTerrainsPossede().size(); y++) {
				if (joueur.getTerrainsPossede().get(y).getCouleur().equals(T.getCouleur())
						&& joueur.getTerrainsPossede().get(y).getPlace() != T.getPlace())
					TB = joueur.getTerrainsPossede().get(y);
			}
			if (!(T.getNbmaison() == 4 && TB.getNbmaison() >= 4))
				throw new jeuException(
						"Vous ne pouvez pas acheter d'hotel car on ne peut pas avoir 2 maisons d'ecart entre les terrains d'une meme famille.");
		}
		if (respmaison == true) {
			for (y = 0; y < joueur.getTerrainsPossede().size(); y++) {
				if (joueur.getTerrainsPossede().get(y).getCouleur().equals(T.getCouleur())
						&& joueur.getTerrainsPossede().get(y).getPlace() != T.getPlace())
					TB = joueur.getTerrainsPossede().get(y);
			}
			for (x = 0; x < joueur.getTerrainsPossede().size(); x++) {
				if (joueur.getTerrainsPossede().get(x).getCouleur().equals(T.getCouleur())
						&& joueur.getTerrainsPossede().get(x).getPlace() != T.getPlace()
						&& joueur.getTerrainsPossede().get(x).getPlace() != TB.getPlace())
					TC = joueur.getTerrainsPossede().get(x);
			}
			if (!(T.getNbmaison() == 4 && TB.getNbmaison() >= 4 && TC.getNbmaison() >= 4))
				throw new jeuException(
						"Vous ne pouvez pas acheter d'hotel car on ne peut pas avoir 2 maisons d'ecart entre les terrains d'une meme famille.");
		}
		T.setNbhotel(true);
		T.setNbmaison(T.getNbmaison() + 1);
		setArgent(getArgent() - T.getPrixMaison());
		T.setPrixLoyer(T.getTabLoyer()[T.getNbmaison()]);
	}

	/**
	 * Action d'acheter une maison à la case T par le Joueur joueur
	 * 
	 * @param T      Case qui va etre achetée
	 * @param joueur Joueur qui veut acheter
	 * @throws jeuException Manque de fonds, si on ne possède pas le terrain, si on
	 *                      a pas de monopole, si on a déjà 4 maisons.
	 */
	public void AcheterMaison(CasePlateau T, Joueur joueur) throws jeuException {
		int y = 0;
		int x;
		boolean respmaison = false;
		CasePlateau TB = T;
		CasePlateau TC = T;
		if (getArgent() < T.getPrixMaison())
			throw new jeuException("Fonds insuffisants pour acheter la maison");
		if (!(T.isEstAchete()))
			throw new jeuException(
					"Vous ne pouvez pas acheter de maison sur " + T.getNom() + " car vous ne le possedez pas.");
		/* if (!(T.getProprietaire().getTerrainsPossede().get(getNumCaseActuelle()).isMonopole())) 
			throw new jeuException("Vous ne pouvez pas acheter de maison car vous n'avez pas de monopole.");
			*Mis en commentaire car bug sur l'interface graphique, les disable button s'en occupent.
		*/
		if (T.getNbmaison() >= 4)
			throw new jeuException("Vous avez deja 4 maisons sur ce terrain.");

		switch (T.getCouleur()) {
		case "MARRON":
			respmaison = false;
			break;
		case "BLEU FONCE":
			respmaison = false;
			break;
		case "VERT":
			respmaison = true;
			break;
		case "JAUNE":
			respmaison = true;
			break;
		case "ROUGE":
			respmaison = true;
			break;
		case "ORANGE":
			respmaison = true;
			break;
		case "VIOLET":
			respmaison = true;
			break;
		case "BLEU CIEL":
			respmaison = true;
			break;
		}
		if (respmaison == false) {
			for (y = 0; y < joueur.getTerrainsPossede().size(); y++) {
				if (joueur.getTerrainsPossede().get(y).getCouleur().equals(T.getCouleur())
						&& joueur.getTerrainsPossede().get(y).getPlace() != T.getPlace())
					TB = joueur.getTerrainsPossede().get(y);
			}
			if (!(maisonT2(T, TB, joueur)))
				throw new jeuException("On ne peut pas avoir 2 maisons d'ecart entre les terrains d'une meme famille.");
		}
		if (respmaison == true) {
			for (y = 0; y < joueur.getTerrainsPossede().size(); y++) {
				if (joueur.getTerrainsPossede().get(y).getCouleur().equals(T.getCouleur())
						&& joueur.getTerrainsPossede().get(y).getPlace() != T.getPlace())
					TB = joueur.getTerrainsPossede().get(y);
			}
			for (x = 0; x < joueur.getTerrainsPossede().size(); x++) {
				if (joueur.getTerrainsPossede().get(x).getCouleur().equals(T.getCouleur())
						&& joueur.getTerrainsPossede().get(x).getPlace() != T.getPlace()
						&& joueur.getTerrainsPossede().get(x).getPlace() != TB.getPlace())
					TC = joueur.getTerrainsPossede().get(x);
			}
			if (!(maisonT3(T, TB, TC, joueur)))
				throw new jeuException("On ne peut pas avoir 2 maisons d'ecart entre les terrains d'une meme famille.");
		}
		T.setNbmaison(T.getNbmaison() + 1);
		setArgent(getArgent() - T.getPrixMaison());
		T.setPrixLoyer(T.getTabLoyer()[T.getNbmaison()]);
	}

	/**
	 * Action d'acheter le terrain de la case T par le Joueur joueur
	 * 
	 * @param T      Case qui va etre achetée
	 * @param joueur Joueur qui veut acheter
	 * @throws jeuException Manque de fonds, si terrain déjà acheté, si T n'est pas
	 *                      un terrain achetable.
	 */
	public void AcheterTerrain(CasePlateau T, Joueur joueur) throws jeuException {
		if (T.getCouleur() == null)
			throw new jeuException(T.getNom() + " n'est pas un terrain!");
		if (getArgent() < T.getValeurTerrain())
			throw new jeuException("Fonds insuffisants pour acheter " + T.getNom());
		if (T.isEstAchete())
			throw new jeuException(
					"Le terrain " + T.getNom() + " est deja achete par " + T.getProprietaire().getNom() + ".");
		setArgent(getArgent() - T.getValeurTerrain());
		T.setEstAchete(true);
		T.setProprietaire(joueur);
		getTerrainsPossede().add(T);
		switch (T.getPlace()) {
		case 35:
			joueur.setGares(joueur.getGares() + 1);
			break;
		case 5:
			joueur.setGares(joueur.getGares() + 1);
			break;
		case 15:
			joueur.setGares(joueur.getGares() + 1);
			break;
		case 25:
			joueur.setGares(joueur.getGares() + 1);
			break;
		case 12:
			joueur.compagnieDouble(joueur);
			break;
		case 28:
			joueur.compagnieDouble(joueur);
			break;
		}
		joueur.Couleur(joueur);
	}

	/**
	 * Le deplacement du joueur
	 * 
	 * @param des    Structure des dés
	 * @param joueur Joueur en question
	 * @throws jeuException Si les dés ne sont pas dans les normes
	 */
	public void seDeplacer(Des des, Joueur joueur) throws jeuException {
		if (des.sommeDes(des.getDes1(), des.getDes2()) > 12 || des.sommeDes(des.getDes1(), des.getDes2()) < 2)
			throw new jeuException("Erreurs de dés.");
		setNumCaseActuelle(des.sommeDes(des.getDes1(), des.getDes2()), joueur);
		testDepassementPlateau(joueur);
		setDeplacement(des.sommeDes(des.getDes1(), des.getDes2()));
	}

	/**
	 * Test si le joueur dépasse les limites du terrain et le remet à la bonne place
	 * et le fait simuler un passage par la case départ
	 * 
	 * @param joueur Joueur en question
	 * @throws jeuException
	 */
	public void testDepassementPlateau(Joueur joueur) throws jeuException {
		if (getNumCaseActuelle() > 39) {
			setNumCaseActuelleADMIN(getNumCaseActuelle() - 40);
			joueur.setArgent(joueur.getArgent() + 200);
		}
	}

	/**
	 * Utile pour les tests, affiche l'arrayList des terrains du joueur
	 * 
	 * @param joueur
	 */
	public void afficheTerrains(Joueur joueur) {
		System.out.println("Le joueur " + joueur.getNom() + " possede les terrains :");
		for (int i = 0; i < getTerrainsPossede().size(); i++) {
			System.out.println(getTerrainsPossede().get(i).getNom());
		}
	}

	/**
	 * Verifie si un joueur possede un monopole
	 * 
	 * @param joueur
	 */
	public void Couleur(Joueur joueur) {
		int marron = 0, bleuc = 0, violet = 0, orange = 0, rouge = 0, jaune = 0, vert = 0, bleuf = 0;

		for (int x = 0; x < joueur.getTerrainsPossede().size(); x++) {
			switch (joueur.getTerrainsPossede().get(x).getCouleur()) {
			case "MARRON":
				marron++;
				break;
			case "BLEU CIEL":
				bleuc++;
				break;
			case "VIOLET":
				violet++;
				break;
			case "ORANGE":
				orange++;
				break;
			case "ROUGE":
				rouge++;
				break;
			case "JAUNE":
				jaune++;
				break;
			case "VERT":
				vert++;
				break;
			case "BLEU FONCE":
				bleuf++;
				break;
			}
		}
		if (marron == 2) {
			System.out.println("Vous avez maintenant un monopole pour les terrains de couleur marron.");
			for (int y = 0; y < joueur.getTerrainsPossede().size(); y++) {
				switch (joueur.getTerrainsPossede().get(y).getCouleur()) {
				case "MARRON":
					joueur.getTerrainsPossede().get(y).setMonopole(true);
					break;
				}
			}
		}
		if (bleuc == 3) {
			System.out.println("Vous avez maintenant un monopole pour les terrains de couleur bleu claire.");
			for (int y = 0; y < joueur.getTerrainsPossede().size(); y++) {
				switch (joueur.getTerrainsPossede().get(y).getCouleur()) {
				case "BLEU CIEL":
					joueur.getTerrainsPossede().get(y).setMonopole(true);
					break;
				}
			}
		}
		if (violet == 3) {
			System.out.println("Vous avez maintenant un monopole pour les terrains de couleur violet.");
			for (int y = 0; y < joueur.getTerrainsPossede().size(); y++) {
				switch (joueur.getTerrainsPossede().get(y).getCouleur()) {
				case "VIOLET":
					joueur.getTerrainsPossede().get(y).setMonopole(true);
					break;
				}
			}
		}
		if (orange == 3) {
			System.out.println("Vous avez maintenant un monopole pour les terrains de couleur orange.");
			for (int y = 0; y < joueur.getTerrainsPossede().size(); y++) {
				switch (joueur.getTerrainsPossede().get(y).getCouleur()) {
				case "ORANGE":
					joueur.getTerrainsPossede().get(y).setMonopole(true);
					break;
				}
			}
		}
		if (rouge == 3) {
			System.out.println("Vous avez maintenant un monopole pour les terrains de couleur rouge.");
			for (int y = 0; y < joueur.getTerrainsPossede().size(); y++) {
				switch (joueur.getTerrainsPossede().get(y).getCouleur()) {
				case "ROUGE":
					joueur.getTerrainsPossede().get(y).setMonopole(true);
					break;
				}
			}
		}
		if (jaune == 3) {
			System.out.println("Vous avez maintenant un monopole pour les terrains de couleur jaune.");
			for (int y = 0; y < joueur.getTerrainsPossede().size(); y++) {
				switch (joueur.getTerrainsPossede().get(y).getCouleur()) {
				case "JAUNE":
					joueur.getTerrainsPossede().get(y).setMonopole(true);
					break;
				}
			}
		}
		if (vert == 3) {
			System.out.println("Vous avez maintenant un monopole pour les terrains de couleur vert.");
			for (int y = 0; y < joueur.getTerrainsPossede().size(); y++) {
				switch (joueur.getTerrainsPossede().get(y).getCouleur()) {
				case "VERT":
					joueur.getTerrainsPossede().get(y).setMonopole(true);
					break;
				}
			}
		}
		if (bleuf == 2) {
			System.out.println("Vous avez maintenant un monopole pour les terrains de couleur bleu foncé.");
			for (int y = 0; y < joueur.getTerrainsPossede().size(); y++) {
				switch (joueur.getTerrainsPossede().get(y).getCouleur()) {
				case "BLEU FONCE":
					joueur.getTerrainsPossede().get(y).setMonopole(true);
					break;
				}
			}
		}
	}

	/**
	 * Verifie si le joueur possede les deux compagnies
	 * 
	 * @param joueur
	 */
	public void compagnieDouble(Joueur joueur) {
		for (int y = 0; y < joueur.getTerrainsPossede().size(); y++) {
			if (joueur.getTerrainsPossede().get(y).getNom().equals("COMPAGNIE D'ELECTRICITE")) {
				for (int x = 0; x < joueur.getTerrainsPossede().size(); x++) {
					if (joueur.getTerrainsPossede().get(x).getNom().equals("COMPAGNIE DES EAUX")) {
						joueur.setServicePublic(true);
					}
				}
			}
		}
	}

	/**
	 * Le joueur est sen prison et veut payer pour sortir
	 * 
	 * @param joueur
	 * @throws jeuException
	 */
	public void veutSortirPayer(Joueur joueur) throws jeuException {
		if (joueur.isEnPrison() == false)
			throw new jeuException("Vous n'etes pas en prison.");
		if (joueur.getArgent() < 50)
			throw new jeuException("Vous n'avez pas assez d'argent.");
		joueur.setArgent(joueur.getArgent() - 50);
		joueur.enPrison(false);
		joueur.setNumCaseActuelleADMIN(10);
	}

	/**
	 * Le joueur est sen prison et veut utiliser sa carte pour sortir
	 * 
	 * @param joueur
	 * @throws jeuException
	 */
	public void veutSortirCarte(Joueur joueur) throws jeuException {
		if (joueur.isEnPrison() == false)
			throw new jeuException("Vous n'etes pas en prison.");
		if (joueur.isLiberation() == false)
			throw new jeuException("Vous n'avez pas de carte de libération.");
		joueur.enPrison(false);
		joueur.setNumCaseActuelleADMIN(10);
		joueur.setLiberation(false);
	}

	/**
	 * Test utile dans AcheterMaison pour respecter une regle du jeux pour les
	 * monopoles à deux terrains
	 * 
	 * @param TA     Terrain où l'on ajoute une maison
	 * @param TB     Terrain de la même couleur
	 * @param joueur
	 * @return
	 */
	public boolean maisonT2(CasePlateau TA, CasePlateau TB, Joueur joueur) {
		boolean nbmaison;
		if (TA.getNbmaison() <= (TB.getNbmaison()))
			nbmaison = true;
		else
			nbmaison = false;
		return nbmaison;
	}

	/**
	 * Test utile dans AcheterMaison pour respecter une regle du jeux pour les
	 * monopoles à trois terrains
	 * 
	 * @param TA     Terrain où l'on ajoute une maison
	 * @param TB     Terrain de la même couleur
	 * @param TC     Terrain de la même couleur
	 * @param joueur
	 * @return
	 */
	public boolean maisonT3(CasePlateau TA, CasePlateau TB, CasePlateau TC, Joueur joueur) {
		boolean nbmaison;
		if ((TA.getNbmaison() <= (TB.getNbmaison())) && (TA.getNbmaison() <= (TC.getNbmaison())))
			nbmaison = true;
		else
			nbmaison = false;
		return nbmaison;
	}

	/**
	 * Récapitule le joueur brièvement sous la forme d'une chaine de caractères
	 * 
	 * @param joueur
	 * @return Résumé bref du joueur
	 */
	public String toString(Joueur joueur) {
		return "Nom: " + joueur.getNom() + "\nArgent: " + joueur.getArgent() + "\nCase: " + joueur.getNumCaseActuelle();
	}

}
