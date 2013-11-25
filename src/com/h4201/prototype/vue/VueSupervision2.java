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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.h4201.prototype.controleur.Controleur;
import com.h4201.prototype.modele.Noeud;
import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.modele.Tournee;
import com.h4201.prototype.utilitaire.Constante;
import com.sun.file.ExampleFileFilter;

public class VueSupervision2 extends MouseAdapter implements ActionListener
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
	private JScrollPane paneT;

	
	private static volatile VueSupervision2 instance = null;
	
	public final static VueSupervision2 getInstance(){
		if(VueSupervision2.instance ==  null){
			synchronized(VueSupervision2.class){
				if(VueSupervision2.instance == null){
					VueSupervision2.instance = new VueSupervision2(Constante.LARGEURSUPERV, Constante.HAUTEURSUPERV);
				}
			}
		}
		return VueSupervision2.instance;
	}
	
	private VueSupervision2(int x, int y){
		
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
		text.setBounds(600, 550, 100, 100);
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
				//System.out.println("nom de fichier " + jFileChooserXML.getSelectedFile().getAbsolutePath());
				
				//lecture du contenu d'un fichier XML avec DOM
				File xml = new File(jFileChooserXML.getSelectedFile().getAbsolutePath());
				Controleur.getInstance().chargerPlan(xml);
				VuePanel.getInstance().initialiserVuePlan();
				fenetre.getContentPane().add(VuePanel.getInstance());
				VuePanel.getInstance().setLayout(null);
				VuePanel.getInstance().setBounds(Constante.POSVUEX, Constante.POSVUEY, Constante.LARGEUR, Constante.HAUTEUR);
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
				VuePanel.getInstance().repaint();
				boutonCalcT.setEnabled(true);
				boutonAjouter.setEnabled(true);
				boutonSupprimer.setEnabled(true);
				
				
				
				//Tableau non affiche, trouver erreur
				tableRecap = new TableRecap(Tournee.getInstance().getTranchesHoraire());
				Object[][] objets = new Object[tableRecap.getLongueur()][1];
				String[] entetes = {"Livraisons par plages H."};
				for(int compte=0; compte<tableRecap.getLongueur(); compte=compte+1){
					objets[compte][0]=tableRecap.getLesLivraisons().get(compte);
					//System.out.println(objets[compte][0]);  //OK
				}
				tableau = new JTable(objets,entetes);
				//tableau.setLayout(null);
				//tableau.setBounds(10, 100, 300, 500);
				//fenetre.getContentPane().add(new JScrollPane(tableau));
				paneT = new JScrollPane();
				paneT.add(tableau);
				paneT.setLayout(null);
				paneT.setBounds(10, 100, 300, 500);

				paneT.setVisible(true);
				tableau.setVisible(true);
				
				fenetre.getContentPane().add(paneT);
				
				/*System.out.println("showing : " + tableau.isShowing());
				System.out.println("visible : " + tableau.isVisible());
				System.out.println("enabled : " + tableau.isEnabled());
				System.out.println("posX : " + tableau.getBounds().getX());
				System.out.println("posY : " + tableau.getBounds().getY());
				System.out.println("nb colonnes : " + tableau.getColumnCount());*/
				
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
 			boutonAjouter.setEnabled(true);
 			boutonSupprimer.setEnabled(true);
 			boutonModeNormal.setEnabled(false);
 			text.setVisible(false);
 			VuePanel.getInstance().repaint();
 		}
		else if (evt.getActionCommand().equals("Retablir")){
			boutonAnnuler.setEnabled(true);
			videRetablir = Controleur.getInstance().retablissementPossible();
			Controleur.getInstance().retablir();
 			if(!videRetablir){
 				boutonRetablir.setEnabled(false);
 			}
 			boutonCalcT.setEnabled(true);
 			boutonAjouter.setEnabled(true);
 			boutonSupprimer.setEnabled(true);
 			boutonModeNormal.setEnabled(false);
 			text.setVisible(false);
 			VuePanel.getInstance().repaint();
		}	
		else if (evt.getActionCommand().equals("Calculer la tournee")){
			Controleur.getInstance().calculTournee();
			VuePanel.getInstance().repaint();
			boutonFeuilleDeRoute.setEnabled(true);
			boutonCalcT.setEnabled(false);
			boutonAjouter.setEnabled(true);
			boutonSupprimer.setEnabled(true);
			text.setVisible(false);
		}
		else if (evt.getActionCommand().equals("Mode ajouter")){
			Controleur.getInstance().notifierClicAjouter();
			boutonModeNormal.setEnabled(true);
			boutonAjouter.setEnabled(false);
			boutonSupprimer.setEnabled(true);
			text.setVisible(false);
			VuePanel.getInstance().repaint();
		}
		else if (evt.getActionCommand().equals("Mode supprimer")){
			Controleur.getInstance().notifierClicSupprimer();
			boutonModeNormal.setEnabled(true);
			boutonAjouter.setEnabled(true);
			boutonSupprimer.setEnabled(false);
			text.setVisible(false);
			VuePanel.getInstance().repaint();
		}
		else if (evt.getActionCommand().equals("Mode normal")){
			Controleur.getInstance().notifierClicNormal();
			boutonModeNormal.setEnabled(false);
			boutonAjouter.setEnabled(true);
			boutonSupprimer.setEnabled(true);
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
		if(Constante.POSVUEX<=evt.getX() && evt.getX()<=Constante.POSVUEX+Constante.LARGEUR && Constante.POSVUEY+23<=evt.getY() && evt.getY()<=Constante.POSVUEY+Constante.HAUTEUR+23){
			posX=evt.getX() - Constante.POSVUEX;
			posY=evt.getY() - Constante.POSVUEY-23;
			System.out.println("test");
			
			System.out.println("posXEnPixel: "+ posX + " posYEnPixel: "+ posY);
			
			
			int posXEnMetre = (int) posX * Constante.LARGEURSUPERV / Constante.LARGEUR;
			int posYEnMetre = (int) posY * Constante.HAUTEURSUPERV / Constante.HAUTEUR;
			
			System.out.println("posXEnMetre: "+ posXEnMetre + " posYEnMetre: "+ posYEnMetre);
			
			noeudClique = VuePanel.getInstance().getLeNoeud(posXEnMetre, posYEnMetre);
			if(noeudClique != null)
			{
				System.out.println("xcliqu�: "+ noeudClique.getX()+ " ycliqu�: "+ noeudClique.getY());
			}
			else
			{
				System.out.println("le noeud est null");
			}

			if(noeudClique != null){
				System.out.println("clic sur noeud");
				if(Controleur.getInstance().getMode()==1){ //AJOUT
					//ouvre pop up avec tranches horaires
					//apres validation -> ajouter le point de livraison, repeindre le plan
					boutonCalcT.setEnabled(true);
					boutonFeuilleDeRoute.setEnabled(false);
					boutonAnnuler.setEnabled(true);
					boutonRetablir.setEnabled(false);
					VuePanel.getInstance().repaint();
				}
				else if(Controleur.getInstance().getMode()==2){//SUPPRESSION
					noeudEstLiv = VuePanel.getInstance().getLePointLivraison(posX, posY);
					if(noeudEstLiv!=null){
						//ouvre pop up avec tranches horaires
						//repaindre le plan      ------> cas de plusieurs livraisons?
						boutonCalcT.setEnabled(true);
						boutonFeuilleDeRoute.setEnabled(false);
						boutonAnnuler.setEnabled(true);
						boutonRetablir.setEnabled(false);
						VuePanel.getInstance().repaint();
					}
				}
				else if(Controleur.getInstance().getMode()==0){
					noeudEstLiv = VuePanel.getInstance().getLePointLivraison(posX, posY);
					if(noeudEstLiv!=null){
						System.out.println("clic pour voir");
						text.setText(noeudEstLiv.getTrancheHoraire().toString()+ "\n" + noeudEstLiv.getClient());
						text.setVisible(true);
					}
				}
			}
		}		
	}
	
	public void ErreurChargement(String messageErreur){
		//JOptionPane popupErreur = new JOptionPane();
		JOptionPane.showMessageDialog(fenetre, messageErreur, "Erreur", JOptionPane.ERROR_MESSAGE);
	}
}
