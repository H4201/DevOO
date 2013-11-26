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
import com.h4201.prototype.modele.PointLivraison;

import com.h4201.prototype.modele.TrancheHoraire;


public class VueTrancheHoraire extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<TrancheHoraire> comboBoxTrancheHoraire=new JComboBox<TrancheHoraire>();
	private JComboBox<PointLivraison> comboBoxPointLivraison=new JComboBox<PointLivraison>();
	private static Noeud noeud;


 private JButton bouton = new JButton("OK");

	private static volatile VueTrancheHoraire instance = null;

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
		centre.add(comboBoxTrancheHoraire, BorderLayout.CENTER);
		contentPane.add(centre,BorderLayout.CENTER);
		
		centre.add(comboBoxPointLivraison, BorderLayout.CENTER);

	
		// bouton.addActionListener(new BoutonListener()); 
		 VueTournee tournee = VueTournee.getInstance();
			
			// a l'ajout d'un point de livraison, on affiche les tranches horaires qui extistent dans la tournee.
			if (Controleur.getInstance().getMode()==1){	
				
		
				for(int index=0;index<tournee.getLesTrancheHoraires().length;index++){
					comboBoxTrancheHoraire.addItem(tournee.getLesVuePointLivraisons().get(index).getPointLivraison().getTrancheHoraire());
					comboBoxTrancheHoraire.setVisible(true);
					comboBoxTrancheHoraire.addItemListener(new ItemState());
					comboBoxTrancheHoraire.addActionListener(new ItemAction());
					 bouton.addActionListener(new BoutonListener()); 
							}
			}
			// pour supprimer un point de livraison, on affiche les point de livraison sur le noeud clique
			else if(Controleur.getInstance().getMode()==2){
				
				for(int indexPointLivraison=0;indexPointLivraison<tournee.getLesVuePointLivraisons().size();indexPointLivraison++){
					comboBoxPointLivraison.addItem(tournee.getLesVuePointLivraisons().get(indexPointLivraison).getPointLivraison());
					comboBoxPointLivraison.setVisible(true);
					comboBoxPointLivraison.addItemListener(new ItemState());
					comboBoxPointLivraison.addActionListener(new ItemAction2());
					 bouton.addActionListener(new BoutonListener2()); 
				}
			}
				
		
		
		
		
		 
	}
	 class ItemState implements ItemListener{
		    public void itemStateChanged(ItemEvent e) {
		    e.getItem();
		    }               
		  }
	 class ItemAction implements ActionListener{
		    public void actionPerformed(ActionEvent e) {
		    	comboBoxTrancheHoraire.getSelectedItem().toString();
	
		    }               
		  }
	 class ItemAction2 implements ActionListener{
		    public void actionPerformed(ActionEvent e) {
		 
		    	comboBoxPointLivraison.getSelectedItem().toString();
		    }               
		  }
	 
	 //Classe écoutant notre bouton
	   public class BoutonListener implements ActionListener{
	      public void actionPerformed(ActionEvent arg0) {
	    
	      Controleur.getInstance().ajoutPointLivraison(noeud, comboBoxTrancheHoraire.getItemAt(comboBoxTrancheHoraire.getSelectedIndex()));
	       bouton.setEnabled(true);
	    
	     }
	   }
	   public class BoutonListener2 implements ActionListener{
		      public void actionPerformed(ActionEvent arg0) {
		    
		      Controleur.getInstance().supprimerPointLivraison(comboBoxPointLivraison.getItemAt(comboBoxPointLivraison.getSelectedIndex()));
		       bouton.setEnabled(true);
		    
		     }
		   }
}
