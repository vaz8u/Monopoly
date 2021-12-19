package test;

import fichiers.Fichier;
import parser.Parser;
import parser.ParserAllerEnPrison;
import parser.ParserCaisseCommunaute;
import parser.ParserCaseDepart;
import parser.ParserChance;
import parser.ParserCompagnie;
import parser.ParserGare;
import parser.ParserImpot;
import parser.ParserParkingGratuit;
import parser.ParserPrison;
import parser.ParserSimpleVisite;
import parser.ParserTaxeDeLuxe;
import parser.ParserTerrain;

public class TestFichier {

	public static void main(String[] args) {
		try {
			Fichier.lire(null,null);
			System.out.println("Pas cool car j'attends une exception...");
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
		
		try {
			Fichier.lire("Terrains.csv",null);
			System.out.println("Pas cool car j'attends une exception...");
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
		
		
		try {
			// Ce nom de fichier est à adapter à votre projet pour qu'il retrouve votre fichier à vous...
			String nomDuFichier = "Parametre/Terrains.csv";
			Fichier.lire(nomDuFichier, null);
			System.out.println("Wouais...coool...j'arrive à ouvrir Parametre/Terrains.csv");
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
		
		try {
			String nomDuFichier = "Parametre/Terrains.csv";
			
			// Maintenant, à vous de jouer ! 
			
			Parser premierParser = null;
			premierParser = new ParserCaseDepart(premierParser) ;
			premierParser = new ParserGare(premierParser) ;
			premierParser = new ParserTerrain(premierParser) ;
			premierParser = new ParserChance(premierParser) ;
			premierParser = new ParserCaisseCommunaute(premierParser);
			premierParser = new ParserImpot(premierParser);
			premierParser = new ParserSimpleVisite(premierParser);
			premierParser = new ParserParkingGratuit(premierParser);
			premierParser = new ParserAllerEnPrison(premierParser);
			premierParser = new ParserTaxeDeLuxe(premierParser);
			premierParser = new ParserPrison(premierParser);
			premierParser = new ParserCompagnie(premierParser);
			// A vous de créer des parser puis de les chainer les uns aux autres avant d'envoyer
			// le premier à la méthode lire
			
			Fichier.lire(nomDuFichier, premierParser);
			
			System.out.println("Wouais...coool...j'arrive à ouvrir Parametre/Terrains.csv");
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
		
		
	}
	
}
