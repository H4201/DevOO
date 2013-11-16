package com.h4201.prototype.modele;

import java.util.Vector;

public class Tournee
{
	  private static int dernierIdTournee;
	  private int idTournee;
	  private Entrepot entrepot;
	  private Vector<Chemin> chemins;
	  private Vector<TrancheHoraire> trancheshoraire;

	  public Tournee(Entrepot entrepot, Vector<TrancheHoraire> trancheshoraire)
	  {
		  this.entrepot = entrepot;
		  this.trancheshoraire = trancheshoraire;
		  this.idTournee = Tournee.dernierIdTournee++;
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
			return trancheshoraire;
		}

		@Override
		public String toString() {
			return "Tournee [idTournee=" + idTournee + ", entrepot=" + entrepot
					+ ", chemins=" + chemins + ", trancheshoraire="
					+ trancheshoraire + "]";
		}
}