package com.h4201.prototype.vue;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JPanel;

import com.h4201.prototype.controleur.Controleur;
import com.h4201.prototype.exception.ExceptionNonInstancie;
import com.h4201.prototype.modele.Noeud;
import com.h4201.prototype.modele.Plan;
import com.h4201.prototype.modele.Tournee;
import com.h4201.prototype.modele.Troncon;
import com.h4201.prototype.utilitaire.Constante;

@SuppressWarnings("serial")
public class VuePlan extends JPanel
{
	private static volatile VuePlan instance = null;
	private Plan plan;
	private double largeur;
	private double hauteur;
	private Color couleurArrierePlan;
	Vector<VueNoeud> lesVueNoeuds = new Vector<VueNoeud>();
	Vector<VueTroncon> lesVueTroncons = new Vector<VueTroncon>();
	Vector<VuePointDeLivraison> lesPointLivraisons = new Vector<VuePointDeLivraison>();
	
	private VuePlan(Plan plan)
	{
		this.plan = plan;
	}
	
	public final static VuePlan setInstance(Plan plan)
	  {
		synchronized(VuePlan.class)
		{
			VuePlan.instance = new VuePlan(plan);
		}
		
		return getInstance();
	  }
	  
  public final static VuePlan getInstance()
  {
      return VuePlan.instance;
  }
	
	public double getLargeur()
	{
	    return largeur;
	}
	    
	public Color getCouleurArrierePlan()
	{
		return couleurArrierePlan ;
	}
	
	public void setCouleurArrierePlan(Color couleur)
	{
		couleurArrierePlan = couleur;
	}

	public double getHauteur()
	{
	        return hauteur;
	}
	    
	public void initialiserVuePlan()
	{
		try
		{
			plan = Plan.getInstance();
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
	public void initialiserVuePointLivraison()
	{
		Controleur controleur = Controleur.getInstance();
		lesTournees = controleur.getTournee();
		for(Tournee tournee : lesTournees)
		{
			tournee.getTrancheshoraire()
			
			
		}
		
	}
	*/
	
	public void dessinerPlan(Graphics g)
	{
		super.paintComponent(g);
		this.setBackground(Constante.ARRIEREPLAN);
		
		for(VueNoeud vueNoeud : lesVueNoeuds )
		{
			vueNoeud.dessinerNoeud(g);
		}
		
		for(VueTroncon vueTroncon : lesVueTroncons )
		{
			vueTroncon.dessinerTroncon(g);
		}		
	}
		
	public Noeud clicPlan(double x, double y)
	{
		return null;
	}

	public void afficherPlan()
	{
		
	}

}
