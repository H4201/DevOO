package com.h4201.prototype.vue;

import javax.swing.JPanel;

import com.h4201.prototype.modele.Noeud;

@SuppressWarnings("serial")
public abstract class VueSupervision extends JPanel
{
	public abstract Noeud clic(double x, double y);
	public abstract void afficher();
}
