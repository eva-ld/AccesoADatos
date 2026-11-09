import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class EjercicioA2 {

    // Directorio donde se almacena el fichero
    private static final String RUTA_FICHERO = "datos/";
    // Nombre del fichero
    private static final String NOMBRE_FICHERO = "enteros.dat";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Número que se añadirá
        int numero;
        do {
            System.out.print("Introducir un número entre 0 y 9: ");
            numero = sc.nextInt();
        } while (numero < 0 || numero > 9);

        // Veces se añadirá
        int veces;
        do {
            System.out.print("Número de veces que se añadirá (entre 1 y 100): ");
            veces = sc.nextInt();
        } while (veces < 1 || veces > 100);

        // Añadir los valores al final del fichero
        try (RandomAccessFile escribeFichero = new RandomAccessFile(RUTA_FICHERO + NOMBRE_FICHERO, "rw")) {
            // Situarse al final del fichero
            escribeFichero.seek(escribeFichero.length());
            for (int i = 0; i < veces; i++) {
                escribeFichero.writeInt(numero);
            }
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero.");
            sc.close();
            return;
        }

        // Mostrar el contenido actualizado
        System.out.println("\nContenido actualizado:");
        try (RandomAccessFile leeFichero = new RandomAccessFile(RUTA_FICHERO + NOMBRE_FICHERO, "r")) {
            while (leeFichero.getFilePointer()< leeFichero.length()) {
                System.out.print(leeFichero.readInt() + " ");
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }

        sc.close();
    }
}