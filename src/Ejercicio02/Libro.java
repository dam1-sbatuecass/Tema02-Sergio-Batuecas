package Ejercicio02;

import java.io.Serializable;

public class Libro implements Serializable{

	String isbn;
	String titulo;
	String autor;
	String editorial;
	
	
	public Libro() {
		// TODO Auto-generated constructor stub
	}
	
	public Libro(String isbn, String titulo, String autor, String editorial) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	
	
	
	
	
}
