package com.h4201.prototype.vue;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.h4201.prototype.modele.Chemin;
import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.modele.Tournee;
import com.h4201.prototype.modele.TrancheHoraire;
import com.h4201.prototype.utilitaire.Constante;

public class VueTournee 
{
	private static volatile VueTournee instance = null;
	private Vector<VueChemin> lesVueChemins;
	private VueEntrepot vueEntrepot;
	private Vector<VuePointLivraison> lesVuePointLivraisons;
	private TrancheHoraire lesTrancheHoraires[];
	private Map<TrancheHoraire, Color> couleursTranchesHoraires;
	private Vector<PointLivraison> lesPointLivraisonsClique;


	public Vector<VueChemin> getLesVueChemins()
	{
		return lesVueChemins;
	}

	public TrancheHoraire[] getLesTrancheHoraires()
	{
		return lesTrancheHoraires;
	}

	public VueEntrepot getVueEntrepot()
	{
		return vueEntrepot;
	}

	public Vector<VuePointLivraison> getLesVuePointLivraisons()
	{
		return lesVuePointLivraisons;
	}

	public Map<TrancheHoraire, Color> getCouleursTranchesHoraires()
	{
		return this.couleursTranchesHoraires;
	}
	
	/**
	 * Constructeur de la vue pour une tournee.
	 */
	private VueTournee()
	{
		super();
	}
	
	/**
	 * Cette methode permet de renvoyer une instance de la classe VueTournee.
	 * @return instance du singleton VueTournee
	 */
	public final static VueTournee getInstance()
	{
		if (VueTournee.instance == null)
		{
			synchronized(VueTournee.class)
			{
				if (VueTournee.instance == null)
				{
					VueTournee.instance = new VueTournee();
				}
			}
		}

		return VueTournee.instance;
	}
	
	/**
	 * Cette methode permet d'initialiser une tournee
	 * On recupere ainsi toute les tranches horaires et on associe a chaque tranche horaire une couleur predefinie.
	 * @return vrai si la map des tranches horaires - couleurs n'est pas vide.
	 */
	protected boolean initialiserTout()
	{
		// On recupere la tournee
		boolean flag = false;
		Tournee tournee = Tournee.getInstance();

		couleursTranchesHoraires = new HashMap<TrancheHoraire,Color>();
		lesTrancheHoraires = tournee.getTranchesHoraire().toArray(new TrancheHoraire[tournee.getTranchesHoraire().size()]);
		Color couleurTrancheHoraire[] = Constante.tabCouleur;

		for(int i=0; i<lesTrancheHoraires.length; i++)
		{
			couleursTranchesHoraires.put(lesTrancheHoraires[i], 
					(i<couleurTrancheHoraire.length) ? couleurTrancheHoraire[i] : Color.BLACK);				
		}

		if(couleursTranchesHoraires.size()!=0)
		{
			flag = true;
		}
		return flag;
	}
	
	/**
	 * Cette methode permet de creer la vue pour l'entrepot et les vues pour chaque point de livraison.
	 * @return vrai si la liste constituee des VuePointLivraison n est pas vide
	 */
	protected boolean initialiserPointLivraisons()
	{
		boolean flag = false;
		boolean ret = initialiserTout();
		vueEntrepot = new VueEntrepot(Tournee.getInstance().getEntrepot());
		lesVuePointLivraisons = new Vector<VuePointLivraison>();
		for(TrancheHoraire trancheHoraire : couleursTranchesHoraires.keySet())
		{
			for(PointLivraison pointLivraison : trancheHoraire.getPointsLivraisons())
			{
				lesVuePointLivraisons.add(new VuePointLivraison(pointLivraison,
						couleursTranchesHoraires.get(trancheHoraire)));
			}
		}

		if(lesVuePointLivraisons.size() > 0)
		{
			flag = true;
		}

		return flag;		
	}
	
