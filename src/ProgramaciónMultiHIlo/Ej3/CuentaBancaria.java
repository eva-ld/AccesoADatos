package ProgramaciónMultiHIlo.Ej3;


public class CuentaBancaria {
    private double saldo;

    // inicializa la cuenta con 100 euros
    public CuentaBancaria() {
        this.saldo = 100.0;
    }

    // Metodo para realizar ingresos o retiradas
    public synchronized void hacerTransaccion(double cantidad) {
        this.saldo += cantidad;
    }

    // Metodo para consultar el saldo final
    public synchronized double getSaldo() {
        return this.saldo;
    }
}
