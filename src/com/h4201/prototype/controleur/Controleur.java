package com.h4201.prototype.controleur;

import java.io.File;
import java.util.Stack;
import java.util.Vector;
import java.util.HashMap;

import com.h4201.prototype.exception.ExceptionFichier;
import com.h4201.prototype.exception.ExceptionNonInstancie;
import com.h4201.prototype.exception.ExceptionXML;
import com.h4201.prototype.modele.*;

public final class Controleur
{
	private static volatile Controleur instance = null;
	private HashMap<Integer, Tournee> tournees = new HashMap<Integer, Tournee>();
	private Stack<Commande> undos = new Stack();
	private Stack<Commande> redos = new Stack();
	
    private Controleur() 
    {
        super();
    } 

    /**
     * Methode permettant de renvoyer une instance de la classe Controleur
     * @return Retourne l'instance du singleton Controleur.
     */
    public final static Controleur getInstance()
    {
        if (Controleur.instance == null)
        {
           synchronized(Controleur.class)
           {
			 if (Controleur.instance == null)
			 {
				 Controleur.instance = new Controleur();
			 }
           }
        }
        
        return Controleur.instance;
    }
    
    public void annuler()
    {
    	
    }
    
    public void retablir()
    {
    	
    	
    }
    
    public void ajoutPointLivraison(int idTournee, Noeud noeud, TrancheHoraire trancheHoraire)
    {
    	Tournee tournee = tournees.get(Integer.valueOf(idTournee));
    	CmdAjouterPtLivraison commandeAjout = new CmdAjouterPtLivraison(tournee, noeud, trancheHoraire);
    	commandeAjout.do_();
    	undos.push(commandeAjout);
    }
    
    public void supprimerPointLivraison(int idTournee, PointLivraison pointLivraison)
    {
    	Tournee tournee = tournees.get(Integer.valueOf(idTournee));
    	CmdSupprimerPtLivraison commandeSuppr = new CmdSupprimerPtLivraison(tournee, pointLivraison);
    	commandeSuppr.do_();
    	undos.push(commandeSuppr);
    }
    
    public void chargerPlan(File fichierXML)
    {
    	try
    	{
	    	CreationPlan.depuisXML(fichierXML);
	    	/* a dessiner le singleton Plan......*/
    	}
    	catch(Exception e)
    	{
    		// construire VueException v(f.getMessage());
    	}
    }
    
    public void chargerDemandeLivraison(File fichierXML) throws ExceptionXML, ExceptionFichier, ExceptionNonInstancie
    {
    	try
    	{
        	Tournee tournee = CreationDemandeLivraison.depuisXML(fichierXML);
        	int idTournee = tournee.getIdTournee();
        	tournees.put(Integer.valueOf(idTournee), tournee);
    	}
    	catch(Exception e)
    	{
    		// construire VueException v(f.getMessage());
    	}
    }
    
    public void calculTournee(Tournee tournee)
    {
    	
    }
}
