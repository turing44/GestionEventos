package org.example.gestioncultural.modelo.util;

import org.example.gestioncultural.modelo.beans.Conferencia;
import org.example.gestioncultural.modelo.beans.Evento;
import org.example.gestioncultural.modelo.beans.Exposicion;
import org.example.gestioncultural.modelo.beans.Taller;

import java.time.LocalDate;
import java.util.List;

public class Validador {
    public void validar(Evento evento) throws IllegalArgumentException{
        if (evento == null) {
            throw new IllegalArgumentException("El evento no puede ser nulo");
        }


        // Validar el tipo de evento
        if (evento instanceof Taller t) {
            validarTaller(t);
        } else if (evento instanceof Conferencia c) {
            validarConferencia(c);
        } else if (evento instanceof Exposicion e) {
            validarExposicion(e);
        } else {
            throw new IllegalArgumentException("Evento de tipo no contemplado");
        }

    }



    public void validarFechaCogida(Evento evento, List<LocalDate> fechasCogidas) throws IllegalArgumentException {
        LocalDate fechaPropuesta = evento.getFecha();

        for (LocalDate fechaCogida : fechasCogidas) {
            if (fechaCogida.isEqual(fechaPropuesta)) {
                throw new IllegalArgumentException("La fecha ya está en uso");
            }
        }
    }

    private void validarTaller(Taller taller) {
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

    }

    private void validarConferencia(Conferencia conferencia) {
        validarCampos(conferencia.getTitulo(), conferencia.getFecha(), conferencia.getPonente());

        if (conferencia.getFecha().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha es anterior a la actual");
        }
    }

    private void validarExposicion(Exposicion exposicion) {
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

    }

    public void validarBorradoPermitido(Evento evento) throws IllegalArgumentException {
        if (evento.estaEnCurso()) {
            throw new IllegalArgumentException("No se puede borrar este evento");
        }
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
