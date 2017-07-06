package com.almundo.callcenter.exception;

/**
 * PollCallFullException.java
 * @descripcion Exception en caso de que el Poll de Llamadas esté lleno
 * @version 
 * 2 jul. 2017
 * @author Domingo Rondon
 */
public class PoolCallFullException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public PoolCallFullException(String message) {
        super(message);
    }

}
