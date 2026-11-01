package BINARIOS.Binario;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Ejercicio1 {
    static void main() {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("¿Cúantos números quieres generar?");
        int cantidad = sc.nextInt();

        if (cantidad <= 0) {
            do {
                System.out.println("No puedes generar una cantidad negativa o nula de números");
                System.out.println("¿Cúantos números quieres generar?");
                cantidad = sc.nextInt();


            } while (cantidad <= 0);
        }

            int min;
            int max;
            System.out.println("Introduce el mínimo:");
            min = sc.nextInt();
            System.out.println("Introduce el máximo:");
            max = sc.nextInt();

            if (min > max) {
                do {
                    System.out.println("El mínimo no puede ser mayor que el máximo.");
                    System.out.println("Introduce el mínimo:");
                    min = Integer.parseInt(sc.nextLine());

                    System.out.println("Introduce el máximo:");
                    max = Integer.parseInt(sc.nextLine());

                } while (min > max);
            }


            try (DataOutputStream escribe = new DataOutputStream(
                    new FileOutputStream("num_aleat.bin", true))) {

                for (int i = 0; i < cantidad; i++) {
                    int numero = rand.nextInt(max - min + 1) + min;
                    escribe.writeInt(numero);
                }

                System.out.println("Números guardados correctamente.");

            } catch (IOException e) {
                System.out.println("Error al escribir en el fichero");
            }

            // LEER LOS NÚMEROS ALMACENADOS

            try (DataInputStream entrada = new DataInputStream(
                    new FileInputStream("num_aleat.bin"))) {

                System.out.println("Contenido del fichero:");

                while (true) {
                    System.out.println(entrada.readInt());
                }

            } catch (java.io.EOFException e) {
                System.out.println("Fin del fichero.");
            } catch (IOException e) {
                System.out.println("Error al leer: " + e.getMessage());
            }

            sc.close();
    }
}

// NOTAS
    // No podemos leer el fichero num_aleat.bin, ya que no podemos leer binario, tenemos que comprobar que funciona en la consola