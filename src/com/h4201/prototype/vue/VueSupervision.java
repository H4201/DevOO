package com.h4201.prototype.vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

//import javax.swing.JFileChooser;
import javax.swing.JFrame;

import com.h4201.prototype.modele.Noeud;
//import com.h4201.prototype.modele.Plan;

@SuppressWarnings("serial")

public class VueSupervision extends JPanel

public class VueSupervision extends MouseAdapter implements ActionListener
{
	//le modele
	//private Plan plan;
	//la vue
	//private VuePlan vuePlan;
	//la fenetre
	private JFrame fenetre;
	//pour la sauvegarde et la lecture des fichiers en xml
	//private JFileChooser jFileChooserXML;
	
	public VueSupervision(int x, int y){
		//creation de la fenetre
		fenetre = new JFrame("Supervision");
		fenetre.setSize(x,y);
		//un clic sur la croix entraine la fermeture de la fenetre
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//centrer la fenetre par rapport à l'écran de l'ordi
		fenetre.setLocationRelativeTo(null);
		
		
		
		
		
		
		fenetre.setVisible(true);
	}
	
	// Méthode appelée quand un bouton est cliqué 
	@Override
	public void actionPerformed(ActionEvent evt) {
			
	}
	
	
	public Noeud clic(double x, double y){
		return null;
	}
	public void afficher(){
		
	}
}
