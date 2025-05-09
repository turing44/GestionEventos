package org.example.gestioncultural.modelo.procesos;

import org.example.gestioncultural.modelo.beans.Evento;

import java.util.ArrayList;

public class GestionEventos {
    protected ArrayList<Evento> eventos;
    protected Repositorio <ArrayList<Evento>> repositorio = new Repositorio<>("eventos.ser");

    public GestionEventos() {
        this.eventos = repositorio.leer();
        if (eventos == null) {
            eventos = new ArrayList<>();
        }
    }


}
