package com.almundo.callcenter.process;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import com.almundo.callcenter.exception.PoolCallFullException;

/**
 * RejectedExecutionHandlerImpl.java
 * @descripcion Manejador que enviará una Excepcion cuando el Pool de Llamadas esté lleno
 * @version 1.0
 * 5 jul. 2017
 * @author Domingo Rondon
 */
public class RejectedExecutionHandlerImpl implements RejectedExecutionHandler{

	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		
		try {
			throw new PoolCallFullException(r.toString() + " is rejected");
		} catch (PoolCallFullException e) {
			e.printStackTrace();
		}
		
	}

}
