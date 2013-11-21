package com.h4201.prototype.vue;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JPanel;

import com.h4201.prototype.exception.ExceptionNonInstancie;
import com.h4201.prototype.modele.Chemin;
import com.h4201.prototype.modele.Noeud;
import com.h4201.prototype.modele.Plan;
import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.modele.Tournee;
import com.h4201.prototype.modele.TrancheHoraire;
import com.h4201.prototype.modele.Troncon;
import com.h4201.prototype.utilitaire.Constante;

@SuppressWarnings("serial")
public class VuePlan extends JPanel
{
	private static volatile VuePlan instance = null;
	private Vector<VueNoeud> lesVueNoeuds = new Vector<VueNoeud>();
	private Vector<VueTroncon> lesVueTroncons = new Vector<VueTroncon>();
	private Vector<VueChemin> lesVueChemins = new Vector<VueChemin>();
	private VueEntrepot vueEntrepot;
	private Vector<VuePointDeLivraison> lesVuePointLivraisons = new Vector<VuePointDeLivraison>();
	private TrancheHoraire lesTrancheHoraires[];
	private Map<TrancheHoraire, Color> CouleursTranchesHoraires = new HashMap<TrancheHoraire,Color>();


	private VuePlan()
	{
		super();
	}

	public final static VuePlan getInstance()
	{
		if (VuePlan.instance == null)
		{
			synchronized(VuePlan.class)
			{
				if (VuePlan.instance == null)
				{
					VuePlan.instance = new VuePlan();
				}
			}
		}

		return VuePlan.instance;
	}

	public void initialiserVuePlan()
	{
		try
		{
			Plan plan = Plan.getInstance();
			Map<Integer, Noeud> structureNoeuds = plan.getNoeuds();
			Vector<Troncon> StructureTroncons= plan.getTroncons();
			for(Integer idNoeud : structureNoeuds.keySet())
			{
				lesVueNoeuds.add(new VueNoeud(structureNoeuds.get(idNoeud)));
			}

			for(Troncon leTroncon : StructureTroncons)
			{
				lesVueTroncons.add(new VueTroncon(leTroncon));
			}

		} catch (ExceptionNonInstancie e)
		{
			e.printStackTrace();
		}
	}

	/*
	public void initialiserTournee()
	{
		// On recupere la tournee
		Tournee tournee = Tournee.getInstance();

		lesTrancheHoraires = tournee.getTranchesHoraire().toArray(new TrancheHoraire[tournee.getTranchesHoraire().size()]);
		Color CouleurTrancheHoraire[] = Constante.tabCouleur;

		for(int i=0; i<CouleurTrancheHoraire.length && i<=lesTrancheHoraires.length; i++)
		{
			CouleursTranchesHoraires.put(lesTrancheHoraires[i], CouleurTrancheHoraire[i]);				
		}
		
		/*
		 * On charge l'entrepot et les points de livraisons de la tournee
		 */
	/*
		vueEntrepot = new VueEntrepot(tournee.getEntrepot());
		for(TrancheHoraire trancheHoraire : CouleursTranchesHoraires.keySet())
		{
			for(PointLivraison pointLivraison : trancheHoraire.getPointsLivraisons())
			{
				lesVuePointLivraisons.add(new VuePointDeLivraison(pointLivraison,CouleursTranchesHoraires.get(trancheHoraire)));
			}
		}
		*/
		/*
		 * on charge les chemins de la tournee
		 */
	/*
		for(Chemin chemin : tournee.getChemins())
		{
			TrancheHoraire th = chemin.getPointLivraisonDestination().getTrancheHoraire();
			lesVueChemins.add(new VueChemin(chemin,CouleursTranchesHoraires.get(th)));
		}
	}
*/
	
	@Override
	public void paintComponent(Graphics g)
	{
		/*
		 * 1. Bouton charger plan
		 * 2. On dessine le plan, les noeuds et les tronçons
		 */
		super.paintComponent(g);
		this.setBackground(Constante.ARRIEREPLAN);

		for(VueNoeud vueNoeud : lesVueNoeuds )
		{
			vueNoeud.dessinerNoeud(g, getWidth());
		}

		for(VueTroncon vueTroncon : lesVueTroncons )
		{
			vueTroncon.dessinerTroncon(g,getWidth());
		}
		
		/*
		 * 1. Bouton charger demande livraison
		 * 1. On dessine l'entrepot 
		 * 2. On dessine les points de livraisons
		 */
		/*
		vueEntrepot.dessinerEntrepot(g, getWidth(), Constante.COULEURENTREPOT);
		for(VuePointDeLivraison vuePointLivraison : lesVuePointLivraisons)
		{
			vuePointLivraison.dessinerPointLivraison(g, getWidth(), vuePointLivraison.getCouleur());
			
		}
		
		/*
		 * 1. Bouton calculer tournee
		 * 2. On dessine la tournee
		 */
		/*
		for(VueChemin vueChemin : lesVueChemins)
		{
			vueChemin.dessinerChemin(g, getWidth(), vueChemin.getCouleur());
		}
		*/
	}

	public Noeud clicPlan(double x, double y)
	{
		return null;
	}

}
