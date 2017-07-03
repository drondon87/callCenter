package com.almundo.callcenter.model;

import org.apache.log4j.Logger;

import com.almundo.callcenter.generador.CallQueue;
import com.almundo.callcenter.process.EstatusPersona;

/**
 * Director.java
 * Clase que contiene atributos particulares del Director del CallCenter
 * @version 1.0
 * 1 jul. 2017
 * @author Domingo Rondon
 */
public class Director extends Persona implements Runnable{

final static Logger logger = Logger.getLogger(Director.class);
	
	private boolean running;
	private EstatusPersona status;
	private long callExiration;
	private int id;
	
	public Director(int id){
		this.id = id;
		this.status = EstatusPersona.FREE;
	}

	public void run() {
		while (running){
			
			if(status == EstatusPersona.FREE){
				
				Call call = CallQueue.removeCall();
				if(call!=null){
					logger.info("Director "+id +" Contestando Llamada ... "+call.getNumero());
					callExiration = System.currentTimeMillis() + (call.getDuracion() * 40 * 100); 
					status = EstatusPersona.IN_CALL;
				}
				
			}else{
				
				if(System.currentTimeMillis() > callExiration){
					status = EstatusPersona.FREE;
					logger.info("Director "+id+" Colgando Llamada ... ");
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
		trThread.setPriority(Thread.MIN_PRIORITY);
		trThread.start();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
