package com.aluracursos.screenmatch.excepcion;

public class ErrorDeDuracionDeConversionException extends RuntimeException {
    private String mensaje;

    public ErrorDeDuracionDeConversionException(String mensaje) {
        this.mensaje=mensaje;
    }

    @Override
    public String getMessage() {
        return this.mensaje;
    }
}
