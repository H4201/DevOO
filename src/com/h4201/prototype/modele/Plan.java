package com.h4201.prototype.modele;

import java.util.HashMap;
import java.util.Vector;

import com.h4201.prototype.exception.ExceptionNoeudInconnu;
import com.h4201.prototype.exception.ExceptionNonInstancie;

/**
 * Un plan est une carte composee de noeuds et de troncons.
 * Il s'agit d'une modelisation d'une carte d'une ville avec ses rues 
 * et les points de livraison.
 * @author Paul
 *
 */
public final class Plan
{
	private static volatile Plan instance = null;
	private Vector<Troncon> troncons;
	private HashMap<Integer, Noeud> noeuds;

	/**
	 * Constructeur d'un plan a partir de ses troncons et noeuds.
	 * @param troncons les troncons reliant les noeuds.
	 * @param noeuds les noeuds du plan.
	 */
  private Plan(Vector<Troncon> troncons, HashMap<Integer, Noeud> noeuds)
  {
	  this.troncons = troncons;
	  this.noeuds = noeuds;
	  Tournee.reinitialiserTournee();
  }
  
  /**
   * Permet de modifier l'instance du plan (singleton).
   * @param troncons les troncons du plan.
   * @param noeuds les noeuds du plan.
   * @return retourne la nouvelle instance.
   */
  protected static Plan setInstance(Vector<Troncon> troncons, 
		  HashMap<Integer, Noeud> noeuds)
  {
	synchronized(Plan.class)
	{
		Plan.instance = new Plan(troncons, noeuds);
	}
	
	return Plan.instance;
  }
  
  /**
   * Recuperer l'instance du plan.
   * @return L'instance du plan.
   * @throws ExceptionNonInstancie si le plan n'a pas ete instancie au prealable.
   */
  public final static Plan getInstance() throws ExceptionNonInstancie
  {
      if(Plan.instance == null) 
      {
    	  throw new ExceptionNonInstancie(Plan.class.getName());
      }
      
	  return Plan.instance;
  }
  
  /**
   * Recuperer un noeud du plan a partir de son id.
   * @param idNoeud id du noeud recherche.
   * @return le nouveau correspondant a l'id.
   * @throws ExceptionNoeudInconnu Si le noeud n'existe pas.
   */
  public final Noeud getNoeudDepuisIdNoeud(Integer idNoeud)
  		throws ExceptionNoeudInconnu
  {
	  Noeud noeud = noeuds.get(idNoeud);
	  
	  if(noeud == null)
		  throw new ExceptionNoeudInconnu(idNoeud.intValue());
	  
	  return noeud;
  }

  /**
   * Recuperer tous les troncons du plan.
   * @return les troncons du plan.
   */
  public final Vector<Troncon> getTroncons()
  {
	  return troncons;
  }

  /**
   * Recuperer tous les noeuds du plan.
   * @return les noeuds du plan.
   */
  public HashMap<Integer, Noeud> getNoeuds()
  {
	  return noeuds;
  }

  /**
   * Affichage des informations du plan.
   */
  public void afficher()
  {
	  System.out.println("\nPLAN");
	  
	  System.out.println("Troncons : ");
	  for(Troncon troncon : troncons)
	  {
		  troncon.afficher();
	  }
	  
	  	System.out.println("Noeuds : ");
		for (Integer key : noeuds.keySet())
		{
			noeuds.get(key).afficher();
		}
  }
  
  /**
   * Recuperer les informations du plan dans une chaine de caracteres.
   */
	@Override
	public String toString() {
		String s = "Plan [troncons=[" + troncons + "], noeuds=" + noeuds + "]";
		
		return s;
	}
}