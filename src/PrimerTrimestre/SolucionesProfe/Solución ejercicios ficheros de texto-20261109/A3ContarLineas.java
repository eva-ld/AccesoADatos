import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class A3ContarLineas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Solicita el nombre del fichero
        System.out.print("Introduce el nombre del fichero: ");
        String nombreFichero = sc.nextLine();

        int contador = 0;

        // Cuenta las líneas del fichero
        try (BufferedReader lector = new BufferedReader(new FileReader(nombreFichero)))
        {

            while (lector.readLine() != null) {
                contador++;
            }

            System.out.println("El fichero contiene " + contador + " líneas.");

        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
        }

        sc.close();
    }
}