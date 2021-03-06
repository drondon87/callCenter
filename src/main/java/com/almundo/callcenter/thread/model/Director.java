package com.almundo.callcenter.thread.model;

import org.apache.log4j.Logger;

import com.almundo.callcenter.enums.EstatusEmpleado;
import com.almundo.callcenter.thread.process.CallHandler;
import com.almundo.callcenter.thread.process.CallQueue;

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
	private EstatusEmpleado status;
	private long callExiration;
	private int id;
	
	public Director(int id){
		this.id = id;
		this.status = EstatusEmpleado.FREE;
	}

	public void run() {
		while (running){
			CallHandler.executionHandler(status, "Director ", id, callExiration);
			
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
