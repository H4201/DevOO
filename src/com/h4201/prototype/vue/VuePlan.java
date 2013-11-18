package com.h4201.prototype.vue;

import javax.swing.JPanel;

import com.h4201.prototype.exception.ExceptionNonInstancie;
import com.h4201.prototype.modele.Noeud;
import com.h4201.prototype.modele.Plan;
import com.h4201.prototype.modele.Troncon;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;
import java.util.Vector;

@SuppressWarnings("serial")
public class VuePlan extends JPanel
{
	
	/*
	 * constante pour le plan
	 */
	public static final int LONGUEUR = 240;
	public static final int LARGEUR = 240;
	public static final int RAYON = 5;
	public static final int RAYONENTREPOT = 10;
	public static final Color ARRIEREPLAN = Color.BLUE;
	
	private Plan plan;
	private double largeur;
	private double hauteur;
	private Color couleurArrierePlan;
	Vector<VueNoeud> lesVueNoeuds = new Vector<VueNoeud>();
	Vector<VueTroncon> lesVueTroncons = new Vector<VueTroncon>();
	Vector<VuePointDeLivraison> lesPointLivraisons = new Vector<VuePointDeLivraison>();
	
	public VuePlan(Plan plan)
	{
		this.plan = plan;
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
	 * methode permettant de dessiner  tt (appel dessiner de chemin au troncon associé, appel dessiner de tt les tronçons, appel dessiner de noeud
	 * ( a partir de x, y  dessine un noeud, dessiner un point de livraison au noeud(associer une couleur))
	 */
	
	public void dessinerPlan(Graphics g)
	{
		
	}
	
	
	public Noeud clicPlan(double x, double y)
	{
		return null;
	}

	public void afficherPlan()
	{
		
	}
	/*
	public Pair<double,double> ConvertirEnPixel(double coordXEnMetre, double coordXEnMetre )
	{    
	    coordXResult = (double) coordXEnMetre * getWidth() / VuePlan.getLargeur();
	    coordYResult = (double) coordYEnMetre * getWidth() / VuePlan.getHauteur();	  
	}
	*/

}
