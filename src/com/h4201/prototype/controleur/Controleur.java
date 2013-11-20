package com.h4201.prototype.controleur;

import java.io.File;
import java.util.Stack;
import java.util.Vector;
import java.util.HashMap;

import com.h4201.prototype.modele.*;
import com.h4201.prototype.vue.VuePlan;

public final class Controleur
{
	private static volatile Controleur instance = null;
	private Stack<Commande> undos = new Stack<Commande>();
	private Stack<Commande> redos = new Stack<Commande>();
	private boolean enModification = false; // superviseur en cours de modification interactive ou non.
	
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
    
    /**
     * Ajouter un Point de Livraison a la Tournee.
     * @param noeud
     * @param trancheHoraire
     */
    public void ajoutPointLivraison(Noeud noeud, TrancheHoraire trancheHoraire)
    {
    	enModification = true;
    	
    	Tournee tournee = Tournee.getInstance();
    	CmdAjouterPtLivraison commandeAjout = new CmdAjouterPtLivraison(noeud, trancheHoraire);
    	commandeAjout.do_();
    	
    	//MAJ des redos/undos
    	redos.clear(); // popAll()
    	undos.push(commandeAjout);
    	// sur la vue : griser 'retablir' && degriser 'annuler'
    }
    
    /**
     * Supprimer un Point de Livraison de la Tournee.
     * @param pointLivraison
     */
    public void supprimerPointLivraison(int idTournee, PointLivraison pointLivraison)
    {
    	enModification = true;
    	
    	CmdSupprimerPtLivraison commandeSuppr = new CmdSupprimerPtLivraison(pointLivraison);
    	commandeSuppr.do_();
    	
    	//MAJ des redos/undos
    	redos.clear(); // popAll()
    	undos.push(commandeSuppr);
    	// sur la vue : griser 'retablir' && degriser 'annuler'
    }

    /**
     * Annuler la derniere Commande effectuee dans l'interface interactive du superviseur.
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
     * Retablir la derniere Commande annulee dans l'interface interactive du superviseur.
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

    /**
     * Charger un Plan a partir d'un fichier XML.
     * @param fichierXML
     */
    public void chargerPlan(File fichierXML)
    {
    	try
    	{
	    	CreationPlan.depuisXML(fichierXML);
	    	VuePlan.getInstance();
    	}
    	catch(Exception e)
    	{
    		// construire VueException v(f.getMessage());
    	}
    }
    
    /**
     * Charger une demande de Livraison a partir d'un fichier XML.
     * @param fichierXML
     */
    public void chargerDemandeLivraison(File fichierXML)
    {
    	try
    	{
        	CreationDemandeLivraison.depuisXML(fichierXML);
        	// appeller la vue
    	}
    	catch(Exception e)
    	{
    		// construire VueException v(f.getMessage());
    	}
    }
   
    /**
     * Calcul des plus courts chemins entre les points de livraisons, une fois la modification interactive sur l'interface (Vue) terminee.
     * Verification de la faisabilite de la tournee.
     */
    public void calculTournee()
    {
    	Tournee t = Tournee.getInstance();
    	
    }
}
