package com.almundo.callcenter.thread.process;

import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

import com.almundo.callcenter.thread.model.Call;

/**
 * CallQueue.java
 * Clase que maneja las colas de Ejecucion
 * @version 
 * 6 jul. 2017
 * @author Domingo Rondon
 */
public class CallQueue {
	
	final static Logger logger = Logger.getLogger(CallQueue.class);
	
	private static CallQueue instance;
	private LinkedBlockingQueue<Call> operadorCallQueue;
	
	private LinkedBlockingQueue<Call> supervisorCallQueue;
	private LinkedBlockingQueue<Call> directorCallQueue;
	
	private int contador;
	
	private CallQueue(){
		this.operadorCallQueue = new LinkedBlockingQueue<Call>();
		this.contador = 1;
		this.supervisorCallQueue = new LinkedBlockingQueue<Call>();
		this.directorCallQueue = new LinkedBlockingQueue<Call>();
	}
	
	private static CallQueue getInstance(){
		if(instance == null )
			instance = new CallQueue();

		return instance;
	}

	/**
	 * Método que Maneja la Cola de llamadas a los Cuales los Operadores
	 * deben eliminarla 
	 * @param duracion Duración de Ejecución de la LLamada
	 * @param cantColaPermitida Cantidad permitida para la Cola de Espera antes de que arroje la exception
	 * 2 jul. 2017
	 * @author Domingo Rondon
	 */
	public static void queueCall(int duracion, int cantColaPermitida) {
		try {
			
			Call call = new Call(getInstance().contador++, duracion);	
			
			logger.info("Recibiendo LLamada "+call.getNumero() + " con duración de " +  call.getDuracion() + " segundos.");
			
			if(getInstance().operadorCallQueue.size()<cantColaPermitida){
				getInstance().operadorCallQueue.put(call);
			}else{
				logger.error("Poll de LLamadas Lleno");
				getInstance().operadorCallQueue.put(call);
				
			}
			
		} catch (InterruptedException e) {
			 e.printStackTrace();
		}
	}
	
	/**
	 * Manejador de Colas dependiendo de la cantidad de Operadores, Directores y SUpervisores
	 * @param duracion Duración de Ejecución de la LLamada
	 * @param cantOperador Cantidad de Hilos Operadores
	 * @param cantSupervisor Cantidad de Hilos Supervisores
	 * @param cantDirector Cantidad de Hilos Directores
	 * 6 jul. 2017
	 * @author Domingo Rondon
	 */
	public static void queueCall(int duracion, int cantOperador, int cantSupervisor, int cantDirector) {
		try {
			
			Call call = new Call(getInstance().contador++, duracion);	
			
			logger.info("Recibiendo LLamada "+call.getNumero() + " con duración de " +  call.getDuracion() + " segundos.");
			
			if(getInstance().operadorCallQueue.size()<cantOperador){
				
				getInstance().operadorCallQueue.put(call);
			
			}else{
				
				logger.info("Pull Operador LLeno");
				
				if(getInstance().supervisorCallQueue.size()<cantSupervisor){
					
					getInstance().supervisorCallQueue.put(call);
				
				}else{
					logger.info("Pull Supervisor LLeno");
					
					if(getInstance().directorCallQueue.size()<cantDirector){
						
						getInstance().directorCallQueue.put(call);
					}else{
						
						logger.info("Pull Director Lleno");
					}
				}
			}
		}catch (InterruptedException e) {
			 e.printStackTrace();
		}
			
	}
	
	/**
	 * Método que remueve las llamadas del Pool de LLamadas
	 * @return
	 * 2 jul. 2017
	 * @author Domingo Rondon
	 */
	public static Call removeCall(){
		
		Call call = null;
		
		if(getInstance().operadorCallQueue.size()>0){
			call = getInstance().operadorCallQueue.poll();
			
		}if(getInstance().supervisorCallQueue.size()>0){
		
			call = getInstance().supervisorCallQueue.poll();
		
		}if(getInstance().directorCallQueue.size()>0){
			
			call = getInstance().directorCallQueue.poll();
		}
		
		if(call!=null){
			logger.info("Tomando LLamada "+call.getNumero());
		}
		
		return call;	
		
	}

}
