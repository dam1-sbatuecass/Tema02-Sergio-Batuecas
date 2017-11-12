package Ejercicio03;

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
		leerFicheroXML("Contactos.xml");

	}

	private static void leerFicheroXML(String ruta) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(ruta));

			document.getDocumentElement().normalize();
			System.out.println("Elemento raiz: " + document.getDocumentElement().getNodeName());

			// Crea una lista con todos los nodos Contactos
			NodeList contactos = document.getElementsByTagName("Contacto");

			//Recorro la lista.
			for (int i = 0; i < contactos.getLength(); i++) {
				Node contacto = contactos.item(i);// Obtengo un nodo.
				Element elemento = (Element) contacto;// Obtengo los elementos del nodo.
				System.out.println("\nContacto: "+(i+1));
				System.out.println("Nombre: " + getNodo("Nombre", elemento));
				System.out.println("Apellidos: " + getNodo("Apellidos", elemento));
				System.out.println("Email: " + getNodo("Email", elemento));
				System.out.println("Telefono: " + getNodo("Telefono", elemento));
				

			}
			System.out.println("\nFin del fichero "+ruta+".");
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
