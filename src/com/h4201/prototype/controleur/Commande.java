package com.h4201.prototype.controleur;

public abstract class Commande 
{
	public abstract void do_();	
	public abstract void undo();
	public abstract void redo();
	public abstract int getMode(); // connaitre le mode (enAjout=1 ou enSuppression=2) de la commande.
}
