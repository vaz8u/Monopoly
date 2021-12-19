package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import jeu.Joueur;

public class UIPlateau {
	
	private static final int NOMBRE_CASES = 40;
	final static String PLATEAU = "Plateau2.jpg";
	final static String COORDONNEES = "CoordonneesCases.csv";

	private HashMap<Integer, UICase> cases = new HashMap<Integer, UICase>();
	private	Image	imagePlateau = null;
	private	HashMap<String, Image>	imagesPions = new HashMap<String, Image>();

	
	public UIPlateau(ArrayList<Pion> listePions) {
		
		for (int i=0;i<41;i++)
			cases.put(i, new UICase());
		
		initImagePlateau(PLATEAU);
		initCoordonnees(COORDONNEES);
		initImagesPions();
	}
	


	private void initImagesPions() {
		imagesPions.put("Chien",new Image(getClass().getResourceAsStream("Chien.png")));
		imagesPions.put("Bateau",new Image(getClass().getResourceAsStream("Bateau.png")));
		imagesPions.put("Brouette",new Image(getClass().getResourceAsStream("Brouette.png")));
		imagesPions.put("Chapeau",new Image(getClass().getResourceAsStream("Chapeau.png")));
		imagesPions.put("Chat",new Image(getClass().getResourceAsStream("Chat.png")));
		imagesPions.put("Chaussure",new Image(getClass().getResourceAsStream("Chaussure.png")));
		imagesPions.put("DeACoudre",new Image(getClass().getResourceAsStream("DeACoudre.png")));
		imagesPions.put("Voiture",new Image(getClass().getResourceAsStream("Voiture.png")));
	}



	public Image getImage() {
		return imagePlateau;
	}


	private void initImagePlateau(String nomFichierPlateau) {
		imagePlateau = new Image(getClass().getResourceAsStream(nomFichierPlateau));
	}
	
	public UICase getCase(int numCase) {
		if (numCase<0 || numCase>NOMBRE_CASES)
			throw new IllegalArgumentException("Le numero de la case est incorrect");
		
		return cases.get(numCase);
	}

	/**
	 * initCase lit un fichier au format .csv. Une ligne doit avoir le format "n;x1;y1;x2;y2;" oÃ¹ n,x1,y1,x2,y2 
	 * sont des entiers. En cas de non respect de ce format, le programme est interrompu.
	 * @param nf nom du fichier contenant les coordonnÃ©es des cases du plateau. Ces coordonnÃ©es
	 * sont celles pour le plateau 800x800 pixels
	 * 
	 * YL : --> A remplacer avec vos parser !!! Ã§a c'est moche et Ã§a doit disparaitre !!!
	 */
	private void initCoordonnees(String nf) {
		BufferedReader f = null;
		String ligne;

		f = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(nf)));
				
		try {
			while ((ligne = f.readLine()) != null) {
				parserCoordonnees(ligne);
			}
			
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	private static final String REGEX_COORDONNEES = "[0-9]+(;[0-9]+){4}";

	
	/**
	 * La mÃ©thode parserCoordonnÃ©es vÃ©rifie que la ligne Ã  parser respecte le bon format. Sinon arrÃªt
	 * du programme en lanÃ§ant une exception du type Error
	 * @param ligne La ligne Ã  parser
	 */
	private void parserCoordonnees(String ligne) {
		if (! Pattern.matches(REGEX_COORDONNEES, ligne))
			throw new Error("Format des coordonnees non respecte");
		

		// Tout est ok pour parser
		int numCase, x1,y1,x2,y2;
		
		String[] mots = ligne.split(";");
		numCase = Integer.parseInt(mots[0]);
		x1 = Integer.parseInt(mots[1]);
		y1 = Integer.parseInt(mots[2]);
		x2 = Integer.parseInt(mots[3]);
		y2 = Integer.parseInt(mots[4]);
		
		UICase c = cases.get(numCase);
		c.setCoordonnees(x1, y1, x2, y2);
	}

	 /**
     * Cette méthode dessiner dessine le plateau et les pions des joueurs présents dans la partie. 
     * Notez qu'au début de la méthode, on vide toutes les UICases. En d'autres termes, les pions disparaissent !
     * Ensuite, pour les dessiner à nouveau, il faudra que vous alliez rechercher dans vos classes métiers, les joueurs
     * encore présents, et repositionner leur pion dans la bonne case. Cette partie est à votre charge car elle dépend de
     * vos classes métier. La dernière boucle for redessine tous les pions comme il faut en fonction des coordonnées des
     * cases du plateau. Il n'y a rien à modifier dans cette partie 
     */
	public void dessiner(Canvas grillePane,ArrayList<Joueur> j) {
		for (int i = 0; i <= NOMBRE_CASES; i++)
			cases.get(i).vider();
     
             // Parcourez ici la liste des joueurs, trouver leur pion, puis ajouter le pion dans la UICase ou se trouve
             // le joueur
     
		for(int i=0;i<j.size();i++) {
			cases.get(j.get(i).getNumCaseActuelle()).poser(j.get(i).getPion());
		}
		
     	grillePane.getGraphicsContext2D().drawImage(imagePlateau,0,0);
     	for (int i = 0; i <= NOMBRE_CASES; i++) {
         	UICase c = cases.get(i);
         	for (int p=0; p<c.getNombrePion();p++) {
             	Image imagePion = imagesPions.get(c.getListePions().get(p).getNom());
             	grillePane.getGraphicsContext2D().drawImage(imagePion,c.x1+30*(p%2),c.y1+30*(p/2));                
         	}
     	}
 	}
}
