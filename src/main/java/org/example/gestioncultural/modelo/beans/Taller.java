package org.example.gestioncultural.modelo.beans;

import java.time.LocalDate;


public class Taller extends Evento {
    private Integer numeroMaximoAsistentes;
    private Double precio;

    public Taller(String titulo, String ponente, LocalDate fecha, Double precio, Integer numeroMaximoAsistentes) {
        super(titulo, ponente, fecha);
        this.numeroMaximoAsistentes = numeroMaximoAsistentes;
        this.precio = precio;
    }


    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getNumeroMaximoAsistentes() {
        return numeroMaximoAsistentes;
    }

    public void setNumeroMaximoAsistentes(Integer numeroMaximoAsistentes) {
        this.numeroMaximoAsistentes = numeroMaximoAsistentes;
    }
}