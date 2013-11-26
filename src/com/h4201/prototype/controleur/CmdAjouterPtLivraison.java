package com.h4201.prototype.controleur;


import com.h4201.prototype.modele.Noeud;
import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.modele.Tournee;
import com.h4201.prototype.modele.TrancheHoraire;
import com.h4201.prototype.utilitaire.Constante;
import com.h4201.prototype.vue.VuePanel;

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
		Tournee.getInstance().ajouterPointLivraison(ptLivraison);

    	VuePanel.getInstance().ajouterNouveauPointLivraison(ptLivraison);
    	VuePanel.getInstance().repaint();
	}
	
	public void undo()
	{
		Tournee.getInstance().supprimerPointLivraison(ptLivraison);
		
		VuePanel.getInstance().supprimerPointLivraison(ptLivraison);
    	VuePanel.getInstance().repaint();
	}
	
	public void redo()
	{
		do_();
	}
	
	/**
	 * connaitre le mode (MODE_AJOUT) de la commande
	 */
	public int getMode()
	{
		return Constante.MODE_AJOUT;
	}
}
