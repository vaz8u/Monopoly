package parser;

import jeu.CarteChance;
import jeu.Plateau;

public class ParserCarteChance extends Parser {
	static int place;

	public ParserCarteChance(Parser suivant) {
		super(suivant);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parser(String ligne) throws Exception {
		String[] Split = ligne.split(";");
		CarteChance carte = new CarteChance(Split[0], Split[1], Split[2]);
		Plateau.getChance().add(carte);
	}

	@Override
	public boolean saitParser(String ligne) {
		return true;
	}
}