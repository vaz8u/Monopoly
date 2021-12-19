package parser;

import jeu.CaseTaxeLuxe;
import jeu.Plateau;

public class ParserTaxeDeLuxe extends Parser {
	static int place;

	public ParserTaxeDeLuxe(Parser suivant) {
		super(suivant);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parser(String ligne) throws Exception {
		String[] Split = ligne.split(";");
		place = Integer.parseInt(Split[0]);
		CaseTaxeLuxe enfin = new CaseTaxeLuxe(Split[1], place);
		Plateau.getPlateau().add(place, enfin);
	}

	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("TAXE DE LUXE");
		
	}

	public static int TaxedeLuxe() {
		return place;

	}

}
