package com.almundo.callcenter.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.almundo.callcenter.exception.EmpleadoNoDisponibleException;
import com.almundo.callcenter.executor.model.DirectorCallCenter;
import com.almundo.callcenter.executor.model.Empleado;
import com.almundo.callcenter.executor.model.OperadorCallCenter;
import com.almundo.callcenter.executor.model.SupervisorCallCenter;

/**
 * CallAsignImpl.java
 * Método que realiza el Procesamiento de la Selección del Empleado que realizará la atención de la llamada
 * @version 
 * 6 jul. 2017
 * @author Domingo Rondon
 */
public class CallAsignImpl implements CallAssign{

	private DirectorCallCenter director;
    private SupervisorCallCenter supervisor;
    private List<OperadorCallCenter> operadores = new ArrayList<OperadorCallCenter>();
    
    
	public Empleado getHandler() throws EmpleadoNoDisponibleException {
		if (operadores.isEmpty() && supervisor == null && director == null) {
            throw new EmpleadoNoDisponibleException(EmpleadoNoDisponibleException.ERROR_NO_EMPLEADO);
        }

        if (!operadores.isEmpty()) {
        	OperadorCallCenter operador = operadores.get(new Random().nextInt(operadores.size()));
            if (operador.isCanHandle()) {
                return operador;
            }
        }

        if (supervisor != null && supervisor.isCanHandle()) {
            return supervisor;
        }

        if (director != null && director.isCanHandle()) {
            return director;
        }

        throw new EmpleadoNoDisponibleException(EmpleadoNoDisponibleException.ERROR_NO_EMPLEADO);
	}
	

	public void addOperador(OperadorCallCenter operador) {
		if (operador.isFree()) {
			operadores.add(operador);
        }
		
	}

	public void asignarSupervisor(SupervisorCallCenter supervisor) {
		 this.supervisor = supervisor;
		
	}

	public void asignarDirector(DirectorCallCenter director) {
		 this.director = director;
		
	}

}
