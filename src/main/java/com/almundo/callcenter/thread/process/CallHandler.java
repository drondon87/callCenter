package com.almundo.callcenter.thread.process;

import org.apache.log4j.Logger;

import com.almundo.callcenter.enums.EstatusEmpleado;
import com.almundo.callcenter.thread.model.Call;
import com.almundo.callcenter.thread.model.Supervisor;

/**
 * CallHandler.java
 * @descripcion Entidad que maneja el Proceso de llamaad
 * @version 
 * 6 jul. 2017
 * @author Domingo Rondon
 */
public class CallHandler {
	
	final static Logger logger = Logger.getLogger(Supervisor.class);
	
	/**
	 * @descripcion Metodo que es utilizatio para el manejo de llamada de los empleados
	 * @param status: Estatus del Empleado en ejecución en caso de esté Libre o Atendiendo llamada
	 * @param paramId: Identificador de la clase que está llamando ya sea Operador, Supervisor o Director
	 * @param id: Número de la entidad en Ejecución
	 * @param callExiration: Tiempo de Duración creado Ramdom para la llamada
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
