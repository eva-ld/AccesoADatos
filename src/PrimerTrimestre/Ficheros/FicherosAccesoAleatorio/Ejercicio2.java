package PrimerTrimestre.Ficheros.FicherosAccesoAleatorio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio2 {

    private static final String RUTA_FICHERO = "C:\\Users\\evalo\\Desktop\\Programación\\AccesoDatos\\src\\Ficheros\\";
    private static final String NOMBRE_FICHERO = "enteros.dat";

    public static void main(String[] args) {

        // Escribir 6 veces el valor 1 y después 10 veces el valor 2 en "enteros.dat"
        // El rw nos permite leer y escribir en el fichero, si ponemos r solo sería lectura y no podríamos modificar
        // Si el fichero no existe lo crea, y si existe escribe en el existente
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

        // Pedir un número entre 0 y 9 por teclado
        int nuevoValor = -1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce un valor del 0 al 9 para añadir al final:");
        nuevoValor = sc.nextInt();

        while (nuevoValor < 0 || nuevoValor > 9) {
            System.out.println("Valor introducido NO válido\nIntroduce un valor del 0 al 9:");
            nuevoValor = sc.nextInt();
        }

        // 3. Solicitar la cantidad de veces que se va a repetir
        int cantidad = 0;
        System.out.println("Introduce cuántas veces quieres añadirlo:");
        cantidad = sc.nextInt();

        while (cantidad <= 0) {
            System.out.println("La cantidad debe ser mayor que 0.\nIntroduce cuántas veces quieres añadirlo:");
            cantidad = sc.nextInt();
        }

        // Abrimos el fichero para añadir los valores al final
        try (RandomAccessFile fichero = new RandomAccessFile(RUTA_FICHERO + NOMBRE_FICHERO, "rw")) {
            // Situamos el puntero al final del fichero para no sobrescribir lo anterior
            fichero.seek(fichero.length());

            // Escribimos el número tantas veces como nos ha pedido el usuario por pantalla
            for (int i = 0; i < cantidad; i++) {
                fichero.writeInt(nuevoValor);
            }
            System.out.println("Valores añadidos correctamente al final del fichero.");
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