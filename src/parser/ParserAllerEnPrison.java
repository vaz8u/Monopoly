package parser;

import jeu.CaseAllerEnPrison;
import jeu.Plateau;

public class ParserAllerEnPrison extends Parser {
	static int place;

	public ParserAllerEnPrison(Parser suivant) {
		super(suivant);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parser(String ligne) throws Exception {
		String[] Split = ligne.split(";");
		place = Integer.parseInt(Split[0]);
		CaseAllerEnPrison p = new CaseAllerEnPrison(Split[1],place);
		Plateau.getPlateau().add(place, p);
	}

	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("ALLEZ EN PRISON");
	}

	public void PlaceNumero() {
		
	}
	
	public static int PlacePrison() {
		return place;

	}

}