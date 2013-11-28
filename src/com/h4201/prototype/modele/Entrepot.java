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
	
	/**
	 * Constructeur.
	 * @param noeud le noeud ou sera place l'entrepot
	 */
	public Entrepot(Noeud noeud)
	{
		super(null, noeud, null);
	}
	
	/**
	 * Recuperer l'heure de depart de l'entrepot (heure de debut de la tournee)
	 * @return L'heure dans un Calendar
	 */
	public Calendar getHeureDepartEstimee() {
		return heureDepartEstimee;
	}

	/**
	 * Modififier l'heure de depart de l'entrepot
	 * @param heureDepartEstimee la nouvelle heure
	 */
	protected void setHeureDepartEstimee(Calendar heureDepartEstimee) {
		this.heureDepartEstimee = heureDepartEstimee;
	}

	/**
	 * Les informations de l'entrepot dans une chaine de caracteres
	 */
	@Override
	public String toString() {
		return "Entrepot [pointLivraison=" + super.toString() + "]";
	}
}