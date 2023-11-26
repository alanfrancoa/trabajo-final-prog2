package modelos.articulos;

public class Subsidiado extends Articulo {

    // Constructor que herada los atributos de la clase abstracta
    public Subsidiado(int id_articulo, String nombre, double precio_neto, int stock, char rubro) {
        super(id_articulo, nombre, precio_neto, stock, rubro);
    }

    @Override
    public double calcularDescuento() {
        if (this.getRubro() == 'A') {
            return 0.3;
        } else if (this.getRubro() == 'B') {
            return 0.24;
        } else {
            return 0.15;
        }
    }

}
