package org.example.gestioncultural.modelo.procesos;

import org.example.gestioncultural.modelo.beans.Evento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

class GestionadorEventos {
    protected Validador validador;
    protected ArrayList<Evento> eventos;
    private final Repositorio <ArrayList<Evento>> repositorio;

    public GestionadorEventos() {
        validador = new Validador();
        repositorio = new Repositorio<>("eventos.ser");

        this.eventos = repositorio.leer();
        if (eventos == null) eventos = new ArrayList<>();
    }

    protected void guardarEventos() {
        this.repositorio.guardar(eventos);
    }

    protected List<LocalDate> fechasEnUso() {
        List<LocalDate> fechasEnUso = new ArrayList<>();

        for (Evento evento : eventos) {
            fechasEnUso.add(evento.getFecha());
        }

        return fechasEnUso;
    }

    public Optional<Evento> obtenerEventoPorId(Integer id) {
        return eventos.stream()
                .filter(evento -> evento.getIdEvento().equals(id))
                .findFirst();
    }
}
