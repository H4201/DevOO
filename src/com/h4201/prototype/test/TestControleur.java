package com.h4201.prototype.test;

import java.io.File;

import com.h4201.prototype.controleur.Controleur;
import com.h4201.prototype.modele.Noeud;
import com.h4201.prototype.modele.Plan;
import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.modele.Tournee;
import com.h4201.prototype.modele.TrancheHoraire;
import com.h4201.prototype.utilitaire.Constante;
import com.h4201.prototype.vue.VueSupervision;

import static org.junit.Assert.*;


public class TestControleur
{
	public TestControleur()
	{
		
	}
	
	public void executerTests()
	{
		System.out.println("Les tests du controleur commencent !");
		
		try
		{
			Controleur controleur = Controleur.getInstance();
			
			// Ouvrir la fenetre
			VueSupervision.getInstance();
			
			// Chargement des donnees
			controleur.chargerPlan(new File("test/plan20x20.xml"));
			
			controleur.chargerDemandeLivraison(new File("test/livraison20x20-2.xml"));
			
			// Notification du mode
			assertEquals(controleur.getMode(), Constante.MODE_NORMAL);
			controleur.notifierClicAjouter();
			assertEquals(controleur.getMode(), Constante.MODE_AJOUT);
			controleur.notifierClicSupprimer();
			assertEquals(controleur.getMode(), Constante.MODE_SUPPRESSION);
			controleur.notifierClicNormal();
			assertEquals(controleur.getMode(), Constante.MODE_NORMAL);
			
			// Calcul de la tournee
//			controleur.calculTournee();
//			assertTrue(!Tournee.getInstance().getChemins().isEmpty());
			
			// Ajout d'un point de livraison
			Noeud noeudTmp = Plan.getInstance().getNoeudDepuisIdNoeud(Integer.valueOf(0));
			TrancheHoraire trancheHoraireTmp = Tournee.getInstance().getTranchesHoraire().get(0);
			int nbNoeuds = trancheHoraireTmp.getPointsLivraisons().size();
			controleur.ajoutPointLivraison(noeudTmp, trancheHoraireTmp);
			assertEquals(nbNoeuds+1, trancheHoraireTmp.getPointsLivraisons().size());
			
			// Suppression d'un point de livraison
			int tailleTrancheHoraireTmp = Tournee.getInstance().getTranchesHoraire().get(0).getPointsLivraisons().size();
			PointLivraison pointLivraisonTmp = Tournee.getInstance().getTranchesHoraire().get(0).getPointsLivraisons().get(0);
			controleur.supprimerPointLivraison(pointLivraisonTmp);
			assertTrue(pointLivraisonTmp.getIdPointLivraison() !=
					Tournee.getInstance().getTranchesHoraire().get(0)
						.getPointsLivraisons().get(0).getIdPointLivraison());
			
			// Annuler
			controleur.annuler();
			assertTrue(controleur.annulationPossible());
			assertEquals(tailleTrancheHoraireTmp,
					Tournee.getInstance().getTranchesHoraire().get(0)
						.getPointsLivraisons().size());
			
			controleur.annuler();
			assertTrue(controleur.annulationPossible());
			assertEquals(nbNoeuds, trancheHoraireTmp.getPointsLivraisons().size());
			
			assertTrue(!controleur.annulationPossible());
			
			// Retablir
			controleur.retablir();
			assertTrue(controleur.retablissementPossible());
			assertEquals(nbNoeuds+1, trancheHoraireTmp.getPointsLivraisons().size());
			
			controleur.retablir();
			assertTrue(controleur.retablissementPossible());
			assertTrue(tailleTrancheHoraireTmp != Tournee.getInstance()
					.getTranchesHoraire().get(0)
					.getPointsLivraisons().size());
			
			assertTrue(controleur.retablissementPossible());
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			System.out.println("Les tests du controleur sont termines.");
		}
	}
}
