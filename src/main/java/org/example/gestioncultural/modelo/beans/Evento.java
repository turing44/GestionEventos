package org.example.gestioncultural.modelo.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public abstract class Evento implements Serializable {
    private static final long serialVersionUID = 1L;

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


    public List<String> obtenerListaDeAtributosComunes() {
        return List.of(
                "ID: " + this.idEvento,
                "Tipo: " + this.getClass().getSimpleName(),
                "En Curso: " + this.enCurso,
                "Fecha: " + this.fecha,
                "Titulo: " + this.titulo,
                "Ponente: " + this.ponente
        );
    }

    public abstract List<String> obtenerListaDeAtributosEspecificos();

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

    public Integer getIdEvento() {
        return idEvento;
    }

    /**
     * Punto debil, no se le deberia poder cambiar el id a un evento
     * */
    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

}
