package com.h4201.prototype.modele;

import java.util.HashMap;
import java.util.Vector;

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

  private Plan(Vector<Troncon> troncons, HashMap<Integer, Noeud> noeuds)
  {
	  this.troncons = troncons;
	  this.noeuds = noeuds;
  }
  
  protected final static Plan setInstance(Vector<Troncon> troncons, 
		  HashMap<Integer, Noeud> noeuds) throws ExceptionNonInstancie
  {
	synchronized(Plan.class)
	{
		Plan.instance = new Plan(troncons, noeuds);
	}
	
	return getInstance();
  }
  
  public final static Plan getInstance() throws ExceptionNonInstancie
  {
      if(Plan.instance == null) 
      {
    	  throw new ExceptionNonInstancie(Plan.class.getName());
      }
      
	  return Plan.instance;
  }
  
  public final Noeud getNoeudDepuisIdNoeud(Integer idNoeud)
  {
	  return noeuds.get(idNoeud);
  }

  public final Vector<Troncon> getTroncons()
  {
	  return troncons;
  }

  public HashMap<Integer, Noeud> getNoeuds()
  {
	  return noeuds;
  }

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
  
	@Override
	public String toString() {
		String s = "Plan [troncons=[" + troncons + "], noeuds=" + noeuds + "]";
		
		return s;
	}
}