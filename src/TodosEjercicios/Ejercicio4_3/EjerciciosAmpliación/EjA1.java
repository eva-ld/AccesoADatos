package TodosEjercicios.Ejercicio4_3.EjerciciosAmpliación;

import java.io.*;
import java.util.Scanner;

// Crea un programa que pida frases al usuario y las guarde en un fichero de texto, cada frase en una línea.


public class EjA1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try{

            BufferedWriter Frase = new BufferedWriter(new FileWriter("Frase.txt"));

            System.out.println("=== GUARDAR FRASE ===");
            System.out.println("Escriba fin cuando haya terminado de escribir todas las frases");
            String frase;

            do {
                System.out.println("Escribe una frase");
                frase = sc.nextLine();

                if (!frase.equalsIgnoreCase("fin")) {
                    Frase.write(frase);
                    Frase.newLine();
                }

            } while (!frase.equalsIgnoreCase("fin"));

            Frase.close();

            System.out.println("=== FIN DE LA ESCRITURA ===");


        }catch (IOException e){
            System.out.println("Error al leer el fichero");
        }
        sc.close();
    }
}
