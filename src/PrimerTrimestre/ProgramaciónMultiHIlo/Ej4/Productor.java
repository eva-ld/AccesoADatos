package PrimerTrimestre.ProgramaciónMultiHIlo.Ej4;

import java.util.Random;

public class Productor implements Runnable {
    private AlmacenCola cola;

    public Productor(AlmacenCola cola) {
        this.cola = cola;
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            for (int i = 1; i <= 10; i++) {
                int numero = random.nextInt(100); // Genera un número aleatorio
                cola.producir(numero);
                Thread.sleep(random.nextInt(400) + 100); // Tiempo de espera al azar
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
