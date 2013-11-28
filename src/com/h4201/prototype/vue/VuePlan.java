package com.h4201.prototype.vue;

import java.awt.Graphics;
import java.util.Map;
import java.util.Vector;

import com.h4201.prototype.exception.ExceptionNonInstancie;
import com.h4201.prototype.modele.Noeud;
import com.h4201.prototype.modele.Plan;
import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.modele.Troncon;

/**
 * Gestion de l'affichage du plan.
 * @author Eva
 *
 */
public class VuePlan
{
	private static volatile VuePlan instance = null;
	private Vector<VueNoeud> lesVueNoeuds;
	private Vector<VueTroncon> lesVueTroncons;
	
	public Vector<VueNoeud> getLesVueNoeuds()
	{
		return lesVueNoeuds;
	}

	public Vector<VueTroncon> getLesVueTroncons()
	{
		return lesVueTroncons;
	}
	
	/**
	 * cette methode permet de retourner le noeud qui a ete clique.
	 * @param x coordonee du clic suivant l axe x
	 * @param y coordonnee y du clic suivant l axe y
	 * @return noeud
	 */
	public Noeud getLeNoeud(double x, double y)
	{
		Noeud noeud = null;
		for(VueNoeud laVueNoeud : lesVueNoeuds )
		{
			if(laVueNoeud.estClique(x, y, laVueNoeud.getNoeud().getX(), laVueNoeud.getNoeud().getY()))
			{			
				noeud = laVueNoeud.getNoeud();
			}
		}
		return noeud;
	}
	
	/**
	 * Cette methode permet de retournee le point de livraison qui a ete clique.
	 * @param x coordonee du clic suivant l axe x
	 * @param y coordonnee y du clic suivant l axe y
	 * @return PointLivraison le point de livraison recherche
	 */
	public PointLivraison getLePointLivraison(double x, double y)
	{
		PointLivraison pointLivraison = null;
		for(VuePointLivraison laVuePointDeLivraison : VueTournee.getInstance().getLesVuePointLivraisons())
		{
			double coordX = laVuePointDeLivraison.getPointLivraison().getNoeud().getX();
			double coordY = laVuePointDeLivraison.getPointLivraison().getNoeud().getY();
			if(laVuePointDeLivraison.estClique(x, y, coordX,coordY) == true)
			{
				pointLivraison = laVuePointDeLivraison.getPointLivraison();
			}
		}
		
		return pointLivraison;
	}
	
	/**
	 * Constructeur de la vue pour le plan.
	 */
	private VuePlan()
	{
		super();
	}
	
	/**
	 * cette methode permet de renvoyer une instance de la classe VuePlan
     * @return instance du singleton VuePlan.
	 */
	public final static VuePlan getInstance()
	{
		if (VuePlan.instance == null)
		{
			synchronized(VuePlan.class)
			{
				if (VuePlan.instance == null)
				{
					VuePlan.instance = new VuePlan();
				}
			}
		}

		return VuePlan.instance;
	}
	
	/**
	 * Initialise le plan, les VueNoeuds et les VueTroncons � partir du modele.
	 */
	public void initialiserVuePlan()
	{
		try
		{
			Plan plan = Plan.getInstance();
			Map<Integer, Noeud> structureNoeuds = plan.getNoeuds();
			Vector<Troncon> StructureTroncons= plan.getTroncons();
			
			lesVueNoeuds = new Vector<VueNoeud>();
			for(Integer idNoeud : structureNoeuds.keySet())
			{
				lesVueNoeuds.add(new VueNoeud(structureNoeuds.get(idNoeud)));
			}

			lesVueTroncons = new Vector<VueTroncon>();
			for(Troncon leTroncon : StructureTroncons)
			{
				lesVueTroncons.add(new VueTroncon(leTroncon));
			}

		} catch (ExceptionNonInstancie e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Cette methode dessine les noeuds et les troncons a partir du fichier xml du plan charge.
	 * @param g graphique ou il faudra dessiner
	 * @param facteurConversionLarg largeur du cadre du plan
	 * @param facteurConversionHaut hauteur du cadre du plan
	 */
	public void dessinerNoeudsTroncons(Graphics g, int facteurConversionLarg, int facteurConversionHaut)
	{
		for(VueNoeud vueNoeud : lesVueNoeuds)
		{
			vueNoeud.dessinerNoeud(g, facteurConversionLarg, facteurConversionHaut);
		}

		for(VueTroncon vueTroncon : lesVueTroncons )
		{
			vueTroncon.dessinerTroncon(g,facteurConversionLarg, facteurConversionHaut );
		}
	}
}
