package com.h4201.prototype.controleur;

import java.util.Vector;

import com.h4201.prototype.modele.Noeud;
import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.modele.Tournee;
import com.h4201.prototype.modele.TrancheHoraire;

public class CmdAjouterPtLivraison extends Commande 
{	
    public Tournee tournee;
    public Noeud noeud;
    public TrancheHoraire trancheHoraire;
    public PointLivraison ptLivraison;

	public CmdAjouterPtLivraison(Tournee tournee, Noeud noeud, TrancheHoraire trancheHoraire)
	{
		this.tournee = tournee;
		this.noeud = noeud;
		this.trancheHoraire = trancheHoraire;
		this.ptLivraison = new PointLivraison("CLIENT", noeud, trancheHoraire); 
	}
	
	public void do_()
	{
    	Vector<TrancheHoraire> tranches = tournee.getTranchesHoraire();
    	
    	for(int i=0 ; i<tranches.size() ; i++)
    		if(tranches.get(i).getHeureDebut() == trancheHoraire.getHeureDebut())
    			tranches.get(i).getPointsLivraisons().add(ptLivraison);
    	// postcondition : on a necessairement ajoute ptLivraison a 1 et 1 seulle trancheHoraire
	}
	
	public void undo()
	{
		Vector<TrancheHoraire> tranches = tournee.getTranchesHoraire();
    	
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
    			// postcondition : on a necessairement suprimmé ptLivraison 1 et 1 seule fois.
    		}
    	}
	}
	
	public void redo()
	{
    	Vector<TrancheHoraire> tranches = tournee.getTranchesHoraire();
    	
    	for(int i=0 ; i<tranches.size() ; i++)
    		if(tranches.get(i).getHeureDebut() == trancheHoraire.getHeureDebut())
    			tranches.get(i).getPointsLivraisons().add(ptLivraison);
    	// postcondition : on a necessairement ajoute ptLivraison a 1 et 1 seulle trancheHoraire
	}
}
