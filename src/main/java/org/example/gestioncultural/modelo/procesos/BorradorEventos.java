package org.example.gestioncultural.modelo.procesos;

import org.example.gestioncultural.modelo.beans.Evento;

import java.util.Optional;

public class BorradorEventos extends GestionadorEventos {

    public void borrarEventoPorId(Integer id) {
        Optional<Evento> evento = obtenerEventoPorId(id);
        evento.ifPresentOrElse(
                e -> {
                    eventos.remove(e);
                    guardarEventos();
                    },
                () -> { throw new IllegalArgumentException("No se encontró el evento con id: " + id); }
        );
    }

}