	/**
	 * Cette methode permet d initialiser les vues pour les chemins.
	 * @return vrai si la liste constituee des chemins n est pas vide
	 */
	protected boolean initialiserTournee()
	{
		/*
		 * on charge les chemins de la tournee
		 */

		boolean flag = false;
		boolean ret  = initialiserTout();

		lesVueChemins = new Vector<VueChemin>();
		int numeroChemin = 0;
		for(Chemin chemin : Tournee.getInstance().getChemins())
		{
			TrancheHoraire th = chemin.getPointLivraisonDestination().getTrancheHoraire();

			if(th == null)
				th = chemin.getPointLivraisonOrigine().getTrancheHoraire();

			lesVueChemins.add(new VueChemin(numeroChemin++, chemin, couleursTranchesHoraires.get(th)));
		}

		if(lesVueChemins.size()!=0)
		{
			flag = true;
		}

		return flag;
	}
	
	/**
	 * Cette methode permet de dessiner tous les points de livraisons de la tournee
	 * @param g graphique ou il faudra dessiner
	 * @param facteurConversionLarg largeur du cadre du plan
	 * @param facteurConversionHaut hauteur du cadre du plan
	 */
	public void dessinerLespointLivraisons(Graphics g,  int facteurConversionLarg, int facteurConversionHaut)
	{		
		vueEntrepot.dessinerEntrepot(g, facteurConversionLarg, facteurConversionHaut, Constante.COULEURENTREPOT);
		for(VuePointLivraison vuePointLivraison : lesVuePointLivraisons)
		{
			vuePointLivraison.dessinerPointLivraison(g, facteurConversionLarg, facteurConversionHaut, vuePointLivraison.getCouleur());			
		}		
	}
	
	/**
	 * Cette methode permet de dessiner une tournee.
	 * On dessine l ensemble des chemins appartennant a la tournee.
	 * @param g graphique ou il faudra dessiner
	 * @param facteurConversionLarg largeur du cadre du plan
	 * @param facteurConversionHaut hauteur du cadre du plan
	 */
	public void dessinerTournee(Graphics g,  int facteurConversionLarg, int facteurConversionHaut)
	{
		for(VueChemin vueChemin : lesVueChemins)
		{
			vueChemin.dessinerChemin(g, facteurConversionLarg, facteurConversionHaut, vueChemin.getCouleur());
		}		
	}
	
	/**
	 * Cette methode permet d ajouter un nouveau point de livraison au plan.
	 * @param pointLivraison
	 */
	public void ajouterNouveauPointLivraison(PointLivraison pointLivraison)
	{
		Color couleur = VueTournee.getInstance().getCouleursTranchesHoraires().get(pointLivraison.getTrancheHoraire());
		lesVuePointLivraisons.add(new VuePointLivraison(pointLivraison, couleur));
	}

	/**
	 * Cette methode permet de supprimer un point de livraison.
	 * @param pointLivraison
	 */
	public void supprimerPointLivraison (PointLivraison pointLivraison)
	{		
		VuePointLivraison vuePointLivraisonASupprimer = null;
		for (VuePointLivraison vuePointLivraison : lesVuePointLivraisons)
		{
			if(vuePointLivraison.getPointLivraison().equals(pointLivraison))
			{
				vuePointLivraisonASupprimer = vuePointLivraison;
			}
		}

		if(vuePointLivraisonASupprimer != null)
		{
			lesVuePointLivraisons.remove(vuePointLivraisonASupprimer);
		}
	}
	
	/**
	 * Cette methode retourne les points de livraisons qui ont ete clique s il en existe plusieurs pour le meme noeud.
	 * @param x coordonnee x du clic
	 * @param y coordonne y du clic
	 * @return liste de point de livraison
	 */
	public  Vector<PointLivraison> lesPointLivraisonsClique(double x, double y)
	{
		lesPointLivraisonsClique = new Vector<PointLivraison>();

		for(VuePointLivraison vuePointLivraison : lesVuePointLivraisons)
		{
			double x1 = vuePointLivraison.getPointLivraison().getNoeud().getX();
			double y1 = vuePointLivraison.getPointLivraison().getNoeud().getY();
			if(vuePointLivraison.estClique(x, y, x1,y1))
			{
				lesPointLivraisonsClique.add(vuePointLivraison.getPointLivraison());
			}		
		}
		return lesPointLivraisonsClique;		
	}
}
