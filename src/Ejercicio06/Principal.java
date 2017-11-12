package Ejercicio06;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class Principal {

	public static void main(String[] args) {
		try {
			FileOutputStream fos = new FileOutputStream("libros_Ejercicio06.xml");
			XMLOutputFactory factory = XMLOutputFactory.newInstance();
			XMLStreamWriter xMLStreamWriter = factory.createXMLStreamWriter(fos, "UTF-8");

			xMLStreamWriter.writeStartDocument();
			xMLStreamWriter.writeStartElement("libros");

			xMLStreamWriter.writeStartElement("Libro");
			xMLStreamWriter.writeAttribute("ISBN", "236-568-896-523-5");

			xMLStreamWriter.writeStartElement("titulo");
			xMLStreamWriter.writeCharacters("Acceso a datos");
			xMLStreamWriter.writeEndElement();

			xMLStreamWriter.writeStartElement("autor");
			xMLStreamWriter.writeCharacters("Alicia Ramos");

			if (preguntarMasAutores("Acceso a datos")) {// True si responde que si.

				do {
					xMLStreamWriter.writeCharacters(", " + getString("Introduce el nombre del coautor:"));// Le pido un coautor y lo escribo en el xml

				} while (getString("¿Quieres introducir otro coautor?").equalsIgnoreCase("si"));// Lo tengo metiendo
																								// autores hasta que me
																								// responda que ya no
																								// quiere meter mas.
			}

			xMLStreamWriter.writeEndElement();

			xMLStreamWriter.writeStartElement("editorial");
			xMLStreamWriter.writeCharacters("Garceta");
			xMLStreamWriter.writeEndElement();

			xMLStreamWriter.writeEndElement();// Cierro libro.
			xMLStreamWriter.writeEndElement();// Cierro libros.
			xMLStreamWriter.writeEndDocument();// Cierro el documento.

			xMLStreamWriter.flush();
			xMLStreamWriter.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("XML escrito satisfactoriamente.");

	}

	private static String getString(String string) {
		Scanner entrada = new Scanner(System.in);
		System.out.println(string);
		return entrada.nextLine();
	}


	public static boolean preguntarMasAutores(String libro) {
		System.out.println("¿Quieres introducir mas autores para el libro: " + libro + "?");
		Scanner entrada = new Scanner(System.in);
		if (entrada.nextLine().equalsIgnoreCase("si")) {
			return true;
		}
		return false;

	}
}
