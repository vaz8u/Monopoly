package parser;

import jeu.CarteComm;
import jeu.Plateau;

public class ParserCarteComm extends Parser {
	static int place;

	public ParserCarteComm(Parser suivant) {
		super(suivant);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parser(String ligne) throws Exception {
		String[] Split = ligne.split(";");
		CarteComm carte = new CarteComm(Split[0], Split[1], Split[2]);
		Plateau.getCommunaute().add(carte);
	}

	@Override
	public boolean saitParser(String ligne) {
		return true;
	}
}