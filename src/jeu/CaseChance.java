package jeu;
/** Héritière case Plateau
*	Gère les cases case chance
*/


public class CaseChance extends CasePlateau{
	
	/** Constructeur
	 * @param nom Nom du joueur
	 * @param place emplacement
	 */
	public CaseChance(String nom, int place) {
		this.setNom(nom);
		this.setPlace(place);
		this.setMessage(" ");
	}
	
	/** Tire une carte chance et appele cette carte dans la classe CarteChance
	 * @param joueur Le joueur qui fait l'action
	 * @throws jeuException
	 */
	@Override
	public void tache(Joueur joueur) throws jeuException {
		setMessage("On tire une carte chance.");
		CarteChance.tacheCarte(joueur,Plateau.getChance(), Plateau.getParticipants());
		setMessage(getMessage()+" "+Plateau.getChance().lastElement().getPhrase());
	}
}
