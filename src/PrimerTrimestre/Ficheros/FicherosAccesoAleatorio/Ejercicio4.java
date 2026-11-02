package PrimerTrimestre.Ficheros.FicherosAccesoAleatorio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio4 {

    private static final String RUTA_FICHERO = "C:\\Users\\evalo\\Desktop\\Programación\\AccesoDatos\\src\\Ficheros\\";
    private static final String NOMBRE_FICHERO = "juegos.dat";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce la edad para buscar juegos recomendados: ");
        int edadUsuario = sc.nextInt();

        System.out.println("\nJUEGOS APTOS PARA EDAD " + edadUsuario);

        try (RandomAccessFile fichero = new RandomAccessFile(RUTA_FICHERO + NOMBRE_FICHERO, "r")) {
            boolean encontrado = false;
            while (fichero.getFilePointer() < fichero.length()) {
                int orden = fichero.readInt();

                char[] nombreChars = new char[15];
                for (int i = 0; i < 15; i++) {
                    nombreChars[i] = fichero.readChar();
                }
                String nombre = new String(nombreChars).trim();

                int edadMinima = fichero.readInt();

                char[] tematicaChars = new char[10];
                for (int i = 0; i < 10; i++) {
                    tematicaChars[i] = fichero.readChar();
                }
                String tematica = new String(tematicaChars).trim();

                if (edadUsuario >= edadMinima) {
                    System.out.println("Nombre: " + nombre + " | Temática: " + tematica + " (Edad mínima: " + edadMinima + ")");
                    encontrado = true;
                }
            }
            if (!encontrado) {
                System.out.println("No hay juegos recomendados para esa edad.");
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
        sc.close();
    }
}