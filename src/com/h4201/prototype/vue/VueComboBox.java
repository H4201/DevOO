package com.h4201.prototype.vue;

import java.awt.BorderLayout;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

import com.h4201.prototype.controleur.Controleur;
import com.h4201.prototype.modele.Noeud;
import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.modele.Tournee;
import com.h4201.prototype.modele.TrancheHoraire;
import com.h4201.prototype.utilitaire.Constante;


public class VueComboBox extends MouseAdapter {

	private static final long serialVersionUID = 1L;
	//private JPanel contentPane;
	private JComboBox<TrancheHoraire> comboBox;
	private JComboBox<PointLivraison> comboBox2;
	private JLabel label;
	//la fenetre
	private JFrame fenetre;
	private JButton boutonOK;
	private TrancheHoraire trancheHoraire;
	private PointLivraison pointLivraison;
	private Noeud noeud;

	private static volatile VueComboBox instance = null;

	/**
	 * Create the frame.
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
	private VueComboBox() {
		
	}
	
	
	public void ouvrirTrancheHoraire(Noeud noeudClique){
		noeud=noeudClique;
		//TrancheHoraire trancheHoraire; 
		
		//creation de la fenetre
		fenetre = new JFrame("Supervision");
		fenetre.setSize(300,300);
		//un clic sur la croix entraine la fermeture de la fenetre
		//fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//centrer la fenetre par rapport a l'ecran de l'ordi
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
		label = new JLabel("TranchesHoraires");
		JPanel top = new JPanel();
		//comboBox.setLayout(null);
		comboBox.setBounds(0, 100, 200, 100);
		label.setLayout(null);
		label.setBounds(0, 0, 200, 100);
	    top.add(label);
	    top.add(comboBox);
	   
	    top.setLayout(null);
	    top.setBounds(0, 0, 200, 200);
	    fenetre.getContentPane().add(top, "North");		
	    
				
		
		 comboBox.addItemListener(new ItemState());
		 comboBox.addActionListener(new ItemAction());
		 
		 fenetre.repaint();
			
		 fenetre.setVisible(true);
	}
	public void ouvrirPointLivraison(Noeud noeudClique){
		noeud=noeudClique;
		//TrancheHoraire trancheHoraire; 
		
		//creation de la fenetre
		fenetre = new JFrame("Supervision");
		fenetre.setSize(300,300);
		//un clic sur la croix entraine la fermeture de la fenetre
		//fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//centrer la fenetre par rapport a l'ecran de l'ordi
		fenetre.setLocationRelativeTo(null);
		
		
		fenetre.getContentPane().setLayout(null);
		
		boutonOK = new JButton("OK");
		boutonOK.setLayout(null);
		boutonOK.setBounds(120, 220, 100, 40);
		fenetre.getContentPane().add(boutonOK, "South");
		
		boutonOK.addActionListener(new BoutonListener2());
		
		VueTournee tournee = VueTournee.getInstance();
		comboBox2 = new JComboBox<PointLivraison>();
		System.out.println(tournee.toString());
		Vector <PointLivraison> listePointLiv=tournee.lesPointLivraisonsClique(noeudClique.getX(), noeudClique.getY());
		for(int i=0; i<listePointLiv.size();i++){
			comboBox2.addItem(listePointLiv.get(i));
		}
		label = new JLabel("PointLivraison");
		JPanel top = new JPanel();
		//comboBox.setLayout(null);
		comboBox2.setBounds(0, 100, 200, 100);
		label.setLayout(null);
		label.setBounds(0, 0, 200, 100);
	    top.add(label);
	    top.add(comboBox2);
	   
	    top.setLayout(null);
	    top.setBounds(0, 0, 200, 200);
	    fenetre.getContentPane().add(top, "North");		
	    
				
		
	    comboBox2.addItemListener(new ItemState());
	    comboBox2.addActionListener(new ItemAction2());
		 
		 fenetre.repaint();
			
		 fenetre.setVisible(true);
	}
	
	 class ItemState implements ItemListener{
		 public void itemStateChanged(ItemEvent e) {
			 //e.getItem();
//			 System.out.println("evenement declenche sur : " + e.getItem());
		  }               
	}

	 class ItemAction implements ActionListener{
		 public void actionPerformed(ActionEvent e) {
			 //comboBox.getSelectedItem();
//			 System.out.println("ActionListener : action sur " + comboBox.getSelectedItem());
			 trancheHoraire = comboBox.getItemAt(comboBox.getSelectedIndex());
		 }               
	 }
	 class ItemAction2 implements ActionListener{
		 public void actionPerformed(ActionEvent e) {
			 //comboBox.getSelectedItem();
//			 System.out.println("ActionListener : action sur " + comboBox2.getSelectedItem());
			 pointLivraison = comboBox2.getItemAt(comboBox2.getSelectedIndex());
		 }               
	 }
	 public class BoutonListener implements ActionListener{
		 public void actionPerformed(ActionEvent e) {
//			 System.out.println(trancheHoraire.toString());
			 Controleur.getInstance().ajoutPointLivraison(noeud, trancheHoraire);
			 fenetre.dispose();
			 //lancer controleur avec le noeud(comment passer l'arg?) et la tranche horaire(ok) puis fermer la fenetre

		 }
	}
	 public class BoutonListener2 implements ActionListener{
		 public void actionPerformed(ActionEvent e) {
//			 System.out.println(pointLivraison.toString());
			 Controleur.getInstance().supprimerPointLivraison(pointLivraison);
			 fenetre.dispose();
			 //lancer controleur avec le noeud(comment passer l'arg?) et la tranche horaire(ok) puis fermer la fenetre

		 }
	}
}
