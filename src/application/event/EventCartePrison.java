package application.event;

import application.Monopoly;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import jeu.jeuException;


public class EventCartePrison implements EventHandler<ActionEvent> {

	private	Monopoly monopoly;
	
	public EventCartePrison(Monopoly monopoly) {
		this.monopoly = monopoly;
	}

	@Override
	public void handle(ActionEvent e) {
		try {
			monopoly.getJoueurCourant().veutSortirCarte(monopoly.getJoueurCourant());
			monopoly.getUiPlateau().dessiner(monopoly.getGrillePane(), monopoly.getListeJoueurs());
		} catch (jeuException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
