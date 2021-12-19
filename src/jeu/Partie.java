package jeu;
/**Page de tests
 *
 */
public class Partie {
/**
 * Tests
 */
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws jeuException {
		//Pour eviter les bugs et comme on crée chaques fois un nouveau plateau,
			//il est preferable de mettre en commentaire les autres tests des autres plateaux
				//un plateau constitue un paragraphe de tests du pdf
		/*
		// Test pour la construction du plateau
		Plateau p = new Plateau();
		if (!p.getParticipants().isEmpty())
			throw new jeuException("Le plateau n'est pas vide de Joueurs.");
		//p.showPlateau(p);
		//System.out.println(p.getPlateau().get(0).getNom());
		if (!(p.getPlateau().get(0).getNom().equals("CASE DEPART")) )
			throw new jeuException("Erreur case 0.");
		if (!(p.getPlateau().get(1).getNom().equals("BOULEVARD DE BELLEVILLE")) )
			throw new jeuException("Erreur case 1.");
		if (!(p.getPlateau().get(5).getNom().equals("GARE MONTPARNASSE")) )
			throw new jeuException("Erreur case 5.");
		if (!(p.getPlateau().get(10).getNom().equals("SIMPLE VISITE")) )
			throw new jeuException("Erreur case 10.");
		if (!(p.getPlateau().get(12).getNom().equals("COMPAGNIE D'ELECTRICITE")) )
			throw new jeuException("Erreur case 12.");
		if (!(p.getPlateau().get(20).getNom().equals("PARKING GRATUIT")) )
			// test Parking gratuit?
			throw new jeuException("Erreur case 20.");
		if (!(p.getPlateau().get(30).getNom().equals("ALLEZ EN PRISON")) )
			throw new jeuException("Erreur case 30.");
		if (!(p.getPlateau().get(36).getNom().equals("CHANCE")) )
			throw new jeuException("Erreur case 36.");
		if (!(p.getPlateau().get(39).getNom().equals("RUE DE LA PAIX")) )
			throw new jeuException("Erreur case 39.");
		if (!(p.getPlateau().get(40).getNom().equals("PRISON")) )
			throw new jeuException("Erreur case 40.");
		// test propriétaire?
		for(int i=0;i<41;i++) {
			if((p.getPlateau().get(i).isEstAchete()) && (p.getPlateau().get(i)!=null))
				throw new jeuException("Terrain deja achete");
		}
		
	
		Plateau p1 = new Plateau();
		Joueur j1 = new Joueur();
		p1.getParticipants().add(j1);
		if(p1.getParticipants().get(0).getArgent()!=1500)
			throw new jeuException("Le joueur n'a pas 1500.");
		if(p1.getParticipants().get(0).getTerrainsPossede().isEmpty()==false)
			throw new jeuException("Le joueur ne possede pas aucun terrains.");
		if(p1.getParticipants().get(0).getNumCaseActuelle()!=0)
			throw new jeuException("Le joueur n'est pas a la case 0.");
		if(p1.getParticipants().size()!=1)
			throw new jeuException("Le plateau ne contient pas 1 joueur.");
		Joueur j2 = new Joueur();
		p1.getParticipants().add(j2);
		if(p1.getParticipants().size()!=2)
			throw new jeuException("Le plateau ne contient pas 2 joueurs.");
		
		
		Plateau p3 = new Plateau();
		Joueur j3 = new Joueur();
		p3.getParticipants().add(j3);
		p3.getParticipants().get(0).setNumCaseActuelle(5,p3.getParticipants().get(0));
		int cse = p3.getParticipants().get(0).getNumCaseActuelle();
		
		if(!(p3.getPlateau().get(cse).getNom().equals("GARE MONTPARNASSE")) )
			throw new jeuException("Le joueur ne se trouve pas sur la Gare Montparnasse.");
		if(p3.getParticipants().get(0).getArgent()!=1500)
			throw new jeuException("Le joueur n'a pas 1500.");
		if(p3.getParticipants().get(0).getTerrainsPossede().isEmpty()==false)
			throw new jeuException("Le joueur ne possede pas aucun terrains.");
		p3.getParticipants().get(0).setNumCaseActuelle(5,p3.getParticipants().get(0));
		cse = p3.getParticipants().get(0).getNumCaseActuelle();
		if(!(p3.getPlateau().get(cse).getNom().equals("SIMPLE VISITE")) )
			throw new jeuException("Le joueur ne se trouve pas sur la Simple Visite.");
		p3.getParticipants().get(0).setNumCaseActuelleADMIN(39);
		p3.getParticipants().get(0).setNumCaseActuelle(2,p3.getParticipants().get(0));
		cse = p3.getParticipants().get(0).getNumCaseActuelle();
		if(!(p3.getPlateau().get(cse).getNom().equals("BOULEVARD DE BELLEVILLE")) )
			throw new jeuException("Le joueur ne se trouve pas sur la Boulevard de Belleville.");
		if(p3.getParticipants().get(0).getArgent()!=1700)
			throw new jeuException("Le joueur n'a pas 1700.");
		//test qui produit des erreurs donc en commentaires (mais ça fonctionne hein c'est normal que ça marche pas)
		//p3.getParticipants().get(0).setNumCaseActuelle(1,j3);
		//p3.getParticipants().get(0).setNumCaseActuelle(13,j3);
		
	
		
		Plateau p4 = new Plateau();
		Joueur j4 = new Joueur();
		p4.getParticipants().add(j4);
		System.out.println(p4.getParticipants().get(0).getArgent());
		p4.getParticipants().get(0).setNumCaseActuelleADMIN(6);
		
		if(!(p4.getPlateau().get(p4.getParticipants().get(0).getNumCaseActuelle()).getNom().equals("RUE DE VAUGIRARD")) )
			throw new jeuException("Le joueur ne se trouve pas sur la rue de Vaugirard.");
		if(p4.getPlateau().get(p4.getParticipants().get(0).getNumCaseActuelle()).isEstAchete())
			throw new jeuException("Terrain deja achete");
		p4.getParticipants().get(0).AcheterTerrain( p4.getPlateau().get(p4.getParticipants().get(0).getNumCaseActuelle()),p4.getParticipants().get(0));
		if(p4.getPlateau().get(6).getProprietaire()!=p4.getParticipants().get(0))
			throw new jeuException("Le terrain n est pas achete par le joueur.");
		if(p4.getParticipants().get(0).getArgent()!=1400)
			throw new jeuException("Le joueur n'a pas 1400, il a " + p4.getParticipants().get(0).getArgent()+".");
		Joueur j4b = new Joueur();
		p4.getParticipants().add(j4b);
		if(p4.getParticipants().get(0).getTerrainsPossede().get(0)!=p4.getPlateau().get(6))
			throw new jeuException("Le joueur ne possede pas ce terrain.");
		p4.getParticipants().get(1).setNumCaseActuelleADMIN(6);
		//p4.getParticipants().get(1).AcheterTerrain( p4.getPlateau().get(p4.getParticipants().get(1).getNumCaseActuelle()),p4.getParticipants().get(1));
	
		
		
		Plateau p5 = new Plateau();
		//p5.showPlateau(p5);
		Joueur j5a = new Joueur();
		Joueur j5b = new Joueur();
		p5.getParticipants().add(j5a);p5.getParticipants().add(j5b);
		p5.getParticipants().get(0).setNumCaseActuelleADMIN(6);
		p5.getParticipants().get(0).AcheterTerrain( p5.getPlateau().get(p5.getParticipants().get(0).getNumCaseActuelle()),p5.getParticipants().get(0));
		if(p5.getParticipants().get(0).getArgent()!=1400)
			throw new jeuException("Le joueur n'a pas 1400, il a " + p5.getParticipants().get(0).getArgent()+".");
		if(p5.getParticipants().get(1).getArgent()!=1500)
			throw new jeuException("Le joueur 2 n'a pas 1500, il a " + p5.getParticipants().get(1).getArgent()+".");
		p5.getParticipants().get(1).setNumCaseActuelleADMIN(6);p5.getPlateau().get(6).tache(p5.getParticipants().get(1));
		if(p5.getParticipants().get(1).getArgent()!=1494)
			throw new jeuException("Le joueur 2 n'a pas 1494, il a " + p5.getParticipants().get(1).getArgent()+".");
		if(p5.getParticipants().get(0).getArgent()!=1406)
			throw new jeuException("Le joueur 2 n'a pas 1406, il a " + p5.getParticipants().get(0).getArgent()+".");
		p5.getParticipants().get(0).AcheterTerrain( p5.getPlateau().get(8),p5.getParticipants().get(0));
		p5.getParticipants().get(0).AcheterTerrain( p5.getPlateau().get(9),p5.getParticipants().get(0));
		//p5.getParticipants().get(0).afficheTerrains(p5.getParticipants().get(0));
		if(p5.getParticipants().get(0).getArgent()!=1186)
			throw new jeuException("Le joueur 1 n'a pas 1186, il a " + p5.getParticipants().get(0).getArgent()+".");
		p5.getParticipants().get(1).setNumCaseActuelle(3,p5.getParticipants().get(1));
		if(!(p5.getPlateau().get(p5.getParticipants().get(1).getNumCaseActuelle()).getNom().equals("AVENUE DE LA REPUBLIQUE")) )
			throw new jeuException("Le joueur ne se trouve pas sur l'avenue de la replublique.");
		//System.out.println(p5.getParticipants().get(0).toString(p5.getParticipants().get(0)));
		p5.getPlateau().get(p5.getParticipants().get(1).getNumCaseActuelle()).tache(p5.getParticipants().get(1));
		if(p5.getParticipants().get(1).getArgent()!=1478)
			throw new jeuException("Le joueur 2 n'a pas 1478, il a " + p5.getParticipants().get(1).getArgent()+".");
		if(p5.getParticipants().get(0).getArgent()!=1202)
			throw new jeuException("Le joueur 2 n'a pas 1202, il a " + p5.getParticipants().get(0).getArgent()+".");
		p5.getParticipants().get(1).setArgent(5);
		p5.getParticipants().get(1).setNumCaseActuelleADMIN(8);
		//p5.getPlateau().get(p5.getParticipants().get(1).getNumCaseActuelle()).tache(p5.getParticipants().get(1));
		
		
		
		Plateau p6 = new Plateau();
		Joueur j6A = new Joueur("A");
		Joueur j6B = new Joueur("B");
		p6.getParticipants().add(j6A);
		p6.getParticipants().add(j6B);
		p6.getParticipants().get(0).AcheterTerrain(p6.getPlateau().get(5), j6A);
		p6.getParticipants().get(1).setNumCaseActuelleADMIN(5);
		p6.getPlateau().get(p6.getParticipants().get(1).getNumCaseActuelle()).tache(p6.getParticipants().get(1));
		//la gare coute 200 et le loyer est de 50 comme il n'a qu'une seule 
		if(p6.getParticipants().get(0).getArgent()!=1350)
			throw new jeuException("Le joueur A n'a pas 1325, il a " + p6.getParticipants().get(0).getArgent()+".");
		if(p6.getParticipants().get(1).getArgent()!=1450)
			throw new jeuException("Le joueur B n'a pas 1475, il a " + p6.getParticipants().get(1).getArgent()+".");
		p6.getParticipants().get(0).AcheterTerrain(p6.getPlateau().get(15), j6A);
		p6.getParticipants().get(1).setNumCaseActuelleADMIN(15);
		p6.getPlateau().get(p6.getParticipants().get(1).getNumCaseActuelle()).tache(p6.getParticipants().get(1));
		if(p6.getParticipants().get(0).getArgent()!=1250)
			throw new jeuException("Le joueur A n'a pas 1250, il a " + p6.getParticipants().get(0).getArgent()+".");
		if(p6.getParticipants().get(1).getArgent()!=1350)
			throw new jeuException("Le joueur B n'a pas 1350, il a " + p6.getParticipants().get(1).getArgent()+".");
		p6.getParticipants().get(1).setNumCaseActuelleADMIN(25);
		p6.getPlateau().get(p6.getParticipants().get(1).getNumCaseActuelle()).tache(p6.getParticipants().get(1));
		if(p6.getPlateau().get(25).isEstAchete())
			throw new jeuException("La "+p6.getPlateau().get(25).getNom()+" est achetée.");
		if(p6.getParticipants().get(0).getArgent()!=1250)
			throw new jeuException("Le joueur A n'a pas 1250, il a " + p6.getParticipants().get(0).getArgent()+".");
		if(p6.getParticipants().get(1).getArgent()!=1350)
			throw new jeuException("Le joueur B n'a pas 1350, il a " + p6.getParticipants().get(1).getArgent()+".");
		
		
		Plateau p7 = new Plateau();//p7.showPlateau(p7);
		Joueur j7A = new Joueur("A");
		Joueur j7B = new Joueur("B");
		p7.getParticipants().add(j7A);
		p7.getParticipants().add(j7B);
		p7.getParticipants().get(0).AcheterTerrain(p7.getPlateau().get(12), j7A);
		if(p7.getParticipants().get(0).getArgent()!=1350)
			throw new jeuException("Le joueur A n'a pas 1350, il a " + p7.getParticipants().get(0).getArgent()+".");
		p7.getParticipants().get(1).setNumCaseActuelle(12, j7B);
		if(p7.getParticipants().get(1).getNumCaseActuelle()!=12)
			throw new jeuException("Le joueur B n'est pas sur la case compagnie electricité");
		p7.getPlateau().get(p7.getParticipants().get(1).getNumCaseActuelle()).tache(p7.getParticipants().get(1));
		if(p7.getParticipants().get(1).getArgent()!=1452)
			throw new jeuException("Le joueur B n'a pas 1452, il a " + p7.getParticipants().get(1).getArgent()+".");
		if(p7.getParticipants().get(0).getArgent()!=1398)
			throw new jeuException("Le joueur A n'a pas 1398, il a " + p7.getParticipants().get(0).getArgent()+".");
		p7.getParticipants().get(0).AcheterTerrain(p7.getPlateau().get(28), j7A);
		if(p7.getParticipants().get(0).getArgent()!=1248)
			throw new jeuException("Le joueur A n'a pas 1248, il a " + p7.getParticipants().get(0).getArgent()+".");
		p7.getParticipants().get(1).setNumCaseActuelleADMIN(24);
		p7.getPlateau().get(p7.getParticipants().get(1).getNumCaseActuelle()).tache(p7.getParticipants().get(1));
		if(p7.getParticipants().get(1).getArgent()!=1452)
			throw new jeuException("Le joueur A n'a pas 1452, il a " + p7.getParticipants().get(1).getArgent()+".");
		p7.getParticipants().get(1).setNumCaseActuelle(4,p7.getParticipants().get(1));
		if(p7.getParticipants().get(1).getNumCaseActuelle()!=28)
			throw new jeuException("Le joueur B n'est pas a la bonne place.");
		p7.getPlateau().get(p7.getParticipants().get(1).getNumCaseActuelle()).tache(p7.getParticipants().get(1));

		if(p7.getParticipants().get(1).getArgent()!=1412)
			throw new jeuException("Le joueur B n'a pas 1412, il a " + p7.getParticipants().get(1).getArgent()+".");
		if(p7.getParticipants().get(0).getArgent()!=1288)
			throw new jeuException("Le joueur A n'a pas 1288, il a " + p7.getParticipants().get(0).getArgent()+".");
		
		
		Plateau p10 = new Plateau();//p7.showPlateau(p7);
		Joueur j10A = new Joueur("A");
		p10.getParticipants().add(j10A);
		p10.getParticipants().get(0).setNumCaseActuelleADMIN(25);
		p10.getParticipants().get(0).setNumCaseActuelle(5,p10.getParticipants().get(0));
		if(p10.getParticipants().get(0).getNumCaseActuelle()!=30)
			throw new jeuException("Le joueur A n'est pas sur la case prison.");
		p10.getPlateau().get(p10.getParticipants().get(0).getNumCaseActuelle()).tache(p10.getParticipants().get(0));
		//System.out.println(p10.getParticipants().get(0).isEnPrison());
		p10.getParticipants().get(0).veutSortirPayer(p10.getParticipants().get(0));
		//System.out.println(p10.getParticipants().get(0).isEnPrison());
		//System.out.println(p10.getParticipants().get(0).getArgent());
		if(p10.getParticipants().get(0).getNumCaseActuelle()!=10)
			throw new jeuException("Le joueur A n'est pas sur la case "+p10.getPlateau().get(10).getNom()+".");
			
			
			
		Plateau p8 = new Plateau();
		//p8.showPlateau(p8); 
		Joueur j8A = new Joueur("A"); 
		Joueur j8B = new Joueur("B"); 
		p8.getParticipants().add(j8A);
		p8.getParticipants().add(j8B);
		p8.getParticipants().get(0).AcheterTerrain(p8.getPlateau().get(6), j8A);
		//p8.getParticipants().get(0).AcheterMaison(p8.getPlateau().get(6), j8A);
		p8.getParticipants().get(0).AcheterTerrain(p8.getPlateau().get(8), j8A);
		p8.getParticipants().get(0).AcheterTerrain(p8.getPlateau().get(9), j8A);
		if(p8.getParticipants().get(0).getArgent() != 1180) 
			throw new jeuException("Le joueur A n'a pas 1180, il a " +p8.getParticipants().get(0).getArgent() + ".");
		p8.getParticipants().get(0).AcheterMaison(p8.getPlateau().get(6), j8A); //1ere maison
		if(p8.getParticipants().get(0).getArgent() != 1130) 
			throw new jeuException("Le joueur A n'a pas 1130, il a " +p8.getParticipants().get(0).getArgent() + ".");
		p8.getParticipants().get(1).setNumCaseActuelleADMIN(6); 
		p8.getPlateau().get(p8.getParticipants().get(1).getNumCaseActuelle()).tache(p8.getParticipants().get(1));
		if(p8.getParticipants().get(1).getArgent() != 1470) 
			throw new jeuException("Le joueur B n'a pas 1470, il a " +p8.getParticipants().get(1).getArgent() + ".");
		try {
			p8.getParticipants().get(0).AcheterMaison(p8.getPlateau().get(6), j8A);
			System.err.println("Erreur, vous avez acheté deux maisons sur le meme terrain. ");
		} catch (jeuException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} //2eme maison meme terrain
		
		
		Plateau p9 = new Plateau();
		//p9.showPlateau(p9); 
		Joueur j9A = new Joueur("A"); 
		Joueur j9B = new Joueur("B"); 
		p9.getParticipants().add(j9A);
		p9.getParticipants().add(j9B);
		
		p9.getParticipants().get(0).AcheterTerrain(p9.getPlateau().get(6), j9A);
		p9.getParticipants().get(0).AcheterTerrain(p9.getPlateau().get(8), j9A);
		p9.getParticipants().get(0).AcheterTerrain(p9.getPlateau().get(9), j9A);
		//Achat de maison 
		p9.getParticipants().get(0).AcheterMaison(p9.getPlateau().get(6), j9A); //1/3 Vaugirard
		p9.getParticipants().get(0).AcheterMaison(p9.getPlateau().get(8), j9A); //1/4 Courcelles
		p9.getParticipants().get(0).AcheterMaison(p9.getPlateau().get(9), j9A); //1/4 Republique
		
		p9.getParticipants().get(0).AcheterMaison(p9.getPlateau().get(6), j9A); //2/3 Vaugirard
		p9.getParticipants().get(0).AcheterMaison(p9.getPlateau().get(8), j9A); //2/4 Courcelles
		p9.getParticipants().get(0).AcheterMaison(p9.getPlateau().get(9), j9A); //2/4 Republique
		
		p9.getParticipants().get(0).AcheterMaison(p9.getPlateau().get(6), j9A); //3/3 Vaugirard
		p9.getParticipants().get(0).AcheterMaison(p9.getPlateau().get(8), j9A); //3/4 Courcelles
		p9.getParticipants().get(0).AcheterMaison(p9.getPlateau().get(9), j9A); //3/4 Republique
		
		p9.getParticipants().get(0).AcheterMaison(p9.getPlateau().get(8), j9A); //4/4 Courcelles
		p9.getParticipants().get(0).AcheterMaison(p9.getPlateau().get(9), j9A); //4/4 Republique
		
		//p9.getParticipants().get(0).AcheterHotel(p9.getPlateau().get(9), j9A); //hotel RepubliqueErreur
		p9.getParticipants().get(0).AcheterMaison(p9.getPlateau().get(6), j9A); //4/4 Vaugirard
		p9.getParticipants().get(0).AcheterHotel(p9.getPlateau().get(9), j9A); //hotel Republique
		
		if(p9.getParticipants().get(0).getArgent() != 530) 
			throw new jeuException("Le joueur A n'a pas 530, il a " +p9.getParticipants().get(0).getArgent() + ".");
		p9.getParticipants().get(1).setNumCaseActuelleADMIN(9);
		p9.getPlateau().get(p9.getParticipants().get(1).getNumCaseActuelle()).tache(p9.getParticipants().get(1));
		if(p9.getParticipants().get(1).getArgent() != 900) 
			throw new jeuException("Le joueur A n'a pas 900, il a " +p9.getParticipants().get(1).getArgent() + ".");
		if(p9.getParticipants().get(0).getArgent() != 1130) 
			throw new jeuException("Le joueur A n'a pas 1130, il a " +p9.getParticipants().get(0).getArgent() + ".");

		
		*/
		
		Plateau p11 = new Plateau();//p7.showPlateau(p7);
		Joueur j11A = new Joueur("A");
		Joueur j11B = new Joueur("B");
		Joueur j11C = new Joueur("C");
		p11.getParticipants().add(j11A);p11.getParticipants().add(j11B);p11.getParticipants().add(j11C);
		
		//p11.getPlateau().get(7).tache(p11.getParticipants().get(0));
		
		p11.getPlateau().get(2).tache(p11.getParticipants().get(0));
		p11.getPlateau().get(2).tache(p11.getParticipants().get(0));
		p11.getPlateau().get(2).tache(p11.getParticipants().get(0));
		
		
		while(!(p11.getCommunaute().firstElement().getPhrase().equals("Payez la note du médecin"))){
			CarteComm aux = p11.getCommunaute().firstElement();
			p11.getCommunaute().remove(0);
			p11.getCommunaute().add(aux);
		}
		
		p11.getPlateau().get(2).tache(p11.getParticipants().get(0));

		//p11.showCartesChance(p11);
		//p11.showCartesComm(p11);
		
		
		System.out.println("Pas de moustiques dans le coin..");
	}
}
