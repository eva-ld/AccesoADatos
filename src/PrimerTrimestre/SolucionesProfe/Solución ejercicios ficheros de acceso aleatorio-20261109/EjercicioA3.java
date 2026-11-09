import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class EjercicioA3 {
    // Directorio donde se almacena el fichero
    private static final String RUTA_FICHERO = "datos/";
    // Nombre del fichero
    private static final String NOMBRE_FICHERO = "juegos.dat";
    // Tamaño de cada registro en bytes
    private static final int TAM_REGISTRO = 48;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Número de juegos a añadir: ");
        int numero = sc.nextInt();
        sc.nextLine();

        // Añadir los juegos al final del fichero
        try (RandomAccessFile escribeFichero = new RandomAccessFile(RUTA_FICHERO + NOMBRE_FICHERO, "rw")) {

            // Se sitúa el puntero al final del fichero
            escribeFichero.seek(escribeFichero.length());
            for (int i = 0; i < numero; i++) {
                System.out.println("\nJuego Nº " + (i + 1));
                System.out.print("Nombre del juego: ");
                String nombre = sc.nextLine();

                System.out.print("Edad mínima: ");
                int edad = sc.nextInt();
                sc.nextLine();

                System.out.print("Temática: ");
                String tema = sc.nextLine();

                // Completar los campos hasta 10 caracteres
                nombre = String.format("%-10s", nombre);
                tema = String.format("%-10s", tema);

                // Calcular el número de registro
                int id = (int) (escribeFichero.length()/ TAM_REGISTRO) + 1;

                // Escribir el registro
                escribeFichero.writeInt(id);
                escribeFichero.writeChars(nombre);
                escribeFichero.writeInt(edad);
                escribeFichero.writeChars(tema);
            }

        } catch (IOException e) {
            System.out.println("Error al escribir el fichero.");
            sc.close();
            return;
        }

        // Mostrar el contenido del fichero
        System.out.println("\nContenido del fichero:");

        try (RandomAccessFile leeFichero = new RandomAccessFile(RUTA_FICHERO + NOMBRE_FICHERO, "r")) {

            while (leeFichero.getFilePointer() < leeFichero.length()) {

                int id = leeFichero.readInt();
                char[] nombre = new char[10];
                for (int i = 0; i < nombre.length; i++) {
                    nombre[i] = leeFichero.readChar();
                }

                int edad = leeFichero.readInt();
                char[] tema = new char[10];
                for (int i = 0; i < tema.length; i++) {
                    tema[i] = leeFichero.readChar();
                }

                // Eliminar los espacios utilizados para completar
                // los campos de longitud fija
                String nombreTexto = new String(nombre).trim();
                String temaTexto = new String(tema).trim();

                System.out.println( "ID: " + id + " | Juego: " + nombreTexto + " | Edad mínima: " + edad + " | Tema: " + temaTexto);
            }

        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }
        sc.close();
    }
}