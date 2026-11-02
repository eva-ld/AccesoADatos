package PrimerTrimestre.BINARIOS;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ej2 {
    private static final String RUTA_FICHERO = "alumnos.dat";

    public static void main(String[] args) {
        String[] nombres = {"Ana", "Carlos", "Elena", "David", "Beatriz"};
        double[] notas = {8.5, 9.2, 7.8, 6.4, 9.9};

        //  Escribir datos de los 5 alumnos
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(RUTA_FICHERO))) {
            for (int i = 0; i < nombres.length; i++) {
                dos.writeUTF(nombres[i]);
                dos.writeDouble(notas[i]);
            }
            System.out.println("Datos de los alumnos guardados con éxito.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el fichero: " + e.getMessage());
        }

        // Leer y mostrar por pantalla
        System.out.println("\n--- Listado de Alumnos ---");
        try (DataInputStream dis = new DataInputStream(new FileInputStream(RUTA_FICHERO))) {
            while (dis.available() > 0) {
                String nombre = dis.readUTF();
                double nota = dis.readDouble();
                System.out.printf("Alumno: %-10s | Nota Media: %.2f%n", nombre, nota);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}