package org.example.gestioncultural.modelo.procesos;

import org.example.gestioncultural.modelo.beans.Evento;

import java.util.ArrayList;
import java.util.Collections;

public class CreadorEventos extends GestionadorEventos {

    public void crearEvento(Evento evento) {
        evento.setIdEvento(obtenerNuevoId());
        eventos.add(evento);
        repositorio.guardar(eventos);
    }

    /**
     * Mira todos los ids en uso y devuelve el id mas alto + 1
     * @return Integer
     */
    private Integer obtenerNuevoId() {
        ArrayList<Integer> idsEnUso = obtenerIdsGenerados();

        if (idsEnUso.isEmpty()) {
            return 0;
        }

        Collections.sort(idsEnUso);

        return idsEnUso.getLast() + 1;
    }


    private ArrayList<Integer> obtenerIdsGenerados() {
        ArrayList<Integer> ids = new ArrayList<>();

        if (!eventos.isEmpty()) {
            for (Evento evento : eventos) {
                ids.add(evento.getIdEvento());
            }
        }

        return ids;
    }


}
