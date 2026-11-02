package PrimerTrimestre.ProgramaciónMultiHIlo.Ej6;

public class Main {
    public static void main(String[] args) {
        int numFilosofos = 5; // Por ejemplo, 5 filósofos en la mesa
        Mesa mesa = new Mesa(numFilosofos);

        for (int i = 0; i < numFilosofos; i++) {
            new Thread(new Filosofo(i, mesa), "Filosofo-" + i).start();
        }
    }
}