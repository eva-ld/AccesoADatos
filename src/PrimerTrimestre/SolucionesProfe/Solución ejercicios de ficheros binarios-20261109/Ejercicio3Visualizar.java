import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

public class Ejercicio3Visualizar {
    // Directorio donde se encuentra el fichero (ruta relativa al proyecto)
    private static final String RUTA_FICHERO = "datos/";
    // Nombre del fichero binario
    private static final String NOMBRE_FICHERO = "datosbeca.bin";

    public static void main(String[] args) {

        // Leer los datos almacenados en el fichero
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

                System.out.println("------ Becario ------");
                System.out.println("Nombre: " + nombre);
                System.out.println("Sexo: " + sexo);
                System.out.println("Edad: " + edad);
                System.out.println("Suspensos: " + suspensos);
                System.out.println("Residencia familiar: " + residencia);
                System.out.println("Ingresos anuales: " + ingresos);
                System.out.println("Tiene beca: " + beca);
            }

        } catch (EOFException e) {
            // Fin del fichero: condición normal para terminar la lectura
            System.out.println("\nFin de la lectura del archivo.");

        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }
    }
}