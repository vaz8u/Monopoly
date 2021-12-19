package parser;

import jeu.CaseChance;
import jeu.Plateau;

public class ParserChance extends Parser {
	static int place;

	public ParserChance(Parser suivant) {
		super(suivant);
		// TODO Auto-generated constructor stub
	}

	public void parser(String ligne) throws Exception {
		String[] Split = ligne.split(";");
		place = Integer.parseInt(Split[0]);
		CaseChance enfin = new CaseChance(Split[1], place);
		Plateau.getPlateau().add(place, enfin);
	}

	public boolean saitParser(String ligne) {
		return ligne.contains("CHANCE");
	}

	public static int Chance() {
		return place;

	}
}
