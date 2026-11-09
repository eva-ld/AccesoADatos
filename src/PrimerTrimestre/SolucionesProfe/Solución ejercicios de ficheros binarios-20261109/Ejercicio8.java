import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio8 {
    // Directorio donde se almacenan los ficheros
    private static final String RUTA_FICHERO = "datos/";

    // Nombre de los ficheros
    private static final String NOMBRE_TEMPERATURAS = "temperaturas.txt";
    private static final String NOMBRE_SEPTEMP = "Septemp.dat";

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("Introduzca el día de septiembre: ");
        int diaBuscado = teclado.nextInt();

        // Crear el fichero binario con las temperaturas del día indicado
        try ( BufferedReader leeFichero = new BufferedReader(new FileReader(RUTA_FICHERO + NOMBRE_TEMPERATURAS));
              DataOutputStream escribeFichero = new DataOutputStream(new FileOutputStream(RUTA_FICHERO + NOMBRE_SEPTEMP))) {

            String linea;

            // Leer el fichero de texto línea a línea
            while ((linea = leeFichero.readLine()) != null) {

                // Ejemplo:
                // Día 01, Hora 00:00, Temperatura 33°C

                String[] datos = linea.split(",");

                String textoDia = datos[0].trim();
                String textoHora = datos[1].trim();
                String textoTemperatura = datos[2].trim();

                // Obtener el día
                int dia = Integer.parseInt(textoDia.replace("Día ", ""));

                // Obtener la hora
                String hora = textoHora.replace("Hora ", "");

                // Obtener la temperatura
                double temperatura = Double.parseDouble( textoTemperatura.replace("Temperatura ", "")
                                .replace("°C", ""));

                // Guardar solamente las temperaturas del día indicado
                if (dia == diaBuscado) {
                    escribeFichero.writeInt(dia);
                    escribeFichero.writeUTF(hora);
                    escribeFichero.writeDouble(temperatura);
                }
            }

            System.out.println(
                    "Datos guardados en '" + NOMBRE_SEPTEMP + "'.");

        } catch (IOException e) {
            System.out.println("Error al procesar los ficheros.");
        }

        teclado.close();
    }
}