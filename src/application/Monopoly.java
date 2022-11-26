package application;

import java.util.ArrayList;

import application.event.EventAchatTerrain;
import application.event.EventCartePrison;
import application.event.EventChoixJoueur;
import application.event.EventGererMaison;
import application.event.EventJouer;
import application.event.EventPasser;
import application.event.EventPayerPrison;
import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jeu.CasePlateau;
import jeu.Joueur;
import jeu.Plateau;
import ui.Pion;
import ui.UIPlateau;
/**
 * Classe principale de l'application Monopoly
 */
public class Monopoly extends Application {

	public final static String ACTION_ACHAT_TERRAIN = "Acheter le terrain";
	public final static String ACTION_GERER_MAISON = "Gerer les maisons et les hotels";
	public final static String ACTION_PAYER_PRISON = "Payer pour sortir de prison";
	public final static String ACTION_LIBERATION = "Utiliser une carte de liberation";
	public final static String ACTION_PASSER = "Passer au suivant";
	public final static String ACTION_JOUER = "Lancer les des";

	/**
	 * Liste de bouttons des joueurs
	 */
	private ArrayList<ToggleButton> tabBoutonsJoueurs = new ArrayList<ToggleButton>();

	private UIPlateau uiPlateau;
	private Canvas grillePane;
	private Button bAvancer;
	private TextField tfDe1;
	private TextField tfDe2;
	private Label messageFooter;


	private static ListView<CasePlateau> proprietesJoueurCourant;
	
	private ArrayList<Joueur> listeJoueurs = new ArrayList<Joueur>();
	private ArrayList<Pion> listePions = new ArrayList<Pion>();
	private Joueur joueurCourant;
	
	private FenetreTerrain fenetreTerrain;

	private int terrainSelectionne = -1;
	private static TextField tfPorteMonnaie;

	private int nbDoubles = 0;

	public ArrayList<Pion> getListePions() {
		return listePions;
	}

	public void setListePions(ArrayList<Pion> listePions) {
		this.listePions = listePions;
	}

	public static ListView<CasePlateau> getZoneProprietes() {
		return proprietesJoueurCourant;
	}

	public ArrayList<ToggleButton> getTabBoutonsJoueurs() {
		return tabBoutonsJoueurs;
	}

	public ArrayList<Joueur> getListeJoueurs() {
		return listeJoueurs;
	}

	public TextField getTfValeurDe1() {
		return tfDe1;
	}

	public TextField getTfValeurDe2() {
		return tfDe2;
	}

	public TextField getTfPorteMonnaie() {
		return tfPorteMonnaie;
	}

	public UIPlateau getUiPlateau() {
		return uiPlateau;
	}

	public Canvas getGrillePane() {
		return grillePane;
	}

