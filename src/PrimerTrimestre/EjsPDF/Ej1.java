package PrimerTrimestre.EjsPDF;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Ej1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        File fichero = new File("num_aleat.bin");

        System.out.print("¿Cuántos números aleatorios quieres generar?: ");
        int cantidad = sc.nextInt();
        System.out.print("Límite inferior del rango: ");
        int min = sc.nextInt();
        System.out.print("Límite superior del rango: ");
        int max = sc.nextInt();

        // Pongo 'true' en el FileOutputStream para activar el modo append y no sobrescribir lo que ya había
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fichero, true))) {
            for (int i = 0; i < cantidad; i++) {
                int num = rand.nextInt((max - min) + 1) + min;
                dos.writeInt(num);
            }
            System.out.println("Números añadidos con éxito.");
        } catch (IOException e) {
            System.err.println("Error al escribir: " + e.getMessage());
        }

        // Leo todo el fichero desde el principio para mostrar cómo queda
        System.out.println("\n--- Contenido del fichero num_aleat.bin ---");
        try (DataInputStream dis = new DataInputStream(new FileInputStream(fichero))) {
            // Recorro el archivo mientras sigan quedando bytes por leer
            while (dis.available() > 0) {
                System.out.print(dis.readInt() + " ");
            }
            System.out.println();
        } catch (IOException e) {
            System.err.println("Error al leer: " + e.getMessage());
        }
    }
}
