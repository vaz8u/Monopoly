package parser;

import jeu.Plateau;
import jeu.CasePrison;

public class ParserPrison extends Parser {

	static int place;

	public ParserPrison(Parser suivant) {
		super(suivant);
		// TODO Auto-generated constructor stub
	}

	public void parser(String ligne) throws Exception {
		String[] Split = ligne.split(";");
		place = Integer.parseInt(Split[0]);
		CasePrison p = new CasePrison(Split[1],place);
		Plateau.getPlateau().add(place, p);

	}

	public boolean saitParser(String ligne) {
		return ligne.contains("PRISON");
	}

	public void PlaceNumero() {
		
	}

	public static int PlacePrison() {
		return place;

	}
}