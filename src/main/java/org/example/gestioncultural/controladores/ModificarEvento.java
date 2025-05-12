package org.example.gestioncultural.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.gestioncultural.modelo.beans.Evento;
import org.example.gestioncultural.modelo.procesos.ModificadorEventos;
import org.example.gestioncultural.utilidades.CreadorUI;
import org.example.gestioncultural.utilidades.ValidadorFormato;

import java.util.Optional;


public class ModificarEvento {
    @FXML
    private Label mensaje;
    @FXML
    private Button btnModificarEvento;
    @FXML
    private VBox contenedorModificaciones;
    @FXML
    private TextField campoId;

    private ValidadorFormato validador = new ValidadorFormato();
    private CreadorUI creadorUI = new CreadorUI();
    private ModificadorEventos modificador  = new ModificadorEventos();


    public void modificarEvento() {
        try {
            mostrarEvento();
            btnModificarEvento.setDisable(true);
            mostrarTextFieldsParaModificar();
        } catch (IllegalArgumentException e) {
            mensaje.setText(e.getMessage());
        }


    }
    private void mostrarEvento() throws IllegalArgumentException {
        Integer id = validador.validarEntero(campoId.getText());
        Optional<Evento> evento = modificador.obtenerEventoPorId(id);
        if (evento.isPresent()) {
            Optional<HBox> vistaEvento = creadorUI.crearVistaEvento(evento.get());
            if (vistaEvento.isPresent()) {
                contenedorModificaciones.getChildren().add(vistaEvento.get());
            } else {
                throw new IllegalArgumentException("No se ha podido mostrar el evento");
            }
        } else {
            throw new IllegalArgumentException("No se encontro el evento");
        }
    }

    private void mostrarTextFieldsParaModificar() {
        TextField campoTitulo = new TextField();
        TextField campoPonente = new TextField();
        TextField campoFecha = new TextField();
        TextField campoHora = new TextField();
        TextField campoPrecio = new TextField();
        TextField campoFechaFin = new TextField();
        TextField campoAsistentes = new TextField();

        contenedorModificaciones.getChildren().addAll();
    }


}
