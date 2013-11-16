package com.h4201.prototype.modele;

import java.util.Calendar;
import java.util.Vector;


public class TrancheHoraire {

  private Calendar heureDebut;
  private Calendar heureFin;
  private Vector<PointLivraison> pointsLivraisons;

  public TrancheHoraire(Calendar heureDebut, Calendar heureFin)
  {
	  this.heureDebut = heureDebut;
	  this.heureFin = heureFin;
  }

  public void ajouterPointLivraison(PointLivraison pointLivraison)
  {
	  this.pointsLivraisons.addElement(pointLivraison);
  }

	public Calendar getHeureDebut() {
		return heureDebut;
	}
	
	public Calendar getHeureFin() {
		return heureFin;
	}
	
	public Vector<PointLivraison> getPointsLivraisons() {
		return pointsLivraisons;
	}
}