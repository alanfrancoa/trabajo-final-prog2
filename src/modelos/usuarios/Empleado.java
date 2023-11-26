package modelos.usuarios;
import java.util.List;
import modelos.articulos.Articulo;

public class Empleado extends UsuarioBase {
    private List<Articulo> listaDeArticulos;


    public List<Articulo> getListaDeArticulos() {
        return listaDeArticulos;
    }

    // Constructor de la clase Empleado que hereda el super() de la clase padre Usuario
    public Empleado(String nombreUsuario, String claveUsuario) {
        super(nombreUsuario, claveUsuario);
    }
     // Métodos para ABM de artículos
     public void agregarArticulo(Articulo nuevoArticulo) {
        listaDeArticulos.add(nuevoArticulo);
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
        listaDeArticulos.removeIf(articulo -> articulo.getId_articulo() == codigoArticulo);
    }
    
    // Implementación del método getTipoUsuario() para Empleado
    @Override
    public String getTipoUsuario() {
        return "EMPLEADO";
    }

   



    
}
