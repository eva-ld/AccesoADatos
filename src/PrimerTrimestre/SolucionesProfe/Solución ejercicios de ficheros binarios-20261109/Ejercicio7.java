import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ejercicio7 {
    // Directorio donde se encuentran los ficheros
    private static final String RUTA_FICHERO = "datos/";

    // Nombre del fichero original
    private static final String NOMBRE_FICHERO = "muchosdatos.bin";

    // Nombres de los ficheros de destino
    private static final String NOMBRE_MENORES = "menores.dat";
    private static final String NOMBRE_ADULTOS = "adultos.dat";
    private static final String NOMBRE_MAYORES = "mayores.dat";

    public static void main(String[] args) {

        // Leer el fichero original y crear los tres ficheros de destino
        try (  DataInputStream leeFichero = new DataInputStream(new FileInputStream(RUTA_FICHERO + NOMBRE_FICHERO));
               DataOutputStream menores = new DataOutputStream(new FileOutputStream(RUTA_FICHERO + NOMBRE_MENORES));
               DataOutputStream adultos = new DataOutputStream(new FileOutputStream(RUTA_FICHERO + NOMBRE_ADULTOS));
               DataOutputStream mayores = new DataOutputStream(new FileOutputStream(RUTA_FICHERO + NOMBRE_MAYORES))) {

            // Se leen registros hasta alcanzar el final del fichero
            while (true) {
                try {
                    String nombre = leeFichero.readUTF();
                    String apellidos = leeFichero.readUTF();
                    int edad = leeFichero.readInt();
                    String telefono = leeFichero.readUTF();
                    String email = leeFichero.readUTF();
                    String ciudad = leeFichero.readUTF();
                    String nacionalidad = leeFichero.readUTF();
                    String profesion = leeFichero.readUTF();

                    // Clasificar la persona según su edad
                    if (edad < 18) {
                        escribirPersona(menores, nombre, apellidos, edad,telefono, email, ciudad, nacionalidad, profesion);
                    } else if (edad <= 65) {
                        escribirPersona(adultos, nombre, apellidos, edad,telefono, email, ciudad,nacionalidad, profesion);
                    } else {
                        escribirPersona( mayores, nombre, apellidos, edad, telefono, email, ciudad,nacionalidad, profesion);
                    }

                } catch (EOFException e) {
                    // Fin del fichero: no quedan más registros
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Error al procesar los ficheros.");
        }

        // Mostrar el contenido de los tres ficheros creados
        System.out.println("\n--- MENORES ---");
        mostrarFichero(RUTA_FICHERO + NOMBRE_MENORES);

        System.out.println("\n--- ADULTOS ---");
        mostrarFichero(RUTA_FICHERO + NOMBRE_ADULTOS);

        System.out.println("\n--- MAYORES ---");
        mostrarFichero(RUTA_FICHERO + NOMBRE_MAYORES);
    }

    // Escribir los datos de una persona en un fichero binario
    private static void escribirPersona( DataOutputStream escribeFichero,
                                         String nombre, String apellidos,
                                         int edad, String telefono,String email, String ciudad,
                                         String nacionalidad,String profesion) throws IOException { // La excepción se delega al principal.

        escribeFichero.writeUTF(nombre);
        escribeFichero.writeUTF(apellidos);
        escribeFichero.writeInt(edad);
        escribeFichero.writeUTF(telefono);
        escribeFichero.writeUTF(email);
        escribeFichero.writeUTF(ciudad);
        escribeFichero.writeUTF(nacionalidad);
        escribeFichero.writeUTF(profesion);
    }

    // Mostrar el contenido de un fichero binario
    private static void mostrarFichero(String rutaFichero) {
        try (DataInputStream leeFichero = new DataInputStream(new FileInputStream(rutaFichero))) {
            int contador = 1;

            // Se leen registros hasta alcanzar el final del fichero
            while (true) {
                try {
                    String nombre = leeFichero.readUTF();
                    String apellidos = leeFichero.readUTF();
                    int edad = leeFichero.readInt();
                    String telefono = leeFichero.readUTF();
                    String email = leeFichero.readUTF();
                    String ciudad = leeFichero.readUTF();
                    String nacionalidad = leeFichero.readUTF();
                    String profesion = leeFichero.readUTF();

                    System.out.println("\nPersona " + contador);
                    System.out.println("Nombre: " + nombre);
                    System.out.println("Apellidos: " + apellidos);
                    System.out.println("Edad: " + edad);
                    System.out.println("Teléfono: " + telefono);
                    System.out.println("Email: " + email);
                    System.out.println("Ciudad: " + ciudad);
                    System.out.println("Nacionalidad: " + nacionalidad);
                    System.out.println("Profesión: " + profesion);

                    contador++;

                } catch (EOFException e) {
                    // Fin del fichero
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("El fichero está vacío o no existe.");
        }
    }
}