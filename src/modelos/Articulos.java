package modelos;

public class Articulos {

    // Atriburos principales
    private int codigoArticulo;
    private String nombre;
    private double precioNeto;
    private int stockArticulo;
    private String rubro;

    // Constructor
    public Articulos(int codigoArticulo, String nombre, double precioNeto, int stockArticulo, String rubro) {
        this.codigoArticulo = codigoArticulo;
        this.nombre = nombre;
        this.precioNeto = precioNeto;
        this.stockArticulo = stockArticulo;
        this.rubro = rubro;
    }

    // Getters y Setters
    public int getCodigoArticulo() {
        return this.codigoArticulo;
    }

    public void setCodigoArticulo(int codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioNeto() {
        return this.precioNeto;
    }

    public void setPrecioNeto(double precioNeto) {
        this.precioNeto = precioNeto;
    }

    public int getStockArticulo() {
        return this.stockArticulo;
    }

    public void setStockArticulo(int stockArticulo) {
        this.stockArticulo = stockArticulo;
    }

    public String getRubro() {
        return this.rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    // Metodo toString()
    @Override
    public String toString() {
        return "Articulo { codigoArticulo=" + codigoArticulo
                + ", nombre=" + nombre +
                ", precioNeto=" + precioNeto
                + ", stockArticulo=" + stockArticulo +
                ", rubro=" + rubro + "}";
    }

}
