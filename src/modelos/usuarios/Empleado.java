package modelos.usuarios;

import java.util.ArrayList;

import modelos.articulos.Articulo;

public class Empleado extends UsuarioBase {

    // Atributo de la clase Empleado 
    private ArrayList<Articulo> listaArticulos;

    // Constructor de la clase Empleado que herada el super() de la clase padre
    // Usuario
    public Empleado(String nombreUsuario, String claveUsuario) {
        super(nombreUsuario, claveUsuario);
        this.listaArticulos = new ArrayList<>();
    }

    public ArrayList<Articulo> getListaDeArticulos() {
        return this.listaArticulos;
    }

    // Implementación del método getTipoUsuario() para Empleado
    @Override
    public String getTipoUsuario() {
        return "EMPLEADO";
    }

    // ---------------- Metodos para el ABM articulos ----------------

    // Agregar Articulo
    public void agregarArticulo(Articulo nuevoArticulo) {
        listaArticulos.add(nuevoArticulo);
    }

    // Editar Articulo
    public void editarArticulo(int codigoArticulo, Articulo articuloModificado, ArrayList<Articulo> listaDeArticulos) {
        for (Articulo articulo : listaDeArticulos) {
            if (articulo.getId_articulo() == codigoArticulo) {
                articulo.setNombre(articuloModificado.getNombre());
                articulo.setPrecio_neto(articuloModificado.getPrecio_neto());
                articulo.setRubro(articuloModificado.getRubro());
                break;
            }
        }
    }

    // Elinar Articulo
    public void eliminarArticulo(int codigoArticulo) {
        listaArticulos.removeIf(articulo -> articulo.getId_articulo() == codigoArticulo);
    }

    @Override
    public String toString() {
        return "Empleado: " + this.getNombreUsuario();
    }

   

   



    
}
