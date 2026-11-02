package PrimerTrimestre.EJ3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Ej3 {
    private static final String RUTA_FICHERO = "producto.dat";

    public static void main(String[] args) {
        Producto p1 = new Producto("Portátil", 899.99, 15);

        // 1. Serialización (escritura del objeto)
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_FICHERO))) {
            oos.writeObject(p1);
            System.out.println("Producto guardado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al serializar el producto: " + e.getMessage());
        }

        // 2. Deserialización (lectura del objeto)
        System.out.println("\n--- Recuperando Producto ---");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA_FICHERO))) {
            Producto pLeido = (Producto) ois.readObject();
            System.out.println(pLeido);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al deserializar el producto: " + e.getMessage());
        }
    }
}