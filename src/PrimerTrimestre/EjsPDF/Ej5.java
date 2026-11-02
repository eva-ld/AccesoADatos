package PrimerTrimestre.EjsPDF;

import java.io.*;

public class Ej5 {
    public static void main(String[] args) {
        File fichero = new File("datosbeca.bin");

        // Compruebo primero si el archivo existe para no dar un error raro
        if (!fichero.exists()) {
            System.out.println("El fichero datosbeca.bin no existe.");
            return;
        }

        System.out.println("=== RESULTADO DE CUANTÍAS DE BECA ===");
        try (DataInputStream dis = new DataInputStream(new FileInputStream(fichero))) {
            while (dis.available() > 0) {
                String nombre = dis.readUTF();
                char sexo = dis.readChar();
                int edad = dis.readInt();
                int suspensos = dis.readInt();
                String residencia = dis.readUTF();
                double ingresos = dis.readDouble();
                String tieneBeca = dis.readUTF();

                // Si tiene 2 o más suspensos o la beca está marcada como NO, directamente no se calcula
                if (suspensos >= 2 || tieneBeca.equalsIgnoreCase("NO")) {
                    continue;
                }

                double totalBeca = 1500.0; //  base fija

                // sumamos 500€ si los ingresos de la casa no superan los 12.000€
                if (ingresos <= 12000.0) {
                    totalBeca += 500.0;
                }

                // más dinero por ser menor de 23 años
                if (edad < 23) {
                    totalBeca += 200.0;
                }

                // comprobamos las notas del curso pasado
                if (suspensos == 0) {
                    totalBeca += 500.0;
                } else if (suspensos == 1) {
                    totalBeca += 200.0;
                }

                // Si dice que no a la residencia familiar es significa que está de alquiler
                if (residencia.equalsIgnoreCase("NO")) { // el ignorecase es para que de igual si escribe en mayusc o minus
                    totalBeca += 1000.0;
                }

                System.out.printf("Becario: %-25s | Cuantía Total: %.2f €%n", nombre, totalBeca);
            }
        } catch (IOException e) {
            System.err.println("Error al procesar el fichero: " + e.getMessage());
        }
    }
}
