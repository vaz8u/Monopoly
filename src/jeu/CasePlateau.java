package jeu;

/** Toutes les cases dans le fichier csv deviennent des CasePlateau
 *
 */
public abstract class CasePlateau { 
	/** Nom de la case
	 * 
	 */
	private String nom; 					
	/** Sa place sur le plateau
	 * 
	 */
	private int place; 						
	
	/** Si c'est un terrain, couleur du terrain
	 * 
	 */
	private String couleur; 				
	/** Si c'est un terrain, contient le prix de 1, 2, 3, 4 maisons ou avec hotel
	 * 
	 */
	private int[] tabLoyer = new int[5]; 	
	/** Si c'est un terrain, prix d'une nouvelle maison
	 * 
	 */
	private int prixMaison; 				
	/** Si c'est un terrain, prix du loyer du terrain
	 * 
	 */
	private int prixLoyer; 					
	/** Si c'est un terrain, nombre de maisons actuelles sur le terrain
	 * 
	 */
	private int Nbmaison; 					
	/** Si c'est un terrain, =vrai si monopole, false sinon
	 * 
	 */
	private boolean monopole; 				
	/** Si c'est un terrain, =vrai si il y a un hôtel, false sinon
	 * 
	 */
	private boolean Nbhotel;				
	
	/** Si c'est une case achetable, prix pour l'acheter
	 * 
	 */
	private int valeurTerrain; 				
	/** Si c'est une case achetable, c'est le joueur qui est propriétaire
	 * 
	 */
	private Joueur proprietaire; 			
	/** Si c'est une case achetable (terrain,gare,compagnie), sait si elle est déjà achetée
	 * 
	 */
	private boolean EstAchete; 				
	
	/** Message pour le joueur qui tombe sur la case, s'affiche sur le footer de l'interface graphique
	 * 
	 */
	private String message;					
	

	/** Toutes les cases ont une tache quand un joueur tombe dessus, peut ne rien faire
	 * 
	 * @param joueur
	 * @throws jeuException
	 */
	public abstract void tache(Joueur joueur) throws jeuException;

	// Getters et setters
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public boolean isEstAchete() {
		return EstAchete;
	}

	public void setEstAchete(boolean estAchete) {
		EstAchete = estAchete;
	}

	public int[] getTabLoyer() {
		return tabLoyer;
	}

	public void setTabLoyer(int[] tabLoyer) {
		this.tabLoyer = tabLoyer;
	}

	public int getPrixMaison() {
		return prixMaison;
	}

	public void setPrixMaison(int prixMaison) {
		this.prixMaison = prixMaison;
	}

	public int getValeurTerrain() {
		return valeurTerrain;
	}

	public void setValeurTerrain(int valeurTerrain) {
		this.valeurTerrain = valeurTerrain;
	}

	public int getPrixLoyer() {
		return prixLoyer;
	}

	public void setPrixLoyer(int prixLoyer) {
		this.prixLoyer = prixLoyer;
	}

	public Joueur getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(Joueur proprietaire) {
		this.proprietaire = proprietaire;
	}

	public boolean isMonopole() {
		return monopole;
	}

	public void setMonopole(boolean monopole) {
		this.monopole = monopole;
	}

	public int getNbmaison() {
		return Nbmaison;
	}

	public void setNbmaison(int Nbmaison) {
		this.Nbmaison = Nbmaison;
	}

	public boolean isNbhotel() {
		return Nbhotel;
	}

	public void setNbhotel(boolean Nbhotel) {
		this.Nbhotel = Nbhotel;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}

	/** Retourne une chaine de caractère qui montre un terrain achetable, 
	 * 	affiché dans l'interface graphique dans liste propriétaires
	 */
	@Override
	public String toString() {
		String x = this.nom + "\nLoyer : " + this.getPrixLoyer();
		if (this.getCouleur() == "COMP" || this.getCouleur() == "GARE")
			x = this.nom;
		if (!(this.getCouleur().isEmpty() || this.getCouleur() == "GARE" || this.getCouleur() == "COMP"))
			x = x + "€\nMaisons : " + this.getNbmaison();
		if (this.isNbhotel())
			x = x + "\nAvec Hôtel";
		if (this.isMonopole())
			x = x + "\nMonopolisé";
		return x;
	}

}
