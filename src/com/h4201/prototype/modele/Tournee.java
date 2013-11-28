package com.h4201.prototype.modele;

import java.util.Vector;

import com.h4201.prototype.modele.Entrepot;
import com.h4201.prototype.modele.TrancheHoraire;
import com.h4201.prototype.modele.Chemin;

/**
 * Une tournee est l'itineraire d'un livreur passant par tous les points de livraison 
 * et optimise pour aller le plus vite possible (choix des troncons).
 * 
 * @author Paul
 *
 */
public class Tournee
{
	private static volatile Tournee instance = null;
	private Entrepot entrepot;
	private Vector<Chemin> chemins;
	private Vector<TrancheHoraire> tranchesHoraire;

	  /**
	   * Constructeur de Tournee.
	   * @param entrepot Noeud de depart
	   * @param tranchesHoraire Liste des tranches horaires contenant 
	   * les points de livraison
	   */
	  private Tournee(Entrepot entrepot, Vector<TrancheHoraire> tranchesHoraire)
	  {
		  this.entrepot = entrepot;
		  this.tranchesHoraire = tranchesHoraire;
		  this.chemins = new Vector<Chemin>();
	  }
	  
	  /**
	   * Modification de l'instance de la tournee (singleton).
	   * @param entrepot - point de depart de la tournee.
	   * @param tranchesHoraire - la liste des tranches horaires de la tournee.
	   * @return la nouvelle instance.
	   */
	  protected static Tournee setInstance(Entrepot entrepot, 
			  Vector<TrancheHoraire> tranchesHoraire)
	  {
		synchronized(Tournee.class)
		{
			Tournee.instance = new Tournee(entrepot, tranchesHoraire);
		}
		
		return Tournee.instance;
	  }
	  
	  /**
	   * Recuperer l'instance de la tournee.
	   * @return l'instance.
	   */
	  public final static Tournee getInstance()
	  {
	      return Tournee.instance;
	  }
	  
	  /**
	   * Reinitialisation de l'instance.
	   */
	  protected static void reinitialiserTournee()
	  {
		  Tournee.instance = null;
	  }

	  /**
	   * Ajouter un chemin a la tournee.
	   * @param chemin entre 2 noeuds.
	   */
	  protected void ajouterChemin(Chemin chemin)
	  {
		  this.chemins.addElement(chemin);
		  
		  for(Troncon troncon : chemin.getTroncons())
		  {
			  troncon.unCheminSupplementairePasseParCeTroncon();
		  }
	  }
	  
	  /**
	   * Supprimer tous les chemins de la tournee.
	   */
	  public void supprimerTousLesChemins()
	  {
		  for(Chemin chemin : this.chemins)
		  {
			  for(Troncon troncon : chemin.getTroncons())
			  {
				  troncon.reinitialisationDesChemins();
			  }
		  }
		  
		  this.chemins = new Vector<Chemin>();
	  }
		
	  /**
	   * Recuperer l'entrepot de la tournee.
	   * @return l'entrepot.
	   */
	public Entrepot getEntrepot() {
		return entrepot;
	}
	
	/**
	 * Recuperer les chemins de la tournee.
	 * @return les chemins.
	 */
	public Vector<Chemin> getChemins() {
		return chemins;
	}
	
	/**
	 * Recuperer les tranches horaire de la tournee.
	 * @return les tranches horaire.
	 */
	public Vector<TrancheHoraire> getTranchesHoraire() { 
		return tranchesHoraire;
	}
	
	/**
	 * Ajouter un point de livraison a la tournee.
	 * Pre-condition : Indiquer la tranche horaire.
	 * @param ptLivraison ajoute.
	 */
	public void ajouterPointLivraison(PointLivraison ptLivraison)
	{
		TrancheHoraire th = ptLivraison.getTrancheHoraire();
		
		if(th != null)
		{
	    	for(int i=0 ; i<tranchesHoraire.size() ; i++)
	    	{
	    		if((tranchesHoraire.get(i)).equals(th))
	    			tranchesHoraire.get(i).getPointsLivraisons().add(ptLivraison);
	    	}
	    	// postcondition : on a necessairement ajoute ptLivraison a 1 et 1 seule trancheHoraire
		}
	}

	/**
	 * Supprimer un point de livraison.
	 * Pre-condition : Indiquer la tranche horaire.
	 * @param ptLivraison supprime
	 */
	public void supprimerPointLivraison(PointLivraison ptLivraison)
	{
    	for(int i=0 ; i<tranchesHoraire.size() ; i++)
    	{
    		if(ptLivraison.getTrancheHoraire() != null
    				&& tranchesHoraire.get(i).equals(ptLivraison.getTrancheHoraire()))
    		{
    			Vector<PointLivraison> ptsLivraison = tranchesHoraire.get(i).getPointsLivraisons();
    			for(int j=0 ; j<ptsLivraison.size() ; j++)
    			{
    				if((ptsLivraison.get(j)).equals(ptLivraison))
    				{
    					ptsLivraison.remove(j);
    				}
    			}    	
    			// postcondition : on a necessairement suprimm� ptLivraison 1 et 1 seule fois.
    		}
    	}
	}
	
	/**
	 * Afficher les informations de la tournee.
	 */
	public void afficher()
	{
		System.out.println("\n" + this.toString());
		System.out.println("Chemins : ");
		for(Chemin chemin : chemins)
		{
			chemin.afficher();
		}
		
		System.out.println("Tranches horaire : ");
		for(TrancheHoraire trancheHoraire : tranchesHoraire)
		{
			trancheHoraire.afficher();
		}
	}

	/**
	 * Recuperer les informations de la tournee dans une string.
	 */
	@Override
	public String toString() {
		return "Tournee [entrepot=" + entrepot + "]";
	}
}