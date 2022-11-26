package jeu;
/** Héritière case Plateau
*	Gère la case simple visite en prison
*/
public class CaseSimpleVisite extends CasePlateau{
	
	/** Constructeur
	 * @param nom Nom du joueur
	 * @param place emplacement
	 */
	public CaseSimpleVisite(String nom, int place) {
		this.setNom(nom);
		this.setPlace(place);
		this.setMessage(" ");
	}
	
	/** Fait l'action de la case
	 * @param joueur Le joueur qui fait l'action
	 */
	@Override
	public void tache(Joueur joueur) {
		setMessage("Vous visitez la prison.");
	}
}
