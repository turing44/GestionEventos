package org.example.gestioncultural.modelo.beans;

import java.util.Date;

public class Evento {
    private String titulo;
    private String ponente;
    private Date fecha;

    private boolean enCurso;
    private Integer idEvento;

    public Evento(String titulo, String ponente, Date fecha) {
        this.titulo = titulo;
        this.ponente = ponente;
        this.fecha = fecha;

        enCurso = false;
        this.idEvento = asignarNuevoId();
    }



    private Integer asignarNuevoId() {
        // obtener ultimo id creado
        return  + 1;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    public boolean estaEnCurso() {
        return enCurso;
    }

    public void setEnCurso(boolean enCurso) {
        this.enCurso = enCurso;
    }

}
