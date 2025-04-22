package org.example.gestioncultural.modelo.beans;

import java.util.Date;

public class Taller {
    private String titulo;
    private Date fecha;
    private String nombreMonitor;
    private Integer precio;
    private Integer numeroMaximoAsistentes;
    private boolean enCurso;
    private Integer idTaller;

    public Taller(String titulo, Date fecha, String nombreMonitor, Integer precio, Integer numeroMaximoAsistentes, Integer idTaller) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.nombreMonitor = nombreMonitor;
        this.precio = precio;
        this.numeroMaximoAsistentes = numeroMaximoAsistentes;
        this.idTaller = idTaller;
        enCurso = false;

    }

    public Taller(String titulo, Date fecha, String nombreMonitor, Integer numeroMaximoAsistentes, Integer idTaller) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.nombreMonitor = nombreMonitor;
        this.precio = 0;
        this.numeroMaximoAsistentes = numeroMaximoAsistentes;
        this.idTaller = idTaller;
        enCurso = false;

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

    public String getNombreMonitor() {
        return nombreMonitor;
    }

    public void setNombreMonitor(String nombreMonitor) {
        this.nombreMonitor = nombreMonitor;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getNumeroMaximoAsistentes() {
        return numeroMaximoAsistentes;
    }

    public void setNumeroMaximoAsistentes(Integer numeroMaximoAsistentes) {
        this.numeroMaximoAsistentes = numeroMaximoAsistentes;
    }

    public boolean isEnCurso() {
        return enCurso;
    }

    public void setEnCurso(boolean enCurso) {
        this.enCurso = enCurso;
    }


}
