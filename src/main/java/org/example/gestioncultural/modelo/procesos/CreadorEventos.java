package org.example.gestioncultural.modelo.procesos;

import org.example.gestioncultural.modelo.beans.Evento;

public class CreadorEventos extends GestionEventos{

    public void crearEvento(Evento evento) {
        eventos.add(evento);
        repositorio.guardar(eventos);
    }


}
