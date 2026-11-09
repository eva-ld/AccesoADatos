import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class EjercicioA1 {
    // Directorio donde se almacena el fichero
    private static final String RUTA_FICHERO = "datos/";
    // Nombre del fichero
    private static final String NOMBRE_FICHERO = "enteros.dat";

    public static void main(String[] args) {

        // Crear el fichero y escribir 6 unos y 10 doses
        try (RandomAccessFile escribeFichero = new RandomAccessFile(RUTA_FICHERO + NOMBRE_FICHERO, "rw")) {

            // Vacia el fichero antes de escribir los datos
            escribeFichero.setLength(0);

            // Escribe 6 veces el valor 1
            for (int i = 0; i < 6; i++) {
                escribeFichero.writeInt(1);
            }

            // Escribe 10 veces el valor 2
            for (int i = 0; i < 10; i++) {
                escribeFichero.writeInt(2);
            }

        } catch (IOException e) {
            System.out.println("Error al escribir el fichero.");
            return;
        }

        // Lee el contenido original
        System.out.println("Leer original:");
        try (RandomAccessFile leeFichero = new RandomAccessFile(RUTA_FICHERO + NOMBRE_FICHERO, "r")) {
            while (leeFichero.getFilePointer() < leeFichero.length()) {
                System.out.print(leeFichero.readInt() + " ");
            }

        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
            return;
        }

        // Solicitar el número que sustituirá a todos los valores 2
        Scanner sc = new Scanner(System.in);
        int numero;
        do {
            System.out.print("\nIntroducir un número entre 0 y 9: ");
            numero = sc.nextInt();

        } while (numero < 0 || numero > 9);

        // Sustituye los valores 2
        try (RandomAccessFile modificaFichero = new RandomAccessFile(RUTA_FICHERO + NOMBRE_FICHERO, "rw")) {

            while (modificaFichero.getFilePointer()< modificaFichero.length()) {
                // Guarda la posición del entero
                long posicion = modificaFichero.getFilePointer();
                int valor = modificaFichero.readInt();

                if (valor == 2) {
                    // Se sitúa de nuevo en la posición del entero y lo sustituye
                    modificaFichero.seek(posicion);
                    modificaFichero.writeInt(numero);
                }
            }

        } catch (IOException e) {
            System.out.println("Error al modificar el fichero.");
            return;
        }

        // Muestra contenido actualizado
        System.out.println("\nContenido actualizado:");

        try (RandomAccessFile leeFichero = new RandomAccessFile(RUTA_FICHERO + NOMBRE_FICHERO, "r")) {
            while (leeFichero.getFilePointer() < leeFichero.length()) {
                System.out.print(leeFichero.readInt() + " ");
            }

        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }
        sc.close();
    }
}