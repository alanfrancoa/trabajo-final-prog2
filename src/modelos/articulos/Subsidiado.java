package modelos.articulos;

public class Subsidiado extends Articulo {

    // Atributo de Subsidiado
    private double precio_final;

    // Constructor que herada los atributos de la clase abstracta
    public Subsidiado(int id_articulo, String nombre, double precio_neto, int stock, char rubro) {
        super(id_articulo, nombre, precio_neto, stock, rubro);
        this.precio_final = 0;
    }

    // Setters
    public void setPrecioFinal(double precio_final) {
        this.precio_final = precio_final;
    }

    // Metodo para aplicar el descuento en base al rubro
    private void setDescuento() {

        if (this.getRubro() == 'A') {
            double descuento = this.getPrecio_neto() * (30 / 100);
            double precio_final = this.getPrecio_neto() - descuento;
            this.setPrecioFinal(precio_final);
        } else if (this.getRubro() == 'B') {
            double descuento = this.getPrecio_neto() * (24 / 100);
            double precio_final = this.getPrecio_neto() - descuento;
            this.setPrecioFinal(precio_final);
           
        } else {
            double descuento = this.getPrecio_neto() * (15 / 100);
            double precio_final = this.getPrecio_neto() - descuento;
            this.setPrecioFinal(precio_final);

        }
    }
}
