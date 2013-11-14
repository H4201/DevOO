package com.h4201.prototype.controleur;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import com.h4201.prototype.exception.ExceptionXML;
import com.h4201.prototype.modele.Noeud;
import com.h4201.prototype.modele.Plan;
import com.h4201.prototype.modele.Troncon;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class FabriquePlan
{
	private static final String type = "Plan";
	
	public FabriquePlan()
	{
		
	}
	
	public static Plan depuisXML(File planXML) throws ExceptionXML
	{
		Plan plan = null;
		
		try
		{
			// création d'une fabrique de documents
	        DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
	
	        // création d'un constructeur de documents
	        DocumentBuilder constructeur = fabrique.newDocumentBuilder();
			
			Document document = constructeur.parse(planXML);
		
		    Element racine = document.getDocumentElement();
		
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
		            
			    	System.out.println(noeud.toString());
		    	}
		    	
		    	// Parcours des troncons
		    	Integer idNoeudOrigine;
		    	Integer idNoeudDestination;
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
		    			
		    			troncon = new Troncon(
		    					noeuds.get(idNoeudOrigine), 
		    					noeuds.get(idNoeudDestination), 
		    					elementTronconSortant.getAttribute("nomRue"), 
		    					Double.valueOf(elementTronconSortant.getAttribute("longueur").replace(",", ".")),
		    					Double.valueOf(elementTronconSortant.getAttribute("vitesse").replace(",", ".")));
		    			
		    			troncons.addElement(troncon);
			    	}
		    	}
		    	
		    	// Plan
		    	plan = new Plan(troncons, noeuds);
		    }
		    else
		    {
		    	throw new ExceptionXML(FabriquePlan.type);
		    }
		} 
		catch (ParserConfigurationException e)
		{
			e.printStackTrace();
		}
		catch (SAXException | IOException e)
		{
			e.printStackTrace();
		}

	    return plan;
	}
}
