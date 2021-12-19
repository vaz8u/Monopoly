package parser;

import jeu.CaseParkingGratuit;
import jeu.Plateau;

public class ParserParkingGratuit extends Parser {
	static int place;

	public ParserParkingGratuit(Parser suivant) {
		super(suivant);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parser(String ligne) throws Exception {
		String[] Split = ligne.split(";");
		place = Integer.parseInt(Split[0]);
		CaseParkingGratuit enfin = new CaseParkingGratuit(Split[1], place);
		Plateau.getPlateau().add(place, enfin);
	}

	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("PARKING GRATUIT");
	}

	public static int ParkingGratuit() {
		return place;

	}

}
