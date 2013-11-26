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

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

import com.h4201.prototype.controleur.Controleur;
import com.h4201.prototype.modele.Noeud;
import com.h4201.prototype.modele.Tournee;
import com.h4201.prototype.modele.TrancheHoraire;
import com.h4201.prototype.utilitaire.Constante;
import com.h4201.prototype.utilitaire.Date;


public class VueTrancheHoraire2 extends MouseAdapter {

	private static final long serialVersionUID = 1L;
	//private JPanel contentPane;
	private JComboBox<String> comboBox;
	private ArrayList<TrancheHoraire> listeDesTranchesHoraire;
	private JLabel label;
	//la fenetre
	private JFrame fenetre;
	private JButton boutonOK;
	private TrancheHoraire trancheHoraire;
	private Noeud noeud;

	private static volatile VueTrancheHoraire2 instance = null;

	/**
	 * Create the frame.
	 */
	public final static VueTrancheHoraire2 getInstance()
	{
		if (VueTrancheHoraire2.instance == null)
		{
			synchronized(VueTrancheHoraire2.class)
			{
				if (VueTrancheHoraire2.instance == null)
				{
					VueTrancheHoraire2.instance = new VueTrancheHoraire2();
				}
			}
		}

		return VueTrancheHoraire2.instance;
	}
	private VueTrancheHoraire2() {
		
	}
	
	public void ouvert(Noeud noeudClique)
	{
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
		
		TrancheHoraire trancheHoraire;
		String trancheHoraireTexte;
		Tournee tournee = Tournee.getInstance();
		comboBox = new JComboBox<String>();
		for(int i=0; i<tournee.getTranchesHoraire().size();i++)
		{
			trancheHoraire = tournee.getTranchesHoraire().get(i);
			listeDesTranchesHoraire.add(trancheHoraire);
			
			trancheHoraireTexte = Date.getHeureFrSimplifieeDepuisCalendar(trancheHoraire.getHeureDebut())
			 + " - " + Date.getHeureFrSimplifieeDepuisCalendar(trancheHoraire.getHeureFin());
			comboBox.addItem(trancheHoraireTexte);
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
			 trancheHoraire = listeDesTranchesHoraire.get(comboBox.getSelectedIndex());
		 }               
	 }
	 public class BoutonListener implements ActionListener{
		 public void actionPerformed(ActionEvent e) {
			 Controleur.getInstance().ajoutPointLivraison(noeud, trancheHoraire);
			 fenetre.dispose();
			 //lancer controleur avec le noeud(comment passer l'arg?) et la tranche horaire(ok) puis fermer la fenetre
		 }
	}
	
}
