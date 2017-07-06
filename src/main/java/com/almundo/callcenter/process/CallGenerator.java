package com.almundo.callcenter.process;

import java.util.Random;

import org.apache.log4j.Logger;

import com.almundo.callcenter.exception.PoolCallFullException;
import com.almundo.callcenter.generador.CallQueue;

/**
 * CallGenerator.java
 * @descripcion Clase que Genera llamadas Aleatorias
 * @version 
 * 5 jul. 2017
 * @author Domingo Rondon
 */
public class CallGenerator implements Runnable{

	final static Logger logger = Logger.getLogger(CallGenerator.class);
	private Random random;
	private Boolean running;
	
	public CallGenerator(){
		random = new Random();
	}
	
	public void start(){
		running = true;
		new Thread(this).start();
	}
	
	public void run() {
		
		while (running){
			int duracion = random.nextInt(10);
			
			if(duracion>4 & duracion<11){
				CallQueue.queueCall(duracion);
				sleep();
			}
		}
		
		
	}
	
	public void stop(){
		running = false;
	}
	
	
	private void sleep(){
		try{
			int sleep = random.nextInt(10);
			//logger.info("Terminando Llamada "+ sleep+ " segundos");
			Thread.sleep(sleep * 1000);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String... args){
		new CallGenerator().start();
	}

	public Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
	}

	public Boolean getRunning() {
		return running;
	}

	public void setRunning(Boolean running) {
		this.running = running;
	}

}
