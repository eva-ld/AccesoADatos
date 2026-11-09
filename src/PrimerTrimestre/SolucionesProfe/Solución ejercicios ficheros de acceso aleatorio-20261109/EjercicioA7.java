import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class EjercicioA7 {

    // Directorio donde se almacenan los ficheros
    private static final String RUTA_FICHERO = "datos/";
    // Nombre de los ficheros
    private static final String NOMBRE_ORIGEN = "precio.dat";
    private static final String NOMBRE_DESTINO = "descuento.dat";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Introducir el porcentaje de descuento (%): ");
        double descuento = sc.nextDouble() / 100;

        // Leer precio.dat y crear descuento.dat
        try ( RandomAccessFile leeFichero = new RandomAccessFile(RUTA_FICHERO + NOMBRE_ORIGEN, "r");
              RandomAccessFile escribeFichero = new RandomAccessFile(RUTA_FICHERO + NOMBRE_DESTINO, "rw")) {

            // Vacia el fichero de destino
            escribeFichero.setLength(0);

            // Leer registros hasta alcanzar el final del fichero
            while (leeFichero.getFilePointer()< leeFichero.length()) {
                // Leer nombre
                char[] nombre = new char[10];
                for (int i = 0; i < nombre.length; i++) {
                    nombre[i] = leeFichero.readChar();
                }

                // Leer DNI
                char[] dni = new char[9];
                for (int i = 0; i < dni.length; i++) {
                    dni[i] = leeFichero.readChar();
                }

                int clases = leeFichero.readInt();
                double total = leeFichero.readDouble();

                // Calcular el importe con descuento
                double totalDescuento =
                        total * (1 - descuento);

                // Guardar DNI, importe anterior e importe final
                escribeFichero.writeChars( String.format( "%-9s", new String(dni).trim()));
                escribeFichero.writeDouble(total);
                escribeFichero.writeDouble(totalDescuento);
            }

        } catch (IOException e) {
            System.out.println("Error al procesar los ficheros.");
            sc.close();
            return;
        }

        // Mostrar el contenido de descuento.dat
        System.out.println("\nContenido de descuento.dat:");

        try (RandomAccessFile leeFichero = new RandomAccessFile(
                RUTA_FICHERO + NOMBRE_DESTINO, "r")) {

            // Leer registros hasta alcanzar el final del fichero
            while (leeFichero.getFilePointer()
                    < leeFichero.length()) {

                // Leer DNI
                char[] dni = new char[9];

                for (int i = 0; i < dni.length; i++) {
                    dni[i] = leeFichero.readChar();
                }

                double importeAnterior = leeFichero.readDouble();
                double importeFinal = leeFichero.readDouble();

                System.out.printf(
                        "%s - Antes: %.2f € - Después: %.2f €%n",
                        new String(dni).trim(),
                        importeAnterior,
                        importeFinal);
            }

        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }

        sc.close();
    }
}