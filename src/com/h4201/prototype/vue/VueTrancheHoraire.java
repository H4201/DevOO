package com.h4201.prototype.vue;

import java.util.Vector;

import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.modele.TrancheHoraire;

/*
 * classe utile au final car d'après l'interface de supervision on a une vue pr les plages horaires
 * et les différentes livraisons pour chacune des plages
 */
public class VueTrancheHoraire
{
	private Vector<TrancheHoraire> trancheHoraires;
	private Vector<PointLivraison> pointLivraisons;

	public Vector<TrancheHoraire> getTrancheHoraires()
	{
		return trancheHoraires;
	}
	
	//liste des livraisons pour une tranche horaire
	public Vector<PointLivraison> getPointLivraisons(TrancheHoraire trancheHoraire)
	{
		return pointLivraisons;
		
	}
	
	public void afficherTrancheHoraires()
	{
		
	}

}
