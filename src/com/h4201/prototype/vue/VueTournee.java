package com.h4201.prototype.vue;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.h4201.prototype.modele.Chemin;
import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.modele.Tournee;
import com.h4201.prototype.modele.TrancheHoraire;
import com.h4201.prototype.utilitaire.Constante;

public class VueTournee 
{
	private static volatile VueTournee instance = null;
	private Vector<VueChemin> lesVueChemins = new Vector<VueChemin>();
	private VueEntrepot vueEntrepot;
	private Vector<VuePointLivraison> lesVuePointLivraisons = new Vector<VuePointLivraison>();
	private TrancheHoraire lesTrancheHoraires[];
	private Map<TrancheHoraire, Color> CouleursTranchesHoraires = new HashMap<TrancheHoraire,Color>();
	
	
	
	public VueEntrepot getVueEntrepot()
	{
		return vueEntrepot;
	}

	public Vector<VuePointLivraison> getLesVuePointLivraisons()
	{
		return lesVuePointLivraisons;
	}
	
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
	
	public boolean initialiserTout()
	{
		// On recupere la tournee
		boolean flag = false;
		Tournee tournee = Tournee.getInstance();

		lesTrancheHoraires = tournee.getTranchesHoraire().toArray(new TrancheHoraire[tournee.getTranchesHoraire().size()]);
		Color CouleurTrancheHoraire[] = Constante.tabCouleur;

		for(int i=0; i<CouleurTrancheHoraire.length && i<lesTrancheHoraires.length; i++)
		{
			CouleursTranchesHoraires.put(lesTrancheHoraires[i], CouleurTrancheHoraire[i]);				
		}
		if(CouleursTranchesHoraires.size()!=0)
		{
			flag = true;
		}
		return flag;
	}
	/*
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
	*/
	
	
	public boolean initialiserPointLivraisons()
	{
		/*
		 * On charge l'entrepot et les points de livraisons de la tournee
		 */
		boolean flag = false;
		boolean ret = initialiserTout();
		vueEntrepot = new VueEntrepot(Tournee.getInstance().getEntrepot());
		for(TrancheHoraire trancheHoraire : CouleursTranchesHoraires.keySet())
		{
			for(PointLivraison pointLivraison : trancheHoraire.getPointsLivraisons())
			{
				lesVuePointLivraisons.add(new VuePointLivraison(pointLivraison,CouleursTranchesHoraires.get(trancheHoraire)));
			}
		}
		if(lesVuePointLivraisons.size()!=0)
		{
			flag = true;
		}
		return flag;		
	}
	public boolean initialiserTournee()
	{
		/*
		 * on charge les chemins de la tournee
		 */
	
		boolean flag = false;
		boolean ret  = initialiserTout();
		for(Chemin chemin : Tournee.getInstance().getChemins())
		{
			TrancheHoraire th = chemin.getPointLivraisonDestination().getTrancheHoraire();
			lesVueChemins.add(new VueChemin(chemin,CouleursTranchesHoraires.get(th)));
		}
		if(lesVueChemins.size()!=0)
		{
			flag = true;
		}
		return flag;
	}
	
	public void dessinerLespointLivraisons(Graphics g,  int facteurConversion)
	{
		
		vueEntrepot.dessinerEntrepot(g, facteurConversion, Constante.COULEURENTREPOT);
		for(VuePointLivraison vuePointLivraison : lesVuePointLivraisons)
		{
			vuePointLivraison.dessinerPointLivraison(g, facteurConversion, vuePointLivraison.getCouleur());			
		}		
	}
	
	public void dessinerTournee(Graphics g,  int facteurConversion)
	{
		for(VueChemin vueChemin : lesVueChemins)
		{
			vueChemin.dessinerChemin(g, facteurConversion, vueChemin.getCouleur());
		}		
	}

}
