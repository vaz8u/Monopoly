package application.event;

import application.FenetreTerrain;
import application.Monopoly;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EventGererMaison implements EventHandler<ActionEvent> {

	private Monopoly monopoly;
	private FenetreTerrain fenetreTerrain;

	public EventGererMaison(Monopoly monopoly, FenetreTerrain fenetreTerrain) {
		this.monopoly = monopoly;
		this.fenetreTerrain = fenetreTerrain;
	}

	@Override
	public void handle(ActionEvent e) {
		if (monopoly.getTerrainSelectionne() == -1)
			monopoly.DialogAction("Tu n'as selectionne aucun terrain !", true);
		else {
			fenetreTerrain = new FenetreTerrain(monopoly.getJoueurCourant(), monopoly.getTerrainSelectionne());
			fenetreTerrain.show();
		}
	}

}
