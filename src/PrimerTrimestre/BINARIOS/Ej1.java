package PrimerTrimestre.BINARIOS;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ej1 {
    private static final String RUTA_FICHERO = "datos/enteros.dat";

    public static void main(String[] args) {

        // Escribir del 1 al 50
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(RUTA_FICHERO))) {
            for (int i = 1; i <= 50; i++) {
                dos.writeInt(i);
            }
            System.out.println("Números del 1 al 50 guardados .");
        } catch (IOException e) {
            System.err.println("Error al escribir el fichero: " + e.getMessage());
        }

        // Lectura y muestra por pantalla
        System.out.println("\n--- Contenido del fichero ---");
        try (DataInputStream dis = new DataInputStream(new FileInputStream(RUTA_FICHERO))) {
            while (dis.available() > 0) {
                int numero = dis.readInt();
                System.out.print(numero + " ");
            }
            System.out.println();
        } catch (IOException e) {
            System.err.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}