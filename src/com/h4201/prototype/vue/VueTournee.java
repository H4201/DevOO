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
	private Vector<VueChemin> lesVueChemins;
	private VueEntrepot vueEntrepot;
	private Vector<VuePointLivraison> lesVuePointLivraisons;
	private TrancheHoraire lesTrancheHoraires[];
	private Map<TrancheHoraire, Color> couleursTranchesHoraires;
	private Vector<PointLivraison> lesPointLivraisonsClique;
	
	
	public Vector<VueChemin> getLesVueChemins() {
		return lesVueChemins;
	}

	public TrancheHoraire[] getLesTrancheHoraires() {
		return lesTrancheHoraires;
	}

	public VueEntrepot getVueEntrepot()
	{
		return vueEntrepot;
	}

	public Vector<VuePointLivraison> getLesVuePointLivraisons()
	{
		return lesVuePointLivraisons;
	}
	
	public Map<TrancheHoraire, Color> getCouleursTranchesHoraires()
	{
		return couleursTranchesHoraires;
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
	
	protected boolean initialiserTout()
	{
		// On recupere la tournee
		boolean flag = false;
		Tournee tournee = Tournee.getInstance();
		
		couleursTranchesHoraires = new HashMap<TrancheHoraire,Color>();
		lesTrancheHoraires = tournee.getTranchesHoraire().toArray(new TrancheHoraire[tournee.getTranchesHoraire().size()]);
		Color CouleurTrancheHoraire[] = Constante.tabCouleur;

		for(int i=0; i<CouleurTrancheHoraire.length && i<lesTrancheHoraires.length; i++)
		{
			couleursTranchesHoraires.put(lesTrancheHoraires[i], CouleurTrancheHoraire[i]);				
		}
		
		if(couleursTranchesHoraires.size()!=0)
		{
			flag = true;
		}
		return flag;
	}
	
	protected boolean initialiserPointLivraisons()
	{
		/*
		 * On charge l'entrepot et les points de livraisons de la tournee
		 */
		boolean flag = false;
		boolean ret = initialiserTout();
		vueEntrepot = new VueEntrepot(Tournee.getInstance().getEntrepot());
		lesVuePointLivraisons = new Vector<VuePointLivraison>();
		for(TrancheHoraire trancheHoraire : couleursTranchesHoraires.keySet())
		{
			for(PointLivraison pointLivraison : trancheHoraire.getPointsLivraisons())
			{
				lesVuePointLivraisons.add(new VuePointLivraison(pointLivraison,
						couleursTranchesHoraires.get(trancheHoraire)));
			}
		}
		
		if(lesVuePointLivraisons.size() > 0)
		{
			flag = true;
		}
		
		return flag;		
	}
	
	protected boolean initialiserTournee()
	{
		/*
		 * on charge les chemins de la tournee
		 */
	
		boolean flag = false;
		boolean ret  = initialiserTout();
		
		lesVueChemins = new Vector<VueChemin>();
		int numeroChemin = 0;
		for(Chemin chemin : Tournee.getInstance().getChemins())
		{
			TrancheHoraire th = chemin.getPointLivraisonDestination().getTrancheHoraire();

			if(th == null)
				th = chemin.getPointLivraisonOrigine().getTrancheHoraire();
			
			lesVueChemins.add(new VueChemin(numeroChemin++, chemin, couleursTranchesHoraires.get(th)));
		}
		
		if(lesVueChemins.size()!=0)
		{
			flag = true;
		}
		
		return flag;
	}
	
	public void dessinerLespointLivraisons(Graphics g,  int facteurConversionLarg, int facteurConversionHaut)
	{		
		vueEntrepot.dessinerEntrepot(g, facteurConversionLarg, facteurConversionHaut, Constante.COULEURENTREPOT);
		for(VuePointLivraison vuePointLivraison : lesVuePointLivraisons)
		{
			vuePointLivraison.dessinerPointLivraison(g, facteurConversionLarg, facteurConversionHaut, vuePointLivraison.getCouleur());			
		}		
	}
	
	public void dessinerTournee(Graphics g,  int facteurConversionLarg, int facteurConversionHaut)
	{
		for(VueChemin vueChemin : lesVueChemins)
		{
			vueChemin.dessinerChemin(g, facteurConversionLarg, facteurConversionHaut, vueChemin.getCouleur());
		}		
	}
	
	public void ajouterNouveauPointLivraison(PointLivraison pointLivraison)
	{
		Color couleur = VueTournee.getInstance().getCouleursTranchesHoraires().get(pointLivraison.getTrancheHoraire());
		lesVuePointLivraisons.add(new VuePointLivraison(pointLivraison, couleur));
	}
	
	 
	public void supprimerPointLivraison (PointLivraison pointLivraison)
	{		
		for (VuePointLivraison vuePointLivraison : lesVuePointLivraisons)
		{
			if(vuePointLivraison.getPointLivraison().equals(pointLivraison))
			{
				lesVuePointLivraisons.remove(vuePointLivraison);
			}
		}
	}
	
	public  Vector<PointLivraison> lesPointLivraisonsClique(double x, double y)
	{
		for(VuePointLivraison vuePointLivraison : lesVuePointLivraisons)
		{
			double x1 = vuePointLivraison.getPointLivraison().getNoeud().getX();
			double y1 = vuePointLivraison.getPointLivraison().getNoeud().getY();
			if(vuePointLivraison.estClique(x, y, x1,y1))
			{
				lesPointLivraisonsClique.add(vuePointLivraison.getPointLivraison());
			}		
		}
		return lesPointLivraisonsClique;		
	}
}
