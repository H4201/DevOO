package com.h4201.prototype.test;

import java.io.File;
import java.util.Vector;

import com.h4201.prototype.controleur.Controleur;
import com.h4201.prototype.modele.AppGraphe;
import com.h4201.prototype.modele.Chemin;
import com.h4201.prototype.modele.CreationDemandeLivraison;
import com.h4201.prototype.modele.CreationPlan;
import com.h4201.prototype.modele.Tournee;
import static org.junit.Assert.*;

public class TestTournee
{
	public TestTournee()
	{
		System.out.println("Les tests commencent !");
		
		this.executerTests();
		
		System.out.println("Les tests sont termines");
	}
	
	public void executerTests()
	{
		try
		{
			// Chargement et calcul
			CreationPlan.depuisXML(new File("test/plan20x20.xml"));			
			CreationDemandeLivraison.depuisXML(new File("test/livraison20x20-2.xml"));
			AppGraphe.getInstance().genererTournee();
			
			
			Vector<Chemin> chemins = Tournee.getInstance().getChemins();
			
			System.out.println("\nNombre de chemins : " + chemins.size());
			
			for(Chemin chemin : chemins)
			{
				System.out.println(chemin.getPointLivraisonOrigine().toString());
				System.out.println(chemin.getPointLivraisonDestination().toString());
				assertTrue(chemin.getTroncons().size() > 0);
				System.out.println("Nombre de troncons : " + chemin.getTroncons().size());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
