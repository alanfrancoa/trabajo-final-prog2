package modelos.articulos;

public class Demandado extends Articulo {
    
    // Atributo 
    private int stockDeseado;

    // Constructor 
    public Demandado(int id_articulo, String nombre, double precio_neto, int stock, char rubro, int stockDeseado) {
        super(id_articulo, nombre, precio_neto, stock, rubro);
        this.stockDeseado = stockDeseado;
    }

    // Metodo abstracto 
    @Override
    public double calcularDescuento() {

        int excedente = Math.max(0, this.getStock() - stockDeseado);
        double porcentajeDescuento = Math.min(0.5, excedente / (double) stockDeseado);
        return porcentajeDescuento;
    }
}
