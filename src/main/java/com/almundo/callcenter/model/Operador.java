package com.almundo.callcenter.model;

import org.apache.log4j.Logger;

import com.almundo.callcenter.generador.CallQueue;
import com.almundo.callcenter.process.EstatusPersona;

/**
 * Operador.java
 * Clase que contiene atributos particulares del Operador
 * @version 1.0
 * 1 jul. 2017
 * @author Domingo Rondon
 */
public class Operador extends Persona implements Runnable{

	final static Logger logger = Logger.getLogger(Operador.class);
	
	private boolean running;
	private EstatusPersona status;
	private long callExiration;
	private int id;

	public Operador(int id){
		this.id = id;
		this.status = EstatusPersona.FREE;
	}
	
	public void run() {
		while (running){
			
			if(status == EstatusPersona.FREE){
				
				Call call = CallQueue.removeCall();
				if(call!=null){
					logger.info("Operador "+id +" Contestando Llamada ... "+call.getNumero());
					callExiration = System.currentTimeMillis() + (call.getDuracion() * 40 * 100); 
					status = EstatusPersona.IN_CALL;
				}
				
			}else{
				
				if(System.currentTimeMillis() > callExiration){
					status = EstatusPersona.FREE;
					logger.info("Operador "+id+" Colgando Llamada ... ");
				}
			}
			
			sleep();
		}
		
	}
	
	private void sleep(){
		try{
			Thread.sleep(1000);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void stop(){
		running = false;
	}
	
	public void start(){
		running = true;
		Thread trThread = new Thread(this);
		trThread.setPriority(Thread.MAX_PRIORITY);
		trThread.start();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
