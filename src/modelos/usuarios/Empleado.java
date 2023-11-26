package modelos.usuarios;

import java.util.List;

import modelos.articulos.Articulo;

public class Empleado extends UsuarioBase {

    // Atributo de la clase Empleado 
    private List<Articulo> listaArticulos;

    // Constructor de la clase Empleado que herada el super() de la clase padre
    // Usuario
    public Empleado(String nombreUsuario, String claveUsuario) {
        super(nombreUsuario, claveUsuario);
    }

    public List<Articulo> getListaDeArticulos() {
        return this.listaArticulos;
    }

    // Implementación del método getTipoUsuario() para Empleado
    @Override
    public String getTipoUsuario() {
        return "EMPLEADO";
    }

    // Metodos para el ABM articulos 
    public void agregarArticulo(Articulo nuevoArticulo) {
        listaArticulos.add(nuevoArticulo);
    }

    public void editarArticulo(int codigoArticulo, Articulo articuloModificado, List<Articulo> listaDeArticulos) {
        for (Articulo articulo : listaDeArticulos) {
            if (articulo.getId_articulo() == codigoArticulo) {
                articulo.setNombre(articuloModificado.getNombre());
                articulo.setPrecio_neto(articuloModificado.getPrecio_neto());
                articulo.setStock(articuloModificado.getStock());
                articulo.setRubro(articuloModificado.getRubro());
                // Modifica otros atributos del artículo si es necesario
                break;
            }
        }
    }

    public void eliminarArticulo(int codigoArticulo) {
        listaArticulos.removeIf(articulo -> articulo.getId_articulo() == codigoArticulo);
    }

   



    
}
