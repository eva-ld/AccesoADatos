package PrimerTrimestre.Binario;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Prueba1 {
    public static void main(String[] args) {

        try (DataOutputStream salida = new DataOutputStream(new FileOutputStream("numeros.dat"))) {
            for (int i = 0; i < 50; i++) {
                salida.writeInt(i);
            }
            System.out.println("Números escritos correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero: " + e.getMessage());
        }

        // Leer del fichero binario
        try (DataInputStream entrada = new DataInputStream(new FileInputStream("numeros.dat"))) {
            for (int i = 0; i < 50; i++) {
                System.out.println(entrada.readInt());
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}