package com.h4201.prototype.vue;

import javax.swing.JPanel;

import com.h4201.prototype.exception.ExceptionNonInstancie;
import com.h4201.prototype.modele.Noeud;
import com.h4201.prototype.modele.Plan;
import com.h4201.prototype.modele.Troncon;

import java.awt.Color;
import java.util.Map;
import java.util.Vector;

@SuppressWarnings("serial")
public class VuePlan extends JPanel

{
	
	private Plan plan;
	
	public VuePlan()
	{
		setBackground(Color.BLUE);
		try
		{
			plan = Plan.getInstance();
		} 
		catch (ExceptionNonInstancie e)
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * methode permettant de dessiner  tt (appel dessiner de chemin au troncon associé, appel dessiner de tt les tronçons, appel dessiner de noeud
	 * ( a partir de x, y  dessine un noeud, dessiner un point de livraison au noeud(associer une couleur))
	 */
	
	public void dessinerPlan()
	{
		Map<Integer, Noeud> lesNoeuds = plan.getNoeuds();
		Vector<Troncon> lesTronçons = plan.getTroncons();
		for(Integer idNoeud : lesNoeuds.keySet())
		{
			double x = lesNoeuds.get(idNoeud).getX();
			double y = lesNoeuds.get(idNoeud).getY();
			
			//VueNoeud noeud = new VueNoeud(x,y,);
		}
	}
	
	
	public Noeud clicPlan(double x, double y)
	{
		return null;
	}

	public void afficher()
	{
		
	}

}
