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
import com.h4201.prototype.exception.ExceptionNoeudInconnu;
import com.h4201.prototype.modele.Noeud;
import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.utilitaire.Constante;
import com.h4201.prototype.utilitaire.Date;
import com.sun.file.ExampleFileFilter;

/**
 * Vuesupervision est l'instance qui va permettre et faire l'affichage de la fenetre de supervision
 * @author Marina
 *
 */
public class VueSupervision extends MouseAdapter implements ActionListener
{
	private JFrame fenetre;
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
	private JLabel textTH;
	private JLabel textPL;
	private JScrollPane paneT;

	
	private static volatile VueSupervision instance = null;
	
	  /**
	   * Recuperer l'instance de VueSupervision.
	   * @return L'instance de VueSupervision.
	   */
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
	
	/**
	 * Constructeur de VueSupervision
	 * @param x : la longueur de la fenetre
	 * @param y : la hauteur de la fenetre
	 */
	private VueSupervision(int x, int y){
		
		fenetre = new JFrame("Supervision");
		
		jFileChooserXML = new JFileChooser();
	    ExampleFileFilter filter = new ExampleFileFilter();
	    filter.addExtension("xml");
	    filter.setDescription("Fichier XML");
	    jFileChooserXML.setFileFilter(filter);
	    jFileChooserXML.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		fenetre.setSize(x,y);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setLocationRelativeTo(null);

		// Creation d'une liste contenant tous les boutons		
		ArrayList<AbstractButton> boutons = new ArrayList<AbstractButton>();
		
		fenetre.getContentPane().setLayout(null);
		
		textTH = new JLabel();
		textTH.setLayout(null);
		textTH.setBounds(50, 550, 800, 300);
		textTH.setVisible(false);
		fenetre.getContentPane().add(textTH);
		
		textPL = new JLabel();
		textPL.setLayout(null);
		textPL.setBounds(50, 570, 900, 300);
		textPL.setVisible(false);
		fenetre.getContentPane().add(textPL);
		
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
	
	  /**
	   * Methode appelee quand un bouton est clique
	   * @param ActionEvent evt qui represente l'evenement : clique sur un bouton
	   */
	@Override
	public void actionPerformed(ActionEvent evt) {
		boolean videRetablir;
		boolean videAnnuler;
		if (evt.getActionCommand().equals("Charger plan")){
			int returnVal = jFileChooserXML.showOpenDialog(null);
			textTH.setVisible(false);
			textPL.setVisible(false);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				
				File xml = new File(jFileChooserXML.getSelectedFile().getAbsolutePath());
				Controleur.getInstance().chargerPlan(xml);
				VuePanel.getInstance().initialiserVuePlan();
				fenetre.getContentPane().add(VuePanel.getInstance());
				VuePanel.getInstance().setLayout(null);
				VuePanel.getInstance().setBounds(Constante.POSVUEX, Constante.POSVUEY, Constante.LARGEUR, Constante.HAUTEUR);
				boutonChargerDemande.setEnabled(true);
				
				//pour cas ou on est pas au premier chargement, il faut passer les boutons en gris
				boutonAnnuler.setEnabled(false);
				boutonRetablir.setEnabled(false);
				boutonCalcT.setEnabled(false);
				boutonAjouter.setEnabled(false);
				boutonSupprimer.setEnabled(false);
				boutonFeuilleDeRoute.setEnabled(false);
			}
		}
		else if (evt.getActionCommand().equals("Charger demandes")){
			int returnVal = jFileChooserXML.showOpenDialog(null);
			textTH.setVisible(false);
			textPL.setVisible(false);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
					
				//lecture du contenu d'un fichier XML avec DOM
				File xml = new File(jFileChooserXML.getSelectedFile().getAbsolutePath());
				Controleur.getInstance().chargerDemandeLivraison(xml);
				VuePanel.getInstance().repaint();
				boutonCalcT.setEnabled(true);
				boutonAjouter.setEnabled(true);
				boutonSupprimer.setEnabled(true);

				
				//Tableau des horaires				
				majTableDesTranchesHoraire();
			}
		}
		else if (evt.getActionCommand().equals("Generer la feuille de route")){
			FeuilleDeRouteEnTexte.getInstance().realisation();
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
 			textPL.setVisible(false);
 			textTH.setVisible(false);
 			VuePanel.getInstance().repaint();
 			
 			majTableDesTranchesHoraire();
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
 			textPL.setVisible(false);
 			textTH.setVisible(false);
 			VuePanel.getInstance().repaint();
 			
 			majTableDesTranchesHoraire();
		}	
		else if (evt.getActionCommand().equals("Calculer la tournee")){
			Controleur.getInstance().calculTournee();
			VuePanel.getInstance().repaint();
			boutonFeuilleDeRoute.setEnabled(true);
			boutonCalcT.setEnabled(false);
			boutonAjouter.setEnabled(true);
			boutonSupprimer.setEnabled(true);
			textPL.setVisible(false);
			textTH.setVisible(false);
			
			majTableDesTranchesHoraire();
		}
		else if (evt.getActionCommand().equals("Mode ajouter")){
			Controleur.getInstance().notifierClicAjouter();
			boutonModeNormal.setEnabled(true);
			boutonAjouter.setEnabled(false);
			boutonSupprimer.setEnabled(true);
			textPL.setVisible(false);
			textTH.setVisible(false);
			VuePanel.getInstance().repaint();
		}
		else if (evt.getActionCommand().equals("Mode supprimer")){
			Controleur.getInstance().notifierClicSupprimer();
			boutonModeNormal.setEnabled(true);
			boutonAjouter.setEnabled(true);
			boutonSupprimer.setEnabled(false);
			textPL.setVisible(false);
			textTH.setVisible(false);
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
	
	  /**
	   * Mise a jour de la table recapitulative des livraisons par tranche horaire (structure + affichage)
	   */
	public void majTableDesTranchesHoraire()
	{
		if(paneT != null) fenetre.getContentPane().remove(paneT);
		
		tableRecap = new TableRecap();
		
		tableau = tableRecap.getTableau();
		paneT = new JScrollPane(tableau);
		paneT.setBounds(10, 100, 300, 500);

		paneT.setVisible(true);
		tableau.setVisible(true);

		fenetre.getContentPane().add(paneT);
	}
	
	  /**
	   * Methode appelee quand la souris est cliquee dans la fenetre supervision
	   * Sert pour les cliques sur les noeuds du plan
	   * @param idNoeud id du noeud recherche.
	   */
	@Override
	public void mouseClicked(MouseEvent evt){
		double posX;
		double posY;
		Noeud noeudClique;
		PointLivraison noeudEstLiv;
		//si le clic a eu lieu dans le plan (POSVUEX<=x<=POSVUEX+LARGEUR et POSVUEY<=y<=POSVUEY+HAUTEUR)
		if(Constante.POSVUEX<=evt.getX() 
				&& evt.getX()<=Constante.POSVUEX+Constante.LARGEUR 
				&& Constante.POSVUEY+23<=evt.getY() 
				&& evt.getY()<=Constante.POSVUEY+Constante.HAUTEUR+23)
		{
			posX=evt.getX()-Constante.POSVUEX;
			posY=evt.getY()-Constante.POSVUEY-23;
			
			posX=(int)posX*Constante.LARGEURSUPERV/Constante.LARGEUR;
			posY=(int)posY*Constante.HAUTEURSUPERV/Constante.HAUTEUR;
			noeudClique = VuePlan.getInstance().getLeNoeud(posX, posY);


			if(noeudClique != null)
			{
				if(Controleur.getInstance().getMode()==Constante.MODE_AJOUT){ //AJOUT
					VueComboBox.getInstance().ouvrirTrancheHoraire(noeudClique);
					boutonCalcT.setEnabled(true);
					boutonFeuilleDeRoute.setEnabled(false);
					boutonAnnuler.setEnabled(true);
					boutonRetablir.setEnabled(false);
					VuePanel.getInstance().repaint();
					
					majTableDesTranchesHoraire();
				}
				else if(Controleur.getInstance().getMode()==Constante.MODE_SUPPRESSION){//SUPPRESSION
					noeudEstLiv = VuePanel.getInstance().getLePointLivraison(posX, posY);
					if(noeudEstLiv!=null){
						VueComboBox.getInstance().ouvrirPointLivraison(noeudClique);
						boutonCalcT.setEnabled(true);
						boutonFeuilleDeRoute.setEnabled(false);
						boutonAnnuler.setEnabled(true);
						boutonRetablir.setEnabled(false);
						VuePanel.getInstance().repaint();
						
						majTableDesTranchesHoraire();
					}
				}
				else if(Controleur.getInstance().getMode()==Constante.MODE_NORMAL){
					noeudEstLiv = VuePanel.getInstance().getLePointLivraison(posX, posY);
					if(noeudEstLiv!=null)
					{
						String infos = "Livraison L" + noeudEstLiv.getIdPointLivraison();
						if(noeudEstLiv.getHeureArriveeEstimee() != null)
						{
							infos += "\nHeure arrivee estimee : " + 
									Date.getHeureFrSimplifieeDepuisCalendar(
									noeudEstLiv.getHeureArriveeEstimee());
						}
						
						textTH.setText(infos);
						textTH.setVisible(true);
						textPL.setText(noeudEstLiv.toString());
						textPL.setVisible(true);
					}
				}
			}
			else{
			}
		}
	}
	
	  /**
	   * popup d'erreur 
	   * @param message d'erreur a afficher dans la popup
	   */
	public void fenetreErreur(String messageErreur){
		JOptionPane.showMessageDialog(fenetre, messageErreur, "Erreur", JOptionPane.ERROR_MESSAGE);
	}
}
