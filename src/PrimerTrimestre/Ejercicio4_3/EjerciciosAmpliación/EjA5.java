package PrimerTrimestre.Ejercicio4_3.EjerciciosAmpliación;

import java.io.*;
import java.util.Scanner;

public class EjA5 {
    public static void main(String[] args) throws IOException {

        Scanner s = new Scanner(System.in);

        System.out.println("Introduce el nombre del fichero");

        String nombre = s.nextLine();

        int contador = 0;

        try{
            BufferedReader br = new BufferedReader(new FileReader(nombre));

            while(br.readLine() != null){
                contador++;
            }
            br.close();
        }catch (IOException e){
            System.out.println("Error al leer el nombre del fichero");
            s.close();
            return;
        }

        String[] lineas = new String[contador]; // Creamos el array

        try {
            BufferedReader br = new BufferedReader(new FileReader(nombre)); // Leemos el archivo y guardamos las lineas en el array

            String linea;
            int posicion = 0;

            while ((linea = br.readLine()) != null) {
                lineas[posicion] = linea;
                posicion++;
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
            s.close();
            return;
        }

        System.out.println(" === FICHERO ESCRITO EN EL ORDEN INVERSO ===");

        try{

        BufferedWriter bw = new BufferedWriter(new FileWriter("Salida.txt"));

            for (int i = lineas.length - 1; i >= 0; i--) {
                bw.write(lineas[i]);
                bw.newLine();
            }

            bw.close();

            System.out.println("Se ha creado salida.txt correctamente.");


        }catch (IOException e){
            System.out.println("Error al escribir fichero.");
        }

        System.out.println("¿Desea leer el fichero salida? Escriba si/no:");
        String respuesta = s.nextLine();

        if (respuesta.toLowerCase().equals("si")) {

            try {
                BufferedReader br = new BufferedReader(new FileReader("salida.txt"));

                String linea;

                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }

                br.close();

            } catch (IOException e) {
                System.out.println("Error al leer el fichero.");
            }
        }

        s.close();
    }
}
