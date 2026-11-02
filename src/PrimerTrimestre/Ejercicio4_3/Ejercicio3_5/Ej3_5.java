package PrimerTrimestre.Ejercicio4_3.Ejercicio3_5;

import java.io.File;
import java.io.IOException;

public class Ej3_5 {
    public static void main(String[] args) {

        System.out.println("=== CREAR DIRECTORIO COPIAS ===");
        // Crear directorio copias en la carpeta de proyecto
        File directorioCopias = new File("copias");

        if (directorioCopias.mkdir()) {
            System.out.println("Directorio \"copias\" creado correctamente");
        } else if (directorioCopias.exists()) {
            System.out.println("El directorio \"copias\" ya existe");
        } else {
            System.out.println("No se ha podido crear el directorio \"copias\"");
        }

        System.out.println("\n === CREAR FICHERO CONFIG.TXT DENTRO DE COPIAS ===");


        // Crear fichero "config.txt" dentro del directorio "copias"
        File ficheroConfig = new File(directorioCopias, "config.txt");

        try {
            if (ficheroConfig.createNewFile()) {
                System.out.println("Fichero 'config.txt' creado correctamente.");
            } else {
                System.out.println("El fichero 'config.txt' ya existe.");
            }
        } catch (IOException e) {
            System.out.println("Error de E/S al intentar crear el fichero: " + e.getMessage());
        }

        // Mostrar el contenido del directorio "copias"

        System.out.println("\n=== CONTENIDO DENTRO DE COPIAS ===");
        File[] elementos = directorioCopias.listFiles();

        if  (elementos != null && elementos.length > 0) {
            for (File file : elementos) {
                if (file.isDirectory()) {
                    System.out.println("Directorio: " + file.getName());
                }
                else if (file.isFile()) {
                    System.out.println("El fichero: " + file.getName());
                }
            }
        } else  {
            System.out.println("DIrectorio vacio");
        }

        // Eliminar fichero config

        System.out.println("\n=== Eliminar fichero y comprobaciones ===");
        if (ficheroConfig.delete()) {
            System.out.println("Fichero 'config.txt' eliminado correctamente");
        } else  {
            System.out.println("El fichero 'config.txt' no se ha podido borrar o no existe");
        }

        // ¿Qué pasa si borramos copias?
        if (directorioCopias.delete()) {
            System.out.println("El directorio 'copias' eliminado correctamente");
        } else {
            System.out.println("No se puede borrar el directorio 'Copias' o no existía");
        }

    }

}
