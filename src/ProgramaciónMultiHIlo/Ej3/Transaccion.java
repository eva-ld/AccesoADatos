package ProgramaciónMultiHIlo.Ej3;

public class Transaccion implements Runnable {
    private final CuentaBancaria cuenta;
    private final double cantidad;

    public Transaccion(CuentaBancaria cuenta, double cantidad) {
        this.cuenta = cuenta;
        this.cantidad = cantidad;
    }

    @Override
    public void run() {
        // Ejecuta el movimiento (ingreso si es positivo, retirada si es negativo)
        cuenta.hacerTransaccion(cantidad);
    }
}
