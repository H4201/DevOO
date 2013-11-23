package com.h4201.prototype.controleur;

import java.io.File;
import java.util.Stack;
import java.util.Vector;
import java.util.HashMap;

import com.h4201.prototype.exception.ExceptionNonInstancie;
import com.h4201.prototype.exception.ExceptionTranchesHorairesNonOrdonees;
import com.h4201.prototype.modele.*;
import com.h4201.prototype.vue.VuePlan;
import com.h4201.prototype.modele.AppGraphe;
import com.h4201.prototype.utilitaire.Constante;


public final class Controleur
{
	private static volatile Controleur instance = null;
	private Stack<Commande> undos = new Stack<Commande>();
	private Stack<Commande> redos = new Stack<Commande>();
	private int mode = Constante.MODE_NORMAL; // mode de la modification interactive par le superviseur

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
     * Methode appellee par la vue pour connaitre le mode actuel (normal, enAjout ou enSuppression)
     * @return
     */
    public int getMode()
    {
    	return mode;
    }
    
    // Notifications de la vue

    public void notifierClicNormal()
    {
    	passerEnModeNormal();
    }
    
    public void notifierClicAjouter()
    {
    	passerEnModeAjout();
    }
    
    public void notifierClicSupprimer()
    {
    	passerEnModeSuppression();
    }
    
    /**
     * Calcul de la tournee et affichage sur le Plan interactif.
     */
    public void calculTournee()
    {    	
    	AppGraphe appG = AppGraphe.getInstance();
    	
    	try 
    	{
			appG.genererTournee();
	    	// VueTournee.afficher
	    	passerEnModeNormal();
		} 
    	catch (ExceptionTranchesHorairesNonOrdonees e) 
    	{
			e.printStackTrace();
		} 
    	catch (ExceptionNonInstancie e) 
		{
			e.printStackTrace();
		}
    }
    
    /**
     * Ajouter un Point de Livraison a la Tournee.
     * @param noeud
     * @param trancheHoraire
     */
    public void ajoutPointLivraison(Noeud noeud, TrancheHoraire trancheHoraire)
    {
    	passerEnModeAjout();
    	
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
    public void supprimerPointLivraison(PointLivraison pointLivraison)
    {
    	passerEnModeSuppression();
    	
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
    		cmd.undo();
    		redos.push(cmd);
    	} // else il n'y a rien a annuler.
    	
    	majModeApresAnnulation();
    	
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
    		cmd.redo();
    		undos.push(cmd);

    		majModeApresRetablissement();    		
    	} // else il n'y a rien a retablir et le mode reste le meme.
    	
    	// determiner le grisage eventuel du bouton 'retablir'
    	if(redos.isEmpty())
    		return true;
    	
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
        	passerEnModeNormal();
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
        	passerEnModeNormal();
    	}
    	catch(Exception e)
    	{
    		// construire VueException v(f.getMessage());
    	}
    }
    
    private void passerEnModeNormal()
    {
    	mode = Constante.MODE_NORMAL;
    }
    
    private void passerEnModeAjout()
    {
    	mode = Constante.MODE_AJOUT;
    }
    
    private void passerEnModeSuppression()
    {
    	mode = Constante.MODE_SUPPRESSION;
    }
    
    /**
     * MAJ du mode : celui de la commande precedente celle que l'on vient d'annuler.
     */
    private void majModeApresAnnulation()
    {
    	if(!undos.isEmpty())
    		mode = undos.get(undos.size()-1).getMode();
    	else // cas particulier ou l'on est revenu a l'etat inital ou aucune commande n'a encore ete faite.
    		passerEnModeNormal();
    }
    
    /**
     * MAJ du mode : celui de la commande retablie
     */
    private void majModeApresRetablissement()
    {
    	mode = undos.get(undos.size()-1).getMode();
    }
}
