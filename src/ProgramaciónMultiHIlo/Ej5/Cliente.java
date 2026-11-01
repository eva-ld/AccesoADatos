package ProgramaciónMultiHIlo.Ej5;

public class Cliente implements Runnable {
    private Peluqueria peluqueria;
    private int idCliente;

    public Cliente(Peluqueria peluqueria, int idCliente) {
        this.peluqueria = peluqueria;
        this.idCliente = idCliente;
    }

    @Override
    public void run() {
        try {
            peluqueria.entrarCliente(idCliente);
            // Si no encuentra silla, termina su ejecución automáticamente
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}