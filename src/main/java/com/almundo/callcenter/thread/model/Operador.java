package com.almundo.callcenter.thread.model;

import org.apache.log4j.Logger;

import com.almundo.callcenter.enums.EstatusEmpleado;
import com.almundo.callcenter.enums.RolEmpleado;
import com.almundo.callcenter.thread.process.CallHandler;
import com.almundo.callcenter.thread.process.CallQueue;

/**
 * Operador.java
 * @version 
 * 6 jul. 2017
 * @author Domingo Rondon
 */
public class Operador implements Runnable{

	final static Logger logger = Logger.getLogger(Operador.class);
	
	private boolean running;
	private EstatusEmpleado status;
	private long callExiration;
	private int id;

	public Operador(int id){
		this.id = id;
		this.status = EstatusEmpleado.FREE;
	}
	
	public void tomarLlamada(RolEmpleado rol){
		Call call = CallQueue.removeCall();
		if(call!=null){
			logger.info("Operador "+id +" Rol "+ rol + " Contestando Llamada ... "+call.getNumero());
			callExiration = System.currentTimeMillis() + (call.getDuracion() * 40 * 100); 
			status = EstatusEmpleado.IN_CALL;
		}
	}
	
	
	public void run() {
		while (running){
			
			CallHandler.executionHandler(status, "Operador ", id, callExiration);	
			
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

	public EstatusEmpleado getStatus() {
		return status;
	}

	public void setStatus(EstatusEmpleado status) {
		this.status = status;
	}

}
