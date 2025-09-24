package org.example.gestioncultural.modelo.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public abstract class Evento implements Serializable {
    private static final long serialVersionUID = 1L;

    private String titulo;
    private String ponente;
    private LocalDate fecha;

    private Integer idEvento;

    protected Evento(String titulo, String ponente, LocalDate fecha) {
        this.titulo = titulo;
        this.ponente = ponente;
        this.fecha = fecha;
    }


    public List<String> obtenerListaDeAtributosComunes() {
        return List.of(
                "ID: " + this.idEvento,
                "Tipo: " + this.getClass().getSimpleName(),
                "En Curso: " + estaEnCurso(),
                "Fecha: " + this.fecha,
                "Titulo: " + this.titulo,
                "Ponente: " + this.ponente
        );
    }

    public abstract List<String> obtenerListaDeAtributosEspecificos();

    public String getTitulo() {
        return titulo;
    }

    public String getPonente() {
        return ponente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }


    public boolean estaEnCurso() {
        return LocalDate.now().isEqual(this.fecha);
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

    @Override
    public boolean equals(Object obj) {
        return ((Evento) obj).getIdEvento().equals(this.getIdEvento());
    }
}
