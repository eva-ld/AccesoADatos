package TodosEjercicios.Ejercicio4_3.EjerciciosAmpliación;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class EjA3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre del fichero: \n");
        String nombreFichero = sc.nextLine();
        int contador = 0;

        try{
            BufferedReader br = new BufferedReader(new FileReader(nombreFichero));

            String linea;
            while((linea = br.readLine()) != null){
                contador++;
            }

            br.close();

            System.out.println("El fichero contiene " + contador + " líneas");

        } catch (IOException e) {
            System.out.println("Error al leer el fichero");
        }

        sc.close();

    }
}
