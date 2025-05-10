package org.example.gestioncultural.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.gestioncultural.modelo.beans.Conferencia;
import org.example.gestioncultural.modelo.beans.Evento;
import org.example.gestioncultural.modelo.beans.Exposicion;
import org.example.gestioncultural.modelo.beans.Taller;
import org.example.gestioncultural.modelo.procesos.CreadorEventos;
import org.example.gestioncultural.modelo.procesos.Validador;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;


public class CrearEvento {
    @FXML private ComboBox<String> comboBoxTipos;

    @FXML private Button btnCrearEvento;
    @FXML private Label mensaje;

    @FXML private TextField campoTitulo;
    @FXML private TextField campoPonente;
    @FXML private TextField campoFecha;
    @FXML private TextField campoHora;
    @FXML private TextField campoPrecio;
    @FXML private TextField campoFechaFin;
    @FXML private TextField campoAsistentes;


    @FXML
    public void initialize() {
        comboBoxTipos.getItems().addAll("Conferencia", "Taller", "Exposicion");
        comboBoxTipos.setOnAction(event -> cambiarFormularioSegunEvento(comboBoxTipos.getValue()));
    }

    @FXML
    public void crearEvento() {
        try {
            Validador validador = new Validador();
            CreadorEventos creadorEventos = new CreadorEventos();
            Evento evento = crearEventoSegunTipo();

            if (validador.esValido(evento)) {
                creadorEventos.crearEvento(evento);
                mensaje.setText("Evento creado correctamente");
                btnCrearEvento.setDisable(true);

            }

        } catch (DateTimeParseException dtpe) {
            mensaje.setText("No has introducido una fecha en el formato correcto (YYYY-MM-DD)");
        } catch (NumberFormatException nfe) {
            mensaje.setText("Has introducido mal los numeros");
        } catch (IllegalStateException | IllegalArgumentException ise) {
            mensaje.setText(ise.getMessage());
        }


    }


    private Evento crearEventoSegunTipo() throws IllegalStateException, DateTimeParseException, NumberFormatException {
        Evento evento;

        switch (comboBoxTipos.getValue()) {
            case "Conferencia":
                evento = new Conferencia(
                        campoTitulo.getText(),
                        campoPonente.getText(),
                        LocalDate.parse(campoFecha.getText()),
                        campoHora.getText() // cambiar por LocalTime
                );
                break;

            case "Taller":
                evento = new Taller(
                        campoTitulo.getText(),
                        campoPonente.getText(),
                        LocalDate.parse(campoFecha.getText()),
                        Double.parseDouble(campoPrecio.getText()),
                        Integer.parseInt(campoAsistentes.getText())

                );
                break;


            case "Exposicion":
                evento = new Exposicion (
                        campoTitulo.getText(),
                        campoPonente.getText(),
                        LocalDate.parse(campoFecha.getText()),
                        Double.parseDouble(campoPrecio.getText()),
                        LocalDate.parse(campoFechaFin.getText())

                );
                break;

            default:
                throw new IllegalStateException("No se ha especificado el tipo de evento");
        }

        return evento;

    }

    private void cambiarFormularioSegunEvento(String eventoSeleccionado) {
        limpiarTextFields();
        btnCrearEvento.setDisable(false);
        switch (eventoSeleccionado) {
            case "Conferencia":
                campoTitulo.setDisable(false);
                campoPonente.setDisable(false);
                campoFecha.setDisable(false);
                campoHora.setDisable(false);
                campoPrecio.setDisable(true);
                campoFechaFin.setDisable(true);
                campoAsistentes.setDisable(true);
                break;

            case "Taller":
                campoTitulo.setDisable(false);
                campoPonente.setDisable(false);
                campoFecha.setDisable(false);
                campoHora.setDisable(true);
                campoPrecio.setDisable(false);
                campoFechaFin.setDisable(true);
                campoAsistentes.setDisable(false);
                break;

            case "Exposicion":
                campoTitulo.setDisable(false);
                campoPonente.setDisable(false);
                campoFecha.setDisable(false);
                campoHora.setDisable(true);
                campoFechaFin.setDisable(false);
                campoPrecio.setDisable(false);
                campoAsistentes.setDisable(true);
        }
    }

    private void limpiarTextFields() {
        campoTitulo.clear();
        campoPonente.clear();
        campoFecha.clear();
        campoHora.clear();
        campoPrecio.clear();
        campoFechaFin.clear();
        campoAsistentes.clear();
    }

}
