package PrimerTrimestre.EjsPDF;

import java.io.*;
import java.util.Scanner;

public class Ej2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File fichero = new File("vehiculos.dat");

        System.out.print("¿Cuántos vehículos deseas introducir?: ");
        int n = sc.nextInt();
        sc.nextLine(); // Limpio el salto de línea del scanner

        // Uso append (true) para que los coches nuevos se añadan al final sin borrar los antiguos
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fichero, true))) {
            for (int i = 0; i < n; i++) {
                System.out.println("\nVehículo #" + (i + 1));
                System.out.print("Matrícula: ");
                String matricula = sc.nextLine();
                System.out.print("Marca: ");
                String marca = sc.nextLine();
                System.out.print("Modelo: ");
                String modelo = sc.nextLine();
                System.out.print("Tamaño del depósito (L): ");
                double deposito = sc.nextDouble();
                sc.nextLine();

                // Guardo los datos ordenados en tipos primitivos
                dos.writeUTF(matricula);
                dos.writeUTF(marca);
                dos.writeUTF(modelo);
                dos.writeDouble(deposito);
            }
        } catch (IOException e) {
            System.err.println("Error al escribir: " + e.getMessage());
        }

        // Muestro todos los vehículos guardados en el fichero
        System.out.println("\n--- Listado de Vehículos Registrados ---");
        try (DataInputStream dis = new DataInputStream(new FileInputStream(fichero))) {
            // Leo exactamente en el mismo orden en el que guardé los datos
            while (dis.available() > 0) {
                String mat = dis.readUTF();
                String mar = dis.readUTF();
                String mod = dis.readUTF();
                double dep = dis.readDouble();
                System.out.printf("Matrícula: %-8s | Marca: %-10s | Modelo: %-10s | Depósito: %.1f L%n", mat, mar, mod, dep);
            }
        } catch (IOException e) {
            System.err.println("Error al leer: " + e.getMessage());
        }
    }
}
