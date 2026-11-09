import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class A2AlumnosBinario {
    // Directorio donde se almacena el fichero (ruta relativa al proyecto)
    private static final String RUTA_FICHERO = "datos/";
    // Nombre del fichero binario
    private static final String NOMBRE_FICHERO = "alumnos.dat";

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        // Escribe la información de los cinco alumnos
        try (DataOutputStream escribir = new DataOutputStream(
                new FileOutputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {

            for (int i = 1; i <= 5; i++) {
                System.out.println("Alumno " + i);

                System.out.print("Nombre: ");
                String nombre = teclado.nextLine();

                System.out.print("Nota media: ");
                double nota = Double.parseDouble(teclado.nextLine());

                escribir.writeUTF(nombre);
                escribir.writeDouble(nota);
            }

        } catch (IOException e) {
            System.out.println("Error al escribir el fichero.");
        }

        // Lee y muestra la información de los alumnos
        try (DataInputStream leer = new DataInputStream(
                new FileInputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {

            for (int i = 1; i <= 5; i++) {
                String nombre = leer.readUTF();
                double nota = leer.readDouble();

                System.out.println(nombre + " - " + nota);
            }

        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }

        teclado.close();
    }
}