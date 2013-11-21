package com.h4201.prototype.vue;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



public class VueFeuilleDeRoute extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

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
		super();
		setTitle("Feuille De Route");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		table = new JTable(new DefaultTableModel());
        getContentPane().add(table.getTableHeader(), BorderLayout.NORTH);
        getContentPane().add(table, BorderLayout.CENTER);
 
        pack();
	
	}

	public void afficherFeuilleDeRoute()
	{
		table.setVisible(true);
	}

}
