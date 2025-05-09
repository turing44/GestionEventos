package org.example.gestioncultural.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController {

    @FXML
    private BorderPane contenedorPrincipal;

    @FXML
    protected void cambiarACrearEvento() {
        try {
            Parent pagina = obtenerPagina("formularioCrearEvento.fxml");
            contenedorPrincipal.setCenter(pagina);
        } catch (IOException e) {
            e.printStackTrace();
            // Mandar error al encontrar la pagina
        }
    }

    @FXML
    protected void cambiarAModificarEvento() {
        try {
            Parent pagina = obtenerPagina("formularioModificarEvento.fxml");
            contenedorPrincipal.setCenter(pagina);
        } catch (IOException e) {
            e.printStackTrace();
            // Mandar error al encontrar la pagina
        }
    }

    @FXML
    protected void cambiarAConsultarEvento() {
        try {
            Parent pagina = obtenerPagina("formularioConsultarEvento.fxml");
            contenedorPrincipal.setCenter(pagina);
        } catch (IOException e) {
            e.printStackTrace();
            // Mandar error al encontrar la pagina
        }
    }

    @FXML
    protected void cambiarABorrarEvento() {
        try {
            Parent pagina = obtenerPagina("formularioBorrarEvento.fxml");
            contenedorPrincipal.setCenter(pagina);
        } catch (IOException e) {
            e.printStackTrace();
            // Mandar error al encontrar la pagina
        }
    }





    private Parent obtenerPagina(String archivoFXML) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/gestioncultural/" + archivoFXML));
        return loader.load();
    }
}
