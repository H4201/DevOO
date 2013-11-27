package com.h4201.prototype.utilitaire;

import com.h4201.prototype.utilitaire.Constante;

/**
 * Classe de conversion des unites de mesure.
 * Classe qui ne contient que des classes statiques. Elle est abstraite car non instanciable. 
 * @author H4201
 */
public abstract class Conversion
{
	/**
	 * Convertit des metres en pixels selon une constante.
	 * @param distanceEnMetres Distance en metres a convertir en pixels.
	 * @return distance en pixels.
	 */
	public static double getPixelDepuisMetre(double distanceEnMetres)
	{
		return distanceEnMetres * Constante.CONVERSION_METRES_EN_PIXELS;
	}
	
	/**
	 * Convertit des pixels en metres selon une constante.
	 * @param distanceEnPixels Distance en pixels a convertir en metres.
	 * @return distance en metres.
	 */
	public static double getMetreDepuisPixel(double distanceEnPixels){
		return distanceEnPixels * Constante.CONVERSION_PIXELS_EN_METRES;
	}
	
	/**
	 * Convertit des metres en kilometres.
	 * @param distanceEnMetres distance en metres a convertir.
	 * @return distance en kilometres.
	 */
	public static double getKilometreDepuisMetre(double distanceEnMetres)
	{
		return (distanceEnMetres / 1000);
	}

}
