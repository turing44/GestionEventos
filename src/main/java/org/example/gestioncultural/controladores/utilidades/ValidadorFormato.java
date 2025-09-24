package org.example.gestioncultural.controladores.utilidades;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ValidadorFormato {
    public String validarTexto(String texto) throws IllegalArgumentException{
        if (texto == null || texto.trim().isBlank()) {
            throw new IllegalArgumentException("Debes completar todos los campos");
        }
        return texto;
    }

    public LocalDate validarFecha(String fecha) throws IllegalArgumentException {
        if (fecha == null || fecha.isBlank()) {
            throw new IllegalArgumentException("La fecha no puede estar vacia");
        }
        try {
            return LocalDate.parse(fecha);


        } catch (DateTimeParseException dtpe) {
            throw new IllegalArgumentException("Formato de fecha incorrecto");
        }
    }


    public LocalDate validarFechaFutura(String fecha) throws IllegalArgumentException{
        if (fecha == null || fecha.isBlank()) {
            throw new IllegalArgumentException("La fecha no puede estar vacia");
        }
        try {
            return validarEsFututa(LocalDate.parse(fecha));

        } catch (DateTimeParseException dtpe) {
            throw new IllegalArgumentException("Formato de fecha incorrecto");
        }

    }

    private LocalDate validarEsFututa(LocalDate fecha) throws IllegalArgumentException{
        if (fecha.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha no puede ser anterior a la actual");
        }
        return fecha;
    }

    public Integer validarEntero(String entero) throws IllegalArgumentException{
        if (entero == null || entero.isBlank()) {
            throw new IllegalArgumentException("Todos los campos deben estar rellenos");
        }
        try {
            return Integer.parseInt(entero);

        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("Formato incorrecto");
        }
    }

    public Double validarDecimal(String decimal) throws IllegalArgumentException{
        if (decimal == null || decimal.isBlank()) {
            throw new IllegalArgumentException("Todos los campos deben estar rellenos");
        }
        try {
            return Double.parseDouble(decimal);

        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("Formato incorrecto");
        }
    }


}
