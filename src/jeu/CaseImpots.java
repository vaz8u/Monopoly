package jeu;
/** H�riti�re case Plateau
*	G�re la case des imp�ts
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
		setMessage("Case imp�ts: vous perdez 200�.");
	}
}
