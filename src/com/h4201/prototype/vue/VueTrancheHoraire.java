package com.h4201.prototype.vue;

import java.awt.BorderLayout;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

import com.h4201.prototype.controleur.Controleur;
import com.h4201.prototype.modele.Noeud;
import com.h4201.prototype.modele.Tournee;
import com.h4201.prototype.modele.TrancheHoraire;


public class VueTrancheHoraire extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<TrancheHoraire> comboBox=new JComboBox<TrancheHoraire>();
	private Tournee tournee;

 private JButton bouton = new JButton("OK");

	private static volatile VueTrancheHoraire instance = null;
	private static Noeud noeud;
	/**
	 * Create the frame.
	 */
	public final static VueTrancheHoraire getInstance()
	{
		if (VueTrancheHoraire.instance == null)
		{
			synchronized(VueTrancheHoraire.class)
			{
				if (VueTrancheHoraire.instance == null)
				{
					
					VueTrancheHoraire.instance = new VueTrancheHoraire( noeud);
				}
			}
		}

		return VueTrancheHoraire.instance;
	}
	public VueTrancheHoraire(Noeud noeudclique) { 
		noeudclique=noeud;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setVisible(true);
		//contentPane.add(comboBox, BorderLayout.CENTER);
		contentPane.add(bouton,BorderLayout.SOUTH);
		
		JPanel centre = new JPanel();
		centre.add(comboBox, BorderLayout.CENTER);

	contentPane.add(centre,BorderLayout.CENTER);
		 bouton.addActionListener(new BoutonListener()); 
		

		
		
			// a l'ajout d'un point de livraison, on elimine la tranche horaire qui existe deja.
			if (Controleur.getInstance().getMode()==1){	
				for(int indexTrancheHoraire=0;indexTrancheHoraire<tournee.getTranchesHoraire().size();indexTrancheHoraire++){
							comboBox.addItem(tournee.getTranchesHoraire().get(indexTrancheHoraire));
							comboBox.setVisible(true);
							}
			}
			// pour supprimer un point de livraison, on affiche que les tranches horaires des point de livraison sur le noeud clique
			else if(Controleur.getInstance().getMode()==2){
			
				VueTournee tourneeCourant = VueTournee.getInstance();
				for(int indexPointLivraison=0;indexPointLivraison<tourneeCourant.getLesVuePointLivraisons().size();indexPointLivraison++){
				comboBox.addItem(tourneeCourant.getLesVuePointLivraisons().get(indexPointLivraison).getPointLivraison().getTrancheHoraire());
				comboBox.setVisible(true);
				}
			}
				
		
		
		 comboBox.addItemListener(new ItemState());
		 comboBox.addActionListener(new ItemAction());
		
		 
	}
	 class ItemState implements ItemListener{
		    public void itemStateChanged(ItemEvent e) {
		    e.getItem();
		    }               
		  }
	 class ItemAction implements ActionListener{
		    public void actionPerformed(ActionEvent e) {
		 comboBox.getSelectedItem().toString();
		    }               
		  }
	 //Classe écoutant notre bouton
	   public class BoutonListener implements ActionListener{
	      public void actionPerformed(ActionEvent arg0) {
	      Controleur.getInstance().ajoutPointLivraison(noeud, comboBox.getItemAt(comboBox.getSelectedIndex()));
	       bouton.setEnabled(true);
	    
	     }
	   }
	
}
