package com.h4201.prototype.modele;

import java.util.Vector;

/**
 * Noeud du Plan, intersection de troncons.
 * Un noeud est un point de livraison possible.
 * @author Paul
 *
 */
public class Noeud
{
  private double x;

  private double y;

  private int idNoeud;

  private Vector<Troncon>  tronconsSortants;

  private Vector<Troncon>  tronconsEntrants;

	/**
	 * Constructeur d'un noeud.
	 * @param idNoeud id du noeud donne par le xml
	 * @param x coordonnee sur l'axe des x
	 * @param y coordonnee sur l'axe des y
	 */
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

  /**
   * Permet d'afficher les informations du noeud.
   */
  public void afficher()
  {
	  System.out.println(this.toString());
  }
  
	@Override
	public String toString() {
		return "Noeud [idNoeud=" + idNoeud + ", x=" + x + ", y=" + y + "]";
	}
}