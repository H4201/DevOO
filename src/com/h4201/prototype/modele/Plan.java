package com.h4201.prototype.modele;

import java.util.Vector;

public class Plan
{
  private Vector<Troncon> troncons;
  private Vector<Noeud> noeuds;

  public Vector<Troncon> getTroncons()
  {
	  return troncons;
  }

  public Vector<Noeud> getNoeuds()
  {
	  return noeuds;
  }
}