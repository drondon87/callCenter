package com.almundo.callcenter.process;

import com.almundo.callcenter.exception.EmpleadoNoDisponibleException;
import com.almundo.callcenter.executor.model.DirectorCallCenter;
import com.almundo.callcenter.executor.model.Empleado;
import com.almundo.callcenter.executor.model.OperadorCallCenter;
import com.almundo.callcenter.executor.model.SupervisorCallCenter;

/**
 * AsignadorLlamada.java
 * Interfaz que contiene las Operaciones del CallCenter
 * @version 1.0
 * 5 jul. 2017
 * @author Domingo Rondon
 */
public interface CallAssign {

	public Empleado getHandler() throws EmpleadoNoDisponibleException;
	public void addOperador(OperadorCallCenter operador);
	public void asignarSupervisor(SupervisorCallCenter supervisor);
	public void asignarDirector(DirectorCallCenter director);
	
}
