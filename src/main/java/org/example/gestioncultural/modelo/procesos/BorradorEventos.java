package org.example.gestioncultural.modelo.procesos;

import org.example.gestioncultural.modelo.beans.Evento;

import java.util.Optional;

public class BorradorEventos extends GestionadorEventos {

    public void borrarEventoPorId(Integer id) throws IllegalArgumentException{
        Optional<Evento> evento = obtenerEventoPorId(id);
        if (evento.isPresent()) {
            try {
                validador.validarBorradoPermitido(evento.get());
                eventos.remove(evento.get());
                guardarEventos();
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e.getMessage());
            }

        } else {
            throw new IllegalArgumentException("No se encontró el evento con id: " + id);
        }

    }

}
