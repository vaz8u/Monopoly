package jeu;
/** Héritière case Plateau
*	Gère la case du parking gratuit
*/
public class CaseParkingGratuit extends CasePlateau{

	/** Constructeur
	 * @param nom Nom du joueur
	 * @param place emplacement
	 */
	public CaseParkingGratuit(String nom, int place) {
		this.setNom(nom);
		this.setPlace(place);
		this.setMessage(" ");
	}
	
	
	/** Ne fais rien, car il n'y a rien à faire sur cette case
	 * @param joueur Le joueur qui fait l'action
	 * @throws jeuException
	 */
	@Override
	public void tache(Joueur joueur) throws jeuException {
		setMessage(joueur.getNom()+" n'a rien à faire, il est sur le parking gratuit.");
	}

}
