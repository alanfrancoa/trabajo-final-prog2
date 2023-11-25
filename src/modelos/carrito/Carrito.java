package modelos.carrito;

import java.util.ArrayList;

public class Carrito {
    private ArrayList<Renglon> listaCompra;

    public Carrito() {
        this.listaCompra = new ArrayList<>();
    }

    public void comprar(Renglon nuevoRenglon) {
        listaCompra.add(nuevoRenglon);
    }

    public void verCarrito() {
        System.out.println("----- Carrito de Compras -----");
        for (Renglon renglon : listaCompra) {
            System.out.println(
                    "Producto: " + renglon.getProducto().getNombre() + " - Cantidad: " + renglon.getCantidad());
        }
        System.out.println("-------------------------------");
    }

    public double verSaldo() {
        // Lógica para calcular el saldo total del carrito
        double saldoTotal = 0.0;
        for (Renglon renglon : listaCompra) {
            saldoTotal += renglon.calcularPrecioTotal();
        }
        return saldoTotal;
    }

    public void finalizarCompra() {
        // Lógica para descontar los productos del stock y limpiar el carrito
        for (Renglon renglon : listaCompra) {
            // Aquí se resta la cantidad de productos en el carrito del stock disponible
            renglon.getProducto().setStock(renglon.getProducto().getStock() - renglon.getCantidad());
        }
        listaCompra.clear(); // Limpiamos el carrito después de la compra
        System.out.println("Compra finalizada. El carrito ha sido limpiado.");
    }

}
