package jeu;

import java.util.Random;
/** Simule un lanc� de d�s
 */
public class Des { 
	/** Premier d�
	 * 
	 */
	private int des1;
	/** Deuxi�me d�
	 * 
	 */
	private int des2;

	/** Constructeur
	 * 
	 */
	public Des() {
		Random random = new Random();
		this.des1 = random.nextInt(6) + 1;
		this.des2 = random.nextInt(6) + 1;
	}

	/** Somme des d�s pour le d�placement
	 * 
	 * @param des1 Premier d�
	 * @param des2 Deuxi�me d�
	 * @return la somme des deux entr�es
	 */
	public int sommeDes(int des1, int des2) {
		return des1 + des2;
	}

	/** Si c'est un double, pour la prison et rejouer
	 * 
	 * @param des1 Premier d�
	 * @param des2 Deuxi�me d�
	 * @return vrai si des1==des2, faux sinon
	 */
	public boolean estDouble(int des1, int des2) {
		return des1 == des2;
	}

	// Getters et setters
	public int getDes1() {
		return des1;
	}

	public void setDes1(int des1) {
		this.des1 = des1;
	}

	public int getDes2() {
		return des2;
	}

	public void setDes2(int des2) {
		this.des2 = des2;
	}

}
