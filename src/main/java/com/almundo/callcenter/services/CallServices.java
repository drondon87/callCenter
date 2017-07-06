package com.almundo.callcenter.services;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.almundo.callcenter.model.OperadorMonitorThread;
import com.almundo.callcenter.model.OperadorSimpleThread;
import com.almundo.callcenter.process.RejectedExecutionHandlerImpl;
import com.almundo.callcenter.process.WorkerThread;

/**
 * CallServices.java
 * @descripcion Servicios de Atención de Llamadas
 * @version 1.0
 * 5 jul. 2017
 * @author Domingo Rondon
 */
public class CallServices {
	
	final static Logger logger = Logger.getLogger(CallServices.class);
	
	/**
	 * @descripcion Metodo que realiza la ejecución de los Operadores
	 * dependiendo de la Cantidad de Llamadas que se requieran atender
	 * En caso de que se conozca y se conozca la cantidad de Operadores
	 * a utilizar
	 * @param cantOperadores
	 * @param cantLlamadasEntrantes
	 * 5 jul. 2017
	 * @author Domingo Rondon
	 */
	public static void atencionLlamadaOperador(int cantOperadores, int cantLlamadasEntrantes){
		
		//Asigna la cantidad de Operadores de Atención de Llamadas
		ExecutorService executor = Executors.newFixedThreadPool(cantOperadores);
		
		try { 
			
			for (int i = 0; i < cantLlamadasEntrantes; i++) {
				Runnable operador = new OperadorSimpleThread(""+i);
	            executor.execute(operador);
	            
	        }
			
	        executor.shutdown();
	        
	        if (!executor.awaitTermination(8000, TimeUnit.MILLISECONDS)) {
		        	executor.shutdown();
		    } 
		} catch (InterruptedException e) {
	        	executor.shutdownNow();
		}
		
		logger.info("Fin Procesamiento de Llamadas");
	        
	}
	
	/**
	 * @descripcion Metodo que prepara Recibe las llamadas dependiendo de la Cantidad de Operadores Requeridos
	 * @param cantOperadores
	 * @param cantTotalOpeTrabajando
	 * @param cantTiempoEjecion
	 * @param cantLlamadasEspera
	 * @param cantLlamadas
	 * 5 jul. 2017
	 * @author Domingo Rondon
	 */
	public static void atencionLlamadasPoolRestringido(int cantOperadores, int cantTotalOpeTrabajando, int cantTiempoEjecion, int cantLlamadasEspera, int cantLlamadas ){
		
		RejectedExecutionHandlerImpl rejectionHandler = new RejectedExecutionHandlerImpl();
		
		ThreadFactory threadFactory = Executors.defaultThreadFactory();
		 
		ThreadPoolExecutor executorPool = new ThreadPoolExecutor(cantOperadores, cantTotalOpeTrabajando, cantTiempoEjecion, TimeUnit.SECONDS,  new ArrayBlockingQueue<Runnable>(cantLlamadasEspera), threadFactory, rejectionHandler);
	    
		  
		OperadorMonitorThread operadorThread = new OperadorMonitorThread(executorPool,2);
		
		Thread thread = new Thread(operadorThread);
		thread.start();
		
		for(int i=0; i<cantLlamadas; i++){
        	
            executorPool.execute(new OperadorSimpleThread("Call "+i));
        }
		try{
			
		Thread.sleep(20000);
        executorPool.shutdown();
        
        if (!executorPool.awaitTermination(8000, TimeUnit.MILLISECONDS)) {
        	executorPool.shutdown();
	        } 
	    } catch (InterruptedException e) {
	    	executorPool.shutdownNow();
	    }
		
		try {
			Thread.sleep(5000);
			operadorThread.shutdown();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	
}
