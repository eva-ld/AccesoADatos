package TodoFicheros.Ficheros.FicherosAccesoAleatorio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercico7 {

    private static final String RUTA_FICHERO = "C:\\Users\\evalo\\Desktop\\Programación\\AccesoDatos\\src\\Ficheros\\";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el porcentaje de descuento a aplicar: ");
        double descuento = sc.nextDouble();

        try (RandomAccessFile fPrecio = new RandomAccessFile(RUTA_FICHERO + "precio.dat", "r");
             RandomAccessFile fDescuento = new RandomAccessFile(RUTA_FICHERO + "descuento.dat", "rw")) {

            fDescuento.setLength(0); // Limpiar fichero

            while (fPrecio.getFilePointer() < fPrecio.length()) {
                char[] nom = new char[10];
                for (int i = 0; i < 10; i++) fPrecio.readChar();

                char[] d = new char[9];
                for (int i = 0; i < 9; i++) d[i] = fPrecio.readChar();

                int clases = fPrecio.readInt();
                double cantidadAnterior = fPrecio.readDouble();

                double cantidadConDescuento = cantidadAnterior - (cantidadAnterior * (descuento / 100.0));

                StringBuffer sbDni = new StringBuffer(new String(d).trim());
                sbDni.setLength(9);
                fDescuento.writeChars(sbDni.toString());
                fDescuento.writeDouble(cantidadAnterior);
                fDescuento.writeDouble(cantidadConDescuento);
            }
            System.out.println("Fichero 'descuento.dat' creado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al procesar los descuentos");
        }

        // Mostrar el contenido final por pantalla
        System.out.println("\nCONTENIDO DEL FICHERO");
        try (RandomAccessFile fichero = new RandomAccessFile(RUTA_FICHERO + "descuento.dat", "r")) {
            while (fichero.getFilePointer() < fichero.length()) {
                char[] d = new char[9];
                for (int i = 0; i < 9; i++) d[i] = fichero.readChar();

                double ant = fichero.readDouble();
                double desc = fichero.readDouble();

                System.out.println("DNI: " + new String(d).trim() +
                        " | Precio Anterior: " + String.format("%.2f", ant) + " €" +
                        " | Precio con Descuento: " + String.format("%.2f", desc) + " €");
                //Uso el %.2f para que cuando se muestre por pantalla no aparezcan tantos decimales y tan solo se ouedan ver 23.
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero");
        }
        sc.close();
    }
}