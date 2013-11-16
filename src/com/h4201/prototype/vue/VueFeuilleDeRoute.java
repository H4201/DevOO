package com.h4201.prototype.vue;

import java.util.ArrayList;

import com.h4201.prototype.modele.Noeud;
import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.modele.Tournee;

public class VueFeuilleDeRoute extends VueSupervision
{
	private Tournee tournee;

	public Tournee getTournee()
	{
		return tournee;
	}
	
	public ArrayList<PointLivraison> getPointDeLivraison(Tournee tournee)
	{
		return null;
	}

	@Override
	public Noeud clic(double x, double y)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void afficher()
	{
		// TODO Auto-generated method stub
		
	}

}
