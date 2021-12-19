package jeu;
/** Héritière case Plateau
*	Gère la case départ
*/
public class CaseDepart extends CasePlateau {

	/** Constructeur
	 * @param nom Nom du joueur
	 * @param place emplacement
	 */
	public CaseDepart(String nom, int place) {
		this.setNom(nom);
		this.setPlace(place);
		this.setMessage(" ");
	}
	
	/** Ne fait rien, le fait d'ajouter 200€ se fait sur dans la classe joueur quand il sort des limites du plateau.
	 * @param Joueur Le joueur qui fait l'action
	 */
	@Override
	public void tache(Joueur joueur) {
		setMessage("Vous etes sur la case depart");
	}
}