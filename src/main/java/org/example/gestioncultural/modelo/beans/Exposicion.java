package org.example.gestioncultural.modelo.beans;

import java.util.Date;

public class Exposicion extends Evento {

    private Double precio;
    private Date fecha_fin;

    public Exposicion(String titulo, String ponente, Date fecha) {
        super(titulo, ponente, fecha);
        this.precio = precio;
        this.fecha_fin = fecha_fin;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }
    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
}
