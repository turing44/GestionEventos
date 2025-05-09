package org.example.gestioncultural.modelo.beans;

import java.util.Date;

public class Conferencia extends Evento {
    private String hora;

    public Conferencia(String titulo, String ponente, Date fecha, Double precio, String hora) {
        super(titulo, ponente, fecha);
        this.hora = hora;
    }

    public String getHora() {
        return hora;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }
}
