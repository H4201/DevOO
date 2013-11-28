package com.h4201.prototype.vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;

import com.h4201.prototype.controleur.Controleur;
import com.h4201.prototype.modele.Noeud;
import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.modele.Tournee;
import com.h4201.prototype.modele.TrancheHoraire;

/**
 * Fenetre avec une combobox pour selectionner une tranche horaire ou un point de livraison
 * Utilisee lors de l'ajout ou de la suppression
 * @author Marina
 *
 */
public class VueComboBox extends MouseAdapter {

	private JComboBox<TrancheHoraire> comboBox;
	private JComboBox<PointLivraison> comboBox2;
	private JLabel label;
	private JFrame fenetre;
	private JButton boutonOK;
	private TrancheHoraire trancheHoraire;
	private PointLivraison pointLivraison;
	private Noeud noeud;

	private static volatile VueComboBox instance = null;

	  /**
	   * Recuperer l'instance de VueComboBox
	   * @return L'instance de VueComboBox
	   */
	public final static VueComboBox getInstance()
	{
		if (VueComboBox.instance == null)
		{
			synchronized(VueComboBox.class)
			{
				if (VueComboBox.instance == null)
				{
					VueComboBox.instance = new VueComboBox();
				}
			}
		}

		return VueComboBox.instance;
	}
	
	/**
	 * Constructeur de VueComboBox
	 */
	private VueComboBox() {
		
	}
	
	/**
	 * Ouverture de la fenetre, avec les tranches horaires dans la combobox
	 * @param noeudClique : le noeud qui a ete clique sur le plan en mode Ajout
	 */
	public void ouvrirTrancheHoraire(Noeud noeudClique){
		noeud=noeudClique;
		
		fenetre = new JFrame("Ajout d'un point de livraison");
		fenetre.setSize(300,300);
		fenetre.setLocationRelativeTo(null);

		fenetre.getContentPane().setLayout(null);
		
		boutonOK = new JButton("OK");
		boutonOK.setLayout(null);
		boutonOK.setBounds(120, 220, 100, 40);
		fenetre.getContentPane().add(boutonOK, "South");
		
		boutonOK.addActionListener(new BoutonListener());
		
		Tournee tournee = Tournee.getInstance();
		comboBox = new JComboBox<TrancheHoraire>();
		for(int i=0; i<tournee.getTranchesHoraire().size();i++){
			comboBox.addItem(tournee.getTranchesHoraire().get(i));
		}
		comboBox.setSelectedIndex(0);
		
		label = new JLabel("Choix de la tranche horaire : ");
		JPanel top = new JPanel();
		comboBox.setBounds(0, 100, 200, 100);
		label.setLayout(null);
		label.setBounds(0, 0, 200, 100);
	    top.add(label);
	    top.add(comboBox);
	   
	    top.setLayout(null);
	    top.setBounds(0, 0, 200, 200);
	    fenetre.getContentPane().add(top, "North");		
		 
		 fenetre.repaint();
			
		 fenetre.setVisible(true);
	}
	
	/**
	 * Ouverture de la fenetre, avec des livraisons dans la combobox
	 * @param noeudClique : le noeud qui a ete clique sur le plan en mode Suppression
	 */
	public void ouvrirPointLivraison(Noeud noeudClique){
		noeud=noeudClique;

		fenetre = new JFrame("Suppression d'un point de livraison");
		fenetre.setSize(300,300);
		fenetre.setLocationRelativeTo(null);
		
		
		fenetre.getContentPane().setLayout(null);
		
		boutonOK = new JButton("OK");
		boutonOK.setLayout(null);
		boutonOK.setBounds(120, 220, 100, 40);
		fenetre.getContentPane().add(boutonOK, "South");
		
		boutonOK.addActionListener(new BoutonListener2());
		
		VueTournee tournee = VueTournee.getInstance();
		comboBox2 = new JComboBox<PointLivraison>();
		Vector <PointLivraison> listePointLiv = 
				tournee.lesPointLivraisonsClique(noeudClique.getX(), noeudClique.getY());
		for(int i=0; i<listePointLiv.size();i++)
		{
			comboBox2.addItem(listePointLiv.get(i));
		}
		comboBox2.setSelectedIndex(0);
		
		label = new JLabel("Choix du point de livraison : ");
		JPanel top = new JPanel();
		comboBox2.setBounds(0, 100, 200, 100);
		label.setLayout(null);
		label.setBounds(0, 0, 200, 100);
	    top.add(label);
	    top.add(comboBox2);
	   
	    top.setLayout(null);
	    top.setBounds(0, 0, 200, 200);
	    fenetre.getContentPane().add(top, "North");		
	    		 
		 fenetre.repaint();
			
		 fenetre.setVisible(true);
	}

	
	 public class BoutonListener implements ActionListener{
		 /**
		  * Methode appelee quand le bouton OK est clique pour le choix de tranche horaire
		  * charge la tranche horaire selectionnee et appelle la methode d'ajout du controleur
		  * @param e qui represente l'evenement : clique sur le bouton OK
		  */
		 public void actionPerformed(ActionEvent e) {
			 trancheHoraire = comboBox.getItemAt(comboBox.getSelectedIndex());
			 Controleur.getInstance().ajoutPointLivraison(noeud, trancheHoraire);
			 fenetre.dispose();
		 }
	}
	 
	 
	 public class BoutonListener2 implements ActionListener{
		 /**
		  * Methode appelee quand le bouton OK est clique pour le choix de livraison
		  * charge le point de livraison selectionne et appelle la methode de suppression du controleur
		  * @param e qui represente l'evenement : clique sur le bouton OK
		  */
		 public void actionPerformed(ActionEvent e) {
			 pointLivraison = comboBox2.getItemAt(comboBox2.getSelectedIndex());
			 Controleur.getInstance().supprimerPointLivraison(pointLivraison);
			 fenetre.dispose();
		 }
	}
}
