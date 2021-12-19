package parser;

import jeu.CaseSimpleVisite;
import jeu.Plateau;

public class ParserSimpleVisite extends Parser {
	static int place;

	public ParserSimpleVisite(Parser suivant) {
		super(suivant);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parser(String ligne) throws Exception {
		String[] Split = ligne.split(";");
		place = Integer.parseInt(Split[0]);
		CaseSimpleVisite enfin = new CaseSimpleVisite(Split[1], place);
		Plateau.getPlateau().add(place, enfin);
	}

	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("SIMPLE VISITE");
	}

	public static int CaisseCommunaute() {
		return place;

	}

}
