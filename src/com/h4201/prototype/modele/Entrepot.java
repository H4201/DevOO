package com.h4201.prototype.modele;

public class Entrepot extends PointLivraison
{
	private int idEntrepot;

  public Entrepot(Noeud noeud)
  {
	  super(null, noeud, null);
  }

  public int getIdEntrepot()
  {
	  return this.idEntrepot;
  }

@Override
public String toString() {
	return "Entrepot [idEntrepot=" + idEntrepot + ", pointLivraison=" + super.toString() + "]";
}
}