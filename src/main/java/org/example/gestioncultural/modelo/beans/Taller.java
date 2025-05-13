package org.example.gestioncultural.modelo.beans;

import java.time.LocalDate;
import java.util.List;


public class Taller extends Evento {
    private Integer aforo;
    private Double precio;

    public Taller(String titulo, String ponente, LocalDate fecha, Double precio, Integer aforo) {
        super(titulo, ponente, fecha);
        this.aforo = aforo;
        this.precio = precio;
    }


    public Double getPrecio() {
        return precio;
    }

    public Integer getAforo() {
        return aforo;
    }


    @Override
    public List<String> obtenerListaDeAtributosEspecificos() {
        return List.of(
                "Precio: " + this.precio,
                "Aforo: " + this.aforo
        );
    }
}