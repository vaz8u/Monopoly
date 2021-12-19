package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jeu.Joueur;
import jeu.jeuException;

public class FenetreTerrain extends Stage {

	public FenetreTerrain(Joueur joueur, int i) {
		try {
			HBox root = new HBox();
			Scene scene = new Scene(root, 300, 200);
			setScene(scene);
			setTitle("Gestion du Terrain");

			VBox vb = new VBox();
			vb.setSpacing(5);
			vb.setPadding(new Insets(10));
			Text nomTerrain = new Text(joueur.getTerrainsPossede().get(i).getNom());
			Text argentJoueur = new Text("Votre argent : " + Integer.toString(joueur.getArgent()));
			Text nbMaison = new Text("Nombre de maisons sur ce terrain : "
					+ Integer.toString(joueur.getTerrainsPossede().get(i).getNbmaison()));
			Text prixMaison = new Text(
					"Prix d'une maison : " + Integer.toString(joueur.getTerrainsPossede().get(i).getPrixMaison()));
			Text prixHotel = new Text(
					"Prix d'un hôtel : " + Integer.toString(joueur.getTerrainsPossede().get(i).getPrixMaison()));
			Text hotel = new Text();
			if (joueur.getTerrainsPossede().get(i).isNbhotel())
				hotel.setText("Il y a un hôtel.");
			else
				hotel.setText("Il n'y a pas d'hôtel.");

			HBox hb = new HBox();
			hb.setSpacing(5);
			hb.setPadding(new Insets(10));
			Button addMaison = new Button("Ajouter une Maison");
			addMaison.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					try {
						joueur.AcheterMaison(joueur.getTerrainsPossede().get(i), joueur);
						nbMaison.setText("Nombre de maisons sur ce terrain : "
								+ Integer.toString(joueur.getTerrainsPossede().get(i).getNbmaison()));
						argentJoueur.setText("Votre argent : " + Integer.toString(joueur.getArgent()));
						Monopoly.setTfPorteMonnaie(Integer.toString(joueur.getArgent()));
						Monopoly.getZoneProprietes().getItems().clear();
						for(int i=0;i<joueur.getTerrainsPossede().size();i++) {
							Monopoly.getZoneProprietes().getItems().add(joueur.getTerrainsPossede().get(i));
						}
					} catch (jeuException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});

			Button addHotel = new Button("Ajouter un Hôtel");
			addHotel.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					try {
						joueur.AcheterHotel(joueur.getTerrainsPossede().get(i), joueur);
						hotel.setText("Il y a un hôtel.");
						argentJoueur.setText("Votre argent : " + Integer.toString(joueur.getArgent()));
						addHotel.setDisable(true);
						Monopoly.setTfPorteMonnaie(Integer.toString(joueur.getArgent()));
						Monopoly.getZoneProprietes().getItems().clear();
						for(int i=0;i<joueur.getTerrainsPossede().size();i++) {
							Monopoly.getZoneProprietes().getItems().add(joueur.getTerrainsPossede().get(i));
						}
					} catch (jeuException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});

			hb.getChildren().addAll(addMaison, addHotel);
			hb.setAlignment(Pos.BOTTOM_LEFT);

			vb.getChildren().addAll(nomTerrain, argentJoueur, nbMaison, hotel, prixMaison, prixHotel, hb);

			root.getChildren().addAll(vb);

			if (joueur.getTerrainsPossede().get(i).getNbmaison() != 4)
				addHotel.setDisable(true);
			if (!(joueur.getTerrainsPossede().get(i).isMonopole())
					|| joueur.getTerrainsPossede().get(i).getNbmaison() >= 4)
				addMaison.setDisable(true);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
