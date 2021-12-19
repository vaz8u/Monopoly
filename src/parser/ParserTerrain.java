package parser;

import jeu.Terrain;
import jeu.Plateau;

public class ParserTerrain extends Parser {

    public ParserTerrain(Parser suivant) {
        super(suivant);
        // TODO Auto-generated constructor stub
    }


    

    @Override
    public void parser(String ligne) throws Exception {
        String[] Split = ligne.split(";");
    	int [] tabLoyer = new int[6];
        int numT=Integer.parseInt(Split[0]);
        String couleur=Split[2];
        String nomT=Split[3];
        int valeur=Integer.parseInt(Split[4]);
        int maison=Integer.parseInt(Split[5]);
        tabLoyer[0]=Integer.parseInt(Split[6]);
        tabLoyer[1]=Integer.parseInt(Split[7]);
        tabLoyer[2]=Integer.parseInt(Split[8]);
        tabLoyer[3]=Integer.parseInt(Split[9]);
        tabLoyer[4]=Integer.parseInt(Split[10]);
        tabLoyer[5]=Integer.parseInt(Split[11]);
        Terrain terrain = new Terrain(nomT, numT, couleur, false, tabLoyer, maison, valeur);
        Plateau.getPlateau().add(numT, terrain);
    }

    @Override
    public boolean saitParser(String ligne) {
        return ligne.contains("TERRAIN CONSTRUCTIBLE");
    }
}
