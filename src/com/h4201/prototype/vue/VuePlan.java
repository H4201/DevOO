package com.h4201.prototype.vue;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;
import java.util.Vector;

import com.h4201.prototype.exception.ExceptionNonInstancie;
import com.h4201.prototype.modele.Noeud;
import com.h4201.prototype.modele.Plan;
import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.modele.TrancheHoraire;
import com.h4201.prototype.modele.Troncon;
import com.h4201.prototype.utilitaire.Constante;

public class VuePlan
{
	private static volatile VuePlan instance = null;
	private Vector<VueNoeud> lesVueNoeuds;
	private Vector<VueTroncon> lesVueTroncons;
	
	public Vector<VueNoeud> getLesVueNoeuds()
	{
		return lesVueNoeuds;
	}

	public Vector<VueTroncon> getLesVueTroncons()
	{
		return lesVueTroncons;
	}
	
	public Noeud getLeNoeud(double x, double y)
	{
		Noeud noeud = null;
		for(VueNoeud laVueNoeud : lesVueNoeuds )
		{
			System.out.println("lavueNoeud : "+ "coordX: "+ laVueNoeud.getNoeud().getX()+ " coordY: "+ laVueNoeud.getNoeud().getY());
			
			if(laVueNoeud.estClique(x, y, laVueNoeud.getNoeud().getX(), laVueNoeud.getNoeud().getY()))
			{			
				noeud = laVueNoeud.getNoeud();
				System.out.println("noeud correspondant : "+ "coordX: "+ noeud.getX()+ " coordY: "+ noeud.getY());
			}
		}
		return noeud;
	}
	
	public PointLivraison getLePointLivraison(double x, double y)
	{
		PointLivraison pointLivraison = null;
		for(VuePointLivraison laVuePointDeLivraison : VueTournee.getInstance().getLesVuePointLivraisons())
		{
			double coordX = laVuePointDeLivraison.getPointLivraison().getNoeud().getX();
			double coordY = laVuePointDeLivraison.getPointLivraison().getNoeud().getY();
			if(laVuePointDeLivraison.estClique(x, y, coordX,coordY) == true)
			{
				pointLivraison = laVuePointDeLivraison.getPointLivraison();
			}
		}
		
		return pointLivraison;
	}
	
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
			
			lesVueNoeuds = new Vector<VueNoeud>();
			for(Integer idNoeud : structureNoeuds.keySet())
			{
				lesVueNoeuds.add(new VueNoeud(structureNoeuds.get(idNoeud)));
			}

			lesVueTroncons = new Vector<VueTroncon>();
			for(Troncon leTroncon : StructureTroncons)
			{
				lesVueTroncons.add(new VueTroncon(leTroncon));
			}

		} catch (ExceptionNonInstancie e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Dessine les noeuds et les troncons à partir du fichier xml plan charge.
	 * @param g
	 * @param facteurConversionLarg
	 * @param facteurConversionHaut
	 */
	public void dessinerNoeudsTroncons(Graphics g, int facteurConversionLarg, int facteurConversionHaut)
	{
		for(VueNoeud vueNoeud : lesVueNoeuds)
		{
			vueNoeud.dessinerNoeud(g, facteurConversionLarg, facteurConversionHaut);
		}

		for(VueTroncon vueTroncon : lesVueTroncons )
		{
			vueTroncon.dessinerTroncon(g,facteurConversionLarg, facteurConversionHaut );
		}
	}
	
	public void dessinerNouveauPointLivraison(Graphics g, Noeud noeud, int facteurConversionLarg, int facteurConversionHaut, TrancheHoraire trancheHoraire)
	{
		if(VueTournee.getInstance().getCouleursTranchesHoraires().containsKey(trancheHoraire))
		{
			Color cTemp = g.getColor();	
			int x = (int) noeud.getX() * facteurConversionLarg / Constante.LARGEURSUPERV;
			int y = (int) noeud.getY() * facteurConversionHaut / Constante.HAUTEURSUPERV;
			int rayon = (int) (Constante.RAYONNOEUD * facteurConversionLarg / Constante.LARGEURSUPERV);
			
			g.setColor(VueTournee.getInstance().getCouleursTranchesHoraires().get(trancheHoraire));
			g.fillOval((int)( x - rayon) ,(int)( y - rayon) , 2 * rayon , 2 * rayon);
	        g.setColor(cTemp);
		}
		else
		{
			System.out.println("la tranche horaires donnée n'est pas existante");
			// afficher une popup d'erreur sur la fenêtre
		}
		
	}
	
	 /**
	  * supprime un point de livraison en un changeant sa couleur par celle du noeud par defaut.
	  * @param g
	  * @param pointLivraison
	  * @param facteurConversionLarg
	  * @param facteurConversionHaut
	  */
	public void griserPointLivraisonSupprimer(Graphics g, PointLivraison pointLivraison,  int facteurConversionLarg, int facteurConversionHaut)
	{
		Color cTemp = g.getColor();
		int x = (int) pointLivraison.getNoeud().getX() * facteurConversionLarg / Constante.LARGEURSUPERV;
		int y = (int) pointLivraison.getNoeud().getY() * facteurConversionHaut / Constante.HAUTEURSUPERV;
		int rayon = (int) (Constante.RAYONNOEUD * facteurConversionLarg / Constante.LARGEURSUPERV);	
		g.setColor(Constante.COULEURNOEUD);
		g.fillOval((int)( x - rayon) ,(int) (y - rayon) , 2 * rayon , 2 * rayon);
	    g.setColor(cTemp);
	}


}
