package jeu;
/** Héritière case Plateau
*	Gère la case des impôts
*/
public class CaseImpots extends CasePlateau{
	
	/** Constructeur
	 * @param nom Nom du joueur
	 * @param place emplacement
	 */
	public CaseImpots(String nom, int place) {
		this.setNom(nom);
		this.setPlace(place);
		this.setMessage(" ");
	}
	
	/** Fait payer les impots au joueur
	 * @param joueur Le joueur qui fait l'action
	 */
	@Override
	public void tache(Joueur joueur) {
		joueur.setArgent(joueur.getArgent()-200);
		setMessage("Case impôts: vous perdez 200€.");
	}
}
