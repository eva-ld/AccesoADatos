import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio6 {
    // Directorio donde se almacena el fichero (ruta relativa al proyecto)
    private static final String RUTA_FICHERO = "datos/";
    // Nombre del fichero binario
    private static final String NOMBRE_FICHERO = "datospersonas.dat";

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("¿Cuántas personas quieres registrar? ");
        int numero = teclado.nextInt();
        teclado.nextLine(); // Limpiar buffer

        // Escribir los datos de las personas en el fichero binario
        try (DataOutputStream escribeFichero = new DataOutputStream(
                new FileOutputStream(RUTA_FICHERO + NOMBRE_FICHERO, true))) {

            for (int i = 1; i <= numero; i++) {
                System.out.println("\n--- Persona " + i + " ---");

                System.out.print("Nombre: ");
                String nombre = teclado.nextLine();

                System.out.print("Apellidos: ");
                String apellidos = teclado.nextLine();

                System.out.print("Edad: ");
                int edad = teclado.nextInt();
                teclado.nextLine(); // Limpiar buffer

                System.out.print("Número de teléfono: ");
                String telefono = teclado.nextLine();

                System.out.print("Email: ");
                String email = teclado.nextLine();

                System.out.print("Ciudad de residencia: ");
                String ciudad = teclado.nextLine();

                System.out.print("Nacionalidad: ");
                String nacionalidad = teclado.nextLine();

                System.out.print("Profesión: ");
                String profesion = teclado.nextLine();

                // Guardar los datos en el mismo orden
                escribeFichero.writeUTF(nombre);
                escribeFichero.writeUTF(apellidos);
                escribeFichero.writeInt(edad);
                escribeFichero.writeUTF(telefono);
                escribeFichero.writeUTF(email);
                escribeFichero.writeUTF(ciudad);
                escribeFichero.writeUTF(nacionalidad);
                escribeFichero.writeUTF(profesion);
            }

            System.out.println( "Datos guardados en '" + NOMBRE_FICHERO + "'.");

        } catch (IOException e) {
            System.out.println("Error al escribir el fichero.");
            teclado.close();
            return;
        }

        // Leer los datos almacenados en el fichero
        System.out.println("\nLeer fichero:");

        try (DataInputStream leeFichero = new DataInputStream(
                new FileInputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {

            int contador = 1;

            // Se leen registros hasta alcanzar el final del fichero
            while (true) {
                String nombre = leeFichero.readUTF();
                String apellidos = leeFichero.readUTF();
                int edad = leeFichero.readInt();
                String telefono = leeFichero.readUTF();
                String email = leeFichero.readUTF();
                String ciudad = leeFichero.readUTF();
                String nacionalidad = leeFichero.readUTF();
                String profesion = leeFichero.readUTF();

                System.out.println("Persona nº " + contador + " ------");
                System.out.println("Nombre: " + nombre);
                System.out.println("Apellidos: " + apellidos);
                System.out.println("Edad: " + edad);
                System.out.println("Teléfono: " + telefono);
                System.out.println("Email: " + email);
                System.out.println("Ciudad: " + ciudad);
                System.out.println("Nacionalidad: " + nacionalidad);
                System.out.println("Profesión: " + profesion);

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