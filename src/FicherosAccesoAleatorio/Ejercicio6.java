package FicherosAccesoAleatorio;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Ejercicio6 {

    private static final String RUTA_FICHERO = "C:\\Users\\evalo\\Desktop\\Programación\\AccesoDatos\\src\\Ficheros\\";
    private static final double PRECIO_CLASE = 45.7;

    public static void main(String[] args) {
        try (RandomAccessFile fPracticas = new RandomAccessFile(RUTA_FICHERO + "practicas.dat", "r");
             RandomAccessFile fPrecio = new RandomAccessFile(RUTA_FICHERO + "precio.dat", "rw")) {

            fPrecio.setLength(0); // Limpiar fichero destino

            while (fPracticas.getFilePointer() < fPracticas.length()) {
                char[] nom = new char[10];
                for (int i = 0; i < 10; i++) nom[i] = fPracticas.readChar();

                char[] ape = new char[35];
                for (int i = 0; i < 35; i++) ape[i] = fPracticas.readChar();

                char[] d = new char[9];
                for (int i = 0; i < 9; i++) d[i] = fPracticas.readChar();

                int vehiculo = fPracticas.readInt();
                int clases = fPracticas.readInt();

                double totalPagar = clases * PRECIO_CLASE;

                StringBuffer sbNombre = new StringBuffer(new String(nom).trim());
                sbNombre.setLength(10);
                fPrecio.writeChars(sbNombre.toString());

                StringBuffer sbDni = new StringBuffer(new String(d).trim());
                sbDni.setLength(9);
                fPrecio.writeChars(sbDni.toString());

                fPrecio.writeInt(clases);
                fPrecio.writeDouble(totalPagar);
            }
            System.out.println("Fichero 'precio.dat' generado correctamente.");
        } catch (IOException e) {
            System.out.println("Error en el proceso: " + e.getMessage());
        }
    }
}