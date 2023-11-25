package modelos.articulos;

public class Subsidiado extends Articulo {

    private double precio_final;
    private double descuentoRealizado;

    public Subsidiado(int id_articulo, String nombre, double precio_neto, int stock, char rubro) {
        super(id_articulo, nombre, precio_neto, stock, rubro);
        calcularDescuento();
    }

    private void calcularDescuento() {
        double descuento;
        if (this.getRubro() == 'A') {
            descuento = this.getPrecio_neto() * 0.3; // 30% de descuento para rubro A
        } else if (this.getRubro() == 'B') {
            descuento = this.getPrecio_neto() * 0.24; // 24% de descuento para rubro B
        } else {
            descuento = this.getPrecio_neto() * 0.15; // 15% de descuento para otros rubros
        }

        this.descuentoRealizado = descuento;
        this.precio_final = this.getPrecio_neto() - descuento;
    }

    public double getPrecioFinal() {
        return precio_final;
    }

    public double getDescuentoRealizado() {
        return descuentoRealizado;
    }
}