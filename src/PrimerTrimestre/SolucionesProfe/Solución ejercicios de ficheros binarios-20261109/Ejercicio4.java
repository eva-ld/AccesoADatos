import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio4 {
    // Directorio donde se almacena el fichero (ruta relativa al proyecto)
    private static final String RUTA_FICHERO = "datos/";
    // Nombre del fichero binario
    private static final String NOMBRE_FICHERO = "datosbeca.bin";

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("Número de becarios a registrar: ");
        int numero = teclado.nextInt();
        teclado.nextLine(); // Limpiar buffer

        // Añadir los datos de los becarios al fichero
        try (DataOutputStream escribeFichero = new DataOutputStream(
                new FileOutputStream(
                        RUTA_FICHERO + NOMBRE_FICHERO, true))) {

            for (int i = 1; i <= numero; i++) {
                System.out.println("Becario Nº " + i);

                System.out.print("Nombre y apellido: ");
                String nombre = teclado.nextLine();

                System.out.print("Sexo (H/M): ");
                String sexo = teclado.nextLine();

                System.out.print("Edad (20-60): ");
                int edad = teclado.nextInt();

                System.out.print("Nº de suspensos (0-4): ");
                int suspensos = teclado.nextInt();
                teclado.nextLine(); // Limpiar buffer

                System.out.print("Residencia familiar (SI/NO): ");
                String residencia = teclado.nextLine();

                System.out.print("Ingresos anuales de la familia: ");
                double ingresos = teclado.nextDouble();
                teclado.nextLine(); // Limpiar buffer

                System.out.print("¿Tiene beca? (SI/NO): ");
                String beca = teclado.nextLine();

                // Guardar los datos en el mismo orden
                escribeFichero.writeUTF(nombre);
                escribeFichero.writeUTF(sexo);
                escribeFichero.writeInt(edad);
                escribeFichero.writeInt(suspensos);
                escribeFichero.writeUTF(residencia);
                escribeFichero.writeDouble(ingresos);
                escribeFichero.writeUTF(beca);
            }

            System.out.println("Datos guardados en '" + NOMBRE_FICHERO + "'.");

        } catch (IOException e) {
            System.out.println("Error al escribir el fichero.");
            teclado.close();
            return;
        }

        // Visualizar los datos almacenados
        System.out.println("\nInicio de la lectura");

        try (DataInputStream leeFichero = new DataInputStream(
                new FileInputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {

            int contador = 1;

            // Se leen registros hasta alcanzar el final del fichero
            while (true) {
                String nombre = leeFichero.readUTF();
                String sexo = leeFichero.readUTF();
                int edad = leeFichero.readInt();
                int suspensos = leeFichero.readInt();
                String residencia = leeFichero.readUTF();
                double ingresos = leeFichero.readDouble();
                String beca = leeFichero.readUTF();

                System.out.println("Becario Nº " + contador);
                System.out.println("Nombre: " + nombre);
                System.out.println("Sexo: " + sexo);
                System.out.println("Edad: " + edad);
                System.out.println("Suspensos: " + suspensos);
                System.out.println("Residencia familiar: " + residencia);
                System.out.println("Ingresos anuales: " + ingresos);
                System.out.println("Tiene beca: " + beca);

                contador++;
            }

        } catch (EOFException e) {
            // Fin del fichero: condición normal para terminar la lectura
            System.out.println("Fin de fichero.");

        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }

        teclado.close();
    }
}