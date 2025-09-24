package org.example.gestioncultural.modelo.procesos;

import org.example.gestioncultural.modelo.beans.Evento;
import org.example.gestioncultural.modelo.beans.Exposicion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ConsultadorEventos extends GestionadorEventos {

    public List<Evento> obtenerTodosEventos() {
        return eventos;
    }

    public Optional<Evento> obtenerEventoEnCurso() {
        return eventos.stream()
                .filter(Evento::estaEnCurso) // esto es igual que .filter(evento -> evento.estaEnCurso())
                .findFirst();
    }

    public Optional<Evento> obtenerProximoEvento() {
        return eventos.stream()
                .sorted(Comparator.comparing(Evento::getFecha))
                .filter(evento ->
                        LocalDate.now().isBefore(evento.getFecha()) && !evento.estaEnCurso())
                .findFirst();
    }

    public List<Evento> obtenerEventosConcluidos() {
        ArrayList<Evento> eventosConcluidos = new ArrayList<>();

        for (Evento evento : eventos) {
            if (evento.getFecha().isBefore(LocalDate.now()) && !evento.estaEnCurso()) {
                eventosConcluidos.add(evento);
            }
        }

        return eventosConcluidos;
    }

    public Optional<Evento> obtenerEventoPorFecha(LocalDate fecha) {
        return eventos.stream()
                .filter(evento ->
                        evento.getFecha().isEqual(fecha) ||
                                (evento instanceof Exposicion expo &&
                                        expo.getFecha().isBefore(fecha) &&
                                        expo.getFecha_fin().isAfter(fecha.minusDays(1)))
                )
                .findFirst();
    }
}
