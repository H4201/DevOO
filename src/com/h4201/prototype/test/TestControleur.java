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

/**
 * Test du controleur et actions sur le modele.
 * @author Paul
 *
 */
public class TestControleur
{
	public TestControleur()
	{
		System.out.println("Les tests du controleur commencent !");
		
		this.executerTests("test/plan20x20.xml", "test/livraison20x20-1.xml");
		
		this.executerTests("test/plan20x20.xml", "test/livraison20x20-2.xml");
		
		this.executerTests("test/plan10x10.xml", "test/livraison10x10-1.xml");

		this.executerTests("test/plan10x10.xml", "test/livraison10x10-2.xml");
		
		this.executerTests("test/plan10x10.xml", "test/livraison10x10-3.xml");
		
		System.out.println("Les tests du controleur sont termines.");
	}
	
	private void executerTests(String fichierPlan, String fichierLivraison)
	{
		try
		{
			Controleur controleur = Controleur.getInstance();
			
			// Ouvrir la fenetre
			VueSupervision.getInstance();
			
			// Chargement des donnees
			controleur.chargerPlan(new File(fichierPlan));
			
			controleur.chargerDemandeLivraison(new File(fichierLivraison));
			
			// Notification du mode
			assertEquals(controleur.getMode(), Constante.MODE_NORMAL);
			controleur.notifierClicAjouter();
			assertEquals(controleur.getMode(), Constante.MODE_AJOUT);
			controleur.notifierClicSupprimer();
			assertEquals(controleur.getMode(), Constante.MODE_SUPPRESSION);
			controleur.notifierClicNormal();
			assertEquals(controleur.getMode(), Constante.MODE_NORMAL);
			
			// Calcul de la tournee
			controleur.notifierClicNormal();
			controleur.calculTournee();
			assertTrue(!Tournee.getInstance().getChemins().isEmpty());
			
			// Ajout d'un point de livraison
			Noeud noeudTmp = Plan.getInstance().getNoeudDepuisIdNoeud(Integer.valueOf(0));
			TrancheHoraire trancheHoraireTmp = Tournee.getInstance().getTranchesHoraire().get(0);
			int nbNoeuds = trancheHoraireTmp.getPointsLivraisons().size();
			controleur.notifierClicAjouter();
			controleur.ajoutPointLivraison(noeudTmp, trancheHoraireTmp);
			assertEquals(nbNoeuds+1, trancheHoraireTmp.getPointsLivraisons().size());
			
			// Suppression d'un point de livraison
			int tailleTrancheHoraireTmp = Tournee.getInstance().getTranchesHoraire().get(0).getPointsLivraisons().size();
			PointLivraison pointLivraisonTmp = Tournee.getInstance().getTranchesHoraire().get(0).getPointsLivraisons().get(0);
			controleur.notifierClicSupprimer();
			controleur.supprimerPointLivraison(pointLivraisonTmp);
			assertTrue(pointLivraisonTmp.getIdPointLivraison() !=
					Tournee.getInstance().getTranchesHoraire().get(0)
						.getPointsLivraisons().get(0).getIdPointLivraison());
			
			// Retour au mode normal
			controleur.notifierClicNormal();
			
			// Annuler
			assertTrue(controleur.annulationPossible());
			controleur.annuler();
			assertEquals(tailleTrancheHoraireTmp,
					Tournee.getInstance().getTranchesHoraire().get(0)
						.getPointsLivraisons().size());
			
			assertTrue(controleur.annulationPossible());
			controleur.annuler();
			assertEquals(nbNoeuds, trancheHoraireTmp.getPointsLivraisons().size());
			
			assertTrue(!controleur.annulationPossible());
			
			// Retablir
			assertTrue(controleur.retablissementPossible());
			controleur.retablir();
			assertEquals(nbNoeuds+1, trancheHoraireTmp.getPointsLivraisons().size());

			assertTrue(controleur.retablissementPossible());
			controleur.retablir();
			assertTrue(tailleTrancheHoraireTmp != Tournee.getInstance()
					.getTranchesHoraire().get(0)
					.getPointsLivraisons().size());
			
			assertTrue(!controleur.retablissementPossible());
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
