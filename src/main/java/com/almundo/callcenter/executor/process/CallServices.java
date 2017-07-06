package com.almundo.callcenter.executor.process;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

/**
 * CallServices.java
 * Servicios de Atención de Llamadas
 * @version 1.0
 * 5 jul. 2017
 * @author Domingo Rondon
 */
public class CallServices {
	
	final static Logger logger = Logger.getLogger(CallServices.class);
	
	/**
	 * Metodo que realiza la ejecución de los Operadores
	 * dependiendo de la Cantidad de Llamadas que se requieran atender
	 * En caso de que se conozca y se conozca la cantidad de Operadores
	 * a utilizar
	 * @param cantOperadores Cantidad de Hilos a Crear
	 * @param cantLlamadasEntrantes Cantidad de Hilos que se estaran ejecutando (Llamadas)
	 * 5 jul. 2017
	 * @author Domingo Rondon
	 */
	public static void atencionLlamadaOperador(int cantOperadores, int cantLlamadasEntrantes){
		
		//Asigna la cantidad de Operadores de Atención de Llamadas
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		try { 
			
			for (int i = 0; i < cantLlamadasEntrantes; i++) {
				
				Runnable operador = new OperadorSimpleThread(""+i);
				executor.execute(operador);
	            
	        }
			
	        executor.shutdown();
	        
	        if (!executor.awaitTermination(3000, TimeUnit.MILLISECONDS)) {
		        	executor.shutdown();
		    } 
		} catch (InterruptedException e) {
	        	executor.shutdownNow();
		}
		
		logger.info("Fin Procesamiento de Llamadas");
	        
	}
	
	/**
	 * Metodo que prepara Recibe las llamadas dependiendo de la Cantidad de Operadores Requeridos
	 * @param cantOperadores Cantidad de Hilos a Crear
	 * @param cantTotalOpeTrabajando Cantidad de Hilos en Ejecución Permitidos
	 * @param cantTiempoEjecion Tiempo de Ejecución del Hilo Creado
	 * @param cantLlamadasEspera Cantidad de Hilos en Espera permitidos
	 * @param cantLlamadas Cantidad de Hilos a lanzar
	 * 5 jul. 2017
	 * @author Domingo Rondon
	 */
	public static void atencionLlamadasPoolRestringido(int cantOperadores, int cantTotalOpeTrabajando, int cantTiempoEjecion, int cantLlamadasEspera, int cantLlamadas ){
		
		RejectedExecutionHandlerImpl rejectionHandler = new RejectedExecutionHandlerImpl();
		
		ThreadFactory threadFactory = Executors.defaultThreadFactory();
		 
		ThreadPoolExecutor executorPool = new ThreadPoolExecutor(cantOperadores, cantTotalOpeTrabajando, cantTiempoEjecion, 
				TimeUnit.SECONDS,  new ArrayBlockingQueue<Runnable>(cantLlamadasEspera), threadFactory, rejectionHandler);
	    
		  
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
