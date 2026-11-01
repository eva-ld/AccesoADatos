package ProgramaciónMultiHIlo.Ej4;

public class Main {
    public static void main(String[] args) {
        // Creamos una cola limitada con capacidad para 5 elementos
        AlmacenCola cola = new AlmacenCola(5);

        // Creamos varios hilos productores y consumidores
        Thread prod1 = new Thread(new Productor(cola), "Productor-1");
        Thread prod2 = new Thread(new Productor(cola), "Productor-2");
        Thread cons1 = new Thread(new Consumidor(cola), "Consumidor-1");
        Thread cons2 = new Thread(new Consumidor(cola), "Consumidor-2");

        // Iniciamos los hilos
        prod1.start();
        prod2.start();
        cons1.start();
        cons2.start();
    }
}