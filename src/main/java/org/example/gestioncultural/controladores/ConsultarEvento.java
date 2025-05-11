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
import org.example.gestioncultural.vista.CreadorUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConsultarEvento {

    @FXML private VBox contenedorEventos;
    @FXML private Label mensaje;

    private ArrayList<Evento> eventos;
    private ConsultadorEventos consultadorEventos = new ConsultadorEventos();
    private CreadorUI creadorUI = new CreadorUI();

    @FXML
    public void initialize() {
        eventos = consultadorEventos.obtenerTodosEventos();
        mostrarTodosEventos();
    }

    @FXML
    public void mostrarProximoEvento() {
        limpiarPanelEventos();

        Optional<Evento> proximoEvento = consultadorEventos.obtenerProximoEvento();

        proximoEvento.ifPresentOrElse(
                evento -> creadorUI.crearVistaEvento(evento),
                () -> mensaje.setText("No hay eventos próximamente")
        );
    }

    @FXML
    public void mostrarEventoEnCurso() {
        limpiarPanelEventos();

        Optional<Evento> eventoEnCurso = consultadorEventos.obtenerEventoEnCurso();

        eventoEnCurso.ifPresentOrElse(
                evento -> creadorUI.crearVistaEvento(evento),
                () -> mensaje.setText("No hay eventos en curso")
        );
    }

    @FXML
    public void mostrarEventosConcluidos() {
        limpiarPanelEventos();
        for (Evento e : consultadorEventos.obtenerEventosConcluidos()) {
            contenedorEventos.getChildren().add(creadorUI.crearVistaEvento(e));
        }
    }

    private void limpiarPanelEventos() {
        contenedorEventos.getChildren().clear();
        mensaje.setText("");
    }

    private void mostrarTodosEventos() {
        limpiarPanelEventos();
        for (Evento e : eventos) {
            contenedorEventos.getChildren().add(creadorUI.crearVistaEvento(e));
        }
    }


}

