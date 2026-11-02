package PrimerTrimestre.EjsPDF;

import java.io.*;
import java.util.Scanner;

public class Ej4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File fichero = new File("datosbeca.bin");

        System.out.print("¿Cuántos becarios deseas introducir?: ");
        int n = sc.nextInt();
        sc.nextLine();

        // le paso true para no borrar los becarios que ya tenía guardados
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fichero, true))) {
            for (int i = 0; i < n; i++) {
                System.out.println("\n--- Becario #" + (i + 1) + " ---");
                System.out.print("Nombre y Apellidos: ");
                String nombre = sc.nextLine();
                System.out.print("Sexo (H/M): ");
                char sexo = sc.nextLine().toUpperCase().charAt(0);
                System.out.print("Edad (20-60): ");
                int edad = sc.nextInt();
                System.out.print("Número de suspensos (0-4): ");
                int suspensos = sc.nextInt();
                sc.nextLine();
                System.out.print("¿Residencia familiar? (SI/NO): ");
                String residencia = sc.nextLine().toUpperCase();
                System.out.print("Ingresos anuales familiares: ");
                double ingresos = sc.nextDouble();
                sc.nextLine();
                System.out.print("¿Tiene beca? (SI/NO): ");
                String tieneBeca = sc.nextLine().toUpperCase();

                dos.writeUTF(nombre);
                dos.writeChar(sexo);
                dos.writeInt(edad);
                dos.writeInt(suspensos);
                dos.writeUTF(residencia);
                dos.writeDouble(ingresos);
                dos.writeUTF(tieneBeca);
            }
        } catch (IOException e) {
            System.err.println("Error al escribir: " + e.getMessage());
        }

        // Muestro de golpe la lista completa de becarios
        System.out.println("\n=== REGISTRO DE BECARIOS ===");
        try (DataInputStream dis = new DataInputStream(new FileInputStream(fichero))) {
            while (dis.available() > 0) {
                String nombre = dis.readUTF();
                char sexo = dis.readChar();
                int edad = dis.readInt();
                int suspensos = dis.readInt();
                String residencia = dis.readUTF();
                double ingresos = dis.readDouble();
                String tieneBeca = dis.readUTF();

                System.out.printf("Nombre: %-20s | Sexo: %c | Edad: %d | Suspensos: %d | Res.Fam: %-2s | Ingresos: %.2f€ | Beca: %s%n",
                        nombre, sexo, edad, suspensos, residencia, ingresos, tieneBeca);
            }
        } catch (IOException e) {
            System.err.println("Error al leer: " + e.getMessage());
        }
    }
}