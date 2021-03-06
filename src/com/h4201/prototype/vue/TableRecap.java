package com.h4201.prototype.vue;

import java.awt.Color;
import java.awt.Component;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;



import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.modele.Tournee;
import com.h4201.prototype.modele.TrancheHoraire;
import com.h4201.prototype.utilitaire.Date;

/**
 * Il s'agit d'une structure qui va nous permettre de stocker les tranches horaires et ses points de livraisons
 * pour ensuite les afficher sur l'interface, dans un tableau recapitulatif.
 * @author Marina
 *
 */
public class TableRecap {
	
	private Vector<String> lesLivraisons;
	private Vector<Object> listeObjets;
	private JTable tableau;

	/**
	 * Constructeur de la table recapitulative
	 */
	public TableRecap()
	{
		listeObjets = new Vector<Object>();
		Vector<TrancheHoraire> tranchesHoraires = Tournee.getInstance().getTranchesHoraire();
		
		lesLivraisons = new Vector<String>();
		
		String listeLivraisonPourTH;
		for(TrancheHoraire trancheHoraire : tranchesHoraires )
		{
			listeLivraisonPourTH = "TH" + trancheHoraire.getIdTrancheHoraire() 
					+ " entre " 
					+ Date.getHeureFrSimplifieeDepuisCalendar(trancheHoraire.getHeureDebut()) 
					+ " et " 
					+ Date.getHeureFrSimplifieeDepuisCalendar(trancheHoraire.getHeureFin());
			
			lesLivraisons.add(listeLivraisonPourTH);
			listeObjets.add((Object)trancheHoraire);
			
			Vector<PointLivraison> livraisons = trancheHoraire.getPointsLivraisons();
			
			// Tri
			Collections.sort(livraisons, new Comparator<PointLivraison>() {
		        @Override public int compare(PointLivraison p1, PointLivraison p2) {
		            if(p1.getHeureArriveeEstimee() != null 
		            		&& p2.getHeureArriveeEstimee() != null)
		            {
			        	if(p1.getHeureArriveeEstimee().after(p2.getHeureArriveeEstimee()))
			            	return 1;
			            else if(p1.getHeureArriveeEstimee().before(p2.getHeureArriveeEstimee()))
			            	return -1;
		            }
		            
		            return 0;
		        }
		    });
			
			// Affichage
		    for(PointLivraison pointLivraison : livraisons)
		    {
		    	listeLivraisonPourTH = "\t\tL" + pointLivraison.getIdPointLivraison();
		    			
		    	if(pointLivraison.getHeureArriveeEstimee() != null)
		    		listeLivraisonPourTH += " a " + Date.getHeureFrSimplifieeDepuisCalendar(
		    				pointLivraison.getHeureArriveeEstimee());
		    	
		    	lesLivraisons.add(listeLivraisonPourTH);
		    	listeObjets.add((Object)pointLivraison);
		    }
		}
		
		// Transformation structure de donnees
		Object[][] objets = new Object[this.getLongueur()][1];
		String[] entetes = {"Livraisons par tranche horaire"};
		for(int compte=0; compte<this.getLongueur(); compte++){
			objets[compte][0]=this.getLesLivraisons().get(compte);
		}
		

        final Map<TrancheHoraire, Color> couleursTranchesHoraires = VuePanel.getInstance().getCouleursTranchesHoraires();
		tableau = new JTable(objets, entetes) {
			private static final long serialVersionUID = -1070527324413234766L;

			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
		    {
		        Component c = super.prepareRenderer(renderer, row, column);

		        if(couleursTranchesHoraires != null)
		        {
			        Object obj = listeObjets.get(row);
			        if(obj instanceof TrancheHoraire)
			        {
				        c.setBackground(couleursTranchesHoraires.get((TrancheHoraire)obj));
			        }
			        else if(obj instanceof PointLivraison)
			        {
			        	if(((PointLivraison)obj).getHeureArriveeEstimee() != null
			        			&& !((PointLivraison)obj).getRespecteTrancheHoraireDemandee())
			        	{
			        		c.setBackground(Color.LIGHT_GRAY);
			        	}
			        	else
			        	{
			        		c.setBackground(Color.WHITE);
			        	}
			        }
			        else
			        {
			        	c.setBackground(Color.WHITE);
			        }
		        }
		        else
		        {
		        	c.setBackground(Color.WHITE);
		        }
		        
		        return c;
		    }
			
			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		
		tableau.setFocusable(false);
		tableau.setRowSelectionAllowed(false);
	}
	
	/**
	 * Recuperer le tableau recaptitulatif (celui d'affichage).
	 * @return le tableau (celui d'affichage)
	 */
	public JTable getTableau()
	{
		return tableau;
	}
	
	/**
	 * Recuperer les string decrivant les livraisons
	 * @return les string decrivant les livraisons
	 */
	public Vector<String> getLesLivraisons(){
		return lesLivraisons;
	}
	
	/**
	 * Recuperer le nombre de livraison
	 * @return le nombre de livraison
	 */
	public int getLongueur(){
		return lesLivraisons.size();
	}

	
}
