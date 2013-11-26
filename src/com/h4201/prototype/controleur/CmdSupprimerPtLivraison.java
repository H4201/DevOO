package com.h4201.prototype.controleur;


import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.modele.Tournee;
import com.h4201.prototype.utilitaire.Constante;
import com.h4201.prototype.vue.VuePanel;

public class CmdSupprimerPtLivraison extends Commande 
{
	public PointLivraison pointLivraison;
	
	public CmdSupprimerPtLivraison(PointLivraison pointLivraison)
	{
		this.pointLivraison = pointLivraison;
	}

	public void do_()
	{
		Tournee.getInstance().supprimerPointLivraison(pointLivraison);
    	
    	VuePanel.getInstance().supprimerPointLivraison(pointLivraison);
    	VuePanel.getInstance().repaint();
	}
	
	public void undo()
	{
		Tournee.getInstance().ajouterPointLivraison(pointLivraison);

    	VuePanel.getInstance().ajouterNouveauPointLivraison(pointLivraison);
    	VuePanel.getInstance().repaint();
	}
	
	public void redo()
	{
		do_();
	}
	
	/**
	 * connaitre le mode (MODE_SUPPRESSION) de la commande
	 */
	public int getMode()
	{
		return Constante.MODE_SUPPRESSION;
	}
}
