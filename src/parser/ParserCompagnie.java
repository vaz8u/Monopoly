package parser;

import jeu.CaseCompagnie;
import jeu.Plateau;

public class ParserCompagnie extends Parser {
	static int place;

	public ParserCompagnie(Parser suivant) {
		super(suivant);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parser(String ligne) throws Exception {
		String[] Split = ligne.split(";");
		place = Integer.parseInt(Split[0]);
		CaseCompagnie enfin = new CaseCompagnie(Split[2], place);
		Plateau.getPlateau().add(place, enfin);
	}

	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("COMPAGNIE");
	}

	public static int Compagnie() {
		return place;

	}

}
