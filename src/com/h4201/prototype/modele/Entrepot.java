package com.h4201.prototype.modele;

/**
 * L'entrepot est le point de depart et d'arrivee de la tournee.
 * Entrepot herite de PointLivraison car c'est un cas particulier de PointLivraison.
 * Aucun client n'est livre mais le noeud fait partie de la tournee.
 * @author Paul
 *
 */
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