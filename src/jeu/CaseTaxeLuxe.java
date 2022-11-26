package jeu;
/** Héritière case Plateau
*	Gère la case taxe de luxe
*/
public class CaseTaxeLuxe extends CasePlateau{
	
	/** Constructeur
	 * @param nom Nom du joueur
	 * @param place emplacement
	 */
	public CaseTaxeLuxe(String nom, int place) {
		this.setNom(nom);
		this.setPlace(place);
		this.setMessage(" ");
	}
	
	/** Fait l'action de la case
	 * @param joueur Le joueur qui fait l'action
	 */
	@Override
	public void tache(Joueur joueur) {
		joueur.setArgent(joueur.getArgent()-100);
		setMessage("Case taxe de luxe : vous payez 100€.");
	}
}
