package com.h4201.prototype.controleur;

import java.io.File;
import java.util.Stack;

import com.h4201.prototype.modele.*;
import com.h4201.prototype.vue.VuePanel;
import com.h4201.prototype.vue.VueSupervision;
import com.h4201.prototype.modele.AppGraphe;
import com.h4201.prototype.utilitaire.Constante;

/**
 * Classe Controleur du modele MVC.
 * @author Steevens
 */
public final class Controleur
{
	private static volatile Controleur instance = null;
	private Stack<Commande> undos = new Stack<Commande>();
	private Stack<Commande> redos = new Stack<Commande>();
	private int mode = Constante.MODE_NORMAL; // mode de la modification interactive par le superviseur

	/**
	 * Constructeur du Controleur.
	 */
    private Controleur() 
    {
        super();
    } 

    /**
     * Methode permettant de renvoyer l'unique instance de la classe Controleur.
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
    
    
    
    /* Notifications et clics depuis la Vue */

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
     * Ajouter un Point de Livraison a la Tournee.
     * 1. Deleguer l'ajout a do_() dans le pattern Command.
     * 2. Mise a jour de la pile d'annulation.
     * @param noeud, le noeud a ajouter.
     * @param trancheHoraire, la tranche horaire qui lui correspond.
     */
    public void ajoutPointLivraison(Noeud noeud, TrancheHoraire trancheHoraire)
    {
    	if(mode != Constante.MODE_AJOUT)
    	{
    		System.out.println("WARNING a l'appel d' ajoutPointLivraison() : l'ajout n'a pas eu lieu car effectue hors du cadre d'un mode 'MODE_AJOUT'");
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
     * * @param pointLivraison, le point de livraison a ajouter.
     */
    public void supprimerPointLivraison(PointLivraison pointLivraison)
    {   
    	if(mode != Constante.MODE_SUPPRESSION)
    	{
    		System.out.println("WARNING a l'appel de supprimerPointLivraison() : la suppression n'a pas eu lieu car effectue hors du cadre d'un mode 'MODE_SUPPRESSION'");
    	}
    	else
	    {
	    	CmdSupprimerPtLivraison commandeSuppr = new CmdSupprimerPtLivraison(pointLivraison);
	    	commandeSuppr.do_();
	    	
	    	/* MAJ de la pile d'annulation */
	    	redos.clear(); // popAll()
	    	undos.push(commandeSuppr);
	    	// comportement attendu sur la vue : 'retablir' est grise && 'annuler' est degrise
	    }
    }
        
    /**
     * Charger un Plan (dans le modele) a partir d'un fichier XML,
     * et gestin de son affichage (dans la vue).
     * @param fichierXML, le fichier contenant le Plan.
     * @return vrai si le chargement a ete effectue sans erreur, faux si il y en a eu une.
     */
    public boolean chargerPlan(File fichierXML)
    {
    	try
    	{
	    	CreationPlan.depuisXML(fichierXML);
	    	VuePanel.getInstance().repaint();
        	passerEnModeNormal();
    	}
    	catch(Exception e)
    	{
    		VueSupervision.getInstance().fenetreErreur(e.getMessage());
    		return false;
    	}
    	
    	return true;
    }
    
    /**
     * Charger une demande de Livraison (dans le modele) a partir d'un fichier XML,
     * et gestion de son affichage (dans la vue).
     * @param fichierXML, le fichier contenant la Demande de Livraison.
     * @return vrai si le chargement a ete effectue sans erreur, faux si il y en a eu une.
     */
    public boolean chargerDemandeLivraison(File fichierXML)
    {
    	try
    	{
        	CreationDemandeLivraison.depuisXML(fichierXML);
	    	VuePanel.getInstance().repaint();
        	passerEnModeNormal();
    	}
    	catch(Exception e)
    	{
    		VueSupervision.getInstance().fenetreErreur((e.getMessage()));
    		return false;
    	}
    	
    	return true;
    }    

    /**
     * Calcul de la tournee (dans le Modele),
	 * et affichage des chemins composants sur le Plan interactif (dans la Vue).
	 * @return vrai si le calcul de la tournee a ete correctement effectue, faux si il y a eu une erreur.
     */
    public boolean calculTournee()
    {    	    	
    	try 
    	{
    		AppGraphe.getInstance().genererTournee();
	    	VuePanel.getInstance().repaint();
	    	passerEnModeNormal();
		}
    	catch (Exception e) 
		{
    		e.printStackTrace();
    		VueSupervision.getInstance().fenetreErreur((e.getMessage()));
    		return false;
		}
    	
    	return true;
    }
    
    
    
    /* Echanges avec le pattern Command */

    /**
     * Annuler la derniere Commande effectuee dans l'interface interactive du superviseur.
	 * 1. Deleguer l'annulation a undo() dans le pattern Command.
     * 2. Mise a jour de la pile de retablissement.
     * 3. Mise a jour du mode d'interaction.
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
     * 3. Mise a jour du mode d'interaction.
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
     * Methode testant, compte tenu de l'etat de la pile d'annulation, l'existence d'une action a 'annuler'.
     * @return true si il est possible d'annuler, false sinon.
     * La vue peut utiliser cette methode pour determiner si il faut griser/muter le bouton 'annuler'.
     */
    public boolean annulationPossible()
    {
    	return !undos.isEmpty();
    }
    
    /**
     * Methode testant, compte tenu de l'etat de la pile de retablissemnt, l'existence d'une action a 'retablir'.
     * @return true si il est possible de retablir, false sinon.
     * La vue peut utiliser cette methode pour determiner si il faut griser/muter le bouton 'retablir'.
     */
    public boolean retablissementPossible()
    {
    	return !redos.isEmpty();
    }
    
    
    
    /* Mode */

    /**
     * Methode permettant de connaitre dans la modification interactive par le superviseur,
     * le mode courant d'interaction.
     * @return Retourne le mode d'interaction.
     */
    public int getMode()
    {
    	return mode;
    }    
    
    /**
     * Methode factorisant le traitement correspondant a un passage du mode interactif en mode NORMAL.
     */
    private void passerEnModeNormal()
    {
    	mode = Constante.MODE_NORMAL;
    }
    
    /**
     * Methode factorisant le traitement correspondant a un passage du mode interactif en mode AJOUT.
     */
    private void passerEnModeAjout()
    {    	
    	mode = Constante.MODE_AJOUT;
    	Tournee.getInstance().supprimerTousLesChemins(); // Suppression des chemins de la tournee, ils serons recalcules 
    	VuePanel.getInstance().repaint();
    }
    
    /**
     * Methode factorisant le traitement correspondant a un passage du mode interactif en mode SUPPRESSION.
     */
    private void passerEnModeSuppression()
    {
    	mode = Constante.MODE_SUPPRESSION;
    	Tournee.getInstance().supprimerTousLesChemins(); // Suppression des chemins de la tournee, ils serons recalcules
    	VuePanel.getInstance().repaint();
    }
    
    /**
     * Methode de MAJ du mode interactif apres une action d'annulation,
     * Le mode deviens celui de la commande qui a ete effectuee avant celle que l'on vient d'annuler.
     */
    private void majModeApresAnnulation()
    {
    	if(!undos.isEmpty())
    	{
    		mode = undos.get(undos.size()-1).getMode();
            Tournee.getInstance().supprimerTousLesChemins(); // Suppression des chemins de la tournee, ils seront recalcules 
	    	VuePanel.getInstance().repaint();
    	}
    	else // cas particulier ou l'on est revenu a l'etat inital ou aucune commande n'a encore ete faite.
    		passerEnModeNormal();
    }
    
    /**
     * Precondition : un retablissement a ete la derniere action effectuee => !undos.isEmpty().
     * Methode de MAJ du mode interactif : il devient celui de la commande retablie.
     */
    private void majModeApresRetablissement()
    {
    	mode = undos.get(undos.size()-1).getMode();
        Tournee.getInstance().supprimerTousLesChemins(); // Suppression des chemins de la tournee, ils seront recalcules 
    	VuePanel.getInstance().repaint();
    }
}
