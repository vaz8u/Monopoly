package application.event;

import application.Monopoly;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import jeu.jeuException;


public class EventPayerPrison implements EventHandler<ActionEvent> {

	private	Monopoly monopoly;
	
	public EventPayerPrison(Monopoly monopoly) {
		this.monopoly = monopoly;
	}

	@Override
	public void handle(ActionEvent e) {
		try {
			monopoly.getJoueurCourant().veutSortirPayer(monopoly.getJoueurCourant());
			monopoly.getUiPlateau().dessiner(monopoly.getGrillePane(), monopoly.getListeJoueurs());
			Monopoly.setTfPorteMonnaie(Integer.toString(monopoly.getJoueurCourant().getArgent()));
		} catch (jeuException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
