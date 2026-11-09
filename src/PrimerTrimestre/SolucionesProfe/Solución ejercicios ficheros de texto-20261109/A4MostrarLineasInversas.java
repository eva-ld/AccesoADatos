import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class A4MostrarLineasInversas {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Solicita el nombre del fichero
        System.out.print("Introduce ruta y nombre del fichero: ");
        String nombreFichero = sc.nextLine();

        // Lista donde se almacenarán las líneas
        ArrayList<String> lineas = new ArrayList<>();

        // Lee el fichero línea a línea
        try (BufferedReader lector = new BufferedReader(new FileReader(nombreFichero)))
        {

            String linea;

            while ((linea = lector.readLine()) != null) {
                // Añade cada línea a la lista
                lineas.add(linea);
            }

            // Mostrar las líneas en orden contrario
            for (int i = lineas.size() - 1; i >= 0; i--) {
                System.out.println(lineas.get(i));
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
        }

        sc.close();
    }
}