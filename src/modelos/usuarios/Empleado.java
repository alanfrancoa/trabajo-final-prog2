package modelos.usuarios;

public class Empleado extends UsuarioBase {

    // Constructor de la clase Empleado que herada el super() de la clase padre
    // Usuario
    public Empleado(String nombreUsuario, String claveUsuario) {
        super(nombreUsuario, claveUsuario);
    }

    // Implementación del método getTipoUsuario() para Empleado
    @Override
    public String getTipoUsuario() {
        return "EMPLEADO";
    }

   



    
}
