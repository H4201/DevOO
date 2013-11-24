package com.h4201.prototype.vue;

import java.awt.Graphics;
import java.util.Map;
import java.util.Vector;

import javax.swing.JPanel;

import com.h4201.prototype.exception.ExceptionNonInstancie;
import com.h4201.prototype.modele.Noeud;
import com.h4201.prototype.modele.Plan;
import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.modele.Tournee;
import com.h4201.prototype.modele.Troncon;
import com.h4201.prototype.utilitaire.Constante;

@SuppressWarnings("serial")
public class VuePlan extends JPanel
{
	private static volatile VuePlan instance = null;
	private Vector<VueNoeud> lesVueNoeuds = new Vector<VueNoeud>();
	private Vector<VueTroncon> lesVueTroncons = new Vector<VueTroncon>();
	
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
			if(laVueNoeud.estClique(x, y, laVueNoeud.getNoeud().getX(), laVueNoeud.getNoeud().getY())==true)
			{
				noeud = laVueNoeud.getNoeud();
			}
		}
		
		return noeud;
	}
	
	public PointLivraison getLePointLivraison(double x, double y, Vector<VuePointLivraison> lesVuePointDeLivraisons)
	{
		PointLivraison pointLivraison = null;
		for(VuePointLivraison laVuePointDeLivraison : lesVuePointDeLivraisons )
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
	/**
	 * Chargement du plan.
	 * On dessine le cadre du plan, puis tous les noeuds et tronçons.
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		this.setBackground(Constante.ARRIEREPLAN);
		
		if((Tournee.getInstance() == null))
		{
			dessinerNoeudsTroncons(g, getWidth(), getHeight());
		}
		
		if((Tournee.getInstance() != null) && (VueTournee.getInstance().initialiserTout()==true))
		{
			dessinerNoeudsTroncons(g, getWidth(), getHeight());
			if(VueTournee.getInstance().initialiserPointLivraisons() == true)
			{
				VueTournee.getInstance().dessinerLespointLivraisons(g, getWidth(), getHeight());
			}			
		}
		
		if((Tournee.getInstance() != null) && (VueTournee.getInstance().initialiserTout()==true) && (VueTournee.getInstance().initialiserPointLivraisons() == true))
		{
			dessinerNoeudsTroncons(g, getWidth(), getHeight());
			
			VueTournee.getInstance().dessinerLespointLivraisons(g, getWidth(), getHeight());
			
			if(VueTournee.getInstance().initialiserTournee()==true)
			{
				VueTournee.getInstance().dessinerTournee(g, getWidth(), getHeight());
			}		
		}		
	}

}
