package PrimerTrimestre.ProgramaciónMultiHIlo.Ej6;

import java.util.Random;

public class Filosofo implements Runnable {
    private final int id;
    private final Mesa mesa;
    private final Random random;

    public Filosofo(int id, Mesa mesa) {
        this.id = id;
        this.mesa = mesa;
        this.random = new Random();
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Pensando
                System.out.println("El filósofo número " + id + " está pensando.");
                Thread.sleep(random.nextInt(4000) + 1000); // Tiempo al azar entre 1000 y 5000 ms

                // Intenta comer
                mesa.cogerTenedores(id);
                System.out.println("Filósofo número " + id + " comiendo.");
                Thread.sleep(random.nextInt(2000) + 1000); // Tiempo comiendo

                // Suelta los cubiertos
                mesa.soltarTenedores(id);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}