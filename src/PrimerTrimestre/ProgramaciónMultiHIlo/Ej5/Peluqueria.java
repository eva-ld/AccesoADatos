package PrimerTrimestre.ProgramaciónMultiHIlo.Ej5;

import java.util.*; // IMPORTO * A LA VEZ


public class Peluqueria {
    private final int numSillas;
    private final Queue<Integer> salaEspera;
    private int clientesEsperando = 0;

    public Peluqueria(int numSillas) {
        this.numSillas = numSillas;
        this.salaEspera = new LinkedList<>();
    }

    public synchronized boolean entrarCliente(int idCliente) throws InterruptedException {
        if (clientesEsperando < numSillas) {
            clientesEsperando++;
            salaEspera.add(idCliente);
            System.out.println("Cliente " + idCliente + " llega y se sienta. Sillas ocupadas: " + clientesEsperando);
            notify(); // Despierta al barbero si está durmiendo
            return true;
        } else {
            System.out.println("Cliente " + idCliente + " llega, no hay sillas libres y se marcha.");
            return false;
        }
    }

    public synchronized void siguienteCliente(int idBarbero) throws InterruptedException {
        while (clientesEsperando == 0) {
            System.out.println("Barbero " + idBarbero + " no tiene clientes y se va a dormir...");
            wait(); // El barbero duerme hasta que llegue un cliente
        }
        clientesEsperando--;
        int idCliente = salaEspera.poll();
        System.out.println("Barbero " + idBarbero + " está afeitando al cliente " + idCliente + ". Sillas ocupadas: " + clientesEsperando);
    }
}