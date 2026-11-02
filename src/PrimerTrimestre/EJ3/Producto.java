package PrimerTrimestre.EJ3;

import java.io.Serializable;

public class Producto implements Serializable {
    private static final long serialVersionUID = 1L; // Recomendado para la serialización

    private String nombre;
    private double precio;
    private int stock;

    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    @Override
    public String toString() {
        return String.format("Producto [Nombre: %s | Precio: %.2f € | Stock: %d]", nombre, precio, stock);
    }
}

