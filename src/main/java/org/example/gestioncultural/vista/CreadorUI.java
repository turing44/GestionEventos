package org.example.gestioncultural.vista;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.gestioncultural.modelo.beans.Evento;

public class CreadorUI {
    public HBox crearVistaEvento(Evento e) throws IllegalArgumentException{
        if (e == null) throw new IllegalArgumentException("No hay ningun evento que mostrar");


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
        return contenedorEvento;
    }
}
