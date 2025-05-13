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
import org.example.gestioncultural.controladores.utilidades.ValidadorFormato;


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


    // Como podria cambiarlos por enums?
    private final String CONFERENCIA = "Conferencia";
    private final String TALLER = "Taller";
    private final String EXPOSICION = "Exposicion";


    @FXML
    public void initialize() {
        comboBoxTipos.getItems().addAll(
                CONFERENCIA,
                TALLER,
                EXPOSICION
        );
        comboBoxTipos.setOnAction(event ->
                onTipoEventoSeleccionado(comboBoxTipos.getValue())
        );
    }

    @FXML
    public void crearEvento() {
        // Esta bien instanciar aqui o debe ser un atributo?
        CreadorEventos creadorEventos = new CreadorEventos();
        Evento evento;
        try {
            evento = crearEventoSegunTipo();
            creadorEventos.crearEvento(evento);
            mensaje.setText("Evento creado correctamente");
            btnCrearEvento.setDisable(true);

        } catch (IllegalArgumentException iae) {
            mensaje.setText(iae.getMessage());
        }

    }

    private Evento crearEventoSegunTipo() throws IllegalArgumentException {
        Evento evento;
        ValidadorFormato validador = new ValidadorFormato();

        switch (comboBoxTipos.getValue()) {
            case CONFERENCIA:
                evento = new Conferencia(
                        validador.validarTexto(campoTitulo.getText()),
                        validador.validarTexto(campoPonente.getText()),
                        validador.validarFechaFutura(campoFecha.getText()),
                        validador.validarTexto(campoHora.getText())
                        );
                break;

            case TALLER:
                evento = new Taller(
                        validador.validarTexto(campoTitulo.getText()),
                        validador.validarTexto(campoPonente.getText()),
                        validador.validarFechaFutura(campoFecha.getText()),
                        validador.validarDecimal(campoPrecio.getText()),
                        validador.validarEntero(campoAsistentes.getText())
                );
                break;


            case EXPOSICION:
                evento = new Exposicion (
                        validador.validarTexto(campoTitulo.getText()),
                        validador.validarTexto(campoPonente.getText()),
                        validador.validarFechaFutura(campoFecha.getText()),
                        validador.validarDecimal(campoPrecio.getText()),
                        validador.validarFechaFutura(campoFechaFin.getText())
                );
                break;

            default:
                throw new IllegalStateException("No se ha especificado el tipo de evento");
        }

        return evento;

    }

    /**
     * cambia el formulario segun el tipo seleccionado
     * @param eventoSeleccionado
     */
    private void onTipoEventoSeleccionado(String eventoSeleccionado) {
        limpiarTextFields();
        btnCrearEvento.setDisable(false);
        switch (eventoSeleccionado) {
            case CONFERENCIA:
                campoTitulo.setDisable(false);
                campoPonente.setDisable(false);
                campoFecha.setDisable(false);
                campoHora.setDisable(false);
                campoPrecio.setDisable(true);
                campoFechaFin.setDisable(true);
                campoAsistentes.setDisable(true);
                break;

            case TALLER:
                campoTitulo.setDisable(false);
                campoPonente.setDisable(false);
                campoFecha.setDisable(false);
                campoHora.setDisable(true);
                campoPrecio.setDisable(false);
                campoFechaFin.setDisable(true);
                campoAsistentes.setDisable(false);
                break;

            case EXPOSICION:
                campoTitulo.setDisable(false);
                campoPonente.setDisable(false);
                campoFecha.setDisable(false);
                campoHora.setDisable(true);
                campoFechaFin.setDisable(false);
                campoPrecio.setDisable(false);
                campoAsistentes.setDisable(true);
                break;

            default:
                campoTitulo.setDisable(true);
                campoPonente.setDisable(true);
                campoFecha.setDisable(true);
                campoHora.setDisable(true);
                campoFechaFin.setDisable(true);
                campoPrecio.setDisable(true);
                campoAsistentes.setDisable(true);
                break;
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

        mensaje.setText("");
    }

}
