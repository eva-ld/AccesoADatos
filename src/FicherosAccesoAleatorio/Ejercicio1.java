package FicherosAccesoAleatorio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio1 {

    private static final String RUTA_FICHERO = "C:\\Users\\evalo\\Desktop\\Programación\\AccesoDatos\\src\\Ficheros\\";
    private static final String NOMBRE_FICHERO = "enteros.dat";

    public static void main(String[] args) {

        // 1. Escribir 6 veces el valor 1 y después 10 veces el valor 2 en "enteros.dat"
        // El rw nos permite leer y escribir en el fichero, si ponemos r solo sería lectura y no podríamos modificar
        // Si el fihcero no existe lo crea, y si existe escribe en el existente
        try (RandomAccessFile fichero = new RandomAccessFile(RUTA_FICHERO + NOMBRE_FICHERO, "rw")) {
            // Aseguramos limpiar el archivo si ya existía
            fichero.setLength(0);

            // Escribir seis veces el número 1
            for (int i = 0; i < 6; i++) {
                fichero.writeInt(1);
            }

            // Escribir diez veces el número 2
            for (int i = 0; i < 10; i++) {
                fichero.writeInt(2);
            }

            System.out.println("Archivo 'enteros.dat' creado y rellenado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero: " + e.getMessage());
            return;
        }

        System.out.println("CONTENIDO DEL FICHERO");
        mostrarContenidoFichero();

        int nuevoValor = -1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce un valor del 1 al 9");
        nuevoValor = sc.nextInt();

        while (nuevoValor < 0 || nuevoValor > 9) {
            System.out.println("Valor introducido NO válido\nIntroduce un valor del 1 al 9");
            nuevoValor = sc.nextInt();
        }
        try (RandomAccessFile fichero = new RandomAccessFile(RUTA_FICHERO + NOMBRE_FICHERO, "rw")) {
            while (fichero.getFilePointer() < fichero.length()) {
                long posicion = fichero.getFilePointer();
                int valor = fichero.readInt();
                if (valor == 2) {
                    fichero.seek(posicion);
                    fichero.writeInt(nuevoValor);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("CONTENIDO DEL FICHERO");
        mostrarContenidoFichero();
    }
    private static void mostrarContenidoFichero() {
        try (RandomAccessFile fichero = new RandomAccessFile(RUTA_FICHERO + NOMBRE_FICHERO, "r")) {
            // Lo abrimos en modo r ya que tan solo queremos ver como esta escrito, no queremos escribir
            System.out.print("[ ");
            while (fichero.getFilePointer() < fichero.length()) {
                int numero = fichero.readInt();
                System.out.print(numero + " ");
            }
            System.out.println("]");
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}
