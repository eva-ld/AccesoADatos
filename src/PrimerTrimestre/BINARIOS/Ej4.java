package PrimerTrimestre.BINARIOS;

import PrimerTrimestre.EJ3.Producto;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Ej4 {
    private static final String RUTA_FICHERO = "productos_multiples.dat";

    public static void main(String[] args) {
        Producto[] productos = {
                new Producto("Teclado Mecánico", 49.99, 30),
                new Producto("Ratón Óptico", 19.95, 50),
                new Producto("Monitor 27''", 219.00, 10)
        };

        // 1. Escritura de múltiples objetos
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_FICHERO))) {
            for (Producto p : productos) {
                oos.writeObject(p);
            }
            System.out.println("Se han guardado los 3 productos en el fichero.");
        } catch (IOException e) {
            System.err.println("Error al escribir el fichero: " + e.getMessage());
        }

        // 2. Lectura secuencial de todos los objetos
        System.out.println("\n--- Catálogo de Productos Recuperados ---");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA_FICHERO))) {
            while (true) {
                Producto p = (Producto) ois.readObject();
                System.out.println(p);
            }
        } catch (EOFException e) {
            System.out.println("\n[Fin de lectura de archivo alcanzado correctamente]");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al leer los objetos: " + e.getMessage());
        }
    }
}
