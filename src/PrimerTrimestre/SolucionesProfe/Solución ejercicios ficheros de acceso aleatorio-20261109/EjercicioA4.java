import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class EjercicioA4 {
    // Directorio donde se almacena el fichero
    private static final String RUTA_FICHERO = "datos/";
    // Nombre del fichero
    private static final String NOMBRE_FICHERO = "juegos.dat";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Edad del jugador: ");
        int edadJugador = sc.nextInt();

        // Leer el fichero de juegos
        try (RandomAccessFile leeFichero = new RandomAccessFile(RUTA_FICHERO + NOMBRE_FICHERO, "r")) {

            System.out.println("\nJuegos recomendados:");

            // Leer registros hasta alcanzar el final del fichero
            while (leeFichero.getFilePointer()< leeFichero.length()) {

                int id = leeFichero.readInt();
                // Leer el nombre de longitud fija
                char[] nombre = new char[10];
                for (int i = 0; i < nombre.length; i++) {
                    nombre[i] = leeFichero.readChar();
                }

                int edad = leeFichero.readInt();
                // Leer la temática de longitud fija
                char[] tema = new char[10];
                for (int i = 0; i < tema.length; i++) {
                    tema[i] = leeFichero.readChar();
                }

                // Mostrar los juegos adecuados para la edad
                if (edadJugador >= edad) {
                    System.out.println(new String(nombre).trim() + " (" + new String(tema).trim() + ")");
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }
        sc.close();
    }
}