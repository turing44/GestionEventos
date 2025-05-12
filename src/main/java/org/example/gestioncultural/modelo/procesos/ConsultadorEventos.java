package org.example.gestioncultural.modelo.procesos;

import org.example.gestioncultural.modelo.beans.Evento;
import org.example.gestioncultural.modelo.beans.Exposicion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Optional;

public class ConsultadorEventos extends GestionadorEventos {

    public ArrayList<Evento> obtenerTodosEventos() {
        return eventos;
    }

    /*
     *
     * Version antigua, antes de aprender programacion funcional
     * public Evento obtenerEventoEnCurso() {
     *         for (Evento evento : eventos) {
     *             if (evento.estaEnCurso()) return evento;
     *         }
     *         return null;
     *     }
     */

    public Optional<Evento> obtenerEventoEnCurso() {
        return eventos.stream()
                .filter(Evento::estaEnCurso) // esto es igual que .filter(evento -> evento.estaEnCurso())
                .findFirst();
    }


    /*public Evento obtenerProximoEvento() {
        List<Evento> eventosOrdenados = new ArrayList<>(eventos);
        eventosOrdenados.sort(Comparator.comparing(Evento::getFecha));

        for (Evento evento : eventosOrdenados) {
            if (LocalDate.now().isBefore(evento.getFecha()) && !evento.estaEnCurso()) {
                return evento;
            }
        }

        throw new IllegalArgumentException("No hay eventos proximamente");
    }*/

    public Optional<Evento> obtenerProximoEvento() {
        return eventos.stream()
                .sorted(Comparator.comparing(Evento::getFecha))
                .filter(evento ->
                        LocalDate.now().isBefore(evento.getFecha()) && !evento.estaEnCurso())
                .findFirst();
    }

    public ArrayList<Evento> obtenerEventosConcluidos() {
        ArrayList<Evento> eventosConcluidos = new ArrayList<>();

        for (Evento evento : eventos) {
            if (evento.getFecha().isBefore(LocalDate.now()) && !evento.estaEnCurso()) {
                eventosConcluidos.add(evento);
            }
        }

        return eventosConcluidos;
    }


    /*public Evento obtenerEventoPorFecha (LocalDate fecha) {
        for (Evento evento : eventos) {
            if (evento.getFecha().equals(fecha)) {
                return evento;
            } else if (evento instanceof Exposicion e) {
                if
                (
                        e.getFecha().isAfter(fecha) &&
                        e.getFecha_fin().isBefore(fecha)
                )
                {
                    return evento;
                }
            }
        }

        return null;
    }*/

    public Optional<Evento> obtenerEventoPorFecha(LocalDate fecha) {
        return eventos.stream()
                .filter(evento ->
                        evento.getFecha().isEqual(fecha) ||
                                (evento instanceof Exposicion expo &&
                                        expo.getFecha().isBefore(fecha) &&
                                        expo.getFecha_fin().isAfter(fecha))
                )
                .findFirst();
    }

    public Optional<Evento> obtenerEventoPorId(Integer id) {
        return eventos.stream()
                .filter(evento -> evento.getIdEvento() == id)
                .findFirst();
    }

}
