package parser;

import jeu.CaseImpots;
import jeu.Plateau;

public class ParserImpot extends Parser {
	static int place;

	public ParserImpot(Parser suivant) {
		super(suivant);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parser(String ligne) throws Exception {
		String[] Split = ligne.split(";");
		place = Integer.parseInt(Split[0]);
		CaseImpots impot = new CaseImpots(Split[1],place);
		Plateau.getPlateau().add(place, impot);
	}

	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("IMPOT SUR LE REVENU");
	}

	public static int Impot() {
		return place;
	}
}
