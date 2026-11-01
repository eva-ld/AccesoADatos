package ProgramaciónMultiHIlo.Ej4;

import java.util.Random;

public class Consumidor implements Runnable {
    private AlmacenCola cola;

    public Consumidor(AlmacenCola cola) {
        this.cola = cola;
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            for (int i = 1; i <= 10; i++) {
                cola.consumir();
                Thread.sleep(random.nextInt(600) + 200); // Tiempo de espera al azar
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
