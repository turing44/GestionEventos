package org.example.gestioncultural.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.example.gestioncultural.modelo.beans.Evento;
import org.example.gestioncultural.modelo.procesos.BorradorEventos;
import org.example.gestioncultural.modelo.procesos.ConsultadorEventos;
import org.example.gestioncultural.vista.CreadorUI;

import java.util.Optional;


public class BorrarEvento {
    private BorradorEventos borradorEventos = new BorradorEventos();
    private CreadorUI creadorUI = new CreadorUI();
    private ConsultadorEventos consultadorEventos = new ConsultadorEventos();

    @FXML private TextField campoIdEvento;
    @FXML private VBox panelConfirmacionBorrado;
    @FXML private Label mensaje;
    @FXML private Button btnBorrarPorId;


    @FXML
    public void borrarEventoPorId() {
        try {
            Integer id = Integer.parseInt(campoIdEvento.getText());
            procesoBorrado(id);
        } catch (NumberFormatException nfe) {
            mensaje.setText("No has introducido un entero");
        } catch (IllegalArgumentException iae) {
            mensaje.setText(iae.getMessage());
        }
    }


    private void procesoBorrado(Integer id) throws IllegalArgumentException {
        Optional<Evento> evento = consultadorEventos.obtenerEventoPorId(id);

        btnBorrarPorId.setDisable(true);
        Button btnConfirmar = new Button("Confirmar");


        if (evento.isPresent()) {
            btnConfirmar.setOnAction(event -> {borradorEventos.borrarEventoPorId(id);});
            panelConfirmacionBorrado.getChildren().addAll(
                    creadorUI.crearVistaEvento(evento.get()),
                    new Label("Seguro que lo quieres borrar el evento"),
                    btnConfirmar
            );

        } else {
            throw new IllegalArgumentException("No existe un evento con el id " + id);
        }

    }
}
