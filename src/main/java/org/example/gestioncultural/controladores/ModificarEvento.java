package org.example.gestioncultural.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.gestioncultural.modelo.beans.Conferencia;
import org.example.gestioncultural.modelo.beans.Evento;
import org.example.gestioncultural.modelo.beans.Exposicion;
import org.example.gestioncultural.modelo.beans.Taller;
import org.example.gestioncultural.modelo.procesos.ModificadorEventos;
import org.example.gestioncultural.controladores.utilidades.CreadorUI;
import org.example.gestioncultural.controladores.utilidades.ValidadorFormato;

import java.util.Optional;


public class ModificarEvento {
    @FXML private Button btnModificarEvento;
    @FXML private Label mensajeModificacion;
    @FXML private Label mensajeBuscador;
    @FXML private TextField campoBuscarId;

    @FXML private VBox vistaEventoAModificar;

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


    private ModificadorEventos modificadorEventos = new ModificadorEventos();
    private ValidadorFormato validador  = new ValidadorFormato();
    private CreadorUI creadorUI = new CreadorUI();

    private Integer idEventoAModificar;


    @FXML
    public void buscarEvento() {
        try {
            limpiar();
            mostrarEventoSeleccionado();
            cambiarFormularioSegunTipo();

        } catch (IllegalArgumentException | IllegalStateException ie) {
            mensajeBuscador.setText(ie.getMessage());
        }
    }

    @FXML
    public void modificarEvento() {
        try {
            Evento eventoModificado = crearEventoSegunTipo(obtenerTipoEvento());
            modificadorEventos.modificarEvento(idEventoAModificar, eventoModificado);
            btnModificarEvento.setDisable(true);
            mensajeModificacion.setText("Modificado");
        } catch (IllegalArgumentException iae) {
            mensajeModificacion.setText(iae.getMessage());
        } catch (IllegalStateException _) {
            mensajeModificacion.setText("El evento no existe");
        }

    }


    private void mostrarEventoSeleccionado() throws IllegalArgumentException, IllegalStateException {
        Integer id;
        Optional<Evento> evento;
        Optional<HBox> vistaEvento;

        id = validador.validarEntero(campoBuscarId.getText());

        evento = modificadorEventos.obtenerEventoPorId(id);

        if (evento.isPresent()) {
            vistaEvento = creadorUI.crearVistaEvento(evento.get());
            vistaEvento.ifPresent(hBox -> vistaEventoAModificar.getChildren().add(hBox));
            idEventoAModificar = id;
        } else {
            throw new IllegalArgumentException("El evento no existe");
        }

    }

    private Evento crearEventoSegunTipo(String tipo) throws IllegalArgumentException {
        Evento evento;

        switch (tipo) {
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
                evento = new Exposicion(
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
     */
    private void cambiarFormularioSegunTipo() throws IllegalStateException {
        String eventoSeleccionado = obtenerTipoEvento();

        btnModificarEvento.setDisable(false);
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
                throw new IllegalStateException("Tipo de evento no soportado");
        }
    }

    private String obtenerTipoEvento() throws IllegalStateException {
        Optional<Evento> evento = modificadorEventos.obtenerEventoPorId(idEventoAModificar);
        if (evento.isPresent()) {
            if (evento.get() instanceof Conferencia) {
                return CONFERENCIA;
            } else if (evento.get() instanceof Taller) {
                return TALLER;
            } else if (evento.get() instanceof Exposicion) {
                return EXPOSICION;
            }
        }
        throw new IllegalStateException();
    }

    private void limpiar() {
        vistaEventoAModificar.getChildren().clear();
        mensajeBuscador.setText("");
    }

}