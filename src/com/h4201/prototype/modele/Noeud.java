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

  /**
   * Recuperer la coordonnee X.
   * @return x
   */
  public double getX()
  {
	  return this.x;
  }

  /**
   * Recuperer la coordonnee Y.
   * @return y
   */
  public double getY()
  {
	  return this.y;
  }
  
  /**
   * Recuperer l'id du noeud.
   * @return id du noeud
   */
  public int getIdNoeud()
  {
	  return this.idNoeud;
  }

  /**
   * Recuperer la liste des troncons qui arrive a ce noeud.
   * @return la liste des troncons entrants.
   */
  protected Vector<Troncon> getTronconsEntrants()
  {
	  return this.tronconsEntrants;
  }

  /**
   * Ajouter un troncon a la liste des troncons entrant.
   * @param le troncon a ajouter
   */
  protected void ajouterTronconEntrant(Troncon troncon)
  {
	  this.tronconsEntrants.addElement(troncon);
  }

  /**
   * Recuperer la liste des troncons qui partent de ce noeud.
   * @return la liste des troncons sortants
   */
  protected Vector<Troncon> getTronconsSortants()
  {
	  return this.tronconsSortants;
  }

  /**
   * Ajouter un troncon sortant a la liste des troncons sortants.
   * @param le troncon a ajouter a la liste des troncons sortants
   */
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
  
  /**
   * Les informations du neoud dans une chaine de caracteres
   */
	@Override
	public String toString() {
		return "Noeud [idNoeud=" + idNoeud + ", x=" + x + ", y=" + y + "]";
	}
}