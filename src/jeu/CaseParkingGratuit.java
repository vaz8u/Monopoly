package jeu;
/** H�riti�re case Plateau
*	G�re la case du parking gratuit
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
	
	
	/** Ne fait rien car il n'y a rien � faire sur cette case
	 * @param Joueur Le joueur qui fait l'action
	 * @throws jeuException
	 */
	@Override
	public void tache(Joueur joueur) throws jeuException {
		setMessage(joueur.getNom()+" n'a rien � faire, il est sur le parking gratuit.");
	}

}
