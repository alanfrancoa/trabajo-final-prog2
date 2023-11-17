package modelos.usuarios;

public abstract class Usuario {

    // Atributos
    private String nombre;
    private String clave;
    private String rol;

    // Constructor
    public Usuario(String nombre, String clave, String rol) {
        this.nombre = nombre;
        this.clave = clave;
        this.rol = rol;
    }

    // Getters y Setters
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return this.clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    // Metodo toString()
    @Override
    public String toString() {
        return "Usuario [nombre=" + nombre + ", rol=" + rol + "]";
    }

    

}
