package modelos.articulos;

public class Simple extends Articulo {
    public Simple(int id_articulo, String nombre, double precio_neto, int stock, char rubro) {
        super(id_articulo, nombre, precio_neto, stock, rubro);
    }

    // Metodos abstractos sobreescritos
    @Override
    public double calcularPrecioFinal() {
        return this.getPrecio_neto();
    }

    @Override
    public String toString() {

        String infoArticuloSimple = "{ id_articulo: " + this.getId_articulo() + " nombre: " + this.getNombre()
                + " precio_neto: " + this.getPrecio_neto() + " stock: " + this.getStock() + " tipo: S (SIMPLE) "
                + " rubro: " + this.getInfoRubro() + "}";

        return infoArticuloSimple;
    }
}
