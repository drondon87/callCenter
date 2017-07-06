package com.almundo.callcenter.thread.model;

import java.io.Serializable;

public class Call implements Serializable{
	
	private int duracion;
	private int numero;

	public Call(int numero, int duracion) {
		this.numero = numero;
		this.duracion = duracion;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	

}
