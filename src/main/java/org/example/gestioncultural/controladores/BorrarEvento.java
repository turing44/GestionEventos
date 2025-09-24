package org.example.gestioncultural.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.gestioncultural.modelo.beans.Evento;
import org.example.gestioncultural.modelo.procesos.BorradorEventos;
import org.example.gestioncultural.controladores.utilidades.CreadorUI;
import org.example.gestioncultural.controladores.utilidades.ValidadorFormato;

import java.util.Optional;


public class BorrarEvento {
    @FXML private TextField campoIdEvento;
    @FXML private VBox panelConfirmacionBorrado;
    @FXML private Label mensaje;

    private BorradorEventos borradorEventos = new BorradorEventos();
    private CreadorUI creadorUI = new CreadorUI();
    private ValidadorFormato validador = new ValidadorFormato();
    private Button btnConfirmar;


    @FXML
    public void borrarEventoPorId() {
        try {
            limpiar();
            Integer id = validador.validarEntero(campoIdEvento.getText());
            procesoBorrado(id);
        } catch (IllegalArgumentException iae) {
            mensaje.setText(iae.getMessage());
        }
    }


    private void procesoBorrado(Integer id) throws IllegalArgumentException {
        Optional<Evento> evento = borradorEventos.obtenerEventoPorId(id);
        Optional<HBox> vistaEvento;

        btnConfirmar = new Button("Confirmar");
        if (evento.isPresent()) {
            btnConfirmar.setOnAction(event -> onBtnConfirmarClicked(id));
            vistaEvento = creadorUI.crearVistaEvento(evento.get());
            if (vistaEvento.isPresent()) {
                panelConfirmacionBorrado.getChildren().addAll(
                        vistaEvento.get(),
                        new Label("Seguro que lo quieres borrar el evento?"),
                        btnConfirmar
                );
            }

        } else {
            throw new IllegalArgumentException("No existe un evento con el id " + id);
        }

    }

    private void onBtnConfirmarClicked(Integer id) {
        borradorEventos.borrarEventoPorId(id);
        btnConfirmar.setDisable(true);
        panelConfirmacionBorrado.getChildren().add(new Label("Evento Borrado"));
    }

    private void limpiar() {
        panelConfirmacionBorrado.getChildren().clear();
        mensaje.setText("");
    }
}
