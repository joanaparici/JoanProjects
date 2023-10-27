package com.fpmislata.movies.http_errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fpmislata.movies.exception.ResourceNotFoundException;

// Clase que maneja las excepciones de la API
@ControllerAdvice
public class ApiExceptionHandler {

    // Manejar excepciones de tipo ResourceNotFoundException
    @ResponseStatus(HttpStatus.NOT_FOUND) // Establecer el código de estado HTTP a 404
    @ExceptionHandler({ ResourceNotFoundException.class }) // Manejar excepciones de este tipo
    @ResponseBody // Indicar que el método devuelve una respuesta en formato JSON
    public ErrorMessage notFoundRequest(Exception exception) {
        return new ErrorMessage(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        // Crear un objeto ErrorMessage con el mensaje de error y el código de estado 404,
        // y devolverlo como respuesta JSON
    }

    // Manejar excepciones genéricas de tipo Exception
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // Establecer el código de estado HTTP a 500
    @ExceptionHandler({ Exception.class }) // Manejar excepciones de tipo genérico Exception
    @ResponseBody // Indicar que el método devuelve una respuesta en formato JSON
    public ErrorMessage exception(Exception exception) {
        exception.printStackTrace(); // Imprimir la traza de la excepción en la consola
        return new ErrorMessage("Internal error", HttpStatus.INTERNAL_SERVER_ERROR.value());
        // Crear un objeto ErrorMessage con un mensaje genérico de error y el código de estado 500,
        // y devolverlo como respuesta JSON
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ErrorMessage badRequest(HttpMessageNotReadableException exception) {
        return new ErrorMessage("Invalid request body", HttpStatus.BAD_REQUEST.value());
    }
}
