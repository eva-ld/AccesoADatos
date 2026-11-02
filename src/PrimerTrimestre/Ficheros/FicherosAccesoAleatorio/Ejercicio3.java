package PrimerTrimestre.Ficheros.FicherosAccesoAleatorio;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Ejercicio3 {

    private static final String RUTA_FICHERO = "C:\\Users\\evalo\\Desktop\\Programación\\AccesoDatos\\src\\Ficheros\\";
    private static final String NOMBRE_FICHERO = "juegos.dat";

    public static void main(String[] args) {

        int[] ordenes = {1, 2, 3};
        String[] nombres = {"Life is Strange", "Palia", "BobEsponja"};
        int[] edades = {16, 12, 7};
        String[] tematicas = {"Aventura", "Simulacion", "Infantil"};

        // Abrimos el fichero en modo lectura y escritura
        try (RandomAccessFile fichero = new RandomAccessFile(RUTA_FICHERO + NOMBRE_FICHERO, "rw")) {
            // Limpiamos el fichero si ya existía
            fichero.setLength(0);

            for (int i = 0; i < ordenes.length; i++) {
                // Escribir número de orden
                fichero.writeInt(ordenes[i]);

                // Escribir nombre de tamaño fijo 15 caracteres
                StringBuffer sbNombre = new StringBuffer(nombres[i]);
                sbNombre.setLength(15);
                fichero.writeChars(sbNombre.toString());

                // Escribir edad mínima recomendada
                fichero.writeInt(edades[i]);

                // Escribir temática de tamaño fijo 10 caracteres
                StringBuffer sbTematica = new StringBuffer(tematicas[i]);
                sbTematica.setLength(10);
                fichero.writeChars(sbTematica.toString());
            }

            System.out.println("Registros de juegos almacenados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero: " + e.getMessage());
        }

        // Mostrar por pantalla cada registro en una línea
        System.out.println("\nCONTENIDO DEL FICHERO");
        try (RandomAccessFile fichero = new RandomAccessFile(RUTA_FICHERO + NOMBRE_FICHERO, "r")) {
            while (fichero.getFilePointer() < fichero.length()) {
                int orden = fichero.readInt();

                char[] nombreChars = new char[15];
                for (int i = 0; i < 15; i++) {
                    nombreChars[i] = fichero.readChar();
                }
                String nombre = new String(nombreChars).trim();

                int edad = fichero.readInt();

                char[] tematicaChars = new char[10];
                for (int i = 0; i < 10; i++) {
                    tematicaChars[i] = fichero.readChar();
                }
                String tematica = new String(tematicaChars).trim();

                System.out.println("Orden: " + orden + ", Nombre: " + nombre + ", Edad mínima: " + edad + ", Temática: " + tematica);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}