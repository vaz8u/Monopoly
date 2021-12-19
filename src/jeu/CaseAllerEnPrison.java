package jeu;
/** Héritière case Plateau
*	Gère la case aller en prison
*/
public class CaseAllerEnPrison extends CasePlateau{

	/** Constructeur
	 * @param nom Nom du joueur
	 * @param place emplacement
	 */
	public CaseAllerEnPrison(String nom, int place) {
		this.setNom(nom);
		this.setPlace(place);
		this.setMessage(" ");
	}
	
	/** Envoie le joueur en prison
	 * @param Joueur Le joueur qui fait l'action
	 */
	@Override
	public void tache(Joueur joueur) {
		setMessage("Le joueur "+joueur.getNom()+" va en prison!");
		try {
			joueur.enPrison(true);
			joueur.setNumCaseActuelleADMIN(40);
		} catch (jeuException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
