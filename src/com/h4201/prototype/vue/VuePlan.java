package com.h4201.prototype.vue;

import java.awt.Color;
import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.h4201.prototype.modele.Noeud;
import com.h4201.prototype.modele.TrancheHoraire;

public class VuePlan 
{
	private Vector<VueNoeud> lesNoeuds = new Vector<VueNoeud>();
	private Vector<VueChemin> lesChemins = new Vector<VueChemin>();
	private double largeur;
	private double hauteur;
	private Color couleurArrierePlan;

	public VuePlan()
	{
		
	}
	
	public VuePlan(double larg, double haute, Color arrierePlan)
	{
		largeur = larg;
        hauteur = haute;
        couleurArrierePlan = arrierePlan;
	}
	
	public Vector<VueNoeud> getLesNoeuds()
	{
		return lesNoeuds;
	}
	
	public Vector<VueChemin> getLesChemins()
	{
		return lesChemins;
	}

	public double getLargeur()
	{
		return largeur;
	}

	public double getHauteur()
	{
		return hauteur;
	}
	
	public Color getCouleurArrierePlan()
	{
		return couleurArrierePlan;
	}
	
	public void setCouleurArrierePlan(Color couleur)
	{
		couleurArrierePlan = couleur;
	}
	
	public Noeud getNoeud(double x, double y)
	{
		return null;
	}
	
	public Element creerNoeudXML(Document document)
	{
		return null;
		
	}
	
	public int construireAPartirDeDOMXML(Element noeudDOMRacine)
	{
		return 0;
		
	}
	
	public void ajouterNoeud(VueNoeud noeud)
	{
		
	}
	
	public void ajouterPointDeLivraison(VueNoeud noeud, TrancheHoraire trancheHoraire, Color couleur)
	{
		
	}
	
	public void supprimerPointDeLivraison(VueNoeud noeud, TrancheHoraire trancheHoraire)
	{
		
	}


	public Noeud clicPlan(double x, double y){

		return null;
	}


	public void afficher()
	{

		
	}




}