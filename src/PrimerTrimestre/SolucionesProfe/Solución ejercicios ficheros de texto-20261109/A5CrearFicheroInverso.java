import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class A5CrearFicheroInverso {
    // Directorio donde se encuentran los ficheros (ruta relativa al proyecto)
    private static final String RUTA_FICHERO = "datos/";
    // Nombre del fichero de salida
    private static final String NOMBRE_SALIDA = "salida.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Solicita el nombre del fichero
        System.out.print("Introduce el nombre del fichero: ");
        String nombreFichero = sc.nextLine();

        // Lista donde se almacenarán las líneas
        ArrayList<String> lineas = new ArrayList<>();

        // Lee el fichero línea a línea
        try (BufferedReader lector = new BufferedReader(
                new FileReader(RUTA_FICHERO + nombreFichero))) {

            String linea;

            while ((linea = lector.readLine()) != null) {
                // Añade cada línea a la lista
                lineas.add(linea);
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
            sc.close();
            return;
        }

        // Escribe las líneas en orden contrario
        try (BufferedWriter escritor = new BufferedWriter(
                new FileWriter(RUTA_FICHERO + NOMBRE_SALIDA))) {

            for (int i = lineas.size() - 1; i >= 0; i--) {
                escritor.write(lineas.get(i));
                escritor.newLine();
            }

            System.out.println("Fichero creado correctamente.");

        } catch (IOException e) {
            System.out.println("Error al escribir el archivo.");
        }

        sc.close();
    }
}