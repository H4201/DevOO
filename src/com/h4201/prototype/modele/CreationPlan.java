package com.h4201.prototype.modele;

import java.io.File;
import java.util.HashMap;
import java.util.Vector;

import org.w3c.dom.*;

import com.h4201.prototype.exception.ExceptionCoordonnees;
import com.h4201.prototype.exception.ExceptionFichier;
import com.h4201.prototype.exception.ExceptionNoeudInconnu;
import com.h4201.prototype.exception.ExceptionNonInstancie;
import com.h4201.prototype.exception.ExceptionXML;
import com.h4201.prototype.utilitaire.Fichier;

/**
 * Classe permettant de creer l'instance de plan a partir d'un fichier XML.
 * Classe ne contenant que des methodes statiques. 
 * Declaree abstract car ne peut etre instanciee.
 * 
 * @author Paul
 *
 */
public abstract class CreationPlan
{
	/**
	 * Permet d'instancier le plan depuis un fichier XML.
	 * 
	 * @param planXML Fichier contenant le XML
	 * @return L'instance du plan
	 * @throws ExceptionXML
	 * @throws ExceptionFichier
	 * @throws ExceptionNonInstancie
	 */
	public static Plan depuisXML(File planXML) 
			throws ExceptionXML, ExceptionFichier, ExceptionNonInstancie,
			ExceptionCoordonnees, ExceptionNoeudInconnu
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
	    	int idNoeud;
	    	double x;
	    	double y;
	    	NodeList listeNoeud = racine.getElementsByTagName("Noeud");
	    	for (int i = 0; i < listeNoeud.getLength(); i++)
	    	{
	    		elementNoeud = (Element) listeNoeud.item(i);
	            
	    		idNoeud = Integer.valueOf(elementNoeud.getAttribute("id"));
	    		x = Double.valueOf(elementNoeud.getAttribute("x"));
	    		y = Double.valueOf(elementNoeud.getAttribute("y"));
	    		
	    		if(x < 0 || y < 0)
	    		{
	    			throw new ExceptionCoordonnees(idNoeud);
	    		}
	    		else
	    		{
		    		noeud = new Noeud(idNoeud,
	            			x,
	            			y);
		            
		            noeuds.put(Integer.valueOf(noeud.getIdNoeud()), noeud);
	    		}
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
	    			
	    			if(noeudDestination == null)
	    			{
	    				throw new ExceptionNoeudInconnu(idNoeudDestination);
	    			}
	    			else
	    			{
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
