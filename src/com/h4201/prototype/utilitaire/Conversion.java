package com.h4201.prototype.utilitaire;

import com.h4201.prototype.utilitaire.Constante;

/**
 * Classe de conversion des unites de mesure.
 * Classe qui ne contient que des classes statiques. Elle est abstraite car non instanciable. 
 * @author Paul
 */
public abstract class Conversion
{
	public static double getPixelDepuisMetre(double distanceEnMetres)
	{
		return distanceEnMetres * Constante.CONVERSION_METRES_EN_PIXELS;
	}
	
	public static double getMetreDepuisPixel(double distanceEnPixels){
		return distanceEnPixels * Constante.CONVERSION_PIXELS_EN_METRES;
	}
	
	public static double getKilometreDepuisMetre(double distanceEnMetres)
	{
		return (distanceEnMetres / 1000);
	}

}
