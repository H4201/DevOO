package com.h4201.prototype.vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.util.ArrayList;




import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.AbstractButton;

import com.h4201.prototype.controleur.Controleur;
import com.h4201.prototype.modele.Noeud;
//import com.h4201.prototype.modele.Plan;
import com.sun.file.ExampleFileFilter;

public class VueSupervision extends MouseAdapter implements ActionListener
{
	//le modele
	//private Plan plan;
	//la vue
	//private VuePlan vuePlan;
	//la fenetre
	private JFrame fenetre;
	//pour la sauvegarde et la lecture des fichiers en xml
	private JFileChooser jFileChooserXML;
	
	public VueSupervision(int x, int y){
		
		//creation de la fenetre
		fenetre = new JFrame("Supervision");
		
		jFileChooserXML = new JFileChooser();
	    ExampleFileFilter filter = new ExampleFileFilter();
	    filter.addExtension("xml");
	    filter.setDescription("Fichier XML");
	    jFileChooserXML.setFileFilter(filter);
	    jFileChooserXML.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
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
		
		//Incrisption de this comme ecouteur (listener) des boutons
		Iterator<AbstractButton> it = boutons.iterator();
		while(it.hasNext())
			it.next().addActionListener(this);
		
		//Inscription de this comme ecouteur des evenements souris dans la fenetre
		//fenetre.addMouseListener(this);
		
		fenetre.repaint();
		
		fenetre.setVisible(true);
	}
	
	// Methode appelee quand un bouton est clique 
	@Override
	public void actionPerformed(ActionEvent evt) {
		if (evt.getActionCommand().equals("Charger plan")){
			int returnVal = jFileChooserXML.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				// debug
				System.out.println("nom de fichier " + jFileChooserXML.getSelectedFile().getAbsolutePath());
				
				//lecture du contenu d'un fichier XML avec DOM
				File xml = new File(jFileChooserXML.getSelectedFile().getAbsolutePath());
				//Controleur.getInstance().chargerPlan(xml);
			}
		}
		else if (evt.getActionCommand().equals("Charger demandes de livraisons")){
			int returnVal = jFileChooserXML.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				// debug
				System.out.println("nom de fichier " + jFileChooserXML.getSelectedFile().getAbsolutePath());
					
				//lecture du contenu d'un fichier XML avec DOM
				File xml = new File(jFileChooserXML.getSelectedFile().getAbsolutePath());
				//Controleur.getInstance().chargerDemandeLivraison(xml);
			}
		}
			
		/*else if (evt.getActionCommand().equals("Generer la feuille de route"))
			
 		else if (evt.getActionCommand().equals("Annuler"))
			
		else if (evt.getActionCommand().equals("Retablir"))
			
		else if (evt.getActionCommand().equals("Calculer la tournee"))
			
		else if (evt.getActionCommand().equals("Ajouter"))
			
		else if (evt.getActionCommand().equals("Supprimer"))
			
	    else // ne rien faire
			*/
		fenetre.repaint();
	}
	
	public Noeud clic(double x, double y){
		return null;
	}
	public void afficher(){
		
	}
}
