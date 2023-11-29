package modelos.usuarios;

import java.util.ArrayList;

import modelos.articulos.Articulo;

public class Empleado extends UsuarioBase {
    private ArrayList<Articulo> listaArticulos;

    // Constructor de la clase Empleado que herada el super() de la clase padre
    public Empleado(String nombreUsuario, String claveUsuario, ArrayList<Articulo> listaArticulos) {
        super(nombreUsuario, claveUsuario);
        this.listaArticulos = listaArticulos;

    }
    // ---------------- Getters ----------------

    // Implementación del método getTipoUsuario() para Empleado
    @Override
    public String getTipoUsuario() {
        return "EMPLEADO";
    }

    public ArrayList<Articulo> getListaArticulos() {
        return this.listaArticulos;
    }

    // ---------------- Metodos para el ABM articulos que hace el Empleado
    // ----------------

    // Agregar Articulo
    public void agregarArticulo(Articulo nuevoArticulo) {
        this.getListaArticulos().add(nuevoArticulo);
    }

    // Editar Articulo
    public void editarArticulo(Articulo articuloModificado, String nombreIngesado, double nuevoPrecio,
            char rubroIngreado) {
        articuloModificado.setNombre(nombreIngesado);
        articuloModificado.setPrecio_neto(nuevoPrecio);
        articuloModificado.setRubro(rubroIngreado);
    }

    // Eliminar Articulo
    public void eliminarArticulo(int codigoArticulo) {
        this.getListaArticulos().removeIf(articulo -> articulo.getId_articulo() == codigoArticulo);
    }

    // Función para ver la lista de articulos
    public void mostrarArticulo(Articulo articulo) {
        System.out.println("{ id_articulo: " + articulo.getId_articulo() + ", nombre: " + articulo.getNombre()
                + ", precio_neto: " + articulo.calcularPrecioFinal() + ", stock: " + articulo.getStock() + "}");
    }

    @Override
    public String toString() {
        return "Empleado: " + this.getNombreUsuario();
    }

}
