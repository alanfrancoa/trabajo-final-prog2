package modelos.articulos;

public class Simple extends Articulo {
    public Simple(int id_articulo, String nombre, double precio_neto, int stock, char rubro) {
        super(id_articulo, nombre, precio_neto, stock, rubro);
    }

    @Override
    public double calcularDescuento() {
        return 0; // No hay descuento para artículos simples
    }
}
