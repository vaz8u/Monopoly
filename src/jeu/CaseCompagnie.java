package jeu;
/** Héritière case Plateau
*	Gère les cases compagnie
*/
public class CaseCompagnie extends CasePlateau{

	/** Constructeur
	 * @param nom Nom du joueur
	 * @param place emplacement
	 */
	public CaseCompagnie(String nom, int place) {
		this.setNom(nom);
		this.setPlace(place);
		this.setValeurTerrain(150);
		this.setPrixLoyer(0);
		this.setProprietaire(null);
		this.setCouleur("COMP");
		this.setMessage(" ");
	}
	
	/** Gère le loyer de la case selon le nombre de compagnies possédés par le Joueur joueur.
	 * @param joueur Le joueur qui fait l'action
	 * @throws jeuException
	 */
	@Override
	public void tache(Joueur joueur) throws jeuException {
		
		int loyer=4;
		if(isEstAchete() && (getProprietaire()!=joueur)) {
			if(getProprietaire().isServicePublic()) 
				loyer=10;
			setPrixLoyer(joueur.getDeplacement()*loyer);
			setMessage("La "+getNom()+"est possedée par "+getProprietaire().getNom()+", payez "+getPrixLoyer()+"€.");
			if(getPrixLoyer()<8) 
				throw new jeuException("Le loyer de la compagnie n'est pas bien calculé.");
			if(joueur.getArgent()-getPrixLoyer()<1)
				throw new jeuException("Le joueur "+joueur.getNom()+" n'a plus d'argent !");
			joueur.setArgent(joueur.getArgent()-getPrixLoyer());
			getProprietaire().setArgent(getPrixLoyer() + getProprietaire().getArgent());
		}
		setMessage("La "+getNom()+" n'est à personne, achetez-la pour "+getValeurTerrain()+"€.");
	}

}
