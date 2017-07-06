# CallCenter
Call Center para Almundo.com

Examen prueba para Almundo.com

#CLASE GeneradorTestCases

Se encuentran los test de validación para recibir la llamada, la misma contiene los siguientes métodos:

- testCallGenerator = Realiza la prueba de Ejecución del Generador de LLamadas.

- testAsignHandlerOperador = Test que evalúa Cual de los Empleados Está libre para atender la llamada. Existen 2 Operadores Libres, 1 Supervisor y 1 Director. La llamada es tomada por uno de los Operadores.

- testAsignHandlerSupervisor = Test que evalúa Cual de los Empleados Está libre para atender la llamada. Existen 2 Operadores No Disponibles, 1 Supervisor y 1 Director. La llamada es tomada por un Supervisor.

- testAsignHandlerDirector = Test que evalúa Cual de los Empleados Está libre para atender la llamada. Existen 2 Operadores No Disponibles, 1 Supervisor No Disponible y 1 Director. La llamada es tomada por un Director

- testAsignHandlerException = Test que evalúa Cual de los Empleados Está libre para atender la llamada. Existen 2 Operadores No Disponibles, 1 Supervisor No Disponible y 1 Director No disponible. La llamada no es antendida y arroja una Excepcion.

#CLASE ExecutorTestCalls

En ella se pueden ejecutar dos métodos que creé utilizando el ExecutorServices, puedo controlar el flujo de llamadas y la cantidad de Hilos ejecutandose el problema es que no supe como poder crear una particularidad para cada hilo (Operador, Supervisor y Director) pero la dejé allí por si pueden darme una opinión sobre lo que pudo fallar en esa implementación.

#CLASE CallCenterTest

Se puede ejecutar las pruebas del Sistema, ahorita existen 2 Operadores , 2 Supervisor y 1 Director, El Método CallGenerator() genera llamdas con tiempo aleatorio entre 5 y 10 segundos (se puede visualizar en el Método run de dicha clase) , luego estos 5 hilos (Operador, Supervisor y Director) van tomando las llamadas y retirandolas del pull.