package com.h4201.prototype.modele;

import java.util.Vector;

public class Noeud
{
  private double x;

  private double y;

  private static int idNoeud = 0;

  private Vector<Troncon>  tronconsSortants;

  private Vector<Troncon>  tronconsEntrants;


  public Noeud(double x, double y) 
  {
	  this.x = x;
	  this.y = y;
	  Noeud.idNoeud++;
  }

  public double getX()
  {
	  return this.x;
  }

  public double getY()
  {
	  return this.y;
  }
  
  public int getIdNoeud()
  {
	  return Noeud.idNoeud;
  }

  public Vector<Troncon> getTronconsEntrants()
  {
	  return this.tronconsEntrants;
  }

  public void ajouterTronconEntrant(Troncon troncon)
  {
	  this.tronconsEntrants.addElement(troncon);
  }

  public Vector<Troncon> getTronconsSortants()
  {
	  return this.tronconsEntrants;
  }

  public void ajouterTronconSortant(Troncon troncon)
  {
	  this.tronconsSortants.addElement(troncon);
  }
}