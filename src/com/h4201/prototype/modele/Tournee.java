package com.h4201.prototype.modele;

import java.util.HashMap;
import java.util.Vector;

import com.h4201.prototype.exception.ExceptionNonInstancie;
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
	  
	  protected final static Tournee setInstance(Entrepot entrepot, 
			  Vector<TrancheHoraire> tranchesHoraire) throws ExceptionNonInstancie
	  {
		synchronized(Tournee.class)
		{
			Tournee.instance = new Tournee(entrepot, tranchesHoraire);
		}
		
		return getInstance();
	  }
	  
	  public final static Tournee getInstance()
	  {
	      return Tournee.instance;
	  }

	  protected void ajouterChemin(Chemin chemin)
	  {
		  this.chemins.addElement(chemin);
	  }
		
		public Entrepot getEntrepot() {
			return entrepot;
		}
		
		public Vector<Chemin> getChemins() {
			return chemins;
		}
		
		public Vector<TrancheHoraire> getTranchesHoraire() { 
			return tranchesHoraire;
		}
		
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

		@Override
		public String toString() {
			return "Tournee [entrepot=" + entrepot + "]";
		}
}