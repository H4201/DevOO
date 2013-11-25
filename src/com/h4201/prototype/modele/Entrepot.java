package com.h4201.prototype.modele;

import com.h4201.prototype.utilitaire.Constante;

/**
 * L'entrepot est le point de depart et d'arrivee de la tournee.
 * Entrepot herite de PointLivraison car c'est un cas particulier de PointLivraison.
 * Aucun client n'est livre mais le noeud fait partie de la tournee.
 * @author Paul
 *
 */
public class Entrepot extends PointLivraison
{
	private int type = Constante.TYPE_POINT_LIVRAISON;
	
	public Entrepot(Noeud noeud)
	{
		super(null, noeud, null);
	}
	
	@Override
	public int getType()
	{
		return type;
	}

	@Override
	public String toString() {
		return "Entrepot [pointLivraison=" + super.toString() + "]";
	}
}