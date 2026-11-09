import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class EjercicioA5 {

    // Directorio donde se almacena el fichero
    private static final String RUTA_FICHERO = "datos/";
    // Nombre del fichero
    private static final String NOMBRE_FICHERO = "practicas.dat";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Número de alumnos a añadir: ");
        int numero = sc.nextInt();
        sc.nextLine();

        // Añadir alumnos al final del fichero
        try (RandomAccessFile escribeFichero = new RandomAccessFile(RUTA_FICHERO + NOMBRE_FICHERO, "rw")) {

            // Situarse al final del fichero
            escribeFichero.seek(escribeFichero.length());
            for (int i = 0; i < numero; i++) {
                System.out.println("\nAlumno Nº " + (i + 1));

                System.out.print("Nombre: ");
                String nombre = sc.nextLine();

                System.out.print("Apellidos: ");
                String apellidos = sc.nextLine();

                System.out.print("DNI: ");
                String dni = sc.nextLine();

                System.out.print("Vehículo: ");
                int vehiculo = sc.nextInt();

                System.out.print("Nº clases: ");
                int clases = sc.nextInt();
                sc.nextLine();

                // Completar los campos de texto hasta su longitud fija
                nombre = String.format("%-10s", nombre);
                apellidos = String.format("%-35s", apellidos);
                dni = String.format("%-9s", dni);

                // Escribir el registro
                escribeFichero.writeChars(nombre);
                escribeFichero.writeChars(apellidos);
                escribeFichero.writeChars(dni);
                escribeFichero.writeInt(vehiculo);
                escribeFichero.writeInt(clases);
            }

        } catch (IOException e) {
            System.out.println("Error al escribir el fichero.");
            sc.close();
            return;
        }

        // Mostrar el contenido del fichero
        System.out.println("\nContenido de practicas.dat:");

        try (RandomAccessFile leeFichero = new RandomAccessFile(
                RUTA_FICHERO + NOMBRE_FICHERO, "r")) {

            while (leeFichero.getFilePointer()
                    < leeFichero.length()) {

                // Leer nombre
                char[] nombre = new char[10];

                for (int i = 0; i < nombre.length; i++) {
                    nombre[i] = leeFichero.readChar();
                }

                // Leer apellidos
                char[] apellidos = new char[35];

                for (int i = 0; i < apellidos.length; i++) {
                    apellidos[i] = leeFichero.readChar();
                }

                // Leer DNI
                char[] dni = new char[9];

                for (int i = 0; i < dni.length; i++) {
                    dni[i] = leeFichero.readChar();
                }

                int vehiculo = leeFichero.readInt();
                int clases = leeFichero.readInt();

                System.out.printf(
                        "%s %s (%s) - Vehículo: %d - Clases: %d%n",
                        new String(nombre).trim(),
                        new String(apellidos).trim(),
                        new String(dni).trim(),
                        vehiculo,
                        clases);
            }

        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }

        sc.close();
    }
}