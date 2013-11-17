package com.h4201.prototype.modele;

import java.util.Vector;

public class Noeud
{
  private double x;

  private double y;

  private int idNoeud;

  private Vector<Troncon>  tronconsSortants;

  private Vector<Troncon>  tronconsEntrants;


  public Noeud(int idNoeud, double x, double y) 
  {
	  this.x = x;
	  this.y = y;
	  this.idNoeud = idNoeud;
	  this.tronconsSortants = new Vector<Troncon>();
	  this.tronconsEntrants = new Vector<Troncon>();
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
	  return this.idNoeud;
  }

  public Vector<Troncon> getTronconsEntrants()
  {
	  return this.tronconsEntrants;
  }

  protected void ajouterTronconEntrant(Troncon troncon)
  {
	  this.tronconsEntrants.addElement(troncon);
  }

  public Vector<Troncon> getTronconsSortants()
  {
	  return this.tronconsEntrants;
  }

  protected void ajouterTronconSortant(Troncon troncon)
  {
	  this.tronconsSortants.addElement(troncon);
  }

  public void afficher()
  {
	  System.out.println(this.toString());
  }
  
	@Override
	public String toString() {
		return "Noeud [idNoeud=" + idNoeud + ", x=" + x + ", y=" + y + "]";
	}
}