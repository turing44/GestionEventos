package org.example.gestioncultural.modelo.util;

import java.io.*;

public class Repositorio<T> {
    private final String rutaArchivo;

    public Repositorio(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void guardar(T objeto) {
        try (FileOutputStream fos = new FileOutputStream(rutaArchivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
             oos.writeObject(objeto);
        } catch (IOException e) {
            System.err.println("Error al guardar datos en " + rutaArchivo + ": " + e.getMessage());
        }
    }

    // Lee el objeto del archivo, o devuelve null si falla
    @SuppressWarnings("unchecked")
    public T leer() {
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            return null;
        }

        try (FileInputStream fis = new FileInputStream(archivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (T) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al leer datos de " + rutaArchivo + ": " + e.getMessage());
            return null;
        }
    }
}
