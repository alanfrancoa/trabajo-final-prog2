package modelos.carrito;

import modelos.articulos.Articulo;

public class Renglon {

    // Atributos
    private int cantidad;
    private Articulo producto;

    // Constructor
    public Renglon(int cantidad, Articulo producto) {
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Articulo getProducto() {
        return producto;
    }

    public void setProducto(Articulo producto) {
        this.producto = producto;
    }

    public double calcularPrecioTotal() {
        return this.cantidad * this.producto.getPrecio_neto();
    }

    public double mostrarDescuento() {
        // Aca va la logica para retornar los descuentos de cada producto
        return 0.0; // Modificar esto
    }
}
