package org.example.gestioncultural.modelo.procesos;

import org.example.gestioncultural.modelo.beans.Evento;
import org.example.gestioncultural.modelo.beans.Exposicion;

import java.time.LocalDate;
import java.util.ArrayList;

public class ConsultadorEventos extends GestionadorEventos {

    public ArrayList<Evento> obtenerTodosEventos() {
        return eventos;
    }

    public Evento obtenerEventoEnCurso() {
        for (Evento evento : eventos) {
            if (evento.estaEnCurso()) return evento;
        }
        return null;
    }

    public ArrayList<Evento> obtenerProximosEventos() {
        ArrayList<Evento> proximosEventos = new ArrayList<>();

        for (Evento evento : eventos) {
            if (evento.getFecha().isAfter(LocalDate.now())) {
                proximosEventos.add(evento);
            }
        }

        return proximosEventos;
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


    public Evento obtenerEventoPorFecha (LocalDate fecha) {
        for (Evento evento : eventos) {
            if (evento.getFecha().equals(fecha)) {
                return evento;
            } else if (evento instanceof Exposicion) {
                if
                (
                        ((Exposicion) evento).getFecha().isAfter(fecha) &&
                        ((Exposicion) evento).getFecha_fin().isBefore(fecha)
                )
                {
                    return evento;
                }
            }
        }

        return null;
    }

}
