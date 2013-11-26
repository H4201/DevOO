package com.h4201.prototype.controleur;

public abstract class Commande 
{
	public abstract Object do_();	
	public abstract Object undo();
	public abstract Object redo();
	public abstract int getMode(); // connaitre le mode (MODE_NORMAL, MODE_AJOUT ou MODE_SUPPRESSION) de la commande.
}
