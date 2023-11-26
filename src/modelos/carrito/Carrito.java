package modelos.carrito;

import java.util.ArrayList;

public class Carrito {
    private ArrayList<Renglon> renglones;

    public Carrito() {
        this.renglones = new ArrayList<>();

    }

    public void comprar(Renglon nuevoRenglon) {
        renglones.add(nuevoRenglon);
    }

    public void verCarrito() {
        System.out.println("----- Carrito de Compras -----");
        for (Renglon renglon : renglones) {
            System.out.println(
                    "Producto: " + renglon.getProducto().getNombre() + " - Cantidad: " + renglon.getCantidad());
        }
        System.out.println("-------------------------------");
    }

    public double verSaldo() {
        // Lógica para calcular el saldo total del carrito
        double saldoTotal = 0.0;
        for (Renglon renglon : renglones) {
            saldoTotal += renglon.calcularPrecioTotal();
        }
        return saldoTotal;
    }

    public void finalizarCompra() {
        // Lógica para descontar los productos del stock y limpiar el carrito
        for (Renglon renglon : renglones) {
            // Aquí se resta la cantidad de productos en el carrito del stock disponible
            renglon.getProducto().setStock(renglon.getProducto().getStock() - renglon.getCantidad());
        }
        renglones.clear(); // Limpiamos el carrito después de la compra
        System.out.println("Compra finalizada. El carrito ha sido limpiado.");
    }

}
