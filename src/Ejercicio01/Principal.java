package Ejercicio01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class Principal {

	public static void main(String[] args) {
		gestionarMenu();

	}

	public static void gestionarMenu() {

		String[] opciones = { "Visualizar todos los contactos", "Guardar los contactos en un XML", "Salir" };

		switch (generarMenu(opciones)) {
		case 0:
			leerContactos();
			gestionarMenu();
			break;
		case 1:
			crearContactosXML();// Guarda los contactos del fichero en un XML.
			gestionarMenu();
			break;
		case 2:
			System.out.println("¡Hasta luego!");
			break;
		default:// Si no me pulsa ninguna de las opciones validas le muestro el menu de nuevo.
			gestionarMenu();
			break;
		}

	}

	public static void crearContactosXML() {
		try {

			// Iniciamos la API de DOM a partir de la clase DocumentBuilderFactory.
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();

			Document document = implementation.createDocument(null, "Contactos.obj", null);

			document.setXmlVersion("1.0");// Asignamos la version de nuestro xml

			ArrayList<Contacto> listaContactos = getContactosFichero();
			Iterator it = listaContactos.iterator();

			while (it.hasNext()) {
				Contacto contacto = (Contacto) it.next();
				Element nodo = document.createElement("Conntacto");// Nodo persona

				document.getDocumentElement().appendChild(nodo);
				// Dentro del nodo persona nos creamos sus nodos hijos/elementos
				// Por cada atributo de la clase, añadimos un elemento.
				crearElemento("Nombre", contacto.getNombre(), nodo, document);
				crearElemento("Apellidos", contacto.getApellidos(), nodo, document);
				crearElemento("Email", contacto.getEmail(), nodo, document);
				crearElemento("Telefono", contacto.getTelefono(), nodo, document);

			}
			Source source = new DOMSource(document);
			Result result = new StreamResult(new java.io.File("Contactos.xml"));

			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);

		} catch (ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		}

		System.out.println("Fichero XML creado con exito.");
	}

	public static ArrayList<Contacto> getContactosFichero() {
		ArrayList<Contacto> lista = new ArrayList<>();
		Contacto contacto = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		ArrayList<Contacto> listaContactos = new ArrayList<>();

		File fichero = new File("Contactos.obj");
		try {
			fis = new FileInputStream(fichero);
			ois = new ObjectInputStream(fis);
			contacto = (Contacto) ois.readObject();

			while (contacto != null) {
				lista.add(contacto);
				contacto = (Contacto) ois.readObject();

			}
			ois.close();
		} catch (IOException ioe) {
			// No muestro nada para.
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Error al leer los objetos " + cnfe.getMessage());
		}

		return lista;
	}

	public static void crearElemento(String datoContacto, String valor, Element nodo, Document document) {
		Element elem = document.createElement(datoContacto);// Creamos un nodo Element
		Text text = document.createTextNode(valor);// Creamos un nodo text-

		elem.appendChild(text);// Añadimos el valor.
		nodo.appendChild(elem);// Añadimos el elemento hijo al nodo
	}

	public static void leerContactos() {
		Contacto contacto = null;
		ObjectInputStream ois = null;

		try {
			File fichero = new File("Contactos.obj");
			FileInputStream fis = new FileInputStream(fichero);// Crea el flujo de entrada.

			ois = new ObjectInputStream(fis);

			while (true) {
				contacto = (Contacto) ois.readObject();
				System.out.println(contacto);
			}

		} catch (IOException e) {
			System.out.println("Fin de la lectura del Fichero.");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ois.close();
		} catch (IOException e) {
			System.out.println("Error al cerrar el flujo de lectura.");
		}
	}

	static int generarMenu(Object[] opciones) {
		// Pinto las opciones
		System.out.println("------------");
		System.out.println("Opciones:");
		for (int i = 0; i < opciones.length; i++) {
			System.out.println("- " + i + ": " + opciones[i]);
		}
		System.out.println("Introduce opción, del 0 al " + (opciones.length - 1));
		// Capturamos un entero por teclado que valga entre los valores posibles:
		int opcion = -1;
		do {
			if (opcion != -1) {
				System.out.println("Repite");
			}
			opcion = enteroPorTeclado();
		} while (opcion < 0 || opcion >= opciones.length);

		return opcion;
	}

	public static String pedirString() {// Pide un string al usuario.
		Scanner entrada = new Scanner(System.in);
		return entrada.nextLine();
	}

	public static int enteroPorTeclado() {// Retorna el entero del usuario y se asegura que sea un entero.
		boolean tengoEntero = false;
		int entero = 0;
		Scanner scan = new Scanner(System.in);
		do {
			try {
				entero = Integer.parseInt(scan.nextLine());
				tengoEntero = true;
			} catch (Exception e) {
				System.out.println("Repite");
			}
		} while (!tengoEntero);
		return entero;
	}

}
