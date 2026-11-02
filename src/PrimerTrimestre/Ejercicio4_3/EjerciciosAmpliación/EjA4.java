package PrimerTrimestre.Ejercicio4_3.EjerciciosAmpliación;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class EjA4 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce el nombre del fichero");
        String nombre = sc.nextLine();

        int contador = 0;

        try{
            BufferedReader br = new BufferedReader(new FileReader(nombre));

            while(br.readLine() != null){
                contador++;
            }
            br.close();
        }catch (IOException e){
            System.out.println("Error al leer el nombre del fichero");
            sc.close();
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
            sc.close();
            return;
        }

        System.out.println(" === FICHERO ESCRITO EN EL ORDEN INVERSO ===");

        // Para mostrarlo en el orden inverso la i debe de tener el valor de las lineas totales -1 ya que las posiciones en al array empiezan en 0, y por ello vamos restando de 1 en 1 hasta que su valor sea 0

        for(int i = lineas.length - 1; i >=0; i--){
            System.out.println(lineas[i]);
        }
    }
}
