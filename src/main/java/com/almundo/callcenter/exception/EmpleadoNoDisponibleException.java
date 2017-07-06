package com.almundo.callcenter.exception;

/**
 * EmpleadoNoDisponibleException.java
 * @descripcion Excepcion que Existe en caso de que no haya Empleado Disponible
 * @version 1.0
 * 3 jul. 2017
 * @author Domingo Rondon
 */
public class EmpleadoNoDisponibleException extends Exception{

	private static final long serialVersionUID = 1L;
	public static final String ERROR_NO_EMPLEADO = "Disculpe, no existen Empleados Disponibles para atender su solicitud";
	
	public EmpleadoNoDisponibleException(String message) {
        super(message);
    }
}
