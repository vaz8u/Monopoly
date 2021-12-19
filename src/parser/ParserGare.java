package parser;

import jeu.CaseGare;
import jeu.Plateau;

public class ParserGare extends Parser {
	static int place;

	public ParserGare(Parser suivant) {
		super(suivant);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parser(String ligne) throws Exception {
		String[] Split = ligne.split(";");
		place = Integer.parseInt(Split[0]);
		CaseGare gare = new CaseGare(Split[2],place);
		Plateau.getPlateau().add(place, gare);
	}

	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("GARE");
	}

	public static int Gare() {
		return place;

	}
}
