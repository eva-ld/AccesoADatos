package TodoFicheros.Ficheros.FicherosAccesoAleatorio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio5 {

    private static final String RUTA_FICHERO = "C:\\Users\\evalo\\Desktop\\Programación\\AccesoDatos\\src\\Ficheros\\";
    private static final String NOMBRE_FICHERO = "practicas.dat";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce la cantidad de alumnos a registrar: ");
        int nAlumnos = sc.nextInt();
        sc.nextLine(); // Limpiar buffer

        try (RandomAccessFile fichero = new RandomAccessFile(RUTA_FICHERO + NOMBRE_FICHERO, "rw")) {
            fichero.setLength(0); // Limpiar archivo si existía

            for (int i = 0; i < nAlumnos; i++) {
                System.out.println("\nAlumno " + (i + 1));
                System.out.print("Nombre (max 10 chars): ");
                String nombre = sc.nextLine();
                System.out.print("Apellidos (max 35 chars): ");
                String apellidos = sc.nextLine();
                System.out.print("DNI (9 chars): ");
                String dni = sc.nextLine();
                System.out.print("Vehículo de prácticas (entero): ");
                int vehiculo = sc.nextInt();
                System.out.print("Número de clases de prácticas (entero): ");
                int clases = sc.nextInt();
                sc.nextLine(); // Limpiar buffer

                StringBuffer sbNombre = new StringBuffer(nombre);
                sbNombre.setLength(10);
                fichero.writeChars(sbNombre.toString());

                StringBuffer sbApellidos = new StringBuffer(apellidos);
                sbApellidos.setLength(35);
                fichero.writeChars(sbApellidos.toString());

                StringBuffer sbDni = new StringBuffer(dni);
                sbDni.setLength(9);
                fichero.writeChars(sbDni.toString());

                fichero.writeInt(vehiculo);
                fichero.writeInt(clases);
            }
            System.out.println("\nAlumnos guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero: " + e.getMessage());
        }

        // Mostrar contenido del fichero
        System.out.println("\nCONTENIDO DEL FICHERO");
        try (RandomAccessFile fichero = new RandomAccessFile(RUTA_FICHERO + NOMBRE_FICHERO, "r")) {
            while (fichero.getFilePointer() < fichero.length()) {
                char[] nom = new char[10];
                for (int i = 0; i < 10; i++) nom[i] = fichero.readChar();

                char[] ape = new char[35];
                for (int i = 0; i < 35; i++) ape[i] = fichero.readChar();

                char[] d = new char[9];
                for (int i = 0; i < 9; i++) d[i] = fichero.readChar();

                int vehiculo = fichero.readInt();
                int clases = fichero.readInt();

                System.out.println("Nombre: " + new String(nom).trim() + " " + new String(ape).trim() +
                        " | DNI: " + new String(d).trim() +
                        " | Vehículo: " + vehiculo +
                        " | Clases: " + clases);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
        sc.close();
    }
}