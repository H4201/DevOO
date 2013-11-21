package com.h4201.prototype.vue;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JPanel;

import com.h4201.prototype.modele.Chemin;
import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.modele.Tournee;
import com.h4201.prototype.modele.TrancheHoraire;
import com.h4201.prototype.utilitaire.Constante;

@SuppressWarnings("serial")
public class VueTournee extends JPanel
{
	private static volatile VueTournee instance = null;
	private Vector<VueChemin> lesVueChemins = new Vector<VueChemin>();
	private VueEntrepot vueEntrepot;
	private Vector<VuePointDeLivraison> lesVuePointLivraisons = new Vector<VuePointDeLivraison>();
	private TrancheHoraire lesTrancheHoraires[];
	private Map<TrancheHoraire, Color> CouleursTranchesHoraires = new HashMap<TrancheHoraire,Color>();
	
	private VueTournee()
	{
		super();
	}
	
	public final static VueTournee getInstance()
	{
		if (VueTournee.instance == null)
		{
			synchronized(VueTournee.class)
			{
				if (VueTournee.instance == null)
				{
					VueTournee.instance = new VueTournee();
				}
			}
		}

		return VueTournee.instance;
	}
	
	public Tournee initialiserTout()
	{
		// On recupere la tournee
		Tournee tournee = Tournee.getInstance();

		lesTrancheHoraires = tournee.getTranchesHoraire().toArray(new TrancheHoraire[tournee.getTranchesHoraire().size()]);
		Color CouleurTrancheHoraire[] = Constante.tabCouleur;

		for(int i=0; i<CouleurTrancheHoraire.length && i<lesTrancheHoraires.length; i++)
		{
			CouleursTranchesHoraires.put(lesTrancheHoraires[i], CouleurTrancheHoraire[i]);				
		}
		return tournee;
	}
	
	public void initialiserPointLivraisons()
	{
		/*
		 * On charge l'entrepot et les points de livraisons de la tournee
		 */
		Tournee tournee = initialiserTout();
		vueEntrepot = new VueEntrepot(tournee.getEntrepot());
		for(TrancheHoraire trancheHoraire : CouleursTranchesHoraires.keySet())
		{
			for(PointLivraison pointLivraison : trancheHoraire.getPointsLivraisons())
			{
				lesVuePointLivraisons.add(new VuePointDeLivraison(pointLivraison,CouleursTranchesHoraires.get(trancheHoraire)));
			}
		}		
	}
	
	public void initialiserTournee()
	{
		/*
		 * on charge les chemins de la tournee
		 */
		Tournee tournee = initialiserTout();
		for(Chemin chemin : tournee.getChemins())
		{
			TrancheHoraire th = chemin.getPointLivraisonDestination().getTrancheHoraire();
			lesVueChemins.add(new VueChemin(chemin,CouleursTranchesHoraires.get(th)));
		}
		
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		this.setBackground(Constante.ARRIEREPLAN);
		vueEntrepot.dessinerEntrepot(g, getWidth(), Constante.COULEURENTREPOT);
		for(VuePointDeLivraison vuePointLivraison : lesVuePointLivraisons)
		{
			vuePointLivraison.dessinerPointLivraison(g, getWidth(), vuePointLivraison.getCouleur());			
		}
			
		/*
		 * 1. Bouton calculer tournee
		 * 2. On dessine la tournee
		 */
		
		for(VueChemin vueChemin : lesVueChemins)
		{
			vueChemin.dessinerChemin(g, getWidth(), vueChemin.getCouleur());
		}
		
	}

}
