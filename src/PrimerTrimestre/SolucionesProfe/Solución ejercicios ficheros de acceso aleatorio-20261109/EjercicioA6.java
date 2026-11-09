import java.io.IOException;
import java.io.RandomAccessFile;

public class EjercicioA6 {

    // Directorio donde se almacenan los ficheros
    private static final String RUTA_FICHERO = "datos/";

    // Nombre de los ficheros
    private static final String NOMBRE_ORIGEN = "practicas.dat";
    private static final String NOMBRE_DESTINO = "precio.dat";

    // Precio de cada clase
    private static final double PRECIO_CLASE = 45.7;

    public static void main(String[] args) {

        // Lee practicas.dat y crear precio.dat
        try ( RandomAccessFile leeFichero = new RandomAccessFile(RUTA_FICHERO + NOMBRE_ORIGEN, "r");
              RandomAccessFile escribeFichero = new RandomAccessFile(RUTA_FICHERO + NOMBRE_DESTINO, "rw") ) {

            // Vaciar el fichero de destino
            escribeFichero.setLength(0);

            // Leer registros hasta alcanzar el final del fichero
            while (leeFichero.getFilePointer()< leeFichero.length()) {
                // Leer nombre
                char[] nombre = new char[10];
                for (int i = 0; i < nombre.length; i++) {
                    nombre[i] = leeFichero.readChar();
                }

                // Leer apellidos
                char[] apellidos = new char[35];
                for (int i = 0; i < apellidos.length; i++) {
                    apellidos[i] = leeFichero.readChar();
                }

                // Leer DNI
                char[] dni = new char[9];
                for (int i = 0; i < dni.length; i++) {
                    dni[i] = leeFichero.readChar();
                }

                // Leer vehículo y número de clases
                int vehiculo = leeFichero.readInt();
                int clases = leeFichero.readInt();

                // Calcular el importe total
                double total = clases * PRECIO_CLASE;

                // Guardar los datos necesarios en precio.dat
                escribeFichero.writeChars(String.format( "%-10s", new String(nombre).trim()));
                escribeFichero.writeChars(String.format("%-9s", new String(dni).trim()));

                escribeFichero.writeInt(clases);
                escribeFichero.writeDouble(total);
            }

        } catch (IOException e) {
            System.out.println("Error al procesar los ficheros.");
            return;
        }

        // Mostrar el contenido de precio.dat
        System.out.println("\nContenido de precio.dat:");

        try (RandomAccessFile leeFichero = new RandomAccessFile(RUTA_FICHERO + NOMBRE_DESTINO, "r")) {

            // Leer registros hasta alcanzar el final del fichero
            while (leeFichero.getFilePointer() < leeFichero.length()) {

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

                System.out.printf( "%s (%s): %d clases - %.2f €%n",new String(nombre).trim(),new String(dni).trim(), clases, total);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }
    }
}