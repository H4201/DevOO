package com.h4201.prototype.utilitaire;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import com.h4201.prototype.exception.ExceptionFichier;
import com.h4201.prototype.exception.ExceptionXML;

public abstract class Fichier
{
	public static final Document getDocumentXml(File fichierXml) throws ExceptionXML, ExceptionFichier
	{
		Document document;
		
		try
		{
			// création d'une fabrique de documents
	        DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
	
	        // création d'un constructeur de documents
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