	/**
	 * Affichage fenetre de la partie
	 * @param primaryStage Stage
	 */
	@Override
	public void start(Stage primaryStage) {
		try {

			initPartie();
			
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);

			initPanneauPlateau(root);

			initPanneauDroit(root);
			initFooter(root);
			
			uiPlateau.dessiner(grillePane, getListeJoueurs());
			primaryStage.setTitle("MONOPOLY");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialisation de la partie droite de l'application
	 * @param root BorderPane
	 */
	private void initPanneauDroit(BorderPane root) {
		VBox panneauDroit = new VBox();
		panneauDroit.setAlignment(Pos.TOP_CENTER);

		initBoutonsJoueurs(panneauDroit);
		initDes(panneauDroit);
		initActions(panneauDroit);
		initZonePropriete(panneauDroit);

		tabBoutonsJoueurs.get(0).fire();

		root.setRight(panneauDroit);
	}

	/**
	 * Initialisation de la partie basse de l'application
	 * @param root BorderPane
	 */
	private void initFooter(BorderPane root) {
		HBox footer = new HBox();
		footer.setAlignment(Pos.BASELINE_LEFT);

		messageFooter = new Label("");
		footer.getChildren().add(messageFooter);

		root.setBottom(footer);
	}

	/**
	 * Initialisation de la partie proprieté de l'application
	 * @param panneauDroit VBox
	 */
	private void initZonePropriete(VBox panneauDroit) {
		panneauDroit.getChildren().add(new Label(" "));

		HBox hb = new HBox();
		hb.getChildren().add(new Label("Porte monnaie : "));
		tfPorteMonnaie = new TextField("0");
		tfPorteMonnaie.setEditable(false);
		hb.getChildren().add(tfPorteMonnaie);

		panneauDroit.getChildren().add(hb);

		panneauDroit.getChildren().add(new Label(" "));
		panneauDroit.getChildren().add(new Label("Liste des proprietaires :"));

		proprietesJoueurCourant = new ListView<CasePlateau>();
		proprietesJoueurCourant.setPrefHeight(0);

		proprietesJoueurCourant.getItems().addListener(new ListChangeListener<CasePlateau>() {
			@Override
			public void onChanged(Change<? extends CasePlateau> arg0) {
				proprietesJoueurCourant.setPrefHeight(proprietesJoueurCourant.getItems().size() * 60);
			}
		});

		proprietesJoueurCourant.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				terrainSelectionne = proprietesJoueurCourant.getSelectionModel().getSelectedIndex();
				System.out.println("Item : " + proprietesJoueurCourant.getSelectionModel().getSelectedIndex());
			}
		});

		panneauDroit.getChildren().add(proprietesJoueurCourant);
	}

	/**
	 * Initialisation des boutons d'action de l'application
	 * @param panneauDroit VBox
	 */
	private void initActions(VBox panneauDroit) {
		VBox box = new VBox();

		bAvancer = new Button(ACTION_JOUER);
		bAvancer.setOnAction(new EventJouer(this));
		box.getChildren().add(bAvancer);

		Button bPasser = new Button(ACTION_PASSER);
		bPasser.setOnAction(new EventPasser(this));
		box.getChildren().add(bPasser);

		Button acheterTerrain = new Button(ACTION_ACHAT_TERRAIN);
		acheterTerrain.setOnAction(new EventAchatTerrain(this));
		box.getChildren().add(acheterTerrain);

		Button gererMaisons = new Button(ACTION_GERER_MAISON);
		gererMaisons.setOnAction(new EventGererMaison(this,this.getFenetreTerrain()));
		box.getChildren().add(gererMaisons);

		Button payerPrison = new Button(ACTION_PAYER_PRISON);
		payerPrison.setOnAction(new EventPayerPrison(this));
		box.getChildren().add(payerPrison);

		Button liberation = new Button(ACTION_LIBERATION);
		liberation.setOnAction(new EventCartePrison(this));
		box.getChildren().add(liberation);

		panneauDroit.getChildren().add(box);
	}

	/**
	 * Initialisation des dés de l'application
	 * @param panneau VBox
	 */
	private void initDes(VBox panneau) {
		Label des = new Label("Dés :");
		tfDe1 = new TextField();
		tfDe1.setPrefColumnCount(2);
		tfDe2 = new TextField();
		tfDe2.setPrefColumnCount(2);
		HBox hb = new HBox();

		hb.getChildren().addAll(des, tfDe1, tfDe2);
		hb.setSpacing(2);

		panneau.getChildren().add(hb);
	}

	/**
	 * Initialisation des boutons joueurs de l'application
	 * @param panneau VBox
	 */
	private void initBoutonsJoueurs(VBox panneau) {
		ToggleGroup group = new ToggleGroup();

		HBox box = new HBox();
		box.setMouseTransparent(true);

		for (Joueur listeJoueur : listeJoueurs) {
			ToggleButton bJoueur = new ToggleButton(listeJoueur.getNom());
			bJoueur.setToggleGroup(group);
			bJoueur.setOnAction(new EventChoixJoueur(this));
			bJoueur.setUserData(listeJoueur);

			box.getChildren().add(bJoueur);
			tabBoutonsJoueurs.add(bJoueur);
		}
		panneau.getChildren().add(box);
	}

	/**
	 * Initialisation de la partie centrale de l'application
	 * @param root BorderPane
	 */
	private void initPanneauPlateau(BorderPane root) {
		Image image = uiPlateau.getImage();
		grillePane = new Canvas(image.getWidth(), image.getHeight());
		root.setCenter(grillePane);
	}

	/**
	 * Création du plateau de jeu
	 */
	public Plateau p = new Plateau(listeJoueurs);

	/**
	 * Initialisation de la partie
	 */
	private void initPartie() {
//todo
		listeJoueurs.add(new Joueur("Han"));
		listePions.add(new Pion("Bateau"));
		listeJoueurs.get(0).setPion(listePions.get(0));

		listeJoueurs.add(new Joueur("Luke"));
		listePions.add(new Pion("Chien"));
		listeJoueurs.get(1).setPion(listePions.get(1));

		listeJoueurs.add(new Joueur("Yoda"));
		listePions.add(new Pion("Voiture"));
		listeJoueurs.get(2).setPion(listePions.get(2));

		uiPlateau = new UIPlateau(listePions);
	}

	/**
	 * Lancement de l'initialisation
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Fenetre de dialogue pour une action
	 * @param message Message à afficher
	 * @param erreur Vrai si action impossible, faux sinon
	 */
	public void DialogAction(String message, boolean erreur) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Achat d'un terrain");
		alert.setContentText(message);

		if (erreur) {
			alert.setHeaderText("Tu ne peux pas faire cette action !");
		} else {
			alert.setAlertType(AlertType.INFORMATION);
			alert.setHeaderText("Achat effectué");
		}

		alert.showAndWait();
	}

	/**
	 * Initialisation de la boite dialogue
	 * @param message Message à afficher
	 */
	public void DialogInfo(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("");
		alert.setContentText(message);
		alert.showAndWait();
	}

	public int getNbDoubles() {
		return nbDoubles;
	}

	public void setNbDoubles(int nbDoubles) {
		this.nbDoubles = nbDoubles;
	}

	public Label getMessageFooter() {
		return messageFooter;
	}

	public int getTerrainSelectionne() {
		return terrainSelectionne;
	}

	public FenetreTerrain getFenetreTerrain() {
		return fenetreTerrain;
	}

	public Joueur getJoueurCourant() {
		return joueurCourant;
	}

	public void setJoueurCourant(Joueur j) {
		joueurCourant = j;
	}


	public static void setTfPorteMonnaie(String i) {
		tfPorteMonnaie.setText(i);
	}

}
