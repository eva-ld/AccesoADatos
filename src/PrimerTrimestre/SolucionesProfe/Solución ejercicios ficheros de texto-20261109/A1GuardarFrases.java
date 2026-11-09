import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class A1GuardarFrases {
    // Directorio donde se almacena el fichero (ruta relativa al proyecto)
    private static final String RUTA_FICHERO = "datos/";
    // Nombre del fichero donde se guardarán las frases
    private static final String NOMBRE_FICHERO = "frases.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Guarda las frases introducidas por el usuario
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(RUTA_FICHERO + NOMBRE_FICHERO)))
        {
            String frase;

            do {
                System.out.print("Introduce una frase ( escribir salir para terminar): ");
                frase = sc.nextLine();

                if (!frase.equalsIgnoreCase("salir")) {
                    escritor.write(frase);
                    escritor.newLine();
                }

            } while (!frase.equalsIgnoreCase("salir"));

            System.out.println("Frases guardadas correctamente.");

        } catch (IOException e) {
            System.out.println("Error al escribir el archivo.");
        }

        sc.close();
    }
}