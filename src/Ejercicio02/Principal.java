package Ejercicio02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Principal {

	public static void main(String[] args) {

		// introducirContactos();
		generarLibrosXML();
	}

	private static void generarLibrosXML() {

		try {

			// Iniciamos la API de DOM a partir de la clase DocumentBuilderFactory.
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();

			Document document = implementation.createDocument(null, "Libros", null);

			document.setXmlVersion("1.0");// Asignamos la version de nuestro xml

			ArrayList<Libro> listaLibros = getLibrosFichero();
			Iterator it = listaLibros.iterator();

			while (it.hasNext()) {
				Libro libro = (Libro) it.next();
				Element nodo = document.createElement("Libro");// Nodo Libro

				document.getDocumentElement().appendChild(nodo);
				// Dentro del nodo libro nos creamos sus nodos hijos/elementos
				// Por cada atributo de la clase, añadimos un elemento.
				nodo.setAttribute("ISBN", libro.getIsbn());
				crearElemento("Titulo", libro.getTitulo(), nodo, document);
				crearElemento("Autor", libro.getAutor(), nodo, document);
				crearElemento("Editorial", libro.getEditorial(), nodo, document);

			}
			Source source = new DOMSource(document);
			Result result = new StreamResult(new java.io.File("libros.xml"));

			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);

		} catch (ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		}

		System.out.println("Fichero XML creado con exito.");
	}
	

	private static void crearElemento(String datoLibro, String valor, Element nodo, Document document) {
		Element elem = document.createElement(datoLibro);// Creamos un nodo Element
		Text text = document.createTextNode(valor);// Creamos un nodo text-

		elem.appendChild(text);// Añadimos el valor.
		nodo.appendChild(elem);// Añadimos el elemento hijo al nodo

	}

	private static ArrayList<Libro> getLibrosFichero() {

		Libro aux = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		ArrayList<Libro> lista = new ArrayList<>();

		File fichero = new File("Libros.obj");
		try {
			fis = new FileInputStream(fichero);
			ois = new ObjectInputStream(fis);
			aux = (Libro) ois.readObject();

			while (aux != null) {
				lista.add(aux);
				aux = (Libro) ois.readObject();

			}
			ois.close();
		} catch (IOException ioe) {
			// No muestro nada para.
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Error al leer los objetos " + cnfe.getMessage());
		}

		return lista;
	}

	public static void introducirContactos() {
		ArrayList<Libro> listaLibros = new ArrayList<>();
		do {

			Libro aux = new Libro();
			aux.setIsbn(pedirString("Introduce el ISBN"));
			aux.setTitulo(pedirString("Introduce el titulo."));
			aux.setAutor(pedirString("Introduce el autor"));
			aux.setEditorial(pedirString("Introduce la editorial."));
			listaLibros.add(aux);

		} while (pedirString("Quieres introducir otro libro").equalsIgnoreCase("si"));

		volcarListaAFichero(listaLibros);

	}

	public static void volcarListaAFichero(ArrayList<Libro> listaLibros) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			File fichero = new File("Libros.obj");
			if (fichero.exists()) {// Si el fichero existe le pongo el true para que no lo borre y no le añado otra
									// cabecera
				fos = new FileOutputStream(fichero, true);
				oos = new MiObjectOutputStream(fos);
			} else {// Si no existe el fichero lo creo con cabecera.
				fos = new FileOutputStream(fichero);
				oos = new ObjectOutputStream(fos);
			}

			for (int i = 0; i < listaLibros.size(); i++) {// Recorre la lista y va escribiendo los contactos.
				oos.writeObject(listaLibros.get(i));
			}

			oos.close();// Cierro el flujo de escritura.

			System.out.println("\n Se han guardado " + listaLibros.size() + " Libros correctamente.");
		} catch (IOException ioe) {
			System.out.println("Se ha producido un error.");
		}

	}

	public static String pedirString(String mensaje) {
		System.out.println(mensaje);
		Scanner entrada = new Scanner(System.in);
		return entrada.nextLine();
	}
}
