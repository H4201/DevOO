package com.h4201.prototype.vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;




import java.util.Iterator;





import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.AbstractButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.h4201.prototype.controleur.Controleur;
import com.h4201.prototype.exception.ExceptionNonInstancie;
import com.h4201.prototype.modele.Noeud;
import com.h4201.prototype.modele.Plan;
import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.modele.Tournee;
import com.h4201.prototype.utilitaire.Constante;
import com.sun.file.ExampleFileFilter;

public class VueSupervision extends MouseAdapter implements ActionListener
{
	//la fenetre
	private JFrame fenetre;
	//pour la sauvegarde et la lecture des fichiers en xml
	private JFileChooser jFileChooserXML;
	private JButton boutonChargerPlan;
	private JButton boutonChargerDemande;
	private JButton boutonFeuilleDeRoute;
	private JButton boutonAnnuler;
	private JButton boutonRetablir;
	private JButton boutonCalcT;
	private JButton boutonAjouter;
	private JButton boutonSupprimer;
	private JButton boutonModeNormal;
	private TableRecap tableRecap;
	private JTable tableau;
	private JLabel text;
	
	private static volatile VueSupervision instance = null;
	
	public final static VueSupervision getInstance(){
		if(VueSupervision.instance ==  null){
			synchronized(VueSupervision.class){
				if(VueSupervision.instance == null){
					VueSupervision.instance = new VueSupervision(Constante.LARGEURSUPERV, Constante.HAUTEURSUPERV);
				}
			}
		}
		return VueSupervision.instance;
	}
	
	private VueSupervision(int x, int y){
		
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
		
		text = new JLabel();
		text.setLayout(null);
		text.setBounds(450, 625, 100, 100);
		text.setVisible(false);
		fenetre.getContentPane().add(text);
		
		boutonChargerPlan = new JButton("Charger plan");
		boutonChargerPlan.setLayout(null);
		boutonChargerPlan.setBounds(0*Constante.LBOUTON, Constante.LIGNEBOUTON1, Constante.LBOUTON, Constante.HBOUTON);
		fenetre.getContentPane().add(boutonChargerPlan, "North");
		boutons.add(boutonChargerPlan);
		
		boutonChargerDemande = new JButton("Charger demandes");
		boutonChargerDemande.setLayout(null);
		boutonChargerDemande.setBounds(1*Constante.LBOUTON, Constante.LIGNEBOUTON1, Constante.LBOUTON, Constante.HBOUTON);
		boutonChargerDemande.setEnabled(false);
		fenetre.getContentPane().add(boutonChargerDemande, "North");
		boutons.add(boutonChargerDemande);
		
		boutonFeuilleDeRoute = new JButton("Generer la feuille de route");
		boutonFeuilleDeRoute.setLayout(null);
		boutonFeuilleDeRoute.setBounds(4*Constante.LBOUTON, Constante.LIGNEBOUTON1, Constante.LBOUTON, Constante.HBOUTON);
		boutonFeuilleDeRoute.setEnabled(false);
		fenetre.getContentPane().add(boutonFeuilleDeRoute, "North");
		boutons.add(boutonFeuilleDeRoute);
		
		boutonAnnuler = new JButton("Annuler");
		boutonAnnuler.setLayout(null);
		boutonAnnuler.setBounds(0*Constante.LBOUTON, Constante.LIGNEBOUTON2, Constante.LBOUTON, Constante.HBOUTON);
		boutonAnnuler.setEnabled(false);
		fenetre.getContentPane().add(boutonAnnuler, "North");
		boutons.add(boutonAnnuler);
		
		boutonRetablir = new JButton("Retablir");
		boutonRetablir.setLayout(null);
		boutonRetablir.setBounds(1*Constante.LBOUTON, Constante.LIGNEBOUTON2, Constante.LBOUTON, Constante.HBOUTON);
		boutonRetablir.setEnabled(false);
		fenetre.getContentPane().add(boutonRetablir, "North");
		boutons.add(boutonRetablir);
		
		boutonCalcT = new JButton("Calculer la tournee");
		boutonCalcT.setLayout(null);
		boutonCalcT.setBounds(0*Constante.LBOUTON, Constante.LIGNEBOUTON3, Constante.LBOUTON, Constante.HBOUTON);
		boutonCalcT.setEnabled(false);
		fenetre.getContentPane().add(boutonCalcT, "South");
		boutons.add(boutonCalcT);
		
		boutonAjouter = new JButton("Mode ajouter");
		boutonAjouter.setLayout(null);
		boutonAjouter.setBounds(2*Constante.LBOUTON, Constante.LIGNEBOUTON3, Constante.LBOUTON, Constante.HBOUTON);
		boutonAjouter.setEnabled(false);
		fenetre.getContentPane().add(boutonAjouter, "South");
		boutons.add(boutonAjouter);
		
		boutonSupprimer = new JButton("Mode supprimer");
		boutonSupprimer.setLayout(null);
		boutonSupprimer.setBounds(3*Constante.LBOUTON, Constante.LIGNEBOUTON3, Constante.LBOUTON, Constante.HBOUTON);
		boutonSupprimer.setEnabled(false);
		fenetre.getContentPane().add(boutonSupprimer, "South");
		boutons.add(boutonSupprimer);
		
		boutonModeNormal = new JButton("Mode normal");
		boutonModeNormal.setLayout(null);
		boutonModeNormal.setBounds(4*Constante.LBOUTON, Constante.LIGNEBOUTON3, Constante.LBOUTON, Constante.HBOUTON);
		boutonModeNormal.setEnabled(false);
		fenetre.getContentPane().add(boutonModeNormal, "South");
		boutons.add(boutonModeNormal);
		
		//Incrisption de this comme ecouteur (listener) des boutons
		Iterator<AbstractButton> it = boutons.iterator();
		while(it.hasNext())
			it.next().addActionListener(this);
		
		//Inscription de this comme ecouteur des evenements souris dans la fenetre
		fenetre.addMouseListener(this);
		
		fenetre.repaint();
		
		fenetre.setVisible(true);
	}
	
