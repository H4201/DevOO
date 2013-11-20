package com.h4201.prototype.vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;




import javax.swing.JButton;
//import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.AbstractButton;

import com.h4201.prototype.modele.Noeud;
//import com.h4201.prototype.modele.Plan;

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
		//centrer la fenetre par rapport a l'ecran de l'ordi
		fenetre.setLocationRelativeTo(null);
		
		// Creation d'une liste contenant tous les boutons		
		ArrayList<AbstractButton> boutons = new ArrayList<AbstractButton>();
		
		fenetre.getContentPane().setLayout(null);
		
		JButton boutonChargerPlan = new JButton("Charger plan");
		boutonChargerPlan.setLayout(null);
		boutonChargerPlan.setBounds(0, 0, 300, 30);
		fenetre.getContentPane().add(boutonChargerPlan, "North");
		boutons.add(boutonChargerPlan);
		
		JButton boutonChargerDemande = new JButton("Charger demandes de livraisons");
		boutonChargerDemande.setLayout(null);
		boutonChargerDemande.setBounds(300, 0, 300, 30);
		fenetre.getContentPane().add(boutonChargerDemande, "North");
		boutons.add(boutonChargerDemande);
		
		JButton boutonFeuilleDeRoute = new JButton("Generer la feuille de route");
		boutonFeuilleDeRoute.setLayout(null);
		boutonFeuilleDeRoute.setBounds(600, 0, 300, 30);
		fenetre.getContentPane().add(boutonFeuilleDeRoute, "North");
		boutons.add(boutonFeuilleDeRoute);
		
		JButton boutonAnnuler = new JButton("Annuler");
		boutonAnnuler.setLayout(null);
		boutonAnnuler.setBounds(0, 25, 300, 30);
		fenetre.getContentPane().add(boutonAnnuler, "North");
		boutons.add(boutonAnnuler);
		
		JButton boutonRetablir = new JButton("Retablir");
		boutonRetablir.setLayout(null);
		boutonRetablir.setBounds(300, 25, 300, 30);
		fenetre.getContentPane().add(boutonRetablir, "North");
		boutons.add(boutonRetablir);
		
		JButton boutonCalcT = new JButton("Calculer la tournee");
		boutonCalcT.setLayout(null);
		boutonCalcT.setBounds(0, 650, 300, 30);
		fenetre.getContentPane().add(boutonCalcT, "South");
		boutons.add(boutonCalcT);
		
		JButton boutonAjouter = new JButton("Ajouter");
		boutonAjouter.setLayout(null);
		boutonAjouter.setBounds(300, 650, 300, 30);
		fenetre.getContentPane().add(boutonAjouter, "South");
		boutons.add(boutonAjouter);
		
		JButton boutonSupprimer = new JButton("Supprimer");
		boutonSupprimer.setLayout(null);
		boutonSupprimer.setBounds(600, 650, 300, 30);
		fenetre.getContentPane().add(boutonSupprimer, "South");
		boutons.add(boutonSupprimer);
		
		fenetre.repaint();
		
		fenetre.setVisible(true);
	}
	
	// Methode appelee quand un bouton est clique 
	@Override
	public void actionPerformed(ActionEvent evt) {
        
	}
	
	public Noeud clic(double x, double y){
		return null;
	}
	public void afficher(){
		
	}
}
