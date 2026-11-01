package TodosEjercicios.Ejercicio4_3.EjerciciosAmpliación;

// Crea un programa que pida al usuario el nombre de un fichero de texto y muestre su contenido en pantalla.
//  Tras cada 24 líneas, deberá hacer una pausa hasta que el usuario pulse Intro.

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class EjA2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce el nombre del fichero: \n");
        String nombreFichero = sc.nextLine();

        int contador = 0;

        try{
            BufferedReader br = new BufferedReader(new FileReader(nombreFichero)); // Lee el archivo que el usuario le escribe
            String linea;
            while((linea = br.readLine()) != null){ // Añade +1 al contador si no es nulo
                System.out.println(linea);
                contador++;

                if(contador == 24){ // ¡EL IF VA DENTRO DEL WHILE XQ SINO NO PARAA!
                    System.out.println("Pulse intro para continuar");
                    sc.nextLine();
                    contador = 0;

                  }

            }

        } catch (IOException e) {
            System.out.println("Error al leer el fichero");
        }
    }
}
