package modelos.articulos;

public abstract class Articulo {

    // Atriburos principales
    private int id_articulo;
    private String nombre;
    private double precio_neto;
    private int stock;
    private char rubro;

    // Constructor
    public Articulo(int id_articulo, String nombre, double precio_neto, int stock, char rubro) {
        this.id_articulo = id_articulo;
        this.nombre = nombre;
        this.precio_neto = precio_neto;
        this.stock = stock;
        this.rubro = rubro;
    }

    // Getters y Setters
    public int getId_articulo() {
        return this.id_articulo;
    }

    public void setId_articulo(int id_articulo) {
        this.id_articulo = id_articulo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio_neto() {
        return this.precio_neto;
    }

    public void setPrecio_neto(double precio_neto) {
        this.precio_neto = precio_neto;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock += stock;
    }

    public char getRubro() {
        return this.rubro;
    }

    public void setRubro(char rubro) {
        this.rubro = rubro;
    }

    // Metodo toString()
    @Override
    public String toString() {
        return "Articulo [id_articulo=" + id_articulo + ", nombre=" + nombre + ", precio_neto=" + precio_neto
                + ", stock=" + stock + ", rubro=" + rubro + "]";
    }

}
