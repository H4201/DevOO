package com.h4201.prototype.vue;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;



public class VueFeuilleDeRoute extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	private JPanel contentPane;
	private JTable table;

	/**
	 * 
	 * 
	 * @param 
	 */
	

	/**
	 * Create the frame.
	 */
	public VueFeuilleDeRoute() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		table = new JTable();
		table.setModel(new FeuilleDeRouteModele());
		contentPane.add(table, BorderLayout.CENTER);
	}

}
