package com.h4201.prototype.controleur;


import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.modele.Tournee;
import com.h4201.prototype.utilitaire.Constante;

public class CmdSupprimerPtLivraison extends Commande 
{
	public PointLivraison pointLivraison;
	
	public CmdSupprimerPtLivraison(PointLivraison pointLivraison)
	{
		this.pointLivraison = pointLivraison;
	}

	public Object do_()
	{
		Tournee.getInstance().supprimerPointLivraison(pointLivraison);
		
		return pointLivraison;
	}
	
	public Object undo()
	{
		Tournee.getInstance().ajouterPointLivraison(pointLivraison);
		
		return pointLivraison;
	}
	
	public Object redo()
	{
		return do_();
	}
	
	/**
	 * connaitre le mode (MODE_SUPPRESSION) de la commande
	 */
	public int getMode()
	{
		return Constante.MODE_SUPPRESSION;
	}
}
