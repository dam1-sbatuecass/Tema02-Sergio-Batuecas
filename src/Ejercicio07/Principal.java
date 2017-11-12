package Ejercicio07;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import Ejercicio01.Contacto;

public class Principal {
	public static void main(String[] args) {
		ArrayList<Contacto> listaContactos = getPersonasFichero();
		
		crearContactosXML(listaContactos);
	}

	/**
	 * Lee el fichero de contactos y lo mete en una lista.
	 * 
	 * @return Una lista con todos los contactos del fichero.
	 */
	public static ArrayList<Contacto> getPersonasFichero() {
		ArrayList<Contacto> listaContactos = new ArrayList<>();

		Contacto contacto;

		FileInputStream fis = null;
		ObjectInputStream ois = null;
		File fichero = new File("Contactos.obj");

		try {
			fis = new FileInputStream(fichero);

			ois = new ObjectInputStream(fis);

			contacto = (Contacto) ois.readObject();
			while (contacto != null) {
				listaContactos.add(contacto);
				contacto = (Contacto) ois.readObject();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		} catch (IOException ioe) {
			// TODO Auto-generated catch block
			ioe.getMessage();
		} catch (ClassNotFoundException cnfe) {
			// TODO Auto-generated catch block
			cnfe.getMessage();
		}

		return listaContactos;
	}

	public static void crearContactosXML(ArrayList<Contacto> listaContactos) {

		FileOutputStream fos;
		try {
			fos = new FileOutputStream("ContactosEjercicio07.xml");
			XMLOutputFactory factory = XMLOutputFactory.newInstance();
			XMLStreamWriter xMLStreamWriter = factory.createXMLStreamWriter(fos, "UTF-8");

			// Como el inicio del documentos y el nodo "Contactos" solo se va a crear una
			// vez no lo metemos en el for.
			xMLStreamWriter.writeStartDocument();
			xMLStreamWriter.writeStartElement("Contactos");

			for (int i = 0; i < listaContactos.size(); i++) {
				// Vamos añadiendo los Atributos de cada objeto contacto.

				xMLStreamWriter.writeStartElement("Contacto");// Abro contacto.

				xMLStreamWriter.writeStartElement("Nombre");// Abro Nombre
				xMLStreamWriter.writeCharacters(listaContactos.get(i).getNombre());// Lo escribo
				xMLStreamWriter.writeEndElement();// Cierro Nombre

				xMLStreamWriter.writeStartElement("Apellidos");// Abro Apellidos
				xMLStreamWriter.writeCharacters(listaContactos.get(i).getApellidos());// Lo escribo
				xMLStreamWriter.writeEndElement();// Cierro Apellidos

				xMLStreamWriter.writeStartElement("Email");// Abro Email
				xMLStreamWriter.writeCharacters(listaContactos.get(i).getEmail());// Lo escribo
				xMLStreamWriter.writeEndElement();// Cierro Email

				xMLStreamWriter.writeStartElement("Telefono");// Abro telefono
				xMLStreamWriter.writeCharacters(listaContactos.get(i).getTelefono());// Lo escribo
				xMLStreamWriter.writeEndElement();// Cierro telefono

				xMLStreamWriter.writeEndElement();// Cierro Contacto.

			}

			xMLStreamWriter.writeEndElement();// Cierro Contactos.
			xMLStreamWriter.writeEndDocument();// Cierro el documento.

			xMLStreamWriter.flush();
			xMLStreamWriter.close();

		} catch (FileNotFoundException fnfe) {
			// TODO Auto-generated catch block
			fnfe.printStackTrace();
		} catch (XMLStreamException xmlse) {
			// TODO Auto-generated catch block
			xmlse.printStackTrace();
		}

		System.out.println("Fichero XML creado con exito.");
	}

	private static String pedirString(String mensaje) {
		Scanner entrada = new Scanner(System.in);
		System.out.println(mensaje);
		return entrada.nextLine();
	}


}
