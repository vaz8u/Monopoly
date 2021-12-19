package application.event;

import application.Monopoly;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import jeu.Des;
import jeu.Plateau;
import jeu.jeuException;

public class EventJouer implements EventHandler<ActionEvent> {

	private Monopoly monopoly;

	public EventJouer(Monopoly monopoly) {
		this.monopoly = monopoly;
	}

	@Override
	public void handle(ActionEvent event) {
		monopoly.getMessageFooter().setText(" ");
		int de1, de2;
		// si on ne tape aucun dés, on fait un aléatoire, sinon on prends les dés tapés
		// et si seulement l'un des deux est tapé ->error
		String tfDe1 = monopoly.getTfValeurDe1().getText();
		String tfDe2 = monopoly.getTfValeurDe2().getText();
		if (monopoly.getTfValeurDe1().getText().isEmpty() && monopoly.getTfValeurDe2().getText().isEmpty()) {
			Des des = new Des();
			de1 = des.getDes1();
			de2 = des.getDes2();
			monopoly.getTfValeurDe1().setText(String.valueOf(de1));
			monopoly.getTfValeurDe2().setText(String.valueOf(de2));
		} else {

			de1 = Integer.parseInt(tfDe1);
			de2 = Integer.parseInt(tfDe2);
		}
		monopoly.getTfValeurDe1().clear();
		monopoly.getTfValeurDe2().clear();
		int nbCases = de1 + de2;

		System.out.println("d1=" + de1 + "  d2=" + de2 + "  nb cases=" + nbCases);

		if (de1 == de2) {
			int nbDbl = monopoly.getNbDoubles();

			nbDbl++;
			monopoly.setNbDoubles(nbDbl);
			if (nbDbl == 1) {
				monopoly.getMessageFooter().setText("C'est ton premier double !");
				monopoly.getJoueurCourant().enPrison(false);
			} else if (nbDbl == 2)
				monopoly.getMessageFooter().setText("C'est ton deuxieme double !! Encore un et c'est la taule...");
			else {
				monopoly.getMessageFooter().setText("Police, menottes, prison...");
				try {
					monopoly.getJoueurCourant().enPrison(true);
					monopoly.getJoueurCourant().setNumCaseActuelleADMIN(40);
					monopoly.getUiPlateau().dessiner(monopoly.getGrillePane(), monopoly.getListeJoueurs());

				} catch (jeuException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					monopoly.setNbDoubles(0);
				} catch (Exception e) {
					monopoly.DialogAction(e.getMessage(), true);
				}
			}
		} else {
			monopoly.setNbDoubles(0);
		}

		try {
			if (monopoly.getJoueurCourant().isEnPrison() == false) {

				monopoly.getJoueurCourant().setNumCaseActuelle(nbCases, monopoly.getJoueurCourant());
				monopoly.getJoueurCourant().getPion().setPosition(monopoly.getJoueurCourant().getNumCaseActuelle());
				Plateau.getPlateau().get(monopoly.getJoueurCourant().getNumCaseActuelle())
						.tache(monopoly.getJoueurCourant());
				if (monopoly.getJoueurCourant().getNumCaseActuelle() == 30) {
					monopoly.getJoueurCourant().enPrison(true);
					monopoly.getJoueurCourant().setNumCaseActuelleADMIN(40);
					monopoly.getMessageFooter().setText(
							"Allez en prison ! Vous ne pouvez plus jouer. Payer, sortez votre carte de sortie ou faites un double pour sortir.");
				}
				Monopoly.setTfPorteMonnaie(Integer.toString(monopoly.getJoueurCourant().getArgent()));
				monopoly.getUiPlateau().dessiner(monopoly.getGrillePane(), monopoly.getListeJoueurs());
				if (monopoly.getMessageFooter().getText().isEmpty())
					monopoly.getMessageFooter().setText(
							Plateau.getPlateau().get(monopoly.getJoueurCourant().getNumCaseActuelle()).getMessage());
				else
					monopoly.getMessageFooter().setText(monopoly.getMessageFooter().getText() + " "
							+ Plateau.getPlateau().get(monopoly.getJoueurCourant().getNumCaseActuelle()).getMessage());
			} else
				monopoly.getMessageFooter().setText(monopoly.getMessageFooter().getText() + " "
						+ "Vous êtes en prison, vous ne pouvez pas jouer. Payer, sortez votre carte de sortie ou faites un double pour sortir.");
		} catch (jeuException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}