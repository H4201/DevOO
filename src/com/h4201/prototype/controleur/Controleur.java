package com.h4201.prototype.controleur;

public final class Controleur
{
	private static volatile Controleur instance = null;

	
    private Controleur() 
    {
        super();
    }

    /**
     * MŽthode permettant de renvoyer une instance de la classe Controleur
     * @return Retourne l'instance du singleton Controleur.
     */
    public final static Controleur getInstance()
    {
        if (Controleur.instance == null)
        {
           synchronized(Controleur.class)
           {
			 if (Controleur.instance == null)
			 {
				 Controleur.instance = new Controleur();
			 }
           }
        }
        
        return Controleur.instance;
    }
}
