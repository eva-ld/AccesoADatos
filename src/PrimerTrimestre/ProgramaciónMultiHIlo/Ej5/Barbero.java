package PrimerTrimestre.ProgramaciónMultiHIlo.Ej5;

public class Barbero implements Runnable {
    private Peluqueria peluqueria;
    private int idBarbero;

    public Barbero(Peluqueria peluqueria, int idBarbero) {
        this.peluqueria = peluqueria;
        this.idBarbero = idBarbero;
    }

    @Override
    public void run() {
        try {
            while (true) {
                peluqueria.siguienteCliente(idBarbero);
                Thread.sleep(2500); // Simula el tiempo que dura el afeitado
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
