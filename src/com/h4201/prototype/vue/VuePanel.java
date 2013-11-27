package com.h4201.prototype.vue;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;

import javax.swing.JPanel;

import com.h4201.prototype.modele.Noeud;
import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.modele.Tournee;
import com.h4201.prototype.modele.TrancheHoraire;
import com.h4201.prototype.utilitaire.Constante;

@SuppressWarnings("serial")
public class VuePanel extends JPanel
{
	private static volatile VuePanel instance = null;
	
	/**
	 * Contructeur d'une vue pour le cadre du plan.
	 */
	private VuePanel()
	{
		super();
	}
	
	/**
	 * 
	 * @return instance de vuePanel
	 */
	public final static VuePanel getInstance()
	{
		if (VuePanel.instance == null)
		{
			synchronized(VuePanel.class)
			{
				if (VuePanel.instance == null)
				{
					VuePanel.instance = new VuePanel();
				}
			}
		}

		return VuePanel.instance;
	}
	
	public void initialiserVuePlan()
	{
		VuePlan.getInstance().initialiserVuePlan(); 
	}
	
	public Map<TrancheHoraire, Color> getCouleursTranchesHoraires()
	{
		return VueTournee.getInstance().getCouleursTranchesHoraires();
	}
	
	public Noeud getLeNoeud(double x, double y)
	{
		return VuePlan.getInstance().getLeNoeud(x, y);
	}
	
	public PointLivraison getLePointLivraison(double x, double y)
	{
		return VuePlan.getInstance().getLePointLivraison(x, y);
	}
	
	public void ajouterNouveauPointLivraison(PointLivraison pointLivraison)
	{
		VueTournee.getInstance().ajouterNouveauPointLivraison(pointLivraison);
	}
	
	public void supprimerPointLivraison(PointLivraison pointLivraison)
	{
		VueTournee.getInstance().supprimerPointLivraison(pointLivraison);
	}
	
	/**
	 * Chargement du plan.
	 * On dessine le cadre du plan, puis tous les noeuds et troncons.
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		this.setBackground(Constante.ARRIEREPLAN);
		
		if((Tournee.getInstance() == null))
		{
			VuePlan.getInstance().dessinerNoeudsTroncons(g, getWidth(), getHeight());
		}
		
		if((Tournee.getInstance() != null) && (VueTournee.getInstance().initialiserTout()==true))
		{
			VuePlan.getInstance().dessinerNoeudsTroncons(g, getWidth(), getHeight());
			if(VueTournee.getInstance().initialiserPointLivraisons() == true)
			{
				VueTournee.getInstance().dessinerLespointLivraisons(g, getWidth(), getHeight());
			}			
		}
		
		if((Tournee.getInstance() != null) && (VueTournee.getInstance().initialiserTout()==true) && (VueTournee.getInstance().initialiserPointLivraisons() == true))
		{
			VuePlan.getInstance().dessinerNoeudsTroncons(g, getWidth(), getHeight());
			
			VueTournee.getInstance().dessinerLespointLivraisons(g, getWidth(), getHeight());
			
			if(VueTournee.getInstance().initialiserTournee()==true)
			{
				VueTournee.getInstance().dessinerTournee(g, getWidth(), getHeight());
			}		
		}
		
	}
}
