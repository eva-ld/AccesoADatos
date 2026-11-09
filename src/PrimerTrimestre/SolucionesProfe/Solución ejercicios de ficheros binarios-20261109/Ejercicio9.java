import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

public class Ejercicio9 {
    // Directorio donde se encuentra el fichero
    private static final String RUTA_FICHERO = "datos/";

    // Nombre del fichero binario
    private static final String NOMBRE_FICHERO = "Septemp.dat";

    public static void main(String[] args) {

        double maxima = Double.NEGATIVE_INFINITY;
        double minima = Double.POSITIVE_INFINITY;
        double suma = 0;

        String horaMaxima = "";
        String horaMinima = "";

        int contador = 0;

        // Leer las temperaturas almacenadas en el fichero binario
        try (DataInputStream leeFichero = new DataInputStream(
                new FileInputStream(
                        RUTA_FICHERO + NOMBRE_FICHERO))) {

            // Se leen registros hasta alcanzar el final del fichero
            while (true) {
                int dia = leeFichero.readInt();
                String hora = leeFichero.readUTF();
                double temperatura = leeFichero.readDouble();

                suma += temperatura;
                contador++;

                // Comprobar si es la temperatura máxima
                if (temperatura > maxima) {
                    maxima = temperatura;
                    horaMaxima = hora;
                }

                // Comprobar si es la temperatura mínima
                if (temperatura < minima) {
                    minima = temperatura;
                    horaMinima = hora;
                }
            }

        } catch (EOFException e) {
            // Fin del fichero: condición normal para terminar la lectura

            if (contador > 0) {
                double media = suma / contador;

                System.out.println("Temperatura máxima: "
                        + maxima + " °C");
                System.out.println("Hora más calurosa: "
                        + horaMaxima);

                System.out.println("Temperatura mínima: "
                        + minima + " °C");
                System.out.println("Hora más fría: "
                        + horaMinima);

                System.out.println("Temperatura media: "
                        + media + " °C");
            } else {
                System.out.println("El fichero no contiene datos.");
            }

        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }
    }
}