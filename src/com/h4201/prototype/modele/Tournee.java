package com.h4201.prototype.modele;

import java.util.Vector;


import com.h4201.prototype.modele.Entrepot;
import com.h4201.prototype.modele.TrancheHoraire;
import com.h4201.prototype.modele.Chemin;

public class Tournee
{
	  private static int dernierIdTournee;
	  private int idTournee;
	  private Entrepot entrepot;
	  private Vector<Chemin> chemins;
	  private Vector<TrancheHoraire> tranchesHoraire;

	  public Tournee(Entrepot entrepot, Vector<TrancheHoraire> tranchesHoraire)
	  {
		  this.entrepot = entrepot;
		  this.tranchesHoraire = tranchesHoraire;
		  this.idTournee = Tournee.dernierIdTournee++;
		  this.chemins = new Vector<Chemin>();
	  }

	  protected void ajouterChemin(Chemin chemin)
	  {
		  this.chemins.addElement(chemin);
	  }

		public int getIdTournee() {
			return idTournee;
		}
		
		public Entrepot getEntrepot() {
			return entrepot;
		}
		
		public Vector<Chemin> getChemins() {
			return chemins;
		}
		
		public Vector<TrancheHoraire> getTrancheshoraire() {
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
			return "Tournee [idTournee=" + idTournee + ", entrepot=" + entrepot + "]";
		}
}