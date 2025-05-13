package org.example.gestioncultural.modelo.beans;

import java.time.LocalDate;
import java.util.List;


public class Exposicion extends Evento {

    private Double precio;
    private LocalDate fecha_fin;

    public Exposicion(String titulo, String ponente, LocalDate fecha, Double precio, LocalDate fecha_fin) {
        super(titulo, ponente, fecha);
        this.precio = precio;
        this.fecha_fin = fecha_fin;
    }

    public Double getPrecio() {
        return precio;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }


    @Override
    public List<String> obtenerListaDeAtributosEspecificos() {
        return List.of(
                "Precio: " + this.precio,
                "Fecha fin: " + this.fecha_fin
        );
    }
}
