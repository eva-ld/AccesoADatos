package PrimerTrimestre.ProgramaciónMultiHIlo.Ej5;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Constantes configurables para sillas y barberos
        final int SILLAS_ESPERA = 3;
        final int NUM_BARBEROS = 2;

        Peluqueria peluqueria = new Peluqueria(SILLAS_ESPERA);

        // Lanzar hilos de barberos
        for (int i = 1; i <= NUM_BARBEROS; i++) {
            new Thread(new Barbero(peluqueria, i)).start();
        }

        // Generar clientes continuamente de forma simulada
        for (int i = 1; i <= 10; i++) {
            new Thread(new Cliente(peluqueria, i)).start();
            Thread.sleep(800); // Tiempo de llegada entre cliente y cliente
        }
    }
}

