package PrimerTrimestre.Binario;
import java.io.*;
import java.util.Scanner;

public class Ejercicio2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Cuantos vehiculos vas a introducir? ");
        int n = Integer.parseInt(sc.nextLine());

        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream("vehiculos.bin", true));
            for (int i = 0; i < n; i++) {
                System.out.println("Vehiculo " + (i + 1) + ":");
                System.out.print("Matricula: ");
                dos.writeUTF(sc.nextLine());
                System.out.print("Marca: ");
                dos.writeUTF(sc.nextLine());
                System.out.print("Tamaño deposito (double): ");
                dos.writeDouble(Double.parseDouble(sc.nextLine()));
                System.out.print("Modelo: ");
                dos.writeUTF(sc.nextLine());
            }
            dos.close();

            DataInputStream dis = new DataInputStream(new FileInputStream("vehiculos.bin"));
            System.out.println("\nDatos almacenados:");
            try {
                while (true) {
                    String matricula = dis.readUTF();
                    String marca = dis.readUTF();
                    double deposito = dis.readDouble();
                    String modelo = dis.readUTF();
                    System.out.println(matricula + " - " + marca + " - " + modelo + " - " + deposito + "L");
                }
            } catch (EOFException e) {
                System.out.println("Fin.");
            }
            dis.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
