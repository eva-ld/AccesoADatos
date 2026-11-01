package ProgramaciónMultiHIlo.Ej4;

public class AlmacenCola {
    private int[] cola;
    private int head = 0;
    private int tail = 0;
    private int count = 0;
    private int capacidad;

    public AlmacenCola(int capacidad) {
        this.capacidad = capacidad;
        this.cola = new int[capacidad];
    }

    // Metodo sincronizado para producir (escribir en la cola)
    public synchronized void producir(int numero) throws InterruptedException {
        while (count == capacidad) {
            System.out.println(Thread.currentThread().getName() + " -> Cola llena. Esperando...");
            wait(); // Libera el monitor y espera a que haya espacio
        }
        cola[tail] = numero;
        tail = (tail + 1) % capacidad;
        count++;
        System.out.println(Thread.currentThread().getName() + " produjo: " + numero + " (Elementos en cola: " + count + ")");
        notifyAll(); // Despierta a los consumidores en espera
    }

    // Metodo sincronizado para consumir (leer de la cola)
    public synchronized int consumir() throws InterruptedException {
        while (count == 0) {
            System.out.println(Thread.currentThread().getName() + " -> Cola vacía. Esperando...");
            wait(); // Libera el monitor y espera a que haya elementos
        }
        int numero = cola[head];
        head = (head + 1) % capacidad;
        count--;
        System.out.println(Thread.currentThread().getName() + " consumió: " + numero + " (Elementos en cola: " + count + ")");
        notifyAll(); // Despierta a los productores en espera
        return numero;
    }
}
