package com.h4201.prototype.controleur;

public abstract class Commande 
{
	public abstract void do_();	
	public abstract void undo();
	public abstract void redo();
	public abstract int getMode(); // connaitre le mode (MODE_NORMAL, MODE_AJOUT ou MODE_SUPPRESSION) de la commande.
}
