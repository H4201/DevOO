package com.h4201.prototype.controleur;

import java.io.File;
import java.util.Stack;
import java.util.Vector;
import java.util.HashMap;

import com.h4201.prototype.exception.ExceptionFichier;
import com.h4201.prototype.exception.ExceptionNonInstancie;
import com.h4201.prototype.exception.ExceptionXML;
import com.h4201.prototype.modele.*;
import com.h4201.prototype.vue.VuePlan;

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
    
    public Vector<TrancheHoraire> getTranchesHoraire(int idTournee)
    {
    	Tournee tournee = tournees.get(Integer.valueOf(idTournee));
    	return tournee.getTranchesHoraire();
    }
    
    public Tournee getTournee(int idTournee)
    {
    	return tournees.get(Integer.valueOf(idTournee));
    }    
    
    
    public void ajoutPointLivraison(int idTournee, Noeud noeud, TrancheHoraire trancheHoraire)
    {
    	Tournee tournee = tournees.get(Integer.valueOf(idTournee));
    	CmdAjouterPtLivraison commandeAjout = new CmdAjouterPtLivraison(tournee, noeud, trancheHoraire);
    	commandeAjout.do_();
    	
    	//MAJ des redos/undos
    	redos.clear(); // popAll()
    	undos.push(commandeAjout);
    	// sur la vue : griser 'retablir' && degriser 'annuler'
    }
    
    public void supprimerPointLivraison(int idTournee, PointLivraison pointLivraison)
    {
    	Tournee tournee = tournees.get(Integer.valueOf(idTournee));
    	CmdSupprimerPtLivraison commandeSuppr = new CmdSupprimerPtLivraison(tournee, pointLivraison);
    	commandeSuppr.do_();
    	
    	//MAJ des redos/undos
    	redos.clear(); // popAll()
    	undos.push(commandeSuppr);
    	// sur la vue : griser 'retablir' && degriser 'annuler'
    }

    /**
     * Annuler la derniere Commande effectuee.
     * @return true si il n'y a plus d'annulation possible (avant ou) après l'execution de cette methode, false sinon.
     * Permet d'informer la vue qu'il faux griser/muter le bouton 'annuler' dans l'interface si plus d'annulation possible.
     */
    public boolean annuler()
    {
    	if(!undos.isEmpty())
    	{
    		Commande cmd = undos.pop();
    		cmd.do_();
    		redos.push(cmd);
    	} 
    	// else il n'y a rien a annuler.
    	
    	// determiner le grisage eventuel du bouton 'annuler'
    	if(undos.isEmpty())
    		return true;
    	
    	return false;
    }
    
    /**
     * Retablir la derniere Commande annulee.
     * @return true si il n'y a plus de retablissement possible (avant ou) après l'execution de cette methode, false sinon.
     * Permet d'informer la vue qu'il faux griser/muter le bouton 'retablir' dans l'interface si plus de retablissement possible.
     */    
    public boolean retablir()
    {
    	if(!redos.isEmpty())
    	{
    		Commande cmd = redos.pop();
    		cmd.do_();
    		undos.push(cmd);
    	} 
    	// else il n'y a rien a retablir.
    	
    	// determiner le grisage eventuel du bouton 'retablir'
    	if(redos.isEmpty())
    	{
    		return true;
    	}
    	
    	return false;
    }
    
    public void chargerPlan(File fichierXML)
    {
    	try
    	{
	    	CreationPlan.depuisXML(fichierXML);
	    	// VuePlan.getInstance().dessinerPlan(new Graphic());
    	}
    	catch(Exception e)
    	{
    		// construire VueException v(f.getMessage());
    	}
    }
    
    public void chargerDemandeLivraison(File fichierXML)
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
