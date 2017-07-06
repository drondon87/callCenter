package com.almundo.callcenter.model;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;

/**
 * OperadorThread.java
 * @descripcion Hilo que trabajara la atencion de la llamada por parte del Operador
 * @version 1.0
 * 5 jul. 2017
 * @author Domingo Rondon
 */
public class OperadorSimpleThread implements Runnable{
	
	final static Logger logger = Logger.getLogger(OperadorSimpleThread.class);
	private String call;

	public OperadorSimpleThread(String s){
        this.call=s;
    }
	
	/**
	 * @descripcion Tiempo de Duración de Atención de la LLamada
	 * 5 jul. 2017
	 * @author Domingo Rondon
	 */
	private void processCall() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	
	@Override
    public String toString(){
        return this.call;
    }
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
			logger.info(Thread.currentThread().getName()+" Contesta. LLamada = "+call);
			processCall();
			logger.info(Thread.currentThread().getName()+" Termina.");
		
	}

}
