package jeu;
/** Héritière case Plateau
*	Gère la case de la prison
*/
public class CasePrison extends CasePlateau {	

	/** Constructeur
	 * @param nom Nom du joueur
	 * @param place emplacement
	 */
	public CasePrison(String nom, int place) {
		this.setNom(nom);
		this.setPlace(place);
		this.setMessage(" ");
	}
	
	/** Sortie de prison du joueur, déjà géré dans EventJouer
	 * @param joueur Le joueur qui fait l'action
	 * @param des entrÃ© necessaire pour les doubles
	 */
	public void sortiePrison(Joueur joueur, Des des) {
		int temps=0;
		if(des.estDouble(des.getDes1(), des.getDes2()))
			joueur.enPrison(false);
		else if(temps==3)
			joueur.enPrison(false);
		else
			temps++;
		}		
	
	/** Envoie le joueur en prison
	 * @param joueur Le joueur qui fait l'action
	 * @throws jeuException
	 */
	@Override
	public void tache(Joueur joueur) throws jeuException {
		joueur.enPrison(true);
		setMessage("Direction prison !");
	}
}