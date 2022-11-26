package jeu;
/** Héritière case Plateau
*	Gère les cases caisse de communauté
*/
public class CaseCaisseComm extends CasePlateau{
	
	/** Constructeur
	 * @param nom Nom du joueur
	 * @param place emplacement
	 */
	public CaseCaisseComm(String nom, int place) {
		this.setNom(nom);
		this.setPlace(place);
		this.setMessage(" ");
	}
	
	/** Tire une carte caisse de la communauté et appele cette carte dans la classe CarteComm
	 * @param joueur Le joueur qui fait l'action
	 * @throws jeuException
	 */
	@Override
	public void tache(Joueur joueur) throws jeuException {
		setMessage("On tire une carte Caisse de la communauté.");
		CarteComm.tacheCarte(joueur, Plateau.getCommunaute() ,Plateau.getParticipants());
		setMessage(getMessage()+" "+Plateau.getCommunaute().lastElement().getPhrase());
	}
}
