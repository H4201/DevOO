package com.h4201.prototype.modele;

import java.util.HashMap;
import java.util.Vector;

public class Plan
{
  private Vector<Troncon> troncons;
  private HashMap<Integer, Noeud> noeuds;

  public Plan(Vector<Troncon> troncons, HashMap<Integer, Noeud> noeuds)
  {
	  this.troncons = troncons;
	  this.noeuds = noeuds;
  }

  public Vector<Troncon> getTroncons()
  {
	  return troncons;
  }

  public HashMap<Integer, Noeud> getNoeuds()
  {
	  return noeuds;
  }

	@Override
	public String toString() {
		String s = "Plan [troncons=[";
		
	    for (Troncon troncon : troncons)
	    {
	        s += troncon.toString();
	    }
	    
	    s += "], noeuds=" + noeuds + "]";
		
		return s;
	}
}