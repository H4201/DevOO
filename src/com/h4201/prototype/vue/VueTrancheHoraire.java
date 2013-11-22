package com.h4201.prototype.vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

import com.h4201.prototype.modele.Tournee;
import com.h4201.prototype.modele.TrancheHoraire;

public class VueTrancheHoraire extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VueTrancheHoraire frame = new VueTrancheHoraire();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VueTrancheHoraire() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		Tournee tournee = Tournee.getInstance();
		
		JComboBox<TrancheHoraire> comboBox = new JComboBox<TrancheHoraire>();
		for(int i=0; i<tournee.getTranchesHoraire().size();i++){
			
				comboBox.addItem(tournee.getTranchesHoraire().get(i));	
		}
		
		
		contentPane.add(comboBox, BorderLayout.CENTER);
	}

}