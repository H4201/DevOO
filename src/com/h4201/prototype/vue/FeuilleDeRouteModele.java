package com.h4201.prototype.vue;


import com.h4201.prototype.modele.PointLivraison;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;


@SuppressWarnings("serial")
public class FeuilleDeRouteModele extends AbstractTableModel  {
	
	private final ArrayList<PointLivraison> lesPointsLivraisons = new ArrayList<PointLivraison> ();
	
	 
    private final String[] entetes = {"Point de Livraison", "Adresse de livraison", "Heure d'arrivée", "Heure de départ", "Itinéraire", "Contact Client"};
    
 
	public FeuilleDeRouteModele()
	{
		 super();
		for(PointLivraison pointLivraison:lesPointsLivraisons){
	      lesPointsLivraisons.add(new PointLivraison(pointLivraison.getClient(), pointLivraison.getNoeud() , pointLivraison.getTrancheHoraire())); // ajouter les paramètres
		}
	}

	@Override
	public int getColumnCount() {
		return entetes.length;
	}

	@Override
	public int getRowCount() {
		return lesPointsLivraisons.size();
	}
	
	public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		
		        switch(columnIndex){
		            case 0:
		                return lesPointsLivraisons.get(rowIndex).getIdPointLivraison();
		            case 1:
		                return lesPointsLivraisons.get(rowIndex).getNoeud().getIdNoeud();// ajouter getadresse, adresse n'apparait null part
		            case 2:
		                return lesPointsLivraisons.get(rowIndex).getTrancheHoraire().getHeureDebut();
		            case 3:
		                return lesPointsLivraisons.get(rowIndex).getTrancheHoraire().getHeureFin();
		            case 4:
		                return lesPointsLivraisons.get(rowIndex).getCheminSortant();

		            default:
		                return null; //Ne devrait jamais arriver
		        }
	
	}

}
