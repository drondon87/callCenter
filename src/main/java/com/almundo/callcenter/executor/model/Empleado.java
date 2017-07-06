package com.almundo.callcenter.executor.model;

/**
 * Empleado.java
 * Entidad que contiene los elementos comunes para Supervisor, Operador y Director
 * @version 1.0
 * 3 jul. 2017
 * @author Domingo Rondon
 */
public class Empleado {
	
	private boolean free;
    private boolean canHandle;

	public boolean isFree() {
		return free;
	}
	public void setFree(boolean free) {
		this.free = free;
	}
	public boolean isCanHandle() {
		return canHandle;
	}
	public void setCanHandle(boolean canHandle) {
		this.canHandle = canHandle;
	}
    
    

}
