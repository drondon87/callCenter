package com.almundo.callcenter.executor.process;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;

import com.almundo.callcenter.exception.PoolCallFullException;

/**
 * RejectedExecutionHandlerImpl.java
 * Manejador que enviará una Excepcion cuando el Pool de Llamadas esté lleno
 * @version 1.0
 * 5 jul. 2017
 * @author Domingo Rondon
 */
public class RejectedExecutionHandlerImpl implements RejectedExecutionHandler{

	final static Logger logger = Logger.getLogger(RejectedExecutionHandlerImpl.class);
	
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		
		logger.debug("Rechazando llamadas");
		try {
			
			throw new PoolCallFullException(r.toString() + " is rejected");
		
		} catch (PoolCallFullException e) {
			e.printStackTrace();
		}
		
	}

	

}
