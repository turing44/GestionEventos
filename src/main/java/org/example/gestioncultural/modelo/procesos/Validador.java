package org.example.gestioncultural.modelo.procesos;

import org.example.gestioncultural.modelo.beans.Conferencia;
import org.example.gestioncultural.modelo.beans.Evento;
import org.example.gestioncultural.modelo.beans.Exposicion;
import org.example.gestioncultural.modelo.beans.Taller;

import java.time.LocalDate;

public class Validador {

    public boolean esValido(Evento evento) throws IllegalArgumentException {
        if (evento instanceof Taller) {
            return esTallerCorrecto((Taller) evento);
        } else if (evento instanceof Conferencia) {
            return esConferenciaCorrecto((Conferencia) evento);
        } else if (evento instanceof Exposicion) {
            return esExposicionCorrecto((Exposicion) evento);
        }

        throw new IllegalArgumentException("Evento de tipo no contemplado");
    }

    private boolean esTallerCorrecto(Taller taller) throws IllegalArgumentException {
        boolean esCorrecto =
                taller.getTitulo() != null &&
                taller.getFecha() != null &&
                taller.getPonente() != null &&
                taller.getNumeroMaximoAsistentes() != null &&
                taller.getPrecio() != null;

        if (esCorrecto) {
            if (taller.getFecha().isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("La fecha es anterior a la actual");
            }
            if (taller.getNumeroMaximoAsistentes() < 1) {
                throw new IllegalArgumentException("El numero de asistentes no puede ser menor que 1");
            }
            if (taller.getPrecio() < 0) {
                throw new IllegalArgumentException("La precio no puede ser negativo");
            }
        }


        return esCorrecto;


    }
    private boolean esConferenciaCorrecto(Conferencia conferencia) {

        boolean esCorrecto =
                conferencia.getTitulo() != null &&
                conferencia.getFecha() != null &&
                conferencia.getPonente() != null;

        if (esCorrecto) {
            if (conferencia.getFecha().isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("La fecha es anterior a la actual");
            }
        }


        return esCorrecto;
    }
    private boolean esExposicionCorrecto(Exposicion exposicion) {

        boolean esCorrecto =
                exposicion.getTitulo() != null &&
                exposicion.getFecha() != null &&
                exposicion.getPonente() != null &&
                exposicion.getFecha_fin() != null &&
                exposicion.getPrecio() != null;

        if (esCorrecto) {
            if (exposicion.getFecha().isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("La fecha es anterior a la actual");
            }
            if (exposicion.getFecha_fin().isBefore(exposicion.getFecha())) {
                throw new IllegalArgumentException("La fecha final es anterior a la fecha de inicio");
            }
            if (exposicion.getPrecio() < 0) {
                throw new IllegalArgumentException("La precio no puede ser negativo");
            }
        }

        return esCorrecto;
    }
}
