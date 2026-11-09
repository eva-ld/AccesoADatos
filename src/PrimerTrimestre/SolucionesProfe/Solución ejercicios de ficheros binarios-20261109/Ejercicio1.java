import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Ejercicio1 {
    // Directorio donde se almacena el fichero (ruta relativa al proyecto)
    private static final String RUTA_FICHERO = "datos/";
    // Nombre del fichero binario
    private static final String NOMBRE_FICHERO = "num_aleat.bin";

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        try {
            // Pedir información por teclado
            System.out.println("Nº de números a generar:");
            int numIntrod = teclado.nextInt();

            System.out.println("Número inferior rango:");
            int min = teclado.nextInt();

            System.out.println("Número superior rango:");
            int max = teclado.nextInt();

            // Comprobar valores introducidos
            if (numIntrod <= 0 || min < 0 || max < 0 || min >= max) {
                System.out.println( "Datos incorrectos: cantidad > 0, rangos positivos y min < max.");
                return;
            }

            if (max - min <= numIntrod) {
                System.out.println( "La diferencia entre el rango superior y el rango inferior debe "
                                  + "ser mayor que el número de números aleatorios a generar.");
                return;
            }

            // Escribir los números en el fichero binario
            try (DataOutputStream escribeFichero = new DataOutputStream(
                    new FileOutputStream(RUTA_FICHERO + NOMBRE_FICHERO,true))) {

                for (int i = 0; i < numIntrod; i++) {
                    int numero = (int) (Math.random() * (max - min + 1)) + min;
                    escribeFichero.writeInt(numero);
                }

            } catch (IOException e) {
                System.out.println("Error al escribir el fichero.");
                return;
            }

            // Leer los números almacenados
            try (DataInputStream leeFichero = new DataInputStream(new FileInputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {

                System.out.println("Contenido del fichero:");

                while(true) {
                    int numero = leeFichero.readInt();
                    System.out.print(numero + " ");
                }

            } catch (EOFException e) {
                // Fin del fichero: condición normal para terminar la lectura
                System.out.println("Fin de fichero.");
            } catch (IOException e) {
                System.out.println("Error al leer el fichero.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Debes introducir valores enteros.");
        }

        teclado.close();
    }
}