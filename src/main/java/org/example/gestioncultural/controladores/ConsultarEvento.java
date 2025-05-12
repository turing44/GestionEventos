package org.example.gestioncultural.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.gestioncultural.modelo.beans.Evento;
import org.example.gestioncultural.modelo.procesos.ConsultadorEventos;
import org.example.gestioncultural.utilidades.CreadorUI;
import org.example.gestioncultural.utilidades.ValidadorFormato;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class ConsultarEvento {

    @FXML private VBox contenedorEventos;
    @FXML private Label mensaje;
    @FXML private TextField campoFecha;

    private ArrayList<Evento> eventos;
    private ConsultadorEventos consultadorEventos = new ConsultadorEventos();
    private CreadorUI creadorUI = new CreadorUI();
    private ValidadorFormato validador  = new ValidadorFormato();

    @FXML
    public void initialize() {
        eventos = consultadorEventos.obtenerTodosEventos();
        mostrarTodosEventos();
    }

    @FXML
    public void mostrarTodosEventos() {
        limpiarPanelEventos();
        for (Evento e : eventos) {
            Optional<HBox> vistaEvento = creadorUI.crearVistaEvento(e);
            vistaEvento.ifPresent(v -> contenedorEventos.getChildren().add(v));
        }
    }

    @FXML
    public void mostrarEventoPorFecha() {
        limpiarPanelEventos();

        try {
            LocalDate fecha = validador.validarFecha(campoFecha.getText());
            Optional<Evento> evento = consultadorEventos.obtenerEventoPorFecha(fecha);

            if (evento.isPresent()) {
                Optional<HBox> vistaEvento = creadorUI.crearVistaEvento(evento.get());
                vistaEvento.ifPresent(v -> contenedorEventos.getChildren().add(v));
            } else {
                mensaje.setText("No hay eventos en esa fecha");
            }

        } catch (IllegalArgumentException iae) {
            mensaje.setText(iae.getMessage());
        }
    }

    @FXML
    public void mostrarProximoEvento() {
        limpiarPanelEventos();

        Optional<Evento> proximoEvento = consultadorEventos.obtenerProximoEvento();

        if (proximoEvento.isPresent()) {
            Optional<HBox> vistaEvento = creadorUI.crearVistaEvento(proximoEvento.get());
            vistaEvento.ifPresent(v -> contenedorEventos.getChildren().add(v));
        } else {
            mensaje.setText("No hay eventos proximamente");
        }
    }

    @FXML
    public void mostrarEventoEnCurso() {
        limpiarPanelEventos();

        Optional<Evento> eventoEnCurso = consultadorEventos.obtenerEventoEnCurso();

        if (eventoEnCurso.isPresent()) {
            Optional<HBox> vistaEvento = creadorUI.crearVistaEvento(eventoEnCurso.get());
            vistaEvento.ifPresent(v -> contenedorEventos.getChildren().add(v));
        } else {
            mensaje.setText("No hay evento en curso");
        }
    }

    @FXML
    public void mostrarEventosConcluidos() {
        limpiarPanelEventos();

        for (Evento e : consultadorEventos.obtenerEventosConcluidos()) {
            Optional<HBox> vistaEvento = creadorUI.crearVistaEvento(e);
            vistaEvento.ifPresent(v -> contenedorEventos.getChildren().add(v));
        }
    }

    private void limpiarPanelEventos() {
        contenedorEventos.getChildren().clear();
        mensaje.setText("");
    }




}

