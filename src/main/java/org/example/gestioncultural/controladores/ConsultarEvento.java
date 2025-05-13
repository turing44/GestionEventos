package org.example.gestioncultural.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.gestioncultural.modelo.beans.Evento;
import org.example.gestioncultural.modelo.procesos.ConsultadorEventos;
import org.example.gestioncultural.controladores.utilidades.CreadorUI;
import org.example.gestioncultural.controladores.utilidades.ValidadorFormato;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ConsultarEvento {

    @FXML private VBox contenedorEventos;
    @FXML private Label mensaje;
    @FXML private TextField campoFecha;

    private List<Evento> eventos;
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

        Optional<HBox> vistaEvento;

        for (Evento e : eventos) {
            vistaEvento = creadorUI.crearVistaEvento(e);
            vistaEvento.ifPresent(v -> contenedorEventos.getChildren().add(v));
        }
    }

    @FXML
    public void mostrarEventoPorFecha() {
        limpiarPanelEventos();

        LocalDate fecha;
        Optional<Evento> evento;
        Optional<HBox> vistaEvento;
        try {
            fecha = validador.validarFecha(campoFecha.getText());
            evento = consultadorEventos.obtenerEventoPorFecha(fecha);

            if (evento.isPresent()) {
                vistaEvento = creadorUI.crearVistaEvento(evento.get());
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

        Optional<HBox> vistaEvento;
        Optional<Evento> proximoEvento = consultadorEventos.obtenerProximoEvento();

        if (proximoEvento.isPresent()) {
            vistaEvento = creadorUI.crearVistaEvento(proximoEvento.get());
            vistaEvento.ifPresent(v -> contenedorEventos.getChildren().add(v));
        } else {
            mensaje.setText("No hay eventos proximamente");
        }
    }

    @FXML
    public void mostrarEventoEnCurso() {
        limpiarPanelEventos();

        Optional<Evento> eventoEnCurso = consultadorEventos.obtenerEventoEnCurso();
        Optional<HBox> vistaEvento;

        if (eventoEnCurso.isPresent()) {
            vistaEvento = creadorUI.crearVistaEvento(eventoEnCurso.get());
            vistaEvento.ifPresent(v -> contenedorEventos.getChildren().add(v));
        } else {
            mensaje.setText("No hay evento en curso");
        }
    }

    @FXML
    public void mostrarEventosConcluidos() {
        limpiarPanelEventos();
        Optional<HBox> vistaEvento;

        for (Evento e : consultadorEventos.obtenerEventosConcluidos()) {
            vistaEvento = creadorUI.crearVistaEvento(e);
            vistaEvento.ifPresent(v -> contenedorEventos.getChildren().add(v));
        }
    }

    private void limpiarPanelEventos() {
        contenedorEventos.getChildren().clear();
        mensaje.setText("");
    }

}

