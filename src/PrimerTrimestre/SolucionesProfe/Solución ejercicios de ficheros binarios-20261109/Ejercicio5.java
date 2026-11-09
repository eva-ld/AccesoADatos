import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

public class Ejercicio5 {
    // Directorio donde se encuentra el fichero (ruta relativa al proyecto)
    private static final String RUTA_FICHERO = "datos/";
    // Nombre del fichero binario
    private static final String NOMBRE_FICHERO = "datosbeca.bin";

    public static void main(String[] args) {

        try (DataInputStream leeFichero = new DataInputStream(
                new FileInputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {

            // Se leen registros hasta alcanzar el final del fichero
            while (true) {
                String nombre = leeFichero.readUTF();
                String sexo = leeFichero.readUTF();
                int edad = leeFichero.readInt();
                int suspensos = leeFichero.readInt();
                String residencia = leeFichero.readUTF();
                double ingresos = leeFichero.readDouble();
                String beca = leeFichero.readUTF();

                if (beca.equalsIgnoreCase("SI")) {
                    int base = 1500;

                    // Ingresos familiares
                    if (ingresos <= 12000) {
                        base += 500;
                    }

                    // Edad
                    if (edad < 23) {
                        base += 200;
                    }

                    // Número de suspensos
                    if (suspensos == 0) {
                        base += 500;
                    } else if (suspensos == 1) {
                        base += 200;
                    } else {
                        // Con dos o más suspensos no se concede la beca
                        continue;
                    }

                    // No reside en el domicilio familiar
                    if (residencia.equalsIgnoreCase("NO")) {
                        base += 1000;
                    }

                    System.out.println(
                            "Nombre: " + nombre + " → Beca: " + base + " €");
                }
            }

        } catch (EOFException e) {
            // Fin del fichero: condición normal para terminar la lectura
            System.out.println("Fin de fichero.");

        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }
    }
}