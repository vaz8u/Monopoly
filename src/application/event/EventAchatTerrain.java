package application.event;

import application.Monopoly;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import jeu.Plateau;
import jeu.jeuException;

public class EventAchatTerrain implements EventHandler<ActionEvent> {

	private	Monopoly	monopoly;
	
	public EventAchatTerrain(Monopoly monopoly) {
		this.monopoly = monopoly;
	}

	@Override
	public void handle(ActionEvent e) {
		try {
			monopoly.getJoueurCourant().AcheterTerrain(Plateau.getPlateau().get(monopoly.getJoueurCourant().getNumCaseActuelle()), monopoly.getJoueurCourant());
			Monopoly.setTfPorteMonnaie(Integer.toString(monopoly.getJoueurCourant().getArgent()));
			Monopoly.getZoneProprietes().getItems().add(Plateau.getPlateau().get(monopoly.getJoueurCourant().getNumCaseActuelle()));
		} catch (jeuException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
