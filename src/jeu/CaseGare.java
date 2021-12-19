package jeu;
/** H�riti�re case Plateau
*	G�re le cases gare
*/
public class CaseGare extends CasePlateau{
	
	/** Constructeur
	 * @param nom Nom du joueur
	 * @param place emplacement
	 */
	public CaseGare(String nom, int place) {
		this.setNom(nom);
		this.setPlace(place);
		this.setEstAchete(false);
		this.setProprietaire(null);
		this.setValeurTerrain(200);
		this.setCouleur("GARE");
		this.setMessage(" ");
		this.setPrixLoyer(25);
	}
	
	/** G�re le loyer de la gare selon le nombre de gares poss�d�s par le Joueur joueur
	 * @param Joueur Le joueur qui fait l'action
	 * @throws jeuException
	 */
	@Override
	public void tache(Joueur joueur) throws jeuException {
		if(isEstAchete() && (getProprietaire()!=joueur)) {
			if(getProprietaire().getGares()==1)
				setPrixLoyer(50);
			if(getProprietaire().getGares()==2)
				setPrixLoyer(100);
			if(getProprietaire().getGares()==3)
				setPrixLoyer(150);
			if(getProprietaire().getGares()==4)
				setPrixLoyer(200);
			setMessage("La "+getNom()+"est possed�e par "+getProprietaire().getNom()+", payez "+getPrixLoyer()+"�.");
			if(joueur.getArgent()-getPrixLoyer()<1)
				throw new jeuException("Le joueur "+joueur.getNom()+" n'a plus d'argent !");
			joueur.setArgent(joueur.getArgent()-getPrixLoyer());
			getProprietaire().setArgent(getPrixLoyer() + getProprietaire().getArgent());
		}
		else if(isEstAchete() && (getProprietaire()==joueur))
			setMessage(getNom()+" c'est chez vous.");
		else
			setMessage(getNom()+" n'est � personne, vous pouvez l'acheter.");
	}
}
