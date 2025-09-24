package org.example.gestioncultural.modelo.beans;

import java.time.LocalDate;
import java.util.List;


public class Conferencia extends Evento {
    private String hora;

    public Conferencia(String titulo, String ponente, LocalDate fecha, String hora) {
        super(titulo, ponente, fecha);
        this.hora = hora;
    }

    public String getHora() {
        return hora;
    }


    @Override
    public List<String> obtenerListaDeAtributosEspecificos() {
        return List.of(
                "Hora: " + this.hora
        );
    }
}
