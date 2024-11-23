package com.prueba_tecnica.prueba_java8.exception;

/**
 * Excepción personalizada lanzada cuando no se encuentra una tarea.
 * 
 * Esta excepción es utilizada para señalar que una tarea solicitada no fue encontrada
 * en el sistema, típicamente en situaciones de búsqueda por ID o nombre. Al ser una excepción
 * sin verificación (unchecked), no es necesario manejarla explícitamente en el código, pero
 * se utiliza para indicar errores específicos de negocio relacionados con la ausencia de tareas.
 * 
 * @see RuntimeException
 */
public class TaskNotFoundException extends RuntimeException{

    /**
     * Constructor que crea una nueva instancia de la excepción con un mensaje personalizado.
     * 
     * @param message El mensaje de error que describe el motivo de la excepción. Este mensaje
     *                se incluirá en la salida de la excepción cuando se lance.
     */
    public TaskNotFoundException(String message){
        super(message);
    }
}
