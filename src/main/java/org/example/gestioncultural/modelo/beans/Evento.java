package org.example.gestioncultural.modelo.beans;

import java.time.LocalDate;

public class Evento {
    private String titulo;
    private String ponente;
    private LocalDate fecha;

    private boolean enCurso;
    private Integer idEvento;

    public Evento(String titulo, String ponente, LocalDate fecha) {
        this.titulo = titulo;
        this.ponente = ponente;
        this.fecha = fecha;

        enCurso = false;
    }



    private void asignarId(Integer idEvento) {
        // obtener ultimo id creado
        this.idEvento = idEvento;

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPonente() {
        return ponente;
    }

    public void setPonente(String ponente) {
        this.ponente = ponente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }


    public boolean estaEnCurso() {
        return enCurso;
    }

    public void setEnCurso(boolean enCurso) {
        this.enCurso = enCurso;
    }

}
