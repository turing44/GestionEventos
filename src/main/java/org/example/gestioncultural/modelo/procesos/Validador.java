package org.example.gestioncultural.modelo.procesos;

import org.example.gestioncultural.modelo.beans.Conferencia;
import org.example.gestioncultural.modelo.beans.Evento;
import org.example.gestioncultural.modelo.beans.Exposicion;
import org.example.gestioncultural.modelo.beans.Taller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Validador {

    public boolean esValido(Evento evento) {
        if (evento == null) {
            throw new IllegalArgumentException("El evento no puede ser nulo");
        }

        // Validar el tipo de evento
        if (evento instanceof Taller) {
            return esTallerCorrecto((Taller) evento);
        } else if (evento instanceof Conferencia) {
            return esConferenciaCorrecto((Conferencia) evento);
        } else if (evento instanceof Exposicion) {
            return esExposicionCorrecto((Exposicion) evento);
        }

        throw new IllegalArgumentException("Evento de tipo no contemplado");
    }

    public LocalDate validarFecha(String fecha) throws IllegalArgumentException {
        if (fecha != null) {
            try {
                return LocalDate.parse(fecha);
            } catch  (DateTimeParseException dtpe) {
                throw new IllegalArgumentException("Formato de fecha no valido");
            }
        }
        throw new IllegalArgumentException("Fecha no valida");
    }

    private boolean esTallerCorrecto(Taller taller) {
        // validacion general
        validarCampos(taller.getTitulo(), taller.getFecha(), taller.getPonente(), taller.getAforo(), taller.getPrecio());

        // validacion especifica
        if (taller.getFecha().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha es anterior a la actual");
        }
        if (taller.getAforo() < 1) {
            throw new IllegalArgumentException("El número de asistentes no puede ser menor que 1");
        }
        if (taller.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }

        return true;
    }

    private boolean esConferenciaCorrecto(Conferencia conferencia) {
        validarCampos(conferencia.getTitulo(), conferencia.getFecha(), conferencia.getPonente());

        if (conferencia.getFecha().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha es anterior a la actual");
        }

        return true;
    }

    private boolean esExposicionCorrecto(Exposicion exposicion) {
        validarCampos(exposicion.getTitulo(), exposicion.getFecha(), exposicion.getPonente(), exposicion.getFecha_fin(), exposicion.getPrecio());

        if (exposicion.getFecha().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha es anterior a la actual");
        }
        if (exposicion.getFecha_fin().isBefore(exposicion.getFecha())) {
            throw new IllegalArgumentException("La fecha final es anterior a la fecha de inicio");
        }
        if (exposicion.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }

        return true;
    }

    /**
     * Valida que los campos no esten ni nulos ni vacios
     * uso numero variable de parametros para hacer la funcion reutilizable
     * @param campos
     *
     */
    private void validarCampos(Object... campos) {
        for (Object campo : campos) {
            if (campo == null) {
                throw new IllegalArgumentException("Un campo obligatorio es nulo");
            }
            if (campo == "") {
                throw new IllegalArgumentException("Ningun campo puede estar vacio");
            }
        }
    }
}
