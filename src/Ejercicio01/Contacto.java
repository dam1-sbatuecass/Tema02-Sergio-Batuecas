package Ejercicio01;

import java.io.Serializable;

public class Contacto implements Serializable {
	String nombre;
	String apellidos;
	String email;
	String telefono;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		String cadena;
		cadena = "Nombre: " + nombre + " Apellidos: " + apellidos + " email: " + email + " Telefono: " + telefono;
		return cadena;
	}

}
