import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ejercicio10 {
    // Directorio donde se encuentra el fichero
    private static final String RUTA_FICHERO = "datos/";

    // Nombre del fichero de nóminas
    private static final String NOMBRE_FICHERO = "nominas.dat";

    // Fichero temporal para guardar las nóminas actualizadas
    private static final String NOMBRE_TEMPORAL = "nominas.tmp";

    public static void main(String[] args) {

        int empleadosBaja = 0;

        // Leer el fichero original y crear el fichero actualizado
        try (
                DataInputStream leeFichero = new DataInputStream(
                        new FileInputStream(
                                RUTA_FICHERO + NOMBRE_FICHERO));

                DataOutputStream escribeFichero = new DataOutputStream(
                        new FileOutputStream(
                                RUTA_FICHERO + NOMBRE_TEMPORAL))
        ) {

            // Se leen registros hasta alcanzar el final del fichero
            while (true) {

                try {
                    String nombre = leeFichero.readUTF();
                    int diasBaja = leeFichero.readInt();
                    double nomina = leeFichero.readDouble();

                    // 0 días de baja: aumento del 5 %
                    if (diasBaja == 0) {
                        nomina = nomina * 1.05;

                        // Entre 1 y 3 días: no se modifica
                    } else if (diasBaja <= 3) {

                        // Entre 4 y 10 días: reducción del 10 %
                    } else if (diasBaja <= 10) {
                        nomina = nomina * 0.90;

                        // Más de 10 días: eliminar empleado
                    } else {
                        empleadosBaja++;
                        continue;
                    }

                    // Guardar el empleado en el fichero temporal
                    escribeFichero.writeUTF(nombre);
                    escribeFichero.writeInt(diasBaja);
                    escribeFichero.writeDouble(nomina);

                } catch (EOFException e) {
                    // Fin del fichero
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Error al procesar el fichero.");
            return;
        }

        // Sustituir el fichero original por el actualizado
        try {
            Path ficheroOriginal = Path.of(
                    RUTA_FICHERO + NOMBRE_FICHERO);

            Path ficheroTemporal = Path.of(
                    RUTA_FICHERO + NOMBRE_TEMPORAL);

            Files.deleteIfExists(ficheroOriginal);
            Files.move(ficheroTemporal, ficheroOriginal);

        } catch (IOException e) {
            System.out.println("Error al actualizar el fichero.");
            return;
        }

        System.out.println(
                "Empleados dados de baja: " + empleadosBaja);

        // Mostrar el fichero actualizado
        mostrarFichero();
    }

    // Mostrar el contenido del fichero actualizado
    private static void mostrarFichero() {

        System.out.println("\n--- NÓMINAS ACTUALIZADAS ---");

        try (DataInputStream leeFichero = new DataInputStream(
                new FileInputStream(
                        RUTA_FICHERO + NOMBRE_FICHERO))) {

            // Se leen registros hasta alcanzar el final del fichero
            while (true) {

                try {
                    String nombre = leeFichero.readUTF();
                    int diasBaja = leeFichero.readInt();
                    double nomina = leeFichero.readDouble();

                    System.out.println(
                            "Nombre: " + nombre
                                    + " | Días de baja: " + diasBaja
                                    + " | Nómina: " + nomina + " €");

                } catch (EOFException e) {
                    // Fin del fichero
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println(
                    "Error al leer el fichero actualizado.");
        }
    }
}