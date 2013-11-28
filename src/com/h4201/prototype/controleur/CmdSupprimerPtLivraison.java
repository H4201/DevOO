package com.h4201.prototype.controleur;

import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.modele.Tournee;
import com.h4201.prototype.utilitaire.Constante;
import com.h4201.prototype.vue.VuePanel;

/**
 * Classe specialisant la classe Commande,
 * Representant une action (commande) de suppression d'un point de livraison dans l'interface d'interaction superviseur.
 * @author Steevens
 */
public class CmdSupprimerPtLivraison extends Commande 
{
	public PointLivraison pointLivraison;
	
	/**
	 * Constructeur de la commande.
	 * @param pointLivraison , le point de livraison cible par l'action de suppression.
	 */
	public CmdSupprimerPtLivraison(PointLivraison pointLivraison)
	{
		this.pointLivraison = pointLivraison;
	}

	/**
	 * Methode 'do' performant l'action (commande) de suppression.
	 * Suppression effective dans le modele et dans l'affichage (la vue).
	 */
	public void do_()
	{
		Tournee.getInstance().supprimerPointLivraison(pointLivraison);    	
    	VuePanel.getInstance().supprimerPointLivraison(pointLivraison);
    	VuePanel.getInstance().repaint();
	}

	/**
	 * Methode 'undo' annulant l'action (commande) de suppression.
	 * Annulation effective dans le modele et dans l'affichage (la vue).
	 */
	public void undo()
	{
		Tournee.getInstance().ajouterPointLivraison(pointLivraison);
    	VuePanel.getInstance().ajouterNouveauPointLivraison(pointLivraison);
    	VuePanel.getInstance().repaint();
	}
	
	/**
	 * Methode 'redo' retablissant l'action (commande) de suppression.
	 * Retablissement effectif dans le modele et dans l'affichage (la vue).
	 */
	public void redo()
	{
		do_();
	}
	
	/**
	 * Methode permetant de connaitre le mode de la commande (SUPPRESSION) 
	 */
	public int getMode()
	{
		return Constante.MODE_SUPPRESSION;
	}
}
