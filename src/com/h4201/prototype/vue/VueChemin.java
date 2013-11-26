package com.h4201.prototype.vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Vector;

import com.h4201.prototype.modele.Chemin;
import com.h4201.prototype.modele.Troncon;
import com.h4201.prototype.utilitaire.Constante;


public class VueChemin
{

	private Chemin chemin;
	private Color couleur;
	private int numeroChemin;
	
	public VueChemin(int numeroChemin, Chemin chemin, Color couleur)
	{
		this.numeroChemin = numeroChemin;
		this.chemin = chemin;
		this.couleur = couleur;
	}
	
	public Color getCouleur()
	{
		return couleur;
	}
	
	private final int ARR_SIZE = 4;
	private void drawArrow(Graphics g1, int x1, int y1, int x2, int y2) {
        Graphics2D g = (Graphics2D) g1.create();

        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx*dx + dy*dy);
        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);

        // Draw horizontal arrow starting in (0, 0)
        g.drawLine(0, 0, len, 0);
        g.fillPolygon(new int[] {len, len-ARR_SIZE, len-ARR_SIZE, len},
                      new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
    }

	public void dessinerChemin(Graphics g, int facteurConversionLarg, int facteurConversionHaut, Color couleur)
	{
			Color cTemp = g.getColor();
			Vector<Troncon> lesTronconsDuChemin = chemin.getTroncons();
						
			for(Troncon leTroncon : lesTronconsDuChemin)
			{
				// Determination des coordonnees
				int x1 = (int)leTroncon.getNoeudOrigine().getX() * facteurConversionLarg / Constante.LARGEURSUPERV;
				int y1 = (int)leTroncon.getNoeudOrigine().getY() * facteurConversionHaut / Constante.HAUTEURSUPERV;
				int x2 = (int)leTroncon.getNoeudDestination().getX() * facteurConversionLarg / Constante.LARGEURSUPERV;
				int y2 = (int)leTroncon.getNoeudDestination().getY() * facteurConversionHaut/ Constante.HAUTEURSUPERV;		
				
				double x3 = x1;
				double y3 = y1;
				double x4 = x2;
				double y4 = y2;
				int facteur = 1;
				// Decalage des chemins si besoin
				if(leTroncon.getNbCheminPassantParCeTroncon() > 1)
				{
					if(numeroChemin%2 == 0)
					{
						facteur = -1;
					}
					
					x3 = x1 + ((numeroChemin/2) * Constante.DECALAGE_CHEMIN) * facteur;
					y3 = y1 + ((numeroChemin/2) * Constante.DECALAGE_CHEMIN) * facteur;
					
					x4 = x3 + x2 - x1;
					y4 = y3 + y2 - y1;
				}
				
				g.setColor(couleur);
				drawArrow(g, (int) Math.round(x3), (int) Math.round(y3), 
						(int) Math.round(x4), (int) Math.round(y4));
				g.setColor(cTemp);
			}		
	}


	
}
