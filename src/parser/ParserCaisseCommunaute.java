package parser;

import jeu.CaseCaisseComm;
import jeu.Plateau;

public class ParserCaisseCommunaute extends Parser {
	static int place;

	public ParserCaisseCommunaute(Parser suivant) {
		super(suivant);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parser(String ligne) throws Exception {
		String[] Split = ligne.split(";");
		place = Integer.parseInt(Split[0]);
		CaseCaisseComm enfin = new CaseCaisseComm(Split[1], place);
		Plateau.getPlateau().add(place, enfin);
	}

	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("CAISSE COMMUNAUTE");
	}

	public static int CaisseCommunaute() {
		return place;

	}
}
