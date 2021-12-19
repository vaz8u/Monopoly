package jeu;
/** Héritière case Plateau
*	Gère les terrains achetables
*/
public class Terrain extends CasePlateau {

	/** Constructeur
	 * 
	 * @param nom
	 * @param numCase
	 * @param couleur
	 * @param estAchete
	 * @param tabLoyer
	 * @param prixMaison
	 * @param valeurTerrain
	 */
	public Terrain(String nom, int numCase, String couleur, boolean estAchete, int[] tabLoyer, int prixMaison,
			int valeurTerrain) {
		this.setNom(nom);
		this.setPlace(numCase);
		this.setCouleur(couleur);
		this.setEstAchete(estAchete);
		this.setTabLoyer(tabLoyer);
		this.setPrixMaison(prixMaison);
		this.setValeurTerrain(valeurTerrain);
		this.setPrixLoyer(tabLoyer[0]);
		this.setProprietaire(null);
		this.setMonopole(false);
		this.setNbmaison(0);
		this.setNbhotel(false);
		this.setMessage(" ");
	}

	/** Fait l'action d'une case terrain, achetable ou non, paye le loyer
	 * @param Joueur Le joueur qui fait l'action
	 * @throws jeuException
	 */
	@Override
	public void tache(Joueur joueur) throws jeuException {
		if (isEstAchete() && (getProprietaire() != joueur)) {
			if (isMonopole() == true && getNbmaison() == 0) {
				if (joueur.getArgent() - getPrixLoyer() < 1)
					throw new jeuException("Le joueur " + joueur.getNom() + " n'a plus d'argent pour payer!");
				joueur.setArgent(joueur.getArgent() - getPrixLoyer() * 2);
				getProprietaire().setArgent(getPrixLoyer() * 2 + getProprietaire().getArgent());
				setMessage("Vous êtes sur " + getNom() + " appartenu par " + getProprietaire().getNom()
						+ " avec un monopole, vous payez " + getPrixLoyer() * 2 + "€.");
			} else {
				if (joueur.getArgent() - getPrixLoyer() < 1)
					throw new jeuException("Le joueur " + joueur.getNom() + " n'a plus d'argent pour payer!");
				joueur.setArgent(joueur.getArgent() - getPrixLoyer());
				getProprietaire().setArgent(getPrixLoyer() + getProprietaire().getArgent());
				setMessage("Vous êtes sur " + getNom() + " appartenu par " + getProprietaire().getNom()
						+ ", vous payez " + getPrixLoyer() + "€.");
			}

		}
		if (isEstAchete() && (getProprietaire() == joueur))
			setMessage("Vous êtes chez vous.");
		else
			setMessage("Vous êtes sur " + getNom() + ", vous pouvez l'acheter pour " + getValeurTerrain() + "€.");
	}

}
