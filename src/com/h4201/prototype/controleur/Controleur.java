package com.h4201.prototype.controleur;

import java.io.File;
import java.util.Stack;
import java.util.Vector;
import java.util.HashMap;

import com.h4201.prototype.exception.ExceptionNonInstancie;
import com.h4201.prototype.exception.ExceptionTrancheHoraire;
import com.h4201.prototype.modele.*;
import com.h4201.prototype.vue.VuePlan;
import com.h4201.prototype.modele.AppGraphe;
import com.h4201.prototype.utilitaire.Constante;

/**
 * 
 * @author Steevens
 *
 */
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
     * Methode appellee par la vue pour connaitre le mode actuel parmi {MODE_NORMAL, MODE_AJOUT, MODE_SUPPRESSION}.
     * @return Retourne le mode.
     */
    public int getMode()
    {
    	return mode;
    }
    
    
    
    /* Notifications de la vue */

    /**
     * Methode appellee par la vue pour nous notifier d'un clic sur le bouton 'Normal'.
     * Nous passons alors en mode Normal.
     */
    public void notifierClicNormal()
    {
    	passerEnModeNormal();
    }
 
    /**
     * Methode appellee par la vue pour nous notifier d'un clic sur le bouton 'Ajouter'.
     * Nous passons alors en mode Ajout.
     */
    public void notifierClicAjouter()
    {
    	passerEnModeAjout();
    }
    
    /**
     * Methode appellee par la vue pour nous notifier d'un clic sur le bouton 'Supprimer'.
     * Nous passons alors en mode Suppression.
     */
    public void notifierClicSupprimer()
    {
    	passerEnModeSuppression();
    }
    
    
    
    /**
     * Calcul de la tournee (dans le Mod�le) et affichage sur le Plan interactif (dans la Vue).
     */
    public void calculTournee()
    {    	
    	AppGraphe appG = AppGraphe.getInstance();
    	
    	try 
    	{
			appG.genererTournee();
	    	/// VueTournee.afficher
	    	passerEnModeNormal();
		} 
    	catch (ExceptionTrancheHoraire e) 
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
     * 1. Deleguer l'ajout a do_() dans le pattern Command.
     * 2. Mise a jour de la pile d'annulation.
     * @param noeud
     * @param trancheHoraire
     */
    public void ajoutPointLivraison(Noeud noeud, TrancheHoraire trancheHoraire)
    {
    	if(mode != Constante.MODE_AJOUT)
    	{
    		/// Exception
    	}
    	else
    	{
	    	CmdAjouterPtLivraison commandeAjout = new CmdAjouterPtLivraison(noeud, trancheHoraire);
	    	commandeAjout.do_();
	    	
	    	/* MAJ de la pile d'annulation */
	    	redos.clear(); // popAll()
	    	undos.push(commandeAjout);
	    	// comportement attendu sur la vue : 'retablir' est grise && 'annuler' est degrise
    	}
    }
    
    /**
     * Supprimer un Point de Livraison de la Tournee.
     * 1. Deleguer la suppression a do_() dans le pattern Command.
     * 2. Mise a jour de la pile d'annulation.     
     * * @param pointLivraison
     */
    public void supprimerPointLivraison(PointLivraison pointLivraison)
    {   	
    	CmdSupprimerPtLivraison commandeSuppr = new CmdSupprimerPtLivraison(pointLivraison);
    	commandeSuppr.do_();
    	
    	/* MAJ de la pile d'annulation */
    	redos.clear(); // popAll()
    	undos.push(commandeSuppr);
    	// comportement attendu sur la vue : 'retablir' est grise && 'annuler' est degrise
    }

    /**
     * Annuler la derniere Commande effectuee dans l'interface interactive du superviseur.
	 * 1. Deleguer l'annulation a undo() dans le pattern Command.
     * 2. Mise a jour de la pile de retablissement.
     * 3. Mise a jour du mode 	
     */
    public void annuler()
    {    	
    	if(!undos.isEmpty())
    	{
    		Commande cmd = undos.pop();
    		cmd.undo();
    		redos.push(cmd);
    	} // else il n'y a rien a annuler.
    	
    	majModeApresAnnulation();
    }
    
    /**
     * Retablir la derniere Commande annulee dans l'interface interactive du superviseur.
	 * 1. Deleguer le retablissement a redo() dans le pattern Command.
     * 2. Mise a jour de la pile d'annulation.
     * 3. Mise a jour du mode 	   
     */
    public void retablir()
    {
    	if(!redos.isEmpty())
    	{
    		Commande cmd = redos.pop();
    		cmd.redo();
    		undos.push(cmd);

    		majModeApresRetablissement();    		
    	} 
    	// else il n'y a rien a retablir, le mode reste le meme et l'on a pas besoin de supprimer tous les chemins
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
    		/// construire VueException v(f.getMessage());
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
        	/// appeller la vue
        	passerEnModeNormal();
    	}
    	catch(Exception e)
    	{
    		/// construire VueException v(f.getMessage());
    	}
    }
    
    
    
    private void passerEnModeNormal()
    {
    	mode = Constante.MODE_NORMAL;
    }
    
    private void passerEnModeAjout()
    {    	
    	mode = Constante.MODE_AJOUT;
    	Tournee.getInstance().supprimerTousLesChemins(); // Suppression des chemins de la tournee, ils serons recalcules    	
    }
    
    private void passerEnModeSuppression()
    {
    	mode = Constante.MODE_SUPPRESSION;
    	Tournee.getInstance().supprimerTousLesChemins(); // Suppression des chemins de la tournee, ils serons recalcules    	
    }
    
    /**
     * MAJ du mode : celui de la commande precedente celle que l'on vient d'annuler.
     */
    private void majModeApresAnnulation()
    {
    	if(!undos.isEmpty())
    	{
    		mode = undos.get(undos.size()-1).getMode();
            Tournee.getInstance().supprimerTousLesChemins(); // Suppression des chemins de la tournee, ils serons recalcules 
    	}
    	else // cas particulier ou l'on est revenu a l'etat inital ou aucune commande n'a encore ete faite.
    		passerEnModeNormal();
    }
    
    /**
     * Precondition : un retablissement a ete la derniere action effectuee => !undos.isEmpty().
     * MAJ du mode : celui de la commande retablie
     */
    private void majModeApresRetablissement()
    {
    	mode = undos.get(undos.size()-1).getMode();
        Tournee.getInstance().supprimerTousLesChemins(); // Suppression des chemins de la tournee, ils serons recalcules 
    }
    
    /**
     * @return true si il est possible d'annuler, false sinon.
     * Permet d'informer la vue qu'il faux griser/muter le bouton 'annuler' dans l'interface si plus d'annulation possible.
     */
    public boolean annulationPossible()
    {
    	return !undos.isEmpty();
    }
    
    /**
     * @return true si il est possible de r�tablir, false sinon.
     * Permet d'informer la vue qu'il faux griser/muter le bouton 'retablir' dans l'interface si plus de retablissement possible.
     */
    public boolean retablissementPossible()
    {
    	return !redos.isEmpty();
    }
}
