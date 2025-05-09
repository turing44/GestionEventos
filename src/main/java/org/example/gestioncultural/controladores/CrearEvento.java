package org.example.gestioncultural.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CrearEvento {
    @FXML private ComboBox<String> comboBoxTipos;

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

    }


    private void cambiarFormularioSegunEvento(String eventoSeleccionado) {
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

}
