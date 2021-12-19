package parser;

import jeu.CaseDepart;
import jeu.CasePlateau;
import jeu.Plateau;

public class ParserCaseDepart extends Parser {
	static int place;

	public ParserCaseDepart(Parser suivant) {
		super(suivant);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parser(String ligne) throws Exception {
		String[] Split = ligne.split(";");
		place = Integer.parseInt(Split[0]);
		CasePlateau c = new CaseDepart(Split[1], place);
		Plateau.getPlateau().add(place, c);
	}

	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("CASE DEPART");
	}

	public static int PlaceDepart() {
		return place;

	}

}