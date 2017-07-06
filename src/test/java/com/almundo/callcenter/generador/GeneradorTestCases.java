package com.almundo.callcenter.generador;

import org.junit.Test;

import com.almundo.callcenter.exception.EmpleadoNoDisponibleException;
import com.almundo.callcenter.executor.model.DirectorCallCenter;
import com.almundo.callcenter.executor.model.Empleado;
import com.almundo.callcenter.executor.model.OperadorCallCenter;
import com.almundo.callcenter.executor.model.SupervisorCallCenter;
import com.almundo.callcenter.process.CallAsignImpl;
import com.almundo.callcenter.process.CallAssign;
import com.almundo.callcenter.thread.process.CallGenerator;

import junit.framework.Assert;

/**
 * GeneradorTestCases.java
 * Clase que contiene los tests de la aplicación
 * @version 1.0
 * 3 jul. 2017
 * @author Domingo Rondon
 */
public class GeneradorTestCases {

	/**
	 * Prueba de Ejecución del Generador de LLamadas
	 * @throws InterruptedException
	 * 3 jul. 2017
	 * @author Domingo Rondon
	 */
	@Test
    public void testCallGenerator() throws InterruptedException {
		new CallGenerator();
    }
	
	/**
	 * Test que evalúa Cual de los Empleados Está libre para atender la llamada
	 * Existen 2 Operadores Libres, 1 Supervisor y 1 Director
	 * La llamada es tomada por uno de los Operadores
	 * @throws EmpleadoNoDisponibleException
	 * 3 jul. 2017
	 * @author Domingo Rondon
	 */
	@Test
	public void testAsignHandlerOperador() throws EmpleadoNoDisponibleException{
		
		CallAssign callAllocator = new CallAsignImpl();
		
		OperadorCallCenter operador1 = new OperadorCallCenter();
        operador1.setCanHandle(true);
        operador1.setFree(true);
        
        OperadorCallCenter operador2 = new OperadorCallCenter();
        operador2.setCanHandle(true);
        operador2.setFree(true);
        
        callAllocator.addOperador(operador1);
        callAllocator.addOperador(operador2);
        
        SupervisorCallCenter supervisor = new SupervisorCallCenter();
        supervisor.setCanHandle(true);
        supervisor.setFree(true);
        
        callAllocator.asignarSupervisor(supervisor);
        
        DirectorCallCenter director = new DirectorCallCenter();
        director.setCanHandle(true);
        director.setFree(true);
        
        callAllocator.asignarDirector(director);
        
        Empleado callHandler = callAllocator.getHandler();
        String respuesta = callHandler.getClass().getName();
        
        Assert.assertNotNull(callHandler.getClass().getName());
        Assert.assertEquals("com.almundo.callcenter.executor.model.OperadorCallCenter", respuesta);
	}
	
	/**
	 * Test que evalúa Cual de los Empleados Está libre para atender la llamada
	 * Existen 2 Operadores No Disponibles, 1 Supervisor y 1 Director
	 * La llamada es tomada por un Supervisor
	 * @throws EmpleadoNoDisponibleException
	 * 3 jul. 2017
	 * @author Domingo Rondon
	 */
	@Test
	public void testAsignHandlerSupervisor() throws EmpleadoNoDisponibleException{
		
		CallAssign callAllocator = new CallAsignImpl();
		
		OperadorCallCenter operador1 = new OperadorCallCenter();
        operador1.setCanHandle(false);
        operador1.setFree(false);
        
        OperadorCallCenter operador2 = new OperadorCallCenter();
        operador2.setCanHandle(false);
        operador2.setFree(false);
        
        callAllocator.addOperador(operador1);
        callAllocator.addOperador(operador2);
        
        SupervisorCallCenter supervisor = new SupervisorCallCenter();
        supervisor.setCanHandle(true);
        supervisor.setFree(true);
        
        callAllocator.asignarSupervisor(supervisor);
        
        DirectorCallCenter director = new DirectorCallCenter();
        director.setCanHandle(true);
        director.setFree(true);
        
        callAllocator.asignarDirector(director);
        
        Empleado callHandler = callAllocator.getHandler();
        String respuesta = callHandler.getClass().getName();
        
        Assert.assertNotNull(callHandler.getClass().getName());
        Assert.assertEquals("com.almundo.callcenter.executor.model.SupervisorCallCenter", respuesta);
	}
	
	/**
	 * Test que evalúa Cual de los Empleados Está libre para atender la llamada
	 * Existen 2 Operadores No Disponibles, 1 Supervisor No Disponible y 1 Director
	 * La llamada es tomada por un Director
	 * @throws EmpleadoNoDisponibleException
	 * 3 jul. 2017
	 * @author Domingo Rondon
	 */
	@Test
	public void testAsignHandlerDirector() throws EmpleadoNoDisponibleException{
		
		CallAssign callAllocator = new CallAsignImpl();
		
		OperadorCallCenter operador1 = new OperadorCallCenter();
        operador1.setCanHandle(false);
        operador1.setFree(false);
        
        OperadorCallCenter operador2 = new OperadorCallCenter();
        operador2.setCanHandle(false);
        operador2.setFree(false);
        
        callAllocator.addOperador(operador1);
        callAllocator.addOperador(operador2);
        
        SupervisorCallCenter supervisor = new SupervisorCallCenter();
        supervisor.setCanHandle(false);
        supervisor.setFree(false);
        
        callAllocator.asignarSupervisor(supervisor);
        
        DirectorCallCenter director = new DirectorCallCenter();
        director.setCanHandle(true);
        director.setFree(true);
        
        callAllocator.asignarDirector(director);
        
        Empleado callHandler = callAllocator.getHandler();
        String respuesta = callHandler.getClass().getName();
        
        Assert.assertNotNull(callHandler.getClass().getName());
        Assert.assertEquals("com.almundo.callcenter.executor.model.DirectorCallCenter", respuesta);
	}
	
	/**
	 * Test que evalúa Cual de los Empleados Está libre para atender la llamada
	 * Existen 2 Operadores No Disponibles, 1 Supervisor No Disponible y 1 Director No disponible
	 * La llamada no es antendida y arroja una Excepcion
	 * @throws EmpleadoNoDisponibleException
	 * 3 jul. 2017
	 * @author Domingo Rondon
	 */
	@Test(expected = EmpleadoNoDisponibleException.class)
	public void testAsignHandlerException() throws EmpleadoNoDisponibleException{
		
		CallAssign callAllocator = new CallAsignImpl();
		
		OperadorCallCenter operador1 = new OperadorCallCenter();
        operador1.setCanHandle(false);
        operador1.setFree(false);
        
        OperadorCallCenter operador2 = new OperadorCallCenter();
        operador2.setCanHandle(false);
        operador2.setFree(false);
        
        callAllocator.addOperador(operador1);
        callAllocator.addOperador(operador2);
        
        SupervisorCallCenter supervisor = new SupervisorCallCenter();
        supervisor.setCanHandle(false);
        supervisor.setFree(false);
        
        callAllocator.asignarSupervisor(supervisor);
        
        DirectorCallCenter director = new DirectorCallCenter();
        director.setCanHandle(false);
        director.setFree(false);
        
        callAllocator.asignarDirector(director);
        
        Empleado callHandler = callAllocator.getHandler();
	}
	
}
