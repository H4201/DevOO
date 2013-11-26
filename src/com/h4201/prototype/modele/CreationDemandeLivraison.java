package com.h4201.prototype.modele;

import java.io.File;
import java.util.Calendar;
import java.util.Vector;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.h4201.prototype.exception.ExceptionFichier;
import com.h4201.prototype.exception.ExceptionNoeudInconnu;
import com.h4201.prototype.exception.ExceptionNonInstancie;
import com.h4201.prototype.exception.ExceptionTrancheHoraire;
import com.h4201.prototype.exception.ExceptionXML;
import com.h4201.prototype.utilitaire.Date;
import com.h4201.prototype.utilitaire.Fichier;


/**
 * Classe permettant de creer une instance de tournee a partir d'un fichier XML.
 * Classe ne contenant que des methodes statiques. 
 * Declaree abstract car ne peut etre instanciee.
 * 
 * @author Paul
 *
 */
public abstract class CreationDemandeLivraison
{
	/**
	 * Permet d'instancier une Tournee sans ses chemins.
	 * En effet, l'instance comprendra les points de livraison, tranches horaire, 
	 * entrepot mais pas les chemins, ils seront calcules par la suite 
	 * et ajoutes a l'instance.
	 * 
	 * @param demandeLivraisonXML Fichier contenant le XML
	 * @return Une tournee sans les chemins pour aller 
	 * d'un point de livraison a l'autre.
	 * @throws ExceptionXML
	 * @throws ExceptionFichier
	 * @throws ExceptionNonInstancie
	 */
	public static Tournee depuisXML(File demandeLivraisonXML) 
			throws ExceptionXML, ExceptionFichier, ExceptionNonInstancie,
			ExceptionNoeudInconnu, ExceptionTrancheHoraire
	{
		Tournee tournee = null;
	
	    Element racine = Fichier.getDocumentXml(demandeLivraisonXML).getDocumentElement();
	
	    if (racine.getNodeName().equals("JourneeType"))
	    {	    	
	    	Element eltEntrepot = (Element) racine.getElementsByTagName("Entrepot").item(0);
	    	Noeud noeudEntrepot = Plan.getInstance().getNoeudDepuisIdNoeud(
						Integer.valueOf(eltEntrepot.getAttribute("adresse"))
	    			);
	    	Entrepot entrepot = new Entrepot(noeudEntrepot);
	    	
	    	Element eltPlagesHoraires = (Element) racine.getElementsByTagName("PlagesHoraires").item(0);
	    	
	    	NodeList eltPlages = eltPlagesHoraires.getElementsByTagName("Plage");
	    	Element eltPlage;
	    	Calendar heureFinTranchePrecedente = Date.getCalendarDepuisHeureAn("0:0:0");
	    	Calendar heureDebutTrancheHoraire;
	    	Calendar heureFinTrancheHoraire;
	    	TrancheHoraire trancheHoraire;
	    	NodeList livraisons;
	    	Element livraison;
	    	Noeud noeudDuPtLivraison;
	    	PointLivraison pointLivraison;
	    	Vector<TrancheHoraire> tranchesHoraire = new Vector<TrancheHoraire>();
	    	for (int i = 0; i < eltPlages.getLength(); i++)
	    	{
	    		eltPlage = (Element) eltPlages.item(i);
	    		
	    		heureDebutTrancheHoraire = Date.getCalendarDepuisHeureAn(eltPlage.getAttribute("heureDebut"));
	    		heureFinTrancheHoraire = Date.getCalendarDepuisHeureAn(eltPlage.getAttribute("heureFin"));
	    		
	    		if(heureDebutTrancheHoraire.after(heureFinTrancheHoraire))
	    		{
	    			throw new ExceptionTrancheHoraire("Heure de debut de la tranche " +
	    					"horaire posterieure a l'heure de fin");
	    		}
	    		else if(heureFinTranchePrecedente.after(heureDebutTrancheHoraire)
	    				&& !heureFinTranchePrecedente.equals(heureDebutTrancheHoraire))
	    		{
	    			throw new ExceptionTrancheHoraire("Des tranches horaires se"
	    					+ " chevauchent ou ne sont pas ordonnees");
	    		}
	    		else
	    		{
	    			heureFinTranchePrecedente = heureFinTrancheHoraire;
	    			
		    		trancheHoraire = new TrancheHoraire(
		    				heureDebutTrancheHoraire,
		    				heureFinTrancheHoraire
							);
			    			
			    	livraisons = ((Element) eltPlage.getElementsByTagName("Livraisons")
			    			.item(0)).getElementsByTagName("Livraison");
			    	for (int j = 0; j < livraisons.getLength(); j++)
			    	{
			    		livraison = (Element) livraisons.item(j);
	
			    		noeudDuPtLivraison = Plan.getInstance().getNoeudDepuisIdNoeud(
			    				Integer.valueOf(livraison.getAttribute("adresse"))
						);
			    		
			    		if(noeudDuPtLivraison != null)
			    		{
				    		pointLivraison = new PointLivraison(
					    		livraison.getAttribute("client"),
					    		noeudDuPtLivraison,
					    		trancheHoraire
				    		);
				    		
				    		trancheHoraire.ajouterPointLivraison(pointLivraison);
			    		}
			    	}
			    	tranchesHoraire.add(trancheHoraire);
	    		}
	    	}
	    	
	    	tournee = Tournee.setInstance(entrepot, tranchesHoraire);
	    }
	    else
	    {
	    	throw new ExceptionXML();
	    }

	    return tournee;
	}
}
