package org.example.gestioncultural.modelo.procesos;

import org.example.gestioncultural.modelo.beans.Evento;
import org.example.gestioncultural.modelo.util.Repositorio;
import org.example.gestioncultural.modelo.util.Validador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class GestionadorEventos {
    private final String NOMBRE_ARCHIVO_SERIALIZADO = "eventos.ser";
    protected Validador validador;
    protected ArrayList<Evento> eventos;
    private final Repositorio<ArrayList<Evento>> repositorio;

    public GestionadorEventos() {
        validador = new Validador();
        repositorio = new Repositorio<>(NOMBRE_ARCHIVO_SERIALIZADO);

        this.eventos = repositorio.leer();
        if (eventos == null) eventos = new ArrayList<>();
    }

    protected void guardarEventos() {
        this.repositorio.guardar(eventos);
    }

    protected List<LocalDate> obtenerFechasCogidas () {
        List<LocalDate> fechasEnUso = new ArrayList<>();

        for (Evento evento : eventos) {
            fechasEnUso.add(evento.getFecha());
        }

        return fechasEnUso;
    }

    /**
     * Le doy una lista como parametro por si en el futuro quiero que se salte varios eventos
     * @param eventosAIgnorar
     * @return
     */
    protected List<LocalDate> obtenerFechasCogidasSaltandoseEventos(List<Evento> eventosAIgnorar) {
        List<LocalDate> fechasEnUso = new ArrayList<>();

        for (Evento evento : eventos) {
            if (!eventosAIgnorar.contains(evento)) {
                fechasEnUso.add(evento.getFecha());
            }

        }

        return fechasEnUso;
    }

    public Optional<Evento> obtenerEventoPorId(Integer id) {
        return eventos.stream()
                .filter(evento -> evento.getIdEvento().equals(id))
                .findFirst();
    }
}
