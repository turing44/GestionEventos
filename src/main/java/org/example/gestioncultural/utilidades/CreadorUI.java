package org.example.gestioncultural.utilidades;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.gestioncultural.modelo.beans.Evento;

import java.util.Optional;

public class CreadorUI {
    public Optional<HBox> crearVistaEvento(Evento e) {
        if (e == null) {return Optional.empty();}


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
        return Optional.of(contenedorEvento);
    }
}
