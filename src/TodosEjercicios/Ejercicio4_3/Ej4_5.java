package TodosEjercicios.Ejercicio4_3;

import java.io.*;
import java.util.Scanner;

public class Ej4_5 {
    public static void main(String[] args) {

        int contador = 0;

        // Contador de líneas totales

        System.out.println("=== CONTADOR DE LINEAS TOTALES ===");

        try {

            BufferedReader entrada = new BufferedReader(new FileReader("datos.txt"));
            String linea;

            while ((linea = entrada.readLine()) != null) {
                contador++;
            }

            entrada.close();

            System.out.println("El fichero contiene " + contador + " líneas.");

        } catch (IOException e) {
            System.out.println("Error al leer el fichero");
        }


        // Escribir una palabra y buscar cuántas líneas del archivo la contienen

        System.out.println("=== CONTADOR DE PALABRAS ===");

        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce una palabra: ");
        String palabra = sc.nextLine();

        int contadorPalabra = 0;

        try {

            BufferedReader br = new BufferedReader(new FileReader("datos.txt"));

            String linea;

            while ((linea = br.readLine()) != null) {

                if (linea.toLowerCase().contains(palabra.toLowerCase())) {
                    contadorPalabra++;
                }
            }

            br.close();
            System.out.println("La palabra aparece en " + contadorPalabra + " líneas.");

        } catch (IOException e) {
            System.out.println("Error al leer el fichero");
        }


        // Copiar el contenido de datos.txt en copia.txt

        System.out.println("=== COPIA DE CONTENIDO A COPIA.TXT ===");

        try {

            BufferedReader br = new BufferedReader(new FileReader("datos.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("copia.txt"));

            String linea;

            while ((linea = br.readLine()) != null) {
                bw.write(linea);
                bw.newLine();
            }

            br.close();
            bw.close();

            System.out.println("Se ha creado copia.txt correctamente.");

        } catch (IOException e) {

            System.out.println("Error al copiar el fichero.");

        }


        // Copiar solo las líneas que tienen texto

        System.out.println("=== COPIAR LINEAS ESCRITAS ===");

        try {

            BufferedReader br = new BufferedReader(new FileReader("datos.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("copia.txt"));

            String linea;

            while ((linea = br.readLine()) != null) {

                if (!linea.trim().isEmpty()) {
                    bw.write(linea);
                    bw.newLine();
                }
            }

            br.close();
            bw.close();

            System.out.println("Se ha creado una copia.txt sin líneas vacías.");

        } catch (IOException e) {

            System.out.println("Error al copiar el fichero.");

        }

        sc.close();
    }
}