package PrimerTrimestre.EjsPDF;

import java.io.*;
import java.util.Scanner;

public class Ej6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File fichero = new File("datospersonas.dat");

        System.out.print("¿Cuántas personas vas a registrar?: ");
        int n = sc.nextInt();
        sc.nextLine();

        // si abrimos el fichero en modo append se van  acumulando los registros de personas
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fichero, true))) {
            for (int i = 0; i < n; i++) {
                System.out.println("\nPersona #" + (i + 1));
                System.out.print("Nombre: "); String nombre = sc.nextLine();
                System.out.print("Apellidos: "); String apellidos = sc.nextLine();
                System.out.print("Edad: "); int edad = sc.nextInt(); sc.nextLine();
                System.out.print("Teléfono: "); String telefono = sc.nextLine();
                System.out.print("Email: "); String email = sc.nextLine();
                System.out.print("Ciudad: "); String ciudad = sc.nextLine();
                System.out.print("Nacionalidad: "); String nacionalidad = sc.nextLine();
                System.out.print("Profesión: "); String profesion = sc.nextLine();

                dos.writeUTF(nombre);
                dos.writeUTF(apellidos);
                dos.writeInt(edad);
                dos.writeUTF(telefono);
                dos.writeUTF(email);
                dos.writeUTF(ciudad);
                dos.writeUTF(nacionalidad);
                dos.writeUTF(profesion);
            }
        } catch (IOException e) {
            System.err.println("Error al escribir: " + e.getMessage());
        }

        System.out.println("\n=== CONTENIDO DE datospersonas.dat ===");
        try (DataInputStream dis = new DataInputStream(new FileInputStream(fichero))) {
            while (dis.available() > 0) {
                System.out.printf("%s %s | %d años | Tel: %s | Email: %s | %s (%s) | %s%n",
                        dis.readUTF(), dis.readUTF(), dis.readInt(), dis.readUTF(),
                        dis.readUTF(), dis.readUTF(), dis.readUTF(), dis.readUTF());
            }
        } catch (IOException e) {
            System.err.println("Error al leer: " + e.getMessage());
        }
    }
}
