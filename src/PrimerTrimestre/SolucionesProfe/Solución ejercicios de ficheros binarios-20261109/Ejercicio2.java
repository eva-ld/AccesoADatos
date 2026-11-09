import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio2 {
    // Directorio donde se almacena el fichero (ruta relativa al proyecto)
    private static final String RUTA_FICHERO = "datos/";
    // Nombre del fichero binario
    private static final String NOMBRE_FICHERO = "vehiculos.dat";

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        // Solicitar el número de vehículos
        System.out.println("Número de vehículos: ");
        int numero = Integer.parseInt(teclado.nextLine());

        // Escribir los datos de los vehículos
        try (DataOutputStream escribeFichero = new DataOutputStream(new FileOutputStream(RUTA_FICHERO + NOMBRE_FICHERO, true))) {

            int i = 1;

            while (i <= numero) {
                // Pedir información del vehículo por teclado
                System.out.println("Matrícula vehículo " + i + ": ");
                String matricula = teclado.nextLine();

                System.out.println("Marca vehículo " + i + ": ");
                String marca = teclado.nextLine();

                System.out.println("Depósito vehículo " + i + ": ");
                double deposito = Double.parseDouble(teclado.nextLine());

                System.out.println("Modelo vehículo " + i + ": ");
                String modelo = teclado.nextLine();

                // Escribir los datos en el fichero
                escribeFichero.writeUTF(matricula);
                escribeFichero.writeUTF(marca);
                escribeFichero.writeDouble(deposito);
                escribeFichero.writeUTF(modelo);

                i++;
            }

        } catch (IOException e) {
            System.out.println("Error al escribir el fichero.");
        }

        // Leer los datos almacenados
        System.out.println("Inicio del fichero");

        try (DataInputStream leeFichero = new DataInputStream(
                new FileInputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {

            while (true)  { // Finaliza cuando se alcanza el final del fichero y se produce una EOFException.
                String matricula = leeFichero.readUTF();
                String marca = leeFichero.readUTF();
                double deposito = leeFichero.readDouble();
                String modelo = leeFichero.readUTF();

                // Mostrar los datos en una sola línea
                System.out.println( matricula + " | " + marca + " | " + deposito + " | " + modelo);
            }

        } catch (EOFException e) {
            System.out.println("Fin de fichero.");
        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }

        teclado.close();
    }
}