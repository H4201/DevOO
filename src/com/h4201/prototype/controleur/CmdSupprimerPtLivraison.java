package com.h4201.prototype.controleur;

import java.util.Vector;

import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.modele.Tournee;
import com.h4201.prototype.modele.TrancheHoraire;

public class CmdSupprimerPtLivraison extends Commande 
{
	public PointLivraison pointLivraison;
	
	public CmdSupprimerPtLivraison(PointLivraison pointLivraison)
	{
		this.pointLivraison = pointLivraison;
	}

	public void do_()
	{
		Tournee t = Tournee.getInstance();
		Vector<TrancheHoraire> tranches = t.getTranchesHoraire();
    	
    	for(int i=0 ; i<tranches.size() ; i++)
    	{
    		if(tranches.get(i).getHeureDebut() == pointLivraison.getTrancheHoraire().getHeureDebut())
    		{
    			Vector<PointLivraison> ptsLivraison = tranches.get(i).getPointsLivraisons();
    			for(int j=0 ; j<ptsLivraison.size() ; j++)
    			{
    				if(ptsLivraison.get(j).getIdPointLivraison() == pointLivraison.getIdPointLivraison())
    				{
    					ptsLivraison.remove(j);
    				}
    			}    	
    			// postcondition : on a necessairement suprimmé ptLivraison 1 et 1 seule fois.
    		}
    	}
	}
	
	public void undo()
	{
		Tournee t = Tournee.getInstance();
    	Vector<TrancheHoraire> tranches = t.getTranchesHoraire();
    	TrancheHoraire trancheHoraire = pointLivraison.getTrancheHoraire();
    	
    	for(int i=0 ; i<tranches.size() ; i++)
    		if(tranches.get(i).getHeureDebut() == trancheHoraire.getHeureDebut())
    			tranches.get(i).getPointsLivraisons().add(pointLivraison);
    	// postcondition : on a necessairement ajoute ptLivraison a 1 et 1 seule trancheHoraire
	}
	
	public void redo()
	{
		Tournee t = Tournee.getInstance();
		Vector<TrancheHoraire> tranches = t.getTranchesHoraire();
    	
    	for(int i=0 ; i<tranches.size() ; i++)
    	{
    		if(tranches.get(i).getHeureDebut() == pointLivraison.getTrancheHoraire().getHeureDebut())
    		{
    			Vector<PointLivraison> ptsLivraison = tranches.get(i).getPointsLivraisons();
    			for(int j=0 ; j<ptsLivraison.size() ; j++)
    			{
    				if(ptsLivraison.get(j).getIdPointLivraison() == pointLivraison.getIdPointLivraison())
    				{
    					ptsLivraison.remove(j);
    				}
    			}    	
    			// postcondition : on a necessairement suprimmé ptLivraison 1 et 1 seule fois.
    		}
    	}
	}
}
