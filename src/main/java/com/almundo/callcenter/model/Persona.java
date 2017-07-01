package com.almundo.callcenter.model;

/**
 * Persona.java
 * Clase Principal que contiene la data de la Persona
 * @version 1.0
 * 1 jul. 2017
 * @author Domingo Rondon
 */
public class Persona {
	
	private String cedula;
	private String nombre;
	private String apellido;
	private String cargo;
	
	public Persona(){
		
	}
	
	public Persona(String cedula, String nombre, String apellido,
			String cargo) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.cargo = cargo;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	@Override
	public String toString() {
		return "Persona [cedula=" + cedula + ", nombre=" + nombre
				+ ", apellido=" + apellido + ", cargo=" + cargo + "]";
	}
	
	

}
