package application.event;

import application.Monopoly;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import jeu.Joueur;

public class EventChoixJoueur implements EventHandler<ActionEvent> {

	private Monopoly monopoly;
	
	
	public EventChoixJoueur(Monopoly monopoly) {
		this.monopoly = monopoly;
	}
	
	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() instanceof ToggleButton) {
			ToggleButton b = (ToggleButton)e.getSource();
			Joueur j = (Joueur) b.getUserData();
			
			monopoly.setJoueurCourant(j);
			System.out.println(monopoly.getJoueurCourant().getNom()+" doit jouer");
			Monopoly.setTfPorteMonnaie(Integer.toString(monopoly.getJoueurCourant().getArgent()));
			
			Monopoly.getZoneProprietes().getItems().clear();
			for(int i=0;i<monopoly.getJoueurCourant().getTerrainsPossede().size();i++) {
				Monopoly.getZoneProprietes().getItems().add(monopoly.getJoueurCourant().getTerrainsPossede().get(i));
			}
		}
	}

}
