package com.h4201.prototype.vue;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;
import java.util.Vector;

import javax.swing.JPanel;

import com.h4201.prototype.exception.ExceptionNonInstancie;
import com.h4201.prototype.modele.Noeud;
import com.h4201.prototype.modele.Plan;
import com.h4201.prototype.modele.TrancheHoraire;
import com.h4201.prototype.modele.Troncon;
import com.h4201.prototype.utilitaire.Constante;

@SuppressWarnings("serial")
public class VuePlan extends JPanel
{
	private static volatile VuePlan instance = null;
	Vector<VueNoeud> lesVueNoeuds = new Vector<VueNoeud>();
	Vector<VueTroncon> lesVueTroncons = new Vector<VueTroncon>();
	Vector<TrancheHoraire> lesTrancheHoraires = new Vector<TrancheHoraire>();
	Vector<VuePointDeLivraison> lesVuePointLivraisons = new Vector<VuePointDeLivraison>();
	
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
	

	public void initialiserTournee()
	{
		
	}
	
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		System.out.println("je dessine le plan\n");
		this.setBackground(Constante.ARRIEREPLAN);
		
		for(VueNoeud vueNoeud : lesVueNoeuds )
		{
			vueNoeud.dessinerNoeud(g, getWidth());	
		}
		System.out.println("j'ai dessin� les noeuds\n");
		for(VueTroncon vueTroncon : lesVueTroncons )
		{
			vueTroncon.dessinerTroncon(g,getWidth());
		}
		System.out.println("j'ai dessin� les tron�ons");
	}
		
	public Noeud clicPlan(double x, double y)
	{
		return null;
	}

	public void afficherPlan()
	{
		
	}

}
