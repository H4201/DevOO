package com.h4201.prototype.controleur;

import java.io.File;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JFileChooser;



import org.w3c.dom.*;
import org.xml.sax.SAXException;

import com.sun.file.ExampleFileFilter;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class FabriquePlan
{
	public FabriquePlan()
	{
		
	}
	
	public void depuisXML(File planXML)
	{
		try
		{
		
			// création d'une fabrique de documents
	        DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
	
	        // création d'un constructeur de documents
	        DocumentBuilder constructeur = fabrique.newDocumentBuilder();
			
			Document document = constructeur.parse(planXML);
		
		    Element racine = document.getDocumentElement();
		
		    System.out.println(racine.getNodeName());
		    
	//	    if (racine.getNodeName().equals("dessin"))
	//	    {
	//	        int resultatConstruction = ConstruireToutAPartirDeDOMXML(racine);
	//	        if (resultatConstruction != Dessin.PARSE_OK)
	//	        {
	//	        	//histoire d'avoir un dessin 
	//	        	dessin.initialiser(100, 100, Color.black);
	//	        	vueDessin.setDessin(dessin);
	//	        	System.out.println("PB de lecture de fichier!");
	//	        } 
	//	    }
	//	    else
	//	    {
	//	    	System.out.println("L'element racine n'est pas bon !");
	//	    }

		} 
		catch (ParserConfigurationException e)
		{
			e.printStackTrace();
		}
		catch (SAXException | IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private void extraireDonneesDuDOM()
	{
//		//lecture des attributs
//        hauteur = Integer.parseInt(noeudDOMRacine.getAttribute("hauteur"));
//        largeur = Integer.parseInt(noeudDOMRacine.getAttribute("largeur"));
//
//        NodeList liste = noeudDOMRacine.getElementsByTagName("couleurArrierePlan");
//        if (liste.getLength() != 1) {
//            return Dessin.PARSE_ERROR;
//        }
//        Element couleurElement = (Element) liste.item(0);
//        int rouge = Integer.parseInt(couleurElement.getAttribute("rouge"));
//        int vert = Integer.parseInt(couleurElement.getAttribute("vert"));
//        int bleu = Integer.parseInt(couleurElement.getAttribute("bleu"));
//        couleurArrierePlan = new Color(rouge, vert, bleu);
//
//        
//        //création des Boules;
//        String tag = "boule";
//        liste = noeudDOMRacine.getElementsByTagName(tag);
//        lesBoules.removeAllElements();
//        for (int i = 0; i < liste.getLength(); i++) {
//            Element bouleElement = (Element) liste.item(i);
//            Boule laNouvelleBoule = new Boule();
//            if (laNouvelleBoule.construireAPartirDeDOMXML(bouleElement)!= Dessin.PARSE_OK){
//                return Dessin.PARSE_ERROR;
//            }
//            
//            //ajout des Ã©lÃ©ments crÃ©Ã©s dans la structure objet
//            //this.AjouterVueBoule(laNouvelleVueBoule);
//            ajouterBoule(laNouvelleBoule);
//            
//        }
	}
}
