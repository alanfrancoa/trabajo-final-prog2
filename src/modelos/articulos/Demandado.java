package modelos.articulos;

public class Demandado extends Articulo {

    // Atributo
    private int stockDeseado;

    // Constructor
    public Demandado(int id_articulo, String nombre, double precio_neto, int stock, int tipoIngresado, char rubro, int stockDeseado) {
        super(id_articulo, nombre, precio_neto, stock, rubro);
        this.stockDeseado = stockDeseado;
    }

    // Getter
    public int getStockDeseado() {
        return this.stockDeseado;
    }

    // ---------------- Metodos abstractos sobrescritos ----------------
    @Override
    public double calcularPrecioFinal() {

        // Calcular el porcentaje excedido
        double porcentajeExcedido = ((double) this.getStock() / stockDeseado - 1) * 100;

        // Aplica descuento con un máximo del 50%
        double descuento = Math.min(porcentajeExcedido, 50);

        // Precio final
        double precioFinal = this.getPrecio_neto() * (1 - descuento / 100);
        return precioFinal;
    }

    @Override
    public String toString() {

        String infoArticuloSimple = "{ id_articulo: " + this.getId_articulo() + " nombre: " + this.getNombre()
                + " precio_neto: " + this.getPrecio_neto() + " stock: " + this.getStock() + " tipo: D (DEMANDADO) "
                + " rubro: " + this.getInfoRubro() + "}";

        return infoArticuloSimple;
    }
}
