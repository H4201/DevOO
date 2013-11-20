package com.h4201.prototype.controleur;

import java.util.Vector;

import com.h4201.prototype.modele.Noeud;
import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.modele.Tournee;
import com.h4201.prototype.modele.TrancheHoraire;

public class CmdAjouterPtLivraison extends Commande 
{	
    public Noeud noeud;
    public TrancheHoraire trancheHoraire;
    public PointLivraison ptLivraison;

	public CmdAjouterPtLivraison(Noeud noeud, TrancheHoraire trancheHoraire)
	{
		this.noeud = noeud;
		this.trancheHoraire = trancheHoraire;
		this.ptLivraison = new PointLivraison("CLIENT", noeud, trancheHoraire); 
	}
	
	public void do_()
	{
		Tournee t = Tournee.getInstance();
    	Vector<TrancheHoraire> tranches = t.getTranchesHoraire();
    	
    	for(int i=0 ; i<tranches.size() ; i++)
    		if(tranches.get(i).getHeureDebut() == trancheHoraire.getHeureDebut())
    			tranches.get(i).getPointsLivraisons().add(ptLivraison);
    	// postcondition : on a necessairement ajoute ptLivraison a 1 et 1 seulle trancheHoraire
	}
	
	public void undo()
	{
		Tournee t = Tournee.getInstance();
		Vector<TrancheHoraire> tranches = t.getTranchesHoraire();
    	
    	for(int i=0 ; i<tranches.size() ; i++)
    	{ 
    		if(tranches.get(i).getHeureDebut() == ptLivraison.getTrancheHoraire().getHeureDebut())
    		{
    			Vector<PointLivraison> ptsLivraison = tranches.get(i).getPointsLivraisons();
    			for(int j=0 ; j<ptsLivraison.size() ; j++)
    			{
    				if(ptsLivraison.get(j).getIdPointLivraison() == ptLivraison.getIdPointLivraison())
    				{
    					ptsLivraison.remove(j);
    				}
    			}    	
    			// postcondition : on a necessairement supprimé ptLivraison 1 et 1 seule fois.
    		}
    	}
	}
	
	public void redo()
	{
		Tournee t = Tournee.getInstance();
    	Vector<TrancheHoraire> tranches = t.getTranchesHoraire();
    	
    	for(int i=0 ; i<tranches.size() ; i++)
    		if(tranches.get(i).getHeureDebut() == trancheHoraire.getHeureDebut())
    			tranches.get(i).getPointsLivraisons().add(ptLivraison);
    	// postcondition : on a necessairement ajoute ptLivraison a 1 et 1 seule trancheHoraire
	}
}
