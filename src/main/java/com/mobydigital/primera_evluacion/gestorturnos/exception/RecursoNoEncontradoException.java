package com.mobydigital.primera_evluacion.gestorturnos.exception;

public class RecursoNoEncontradoException extends IllegalArgumentException {
    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}