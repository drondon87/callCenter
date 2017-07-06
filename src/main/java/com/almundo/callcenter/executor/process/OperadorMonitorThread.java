package com.almundo.callcenter.executor.process;

import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;

/**
 * OperadorMonitorThread.java
 * @descripcion Clase que Monitorea todas las llamadas Atendidas
 * @version 
 * 5 jul. 2017
 * @author Domingo Rondon
 */
public class OperadorMonitorThread implements Runnable{
	
	final static Logger logger = Logger.getLogger(OperadorMonitorThread.class);
	private ThreadPoolExecutor executor;
    private int seconds;
    private boolean run=true;
    
    public OperadorMonitorThread(ThreadPoolExecutor executor, int delay)
    {
        this.executor = executor;
        this.seconds=delay;
    }
    
    public void shutdown(){
        this.run=false;
    }

    public void run() {
		
    	while(run){
			
			logger.info(
          String.format("[Cola de Llamadas Atendidas] Active: %d, Completed: %d, Task: %d, isTerminated: %s",
              this.executor.getActiveCount(),
              this.executor.getCompletedTaskCount(),
              this.executor.getTaskCount(),
              this.executor.isTerminated()));
            try {
                Thread.sleep(seconds*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
	}

}
