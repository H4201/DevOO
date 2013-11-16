package com.h4201.prototype.modele;

public class Entrepot extends PointLivraison
{
	private int idEntrepot;

  public Entrepot(Noeud noeud, TrancheHoraire trancheHoraire)
  {
	  super(noeud, trancheHoraire);
  }

  public int getIdEntrepot()
  {
	  return this.idEntrepot;
  }

}