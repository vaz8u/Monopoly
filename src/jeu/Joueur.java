package jeu;

import java.util.ArrayList;

import ui.Pion;

/**
 * D�finie un joueur avec des fonctions par rapport aux actions
 * 	qu'il peut effectuer pendant la partie
 */
public class Joueur {

	/**
	 * son nom
	 */
	private String nom;
	/**
	 * position du joueur
	 */
	private int numCaseActuelle;
	/**
	 * son argent
	 */
	private int argent;
	/**
	 * tous les terrains du joueur
	 */
	private ArrayList<CasePlateau> terrainsPossede;
	/**
	 * nombre de gares poss�d� par le joueur
	 */
	private int gares;
	/**
	 * si il est en prison
	 */
	private boolean enPrison;
	/**
	 * s'il poss�de les deux services publics=true ; false sinon
	 */
	private boolean servicePublic;
	/**
	 * enregistre son dernier d�placement, utile seulement pour calcul loyer s'il tombe sur un service public
	 */
	private int deplacement;
	/**
	 * =true s'il poss�de une carte de lib�ration de prison
	 */
	private boolean liberation;
	/**
	 * son pion
	 */
	private Pion pion;

	/**
	 * Constructeur avec nom
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


	// getters et setters
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
	 * additionne un nombre � la position actuelle du joueur
	 * @param numCaseActuelle Position actuelle du joueur sur le plateau
	 * @param joueur          Le joueur que l'on implique
	 * @throws jeuException Si le d�placement est trop petit ou trop grand pour deux
	 *                      d�s de 6 faces
	 */

	public void setNumCaseActuelle(int numCaseActuelle, Joueur joueur) throws jeuException {
		if (numCaseActuelle < 2 || numCaseActuelle > 12)
			throw new jeuException("Erreur setCase.");
		this.numCaseActuelle = numCaseActuelle + joueur.getNumCaseActuelle();
		joueur.testDepassementPlateau(joueur);
		joueur.setDeplacement(numCaseActuelle);
	}

