package com.h4201.prototype.modele;

import java.io.File;
import java.util.HashMap;
import java.util.Vector;

import org.w3c.dom.*;

import com.h4201.prototype.exception.ExceptionFichier;
import com.h4201.prototype.exception.ExceptionNonInstancie;
import com.h4201.prototype.exception.ExceptionXML;
import com.h4201.prototype.utilitaire.Fichier;

public abstract class CreationPlan
{
	
	public static Plan depuisXML(File planXML) 
			throws ExceptionXML, ExceptionFichier, ExceptionNonInstancie
	{
		Plan plan = null;
	
	    Element racine = Fichier.getDocumentXml(planXML).getDocumentElement();
	
	    if (racine.getNodeName().equals("Reseau"))
	    {
    		HashMap<Integer, Noeud> noeuds = new HashMap<Integer, Noeud>();
    		Vector<Troncon> troncons = new Vector<Troncon>();
	    	
	    	// Parcours des noeuds
	    	Noeud noeud;
	    	Element elementNoeud;
	    	NodeList listeNoeud = racine.getElementsByTagName("Noeud");
	    	for (int i = 0; i < listeNoeud.getLength(); i++)
	    	{
	    		elementNoeud = (Element) listeNoeud.item(i);
	            noeud = new Noeud(Integer.valueOf(elementNoeud.getAttribute("id")),
            			Double.valueOf(elementNoeud.getAttribute("x")),
            			Double.valueOf(elementNoeud.getAttribute("y")));
	            
	            noeuds.put(Integer.valueOf(noeud.getIdNoeud()), noeud);
	    	}
	    	
	    	// Parcours des troncons
	    	Integer idNoeudOrigine;
	    	Integer idNoeudDestination;
	    	Noeud noeudOrigine;
	    	Noeud noeudDestination;
	    	Troncon troncon;
	    	NodeList listeTronconSortant;
	    	Element elementTronconSortant;
	    	for (int i = 0; i < listeNoeud.getLength(); i++)
	    	{
	    		elementNoeud = (Element) listeNoeud.item(i);
	    		
	    		idNoeudOrigine = Integer.valueOf(elementNoeud.getAttribute("id"));
	    		
	    		listeTronconSortant = elementNoeud.getElementsByTagName("TronconSortant");
	    		for (int j = 0; j < listeTronconSortant.getLength(); j++)
		    	{
	    			elementTronconSortant = (Element) listeTronconSortant.item(j);
	    			
	    			idNoeudDestination = Integer.valueOf(elementTronconSortant.getAttribute("destination"));
	    			
	    			noeudOrigine = noeuds.get(idNoeudOrigine);
	    			noeudDestination = noeuds.get(idNoeudDestination);
	    			
	    			troncon = new Troncon(
	    					noeudOrigine, 
	    					noeudDestination, 
	    					elementTronconSortant.getAttribute("nomRue"), 
	    					Double.valueOf(elementTronconSortant.getAttribute("longueur").replace(",", ".")),
	    					Double.valueOf(elementTronconSortant.getAttribute("vitesse").replace(",", ".")));
	    			
	    			troncons.addElement(troncon);
	    			
	    			noeudOrigine.ajouterTronconSortant(troncon);
	    			noeudDestination.ajouterTronconEntrant(troncon);
		    	}
	    	}
	    	
	    	// Plan
	    	plan = Plan.setInstance(troncons, noeuds);
	    }
	    else
	    {
	    	throw new ExceptionXML(CreationPlan.class.getName());
	    }

	    return plan;
	}
}