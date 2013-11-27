package com.h4201.prototype.utilitaire;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import com.h4201.prototype.exception.ExceptionFichier;
import com.h4201.prototype.exception.ExceptionXML;

/**
 * Classe des actions possibles sur un fichier.
 * Classe qui ne contient que des classes statiques. Elle est abstraite car non instanciable. 
 * @author H4201
 */
public abstract class Fichier
{
	/**
	 * Lecture d'un fichier XML.
	 * @param fichierXml fichier de type File
	 * @return le document parse.
	 * @throws ExceptionXML Si ce n'est pas du xml
	 * @throws ExceptionFichier Si la lecture du fichier echoue
	 */
	public static final Document getDocumentXml(File fichierXml) throws ExceptionXML, ExceptionFichier
	{
		Document document;
		
		try
		{
			// creation d'une fabrique de documents
	        DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
	
	        // creation d'un constructeur de documents
	        DocumentBuilder constructeur = fabrique.newDocumentBuilder();
			
			document = constructeur.parse(fichierXml);
		}
		catch (ParserConfigurationException e)
		{
			throw new ExceptionXML(fichierXml.getName() + " - " + e.getMessage());
		}
		catch(Exception e)
		{
			throw new ExceptionFichier(fichierXml.getName() + " - " + e.getMessage());
		}
		
		return document;
	}
}
