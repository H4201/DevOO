package com.h4201.prototype.controleur;

/**
 * Classe implementant le pattern Command.
 * Classe abstraite, destinee a etre specialisee.
 * @author Steevens
 */
public abstract class Commande 
{
	public abstract void do_();	
	public abstract void undo();
	public abstract void redo();
	public abstract int getMode(); // connaitre le mode interactif correspondant a la commande.
}
