package PrimerTrimestre.ProgramaciónMultiHIlo.Ej6;

import java.util.concurrent.Semaphore;

public class Mesa {
    private final boolean[] tenedores;
    private final Semaphore limitadorComensales; // Limita a máximo 2 filósofos comiendo a la vez

    public Mesa(int numFilosofos) {
        tenedores = new boolean[numFilosofos]; // false significa que está libre
        limitadorComensales = new Semaphore(2, true); // Máximo 2 comensales simultáneos y evita inanición (fairness)
    }

    public void cogerTenedores(int id) throws InterruptedException {
        // Limitamos a que solo 2 filósofos intenten comer a la vez en la mesa
        limitadorComensales.acquire();

        synchronized (this) {
            int izq = id;
            int der = (id + 1) % tenedores.length;

            // Espera si alguno de los dos tenedores está ocupado
            while (tenedores[izq] || tenedores[der]) {
                wait();
            }

            // Ocupa los tenedores
            tenedores[izq] = true;
            tenedores[der] = true;
        }
    }

    public synchronized void soltarTenedores(int id) {
        int izq = id;
        int der = (id + 1) % tenedores.length;

        tenedores[izq] = false;
        tenedores[der] = false;

        notifyAll(); // Despierta a los filósofos que esperaban
        limitadorComensales.release(); // Libera plaza en la mesa
    }
}