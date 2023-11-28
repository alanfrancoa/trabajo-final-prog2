package modelos.articulos;

public class Subsidiado extends Articulo {

    // Constructor que herada los atributos de la clase abstracta
    public Subsidiado(int id_articulo, String nombre, double precio_neto, int stock, char rubro) {
        super(id_articulo, nombre, precio_neto, stock, rubro);
    }

    // Aplicar descuento segun categoria
    @Override
    public double calcularPrecioFinal() {
        switch (this.getRubro()) {
            case 'A':
                return this.getPrecio_neto() * 0.7; // Descuento del 30%
            case 'B':
                return this.getPrecio_neto() * 0.76; // Descuento del 24%
            case 'C':
                return this.getPrecio_neto() * 0.85; // Descuento del 15%
            default:
                return this.getPrecio_neto();
        }
        
    }

    @Override
    public String toString() {

        String infoArticuloSimple = "{ id_articulo: " + this.getId_articulo() + " nombre: " + this.getNombre()
                + " precio_neto: " + this.getPrecio_neto() + " stock: " + this.getStock() + " tipo: S (SUBSIDIADO) "
                + " rubro: " + this.getInfoRubro() + "}";

        return infoArticuloSimple;
    }

}
