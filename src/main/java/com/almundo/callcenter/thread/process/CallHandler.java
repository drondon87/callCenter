package com.almundo.callcenter.thread.process;

import org.apache.log4j.Logger;

import com.almundo.callcenter.enums.EstatusEmpleado;
import com.almundo.callcenter.thread.model.Call;
import com.almundo.callcenter.thread.model.Supervisor;

/**
 * CallHandler.java
 * Entidad que maneja el Proceso de llamaad
 * @version 
 * 6 jul. 2017
 * @author Domingo Rondon
 */
public class CallHandler {
	
	final static Logger logger = Logger.getLogger(Supervisor.class);
	
	/**
	 * Metodo que es utilizatio para el manejo de llamada de los empleados
	 * @param status: Estatus del Empleado en ejecuci�n en caso de est� Libre o Atendiendo llamada
	 * @param paramId: Identificador de la clase que est� llamando ya sea Operador, Supervisor o Director
	 * @param id: N�mero de la entidad en Ejecuci�n
	 * @param callExiration: Tiempo de Duraci�n creado Ramdom para la llamada
	 * 6 jul. 2017
	 * @author Domingo Rondon
	 */
	public static void executionHandler(EstatusEmpleado status, String paramId,int id,long callExiration){
		
		if(status == EstatusEmpleado.FREE){
			
			Call call = CallQueue.removeCall();
			if(call!=null){
				logger.info(paramId+id +" Contestando Llamada ... "+call.getNumero());
				callExiration = System.currentTimeMillis() + (call.getDuracion() * 40 * 100); 
				status = EstatusEmpleado.IN_CALL;
			}
			
		}else{
			
			if(System.currentTimeMillis() > callExiration){
				status = EstatusEmpleado.FREE;
				logger.info("Supervisor "+id+" Colgando Llamada ... ");
			}
		}
	}

}
