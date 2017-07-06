package com.almundo.callcenter.thread.process;

import com.almundo.callcenter.thread.model.Director;
import com.almundo.callcenter.thread.model.Operador;
import com.almundo.callcenter.thread.model.Supervisor;

/**
 * CallCenterTest.java
 * Método que ejecuta las pruebas de los Call Center
 * @version 
 * 6 jul. 2017
 * @author Domingo Rondon
 */
public class CallCenterTest {
	
	public static void main(String args[]){
		new CallGenerator().start();
		new Operador(1).start();
		new Operador(2).start();
		new Supervisor(1).start();
		new Supervisor(2).start();
		new Director(1).start();
	}

}
