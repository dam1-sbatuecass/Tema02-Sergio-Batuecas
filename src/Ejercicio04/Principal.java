package Ejercicio04;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Principal {

	public static void main(String[] args) {
		leerFicheroXML("libros.xml");

	}

	private static void leerFicheroXML(String ruta) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(ruta));

			document.getDocumentElement().normalize();
			System.out.println("Elemento raiz: " + document.getDocumentElement().getNodeName());

			// Crea una lista con todos los nodos Contactos
			NodeList libros = document.getElementsByTagName("Libro");

			// Recorro la lista.
 			for (int i = 0; i < libros.getLength(); i++) {
				Node contacto = libros.item(i);// Obtengo un nodo.
				Element elemento = (Element) contacto;// Obtengo los elementos del nodo.

				System.out.println("\nLibro nº: " + (i + 1) + ". ISBN:" + libros.item(i).getAttributes().item(0).getTextContent()+".");//Me da el primer atributo del nodo libro.
				System.out.println("Titulo: " + getNodo("Titulo", elemento)+".");//Busco el nodo con el nombre que le paso y muestro  el valor que contiene.
				System.out.println("Autor: " + getNodo("Autor", elemento)+".");
				System.out.println("Editorial: " + getNodo("Editorial", elemento)+".");

			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static String getNodo(String etiqueta, Element elem) {
		// Solamente existe un elemento con dicho nombre/Etiqueta.
		Node nodo = elem.getElementsByTagName(etiqueta).item(0).getFirstChild();
		return nodo.getTextContent();
	}

}
