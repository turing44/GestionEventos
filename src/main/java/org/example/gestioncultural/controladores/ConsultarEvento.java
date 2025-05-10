package org.example.gestioncultural.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.gestioncultural.modelo.beans.Conferencia;
import org.example.gestioncultural.modelo.beans.Evento;
import org.example.gestioncultural.modelo.beans.Exposicion;
import org.example.gestioncultural.modelo.beans.Taller;
import org.example.gestioncultural.modelo.procesos.ConsultadorEventos;

import java.util.ArrayList;
import java.util.List;

public class ConsultarEvento {

    @FXML private VBox contenedorEventos;
    @FXML private Label mensaje;

    private ArrayList<Evento> eventos;
    private ConsultadorEventos consultadorEventos = new ConsultadorEventos();

    @FXML
    public void initialize() {
        eventos = consultadorEventos.obtenerTodosEventos();
        mostrarTodosEventos();
    }

    @FXML
    public void mostrarProximoEvento() {
        try {
           // crearVistaEvento(consultadorEventos.obtenerProximoEvento());
        } catch (IllegalArgumentException e) {
            mensaje.setText(e.getMessage());
        }

    }
    @FXML
    public void mostrarEventoEnCurso() {
        try {
            crearVistaEvento(consultadorEventos.obtenerEventoEnCurso());
        } catch (IllegalArgumentException e) {
            mensaje.setText(e.getMessage());
        }
    }

    @FXML
    public void mostrarEventosConcluidos() {
        for (Evento e : consultadorEventos.obtenerEventosConcluidos()) {
            contenedorEventos.getChildren().add(crearVistaEvento(e));
        }
    }


    private void mostrarTodosEventos() {
        for (Evento e : eventos) {
            contenedorEventos.getChildren().add(crearVistaEvento(e));
        }
    }

    private HBox crearVistaEvento(Evento e) throws IllegalArgumentException{
        if (e == null) throw new IllegalArgumentException("No hay ningun evento que mostrar");


        HBox contenedorEvento = new HBox(10);
        contenedorEvento.setStyle("-fx-border-color: #ccc; -fx-padding: 10; -fx-background-color: #f9f9f9;");

        VBox texto = new VBox(5);

        for (String atributoComun : e.obtenerListaDeAtributosComunes()) {
            texto.getChildren().add(new Label(atributoComun));
        }
        for (String atributoEspecifico : e.obtenerListaDeAtributosEspecificos()) {
            texto.getChildren().add(new Label(atributoEspecifico));
        }

        contenedorEvento.getChildren().add(texto);
        return contenedorEvento;
    }
}

