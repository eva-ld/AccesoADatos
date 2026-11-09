import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class A2MostrarFichero {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Solicita el nombre del fichero
        System.out.print("Introduce el nombre del fichero: ");
        String nombreFichero = sc.nextLine();

        int contador = 0;

        // Lee y muestra el contenido del fichero
        try (BufferedReader lector = new BufferedReader(new FileReader(nombreFichero)))
        {
            String linea;

            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
                contador++;

                // Pausa cada 24 líneas
                if (contador % 24 == 0) {
                    System.out.println("Pulsa Intro para continuar...");
                    sc.nextLine();
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
        }
        sc.close();
    }
}