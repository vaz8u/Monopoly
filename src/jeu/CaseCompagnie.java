package jeu;
/** H�riti�re case Plateau
*	G�re les cases compagnie
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
	
	/** G�re le loyer de la case selon le nombre de compagnies poss�d�s par le Joueur joueur.
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
			setMessage("La "+getNom()+"est possed�e par "+getProprietaire().getNom()+", payez "+getPrixLoyer()+"�.");
			if(getPrixLoyer()<8) 
				throw new jeuException("Le loyer de la compagnie n'est pas bien calcul�.");
			if(joueur.getArgent()-getPrixLoyer()<1)
				throw new jeuException("Le joueur "+joueur.getNom()+" n'a plus d'argent !");
			joueur.setArgent(joueur.getArgent()-getPrixLoyer());
			getProprietaire().setArgent(getPrixLoyer() + getProprietaire().getArgent());
		}
		setMessage("La "+getNom()+" n'est � personne, achetez-la pour "+getValeurTerrain()+"�.");
	}

}
