package com.h4201.prototype.controleur;

import com.h4201.prototype.modele.Noeud;
import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.modele.Tournee;
import com.h4201.prototype.modele.TrancheHoraire;
import com.h4201.prototype.utilitaire.Constante;
import com.h4201.prototype.vue.VuePanel;

/**
 * Classe specialisant la classe Commande,
 * Representant une action (commande) d'ajout d'un point de livraison dans l'interface d'interaction superviseur.
 * @author Steevens
 */
public class CmdAjouterPtLivraison extends Commande 
{	
    public Noeud noeud;
    public TrancheHoraire trancheHoraire;
    public PointLivraison ptLivraison;

    /**
     * Constructeur de la commande.
     * @param noeud , le noeud a ajouter.
     * @param trancheHoraire , la tranche horaire correspondante.
     */
	public CmdAjouterPtLivraison(Noeud noeud, TrancheHoraire trancheHoraire)
	{
		this.noeud = noeud;
		this.trancheHoraire = trancheHoraire;
		this.ptLivraison = new PointLivraison("CLIENT", noeud, trancheHoraire); 
	}
	
	/**
	 * Methode 'do' performant l'action (commande) d'ajout.
	 * Ajout effectif dans le modele et dans l'affichage (la vue).
	 */
	public void do_()
	{
		Tournee.getInstance().ajouterPointLivraison(ptLivraison);
    	VuePanel.getInstance().ajouterNouveauPointLivraison(ptLivraison);
    	VuePanel.getInstance().repaint();
	}

	/**
	 * Methode 'undo' annulant l'action (commande) d'ajout.
	 * Annulation effective dans le modele et dans l'affichage (la vue).
	 */
	public void undo()
	{
		Tournee.getInstance().supprimerPointLivraison(ptLivraison);
		VuePanel.getInstance().supprimerPointLivraison(ptLivraison);
    	VuePanel.getInstance().repaint();
	}
	
	/**
	 * Methode 'redo' retablissant l'action (commande) d'ajout.
	 * Retablissement effectif dans le modele et dans l'affichage (la vue).
	 */
	public void redo()
	{
		do_();
	}
	
	/**
	 * Methode permetant de connaitre le mode de la commande (AJOUT) 
	 */
	public int getMode()
	{
		return Constante.MODE_AJOUT;
	}
}
