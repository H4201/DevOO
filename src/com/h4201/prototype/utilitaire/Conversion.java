package com.h4201.prototype.utilitaire;

import com.h4201.prototype.utilitaire.Constante;

/**
 * Classe 
 * Classe qui ne contient que des classes statiques. Elle est abstraite car non instanciable. 
 * @author Paul
 */
public abstract class Conversion
{
	public static double getPixelDepuisMetre(double distanceEnMetres)
	{
		return distanceEnMetres * Constante.CONVERSION_METRES_EN_PIXELS;
	}

}
