package com.h4201.prototype.modele;

import java.util.Calendar;

/**
 * L'entrepot est le point de depart et d'arrivee de la tournee.
 * Entrepot herite de PointLivraison car c'est un cas particulier de PointLivraison.
 * Aucun client n'est livre mais le noeud fait partie de la tournee.
 * @author Paul
 *
 */
public class Entrepot extends PointLivraison
{
	private Calendar heureDepartEstimee;
	
	public Entrepot(Noeud noeud)
	{
		super(null, noeud, null);
	}
	
	public Calendar getHeureDepartEstimee() {
		return heureDepartEstimee;
	}

	public void setHeureDepartEstimee(Calendar heureDepartEstimee) {
		this.heureDepartEstimee = heureDepartEstimee;
	}

	@Override
	public String toString() {
		return "Entrepot [pointLivraison=" + super.toString() + "]";
	}
}