	// Methode appelee quand un bouton est clique 
	@Override
	public void actionPerformed(ActionEvent evt) {
		boolean videRetablir;
		boolean videAnnuler;
		if (evt.getActionCommand().equals("Charger plan")){
			int returnVal = jFileChooserXML.showOpenDialog(null);
			text.setVisible(false);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				// debug
				System.out.println("nom de fichier " + jFileChooserXML.getSelectedFile().getAbsolutePath());
				
				//lecture du contenu d'un fichier XML avec DOM
				File xml = new File(jFileChooserXML.getSelectedFile().getAbsolutePath());
				Controleur.getInstance().chargerPlan(xml);
				VuePlan.getInstance().initialiserVuePlan();
				fenetre.getContentPane().add(VuePlan.getInstance());
				VuePlan.getInstance().setLayout(null);
				VuePlan.getInstance().setBounds(Constante.POSVUEX, Constante.POSVUEY, Constante.LARGEUR, Constante.HAUTEUR);
				boutonChargerDemande.setEnabled(true);
			}
		}
		else if (evt.getActionCommand().equals("Charger demandes")){
			int returnVal = jFileChooserXML.showOpenDialog(null);
			text.setVisible(false);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				// debug
				System.out.println("nom de fichier " + jFileChooserXML.getSelectedFile().getAbsolutePath());
					
				//lecture du contenu d'un fichier XML avec DOM
				File xml = new File(jFileChooserXML.getSelectedFile().getAbsolutePath());
				Controleur.getInstance().chargerDemandeLivraison(xml);
				VuePlan.getInstance().repaint();
				boutonCalcT.setEnabled(true);
				boutonAjouter.setEnabled(true);
				boutonSupprimer.setEnabled(true);
				
				
				
				//Tableau non affiche, trouver erreur
				tableRecap = new TableRecap(Tournee.getInstance().getTranchesHoraire());
				Object[][] objets = new Object[1][tableRecap.getLongueur()];
				String[] entetes = {"Livraisons par plages H."};
				for(int compte=0; compte<tableRecap.getLongueur(); compte=compte+1){
					objets[compte][0]=tableRecap.getLesLivraisons().get(compte);
				}
				tableau = new JTable(objets,entetes);
				//tableau.setLayout(null);
				//tableau.setBounds(0, 60, 100, 300);
				fenetre.getContentPane().add(new JScrollPane(tableau));
				fenetre.repaint();
				
				
				
				
				
				
			}
		}
		else if (evt.getActionCommand().equals("Generer la feuille de route")){
			//fonction xinlu pour enregistrer dans un fichier
		}
 		else if (evt.getActionCommand().equals("Annuler")){
 			boutonRetablir.setEnabled(true);
 			Controleur.getInstance().annuler();
 			videAnnuler = Controleur.getInstance().annulationPossible();
 			if(!videAnnuler){
 				boutonAnnuler.setEnabled(false);
 			}
 			boutonCalcT.setEnabled(true);
 			text.setVisible(false);
 		}
		else if (evt.getActionCommand().equals("Retablir")){
			boutonAnnuler.setEnabled(true);
			videRetablir = Controleur.getInstance().retablissementPossible();
			Controleur.getInstance().retablir();
 			if(!videRetablir){
 				boutonRetablir.setEnabled(false);
 			}
 			boutonCalcT.setEnabled(true);
 			text.setVisible(false);
		}	
		else if (evt.getActionCommand().equals("Calculer la tournee")){
			Controleur.getInstance().calculTournee();
			VuePlan.getInstance().repaint();
			boutonFeuilleDeRoute.setEnabled(true);
			boutonCalcT.setEnabled(false);
			text.setVisible(false);
		}
		else if (evt.getActionCommand().equals("Mode ajouter")){
			Controleur.getInstance().notifierClicAjouter();
			text.setVisible(false);
		}
		else if (evt.getActionCommand().equals("Mode supprimer")){
			Controleur.getInstance().notifierClicSupprimer();
			text.setVisible(false);
		}
		else if (evt.getActionCommand().equals("Mode normal")){
			Controleur.getInstance().notifierClicNormal();
		}
		fenetre.repaint();
	}
	
	
	//Methode appelee quand la souris est cliquee dans la fenetre
	@Override
	public void mouseClicked(MouseEvent evt){
		double posX;
		double posY;
		Noeud noeudClique;
		PointLivraison noeudEstLiv;
		//si le clic a eu lieu dans le plan (POSVUEX<=x<=POSVUEX+LARGEUR et POSVUEY<=y<=POSVUEY+HAUTEUR)
		if(Constante.POSVUEX<=evt.getX() && evt.getX()<=Constante.POSVUEX+Constante.LARGEUR && Constante.POSVUEY+22<=evt.getY() && evt.getY()<=Constante.POSVUEY+Constante.HAUTEUR+22){
			posX=evt.getX()-Constante.POSVUEX;
			posY=evt.getY()-Constante.POSVUEY-22;
			
			posX=posX*Constante.CONVERSION_PIXELS_EN_METRES*Constante.LARGEUR;
			posY=posY*Constante.CONVERSION_PIXELS_EN_METRES*Constante.HAUTEUR;
			noeudClique = VuePlan.getInstance().getLeNoeud(posX, posY);
			System.out.println("il y a eu un clic");
			System.out.println("evt : " + evt.getX() + ", " + evt.getY());
			System.out.println("pos : " + posX + ", " + posY);

			if(noeudClique != null){
				System.out.println("clic sur noeud");
				if(Controleur.getInstance().getMode()==1){ //AJOUT
					//ouvre pop up avec tranches horaires
					//apres validation -> ajouter le point de livraison, repaindre le plan
					boutonCalcT.setEnabled(true);
					boutonFeuilleDeRoute.setEnabled(false);
					boutonAnnuler.setEnabled(true);
					boutonRetablir.setEnabled(false);
				}
				else if(Controleur.getInstance().getMode()==2){//SUPPRESSION
					noeudEstLiv = VuePlan.getInstance().getLePointLivraison(posX, posY, VueTournee.getInstance().getLesVuePointLivraisons());
					if(noeudEstLiv!=null){
						//ouvre pop up avec tranches horaires
						//repaindre le plan      ------> cas de plusieurs livraisons?
						boutonCalcT.setEnabled(true);
						boutonFeuilleDeRoute.setEnabled(false);
						boutonAnnuler.setEnabled(true);
						boutonRetablir.setEnabled(false);
					}
				}
				else if(Controleur.getInstance().getMode()==0){
					noeudEstLiv = VuePlan.getInstance().getLePointLivraison(posX, posY, VueTournee.getInstance().getLesVuePointLivraisons());
					if(noeudEstLiv!=null){
						System.out.println("clic pour voir");
						text.setText(noeudEstLiv.getTrancheHoraire().toString()+ "\n" + noeudEstLiv.getClient());
						text.setVisible(true);
					}
				}
			}
		}		
	}
}
