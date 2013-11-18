package com.h4201.prototype.modele;

import java.io.File;
import java.util.Vector;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.h4201.prototype.exception.ExceptionFichier;
import com.h4201.prototype.exception.ExceptionNonInstancie;
import com.h4201.prototype.exception.ExceptionXML;
import com.h4201.prototype.utilitaire.Date;
import com.h4201.prototype.utilitaire.Fichier;


public abstract class CreationDemandeLivraison
{
	
	public static Tournee depuisXML(File demandeLivraisonXML) 
			throws ExceptionXML, ExceptionFichier, ExceptionNonInstancie
	{
		Tournee tournee = null;
	
	    Element racine = Fichier.getDocumentXml(demandeLivraisonXML).getDocumentElement();
	
	    if (racine.getNodeName().equals("JourneeType"))
	    {	    	
	    	Element eltEntrepot = (Element) racine.getElementsByTagName("Entrepot").item(0);
	    	Entrepot entrepot = new Entrepot(
	    			Plan.getInstance().getNoeudDepuisIdNoeud(
	    					Integer.valueOf(eltEntrepot.getAttribute("adresse"))
	    			)
	    	);
	    	
	    	Element eltPlagesHoraires = (Element) racine.getElementsByTagName("PlagesHoraires").item(0);
	    	
	    	NodeList eltPlages = eltPlagesHoraires.getElementsByTagName("Plage");
	    	Element eltPlage;
	    	TrancheHoraire trancheHoraire;
	    	NodeList livraisons;
	    	Element livraison;
	    	PointLivraison pointLivraison;
	    	Vector<TrancheHoraire> tranchesHoraire = new Vector<TrancheHoraire>();
	    	for (int i = 0; i < eltPlages.getLength(); i++)
	    	{
	    		eltPlage = (Element) eltPlages.item(i);
	    		
	    		trancheHoraire = new TrancheHoraire(
	    				Date.getCalendarDepuisHeureAn(eltPlage.getAttribute("heureDebut")),
	    				Date.getCalendarDepuisHeureAn(eltPlage.getAttribute("heureFin"))
						);
		    			
		    	livraisons = ((Element) eltPlage.getElementsByTagName("Livraisons")
		    			.item(0)).getElementsByTagName("Livraison");
		    	for (int j = 0; j < livraisons.getLength(); j++)
		    	{
		    		livraison = (Element) livraisons.item(j);

		    		pointLivraison = new PointLivraison(
			    		livraison.getAttribute("client"),
			    		Plan.getInstance().getNoeudDepuisIdNoeud(
			    				Integer.valueOf(livraison.getAttribute("adresse"))
						),
						trancheHoraire
		    		);
		    		
		    		trancheHoraire.ajouterPointLivraison(pointLivraison);
		    	}
		    	tranchesHoraire.add(trancheHoraire);
	    	}
	    	
	    	tournee = new Tournee(entrepot, tranchesHoraire);
	    }
	    else
	    {
	    	throw new ExceptionXML(CreationDemandeLivraison.class.getName());
	    }

	    return tournee;
	}
}
