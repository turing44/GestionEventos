package org.example.gestioncultural.modelo.procesos;

import org.example.gestioncultural.modelo.beans.Evento;
import org.example.gestioncultural.modelo.beans.Exposicion;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ModificadorEventos extends GestionadorEventos {

    public void modificarEvento(Integer idEventoAModificar, Evento nuevoEvento) throws IllegalArgumentException{
        Optional<Evento> eventoAModificar = obtenerEventoPorId(idEventoAModificar);

        if (eventoAModificar.isPresent()) {
            nuevoEvento.setIdEvento(idEventoAModificar);
            validarModificacion(nuevoEvento);
            eventos.remove(eventoAModificar.get());
            eventos.add(nuevoEvento);
            guardarEventos();
        } else {
            // Se comprueba tambien en ModificarEvento, se debe volver a comprobar?
            throw new IllegalArgumentException("No existe evento con ese ID");
        }
    }

    private void validarModificacion(Evento evento) throws IllegalArgumentException {
        validador.validar(evento);
        validarNoEstaEnCursoNiConcluido(evento);
        validarModificacionFechaPermitida(evento);
    }

    private void validarNoEstaEnCursoNiConcluido(Evento evento) throws IllegalArgumentException {
        // debo repetir aqui las validaciones de si es nulo??

        if (evento.estaEnCurso() || evento.getFecha().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Solo se pueden modificar eventos futuros");
        }
    }


    /**
     * Como tienen el mismo id sacará de la lista el evento antiguo para escoger su fecha
     * @param evento
     * @throws IllegalArgumentException
     */
    private void validarModificacionFechaPermitida(Evento evento) throws IllegalArgumentException {
        validador.validarFechaCogida(
                evento,
                obtenerFechasCogidasSaltandoseEventos(List.of(evento))
        );
    }
}
