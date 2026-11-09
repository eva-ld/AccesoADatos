import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio3 {
    // Directorio donde se almacena el fichero (ruta relativa al proyecto)
    private static final String RUTA_FICHERO = "datos/";
    // Nombre del fichero binario
    private static final String NOMBRE_FICHERO = "datosbeca.bin";

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("Nombre y apellido: ");
        String nombre = teclado.nextLine();

        System.out.print("Sexo (H/M): ");
        String sexo = teclado.nextLine();

        System.out.print("Edad (20-60): ");
        int edad = teclado.nextInt();

        System.out.print("Número de suspensos (0-4): ");
        int suspensos = teclado.nextInt();
        teclado.nextLine(); // Limpiar buffer

        System.out.print("¿Residencia familiar? (SI/NO): ");
        String residencia = teclado.nextLine();

        System.out.print("Ingresos anuales de la familia: ");
        double ingresos = teclado.nextDouble();
        teclado.nextLine(); // Limpiar buffer

        System.out.print("¿Tiene beca? (SI/NO): ");
        String beca = teclado.nextLine();

        // Guardar los datos en el fichero binario
        try (DataOutputStream escribeFichero = new DataOutputStream(
                new FileOutputStream(RUTA_FICHERO + NOMBRE_FICHERO, true))) {

            escribeFichero.writeUTF(nombre);
            escribeFichero.writeUTF(sexo);
            escribeFichero.writeInt(edad);
            escribeFichero.writeInt(suspensos);
            escribeFichero.writeUTF(residencia);
            escribeFichero.writeDouble(ingresos);
            escribeFichero.writeUTF(beca);

            System.out.println("Datos guardados correctamente.");

        } catch (IOException e) {
            System.out.println("Error al escribir el fichero.");
        }

        teclado.close();
    }
}