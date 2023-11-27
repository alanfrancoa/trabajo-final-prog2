package modelos.carrito;

import modelos.articulos.Articulo;
import modelos.articulos.Demandado;
import modelos.articulos.Subsidiado;

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
        double precioTotal = this.cantidad * this.producto.getPrecio_neto();
        return precioTotal;
    }

    public void mostrarDescuento() {
        
        if (this.producto instanceof Subsidiado) {

            double descuentoSubsidiado = 1 - ((Subsidiado) producto).calcularPrecioFinal() / producto.getPrecio_neto() * 100 ;

            System.out.println("Descuento Subsidiado: " + descuentoSubsidiado + "%");

        } else if (this.producto instanceof Demandado){

            double porcentajeExcedido = ((double) producto.getStock() / ((Demandado) producto).getStockDeseado() - 1) * 100;

            double descuento = Math.min(porcentajeExcedido, 50);
            
            System.out.println("Descuento Demandado: " + descuento + "%");
        } else {
            System.out.println("Sin descuento");
        }
       
    }
}
