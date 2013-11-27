package com.h4201.prototype.vue;

import java.awt.Graphics;
import java.util.Map;
import java.util.Vector;

import com.h4201.prototype.exception.ExceptionNonInstancie;
import com.h4201.prototype.modele.Noeud;
import com.h4201.prototype.modele.Plan;
import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.modele.Troncon;

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
//			System.out.println("lavueNoeud : "+ "coordX: "+ laVueNoeud.getNoeud().getX()+ " coordY: "+ laVueNoeud.getNoeud().getY());
			
			if(laVueNoeud.estClique(x, y, laVueNoeud.getNoeud().getX(), laVueNoeud.getNoeud().getY()))
			{			
				noeud = laVueNoeud.getNoeud();
//				System.out.println("noeud correspondant : "+ "coordX: "+ noeud.getX()+ " coordY: "+ noeud.getY());
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
	 * Dessine les noeuds et les troncons � partir du fichier xml plan charge.
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
}