	/**
	 * met le joueur � la case donn�e sans prendre en compte sa case actuelle
	 * @param nouvelleCase Nouvelle case du joueur
	 * @throws jeuException La nouvelle case ne doit pas d�passer la taille du
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

	// m�thodes m�tiers

	/**
	 * Action d'acheter un hotel � la case T par le Joueur
	 * @param T      Case qui va etre achet�e
	 * @param joueur Joueur qui veut acheter
	 * @throws jeuException Manque de fonds, si on ne poss�de pas le terrain, si on
	 *                      n'a pas de monopole, si on a d�j� un hotel.
	 */
	public void AcheterHotel(CasePlateau T, Joueur joueur) throws jeuException {
		int y = 0;
		int x;
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
		if (isRespmaison(T)) {
			for (y = 0; y < joueur.getTerrainsPossede().size(); y++) {
				if (joueur.getTerrainsPossede().get(y).getCouleur().equals(T.getCouleur())
						&& joueur.getTerrainsPossede().get(y).getPlace() != T.getPlace())
					TB = joueur.getTerrainsPossede().get(y);
			}
			if (!(T.getNbmaison() == 4 && TB.getNbmaison() >= 4))
				throw new jeuException(
						"Vous ne pouvez pas acheter d'hotel car on ne peut pas avoir 2 maisons d'ecart entre les terrains d'une meme famille.");
		}
		else {
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

	private boolean isRespmaison(CasePlateau T) {
		return T.getCouleur().equals("MARRON") || T.getCouleur().equals("BLEU FONCE");
	}

	/**
	 * Action d'acheter une maison � la case T par le Joueur
	 * @param T      Case qui va etre achet�e
	 * @param joueur Joueur qui veut acheter
	 * @throws jeuException Manque de fonds, si on ne poss�de pas le terrain, si on
	 *                      n'a pas de monopole, si on a d�j� 4 maisons.
	 */
	public void AcheterMaison(CasePlateau T, Joueur joueur) throws jeuException {
		int y = 0;
		int x;
		CasePlateau TB = T;
		CasePlateau TC = T;
		if (getArgent() < T.getPrixMaison())
			throw new jeuException("Fonds insuffisants pour acheter la maison");
		if (!(T.isEstAchete()))
			throw new jeuException(
					"Vous ne pouvez pas acheter de maison sur " + T.getNom() + " car vous ne le possedez pas.");
		/* if (!(T.getProprietaire().getTerrainsPossede().get(getNumCaseActuelle()).isMonopole())) 
			throw new jeuException("Vous ne pouvez pas acheter de maison, car vous n'avez pas de monopole.");
			*Mis en commentaire, bug sur l'interface graphique, les disable button s'en occupent.
		*/
		if (T.getNbmaison() >= 4)
			throw new jeuException("Vous avez deja 4 maisons sur ce terrain.");

		if (isRespmaison(T)) {
			for (y = 0; y < joueur.getTerrainsPossede().size(); y++) {
				if (joueur.getTerrainsPossede().get(y).getCouleur().equals(T.getCouleur())
						&& joueur.getTerrainsPossede().get(y).getPlace() != T.getPlace())
					TB = joueur.getTerrainsPossede().get(y);
			}
			if (!(maisonT2(T, TB)))
				throw new jeuException("On ne peut pas avoir 2 maisons d'ecart entre les terrains d'une meme famille.");
		}
		else {
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
			if (!(maisonT3(T, TB, TC)))
				throw new jeuException("On ne peut pas avoir 2 maisons d'ecart entre les terrains d'une meme famille.");
		}
		T.setNbmaison(T.getNbmaison() + 1);
		setArgent(getArgent() - T.getPrixMaison());
		T.setPrixLoyer(T.getTabLoyer()[T.getNbmaison()]);
	}

	/**
	 * Action d'acheter le terrain de la case T par le joueur
	 * @param T      Case qui va etre achet�e
	 * @param joueur Joueur qui veut acheter
	 * @throws jeuException Manque de fonds, si terrain d�j� achet�, si T n'est pas
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
			case 35, 5, 15, 25 -> joueur.setGares(joueur.getGares() + 1);
			case 12, 28 -> joueur.compagnieDouble(joueur);
		}
		joueur.Couleur(joueur);
	}

	/**
	 * Le deplacement du joueur
	 * 
	 * @param des    Structure des d�s
	 * @param joueur Joueur en question
	 * @throws jeuException Si les d�s ne sont pas dans les normes
	 */
	public void seDeplacer(Des des, Joueur joueur) throws jeuException {
		if (des.sommeDes(des.getDes1(), des.getDes2()) > 12 || des.sommeDes(des.getDes1(), des.getDes2()) < 2)
			throw new jeuException("Erreurs de d�s.");
		setNumCaseActuelle(des.sommeDes(des.getDes1(), des.getDes2()), joueur);
		testDepassementPlateau(joueur);
		setDeplacement(des.sommeDes(des.getDes1(), des.getDes2()));
	}

	/**
	 * Test si le joueur d�passe les limites du terrain et le remet � la bonne place
	 * et le fait simuler un passage par la case d�part
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
	 * @param joueur Joueur en question
	 */
	public void afficheTerrains(Joueur joueur) {
		System.out.println("Le joueur " + joueur.getNom() + " possede les terrains :");
		for (int i = 0; i < getTerrainsPossede().size(); i++) {
			System.out.println(getTerrainsPossede().get(i).getNom());
		}
	}

	/**
	 * Verifie si un joueur possede un monopole
	 * @param joueur Joueur en question
	 */
	public void Couleur(Joueur joueur) {
		int marron = 0, bleuc = 0, violet = 0, orange = 0, rouge = 0, jaune = 0, vert = 0, bleuf = 0;

		for (int x = 0; x < joueur.getTerrainsPossede().size(); x++) {
			switch (joueur.getTerrainsPossede().get(x).getCouleur()) {
				case "MARRON" -> marron++;
				case "BLEU CIEL" -> bleuc++;
				case "VIOLET" -> violet++;
				case "ORANGE" -> orange++;
				case "ROUGE" -> rouge++;
				case "JAUNE" -> jaune++;
				case "VERT" -> vert++;
				case "BLEU FONCE" -> bleuf++;
			}
		}
		if (marron == 2) {
			System.out.println("Vous avez maintenant un monopole pour les terrains de couleur marron.");
			for (int y = 0; y < joueur.getTerrainsPossede().size(); y++) {
				if ("MARRON".equals(joueur.getTerrainsPossede().get(y).getCouleur())) {
					joueur.getTerrainsPossede().get(y).setMonopole(true);
				}
			}
		}
		if (bleuc == 3) {
			System.out.println("Vous avez maintenant un monopole pour les terrains de couleur bleu claire.");
			for (int y = 0; y < joueur.getTerrainsPossede().size(); y++) {
				if ("BLEU CIEL".equals(joueur.getTerrainsPossede().get(y).getCouleur())) {
					joueur.getTerrainsPossede().get(y).setMonopole(true);
				}
			}
		}
		if (violet == 3) {
			System.out.println("Vous avez maintenant un monopole pour les terrains de couleur violet.");
			for (int y = 0; y < joueur.getTerrainsPossede().size(); y++) {
				if ("VIOLET".equals(joueur.getTerrainsPossede().get(y).getCouleur())) {
					joueur.getTerrainsPossede().get(y).setMonopole(true);
				}
			}
		}
		if (orange == 3) {
			System.out.println("Vous avez maintenant un monopole pour les terrains de couleur orange.");
			for (int y = 0; y < joueur.getTerrainsPossede().size(); y++) {
				if ("ORANGE".equals(joueur.getTerrainsPossede().get(y).getCouleur())) {
					joueur.getTerrainsPossede().get(y).setMonopole(true);
				}
			}
		}
		if (rouge == 3) {
			System.out.println("Vous avez maintenant un monopole pour les terrains de couleur rouge.");
			for (int y = 0; y < joueur.getTerrainsPossede().size(); y++) {
				if ("ROUGE".equals(joueur.getTerrainsPossede().get(y).getCouleur())) {
					joueur.getTerrainsPossede().get(y).setMonopole(true);
				}
			}
		}
		if (jaune == 3) {
			System.out.println("Vous avez maintenant un monopole pour les terrains de couleur jaune.");
			for (int y = 0; y < joueur.getTerrainsPossede().size(); y++) {
				if ("JAUNE".equals(joueur.getTerrainsPossede().get(y).getCouleur())) {
					joueur.getTerrainsPossede().get(y).setMonopole(true);
				}
			}
		}
		if (vert == 3) {
			System.out.println("Vous avez maintenant un monopole pour les terrains de couleur vert.");
			for (int y = 0; y < joueur.getTerrainsPossede().size(); y++) {
				if ("VERT".equals(joueur.getTerrainsPossede().get(y).getCouleur())) {
					joueur.getTerrainsPossede().get(y).setMonopole(true);
				}
			}
		}
		if (bleuf == 2) {
			System.out.println("Vous avez maintenant un monopole pour les terrains de couleur bleu fonc�.");
			for (int y = 0; y < joueur.getTerrainsPossede().size(); y++) {
				if ("BLEU FONCE".equals(joueur.getTerrainsPossede().get(y).getCouleur())) {
					joueur.getTerrainsPossede().get(y).setMonopole(true);
				}
			}
		}
	}

	/**
	 * Verifie si le joueur possede les deux compagnies
	 * @param joueur Joueur en question
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
	 * @param joueur Joueur en question
	 * @throws jeuException
	 */
	public void veutSortirPayer(Joueur joueur) throws jeuException {
		if (!joueur.isEnPrison())
			throw new jeuException("Vous n'etes pas en prison.");
		if (joueur.getArgent() < 50)
			throw new jeuException("Vous n'avez pas assez d'argent.");
		joueur.setArgent(joueur.getArgent() - 50);
		joueur.enPrison(false);
		joueur.setNumCaseActuelleADMIN(10);
	}

	/**
	 * Le joueur est sen prison et veut utiliser sa carte pour sortir
	 * @param joueur Joueur en question
	 * @throws jeuException
	 */
	public void veutSortirCarte(Joueur joueur) throws jeuException {
		if (!joueur.isEnPrison())
			throw new jeuException("Vous n'etes pas en prison.");
		if (!joueur.isLiberation())
			throw new jeuException("Vous n'avez pas de carte de lib�ration.");
		joueur.enPrison(false);
		joueur.setNumCaseActuelleADMIN(10);
		joueur.setLiberation(false);
	}

	/**
	 * Test utile dans AcheterMaison pour respecter une regle du jeu pour les
	 * monopoles � deux terrains
	 * 
	 * @param TA Terrain o� l'on ajoute une maison
	 * @param TB Terrain de la m�me couleur
	 * @return true si le joueur possede les deux terrains de la m�me couleur
	 */
	public boolean maisonT2(CasePlateau TA, CasePlateau TB) {
		return TA.getNbmaison() <= (TB.getNbmaison());
	}

	/**
	 * Test utile dans AcheterMaison pour respecter une regle du jeux pour les
	 * monopoles � trois terrains
	 * 
	 * @param TA Terrain o� l'on ajoute une maison
	 * @param TB Terrain de la m�me couleur
	 * @param TC Terrain de la m�me couleur
	 * @return	true si le joueur possede les trois terrains de la m�me couleur
	 */
	public boolean maisonT3(CasePlateau TA, CasePlateau TB, CasePlateau TC) {
		return (TA.getNbmaison() <= (TB.getNbmaison())) && (TA.getNbmaison() <= (TC.getNbmaison()));
	}

	/**
	 * R�capitule le joueur bri�vement sous la forme d'une chaine de caract�res
	 * @param joueur Joueur en question
	 * @return R�sum� bref du joueur
	 */
	public String toString(Joueur joueur) {
		return "Nom: " + joueur.getNom() + "\nArgent: " + joueur.getArgent() + "\nCase: " + joueur.getNumCaseActuelle();
	}
}
