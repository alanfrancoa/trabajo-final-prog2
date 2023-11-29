package modelos.usuarios;

public class Cliente extends UsuarioBase {

    // Atributo de la clase Cliente
    private double saldo;

    // Constructor de la clase Cliente que hereda el super() de la clase padre
    // UsuarioBase
    public Cliente(String nombreUsuario, String claveUsuario, double saldo) {
        super(nombreUsuario, claveUsuario);
        this.saldo = saldo;
    }

    // ---------------- Getters y Setters propios de la clase ----------------
    public double getSaldo() {
        return this.saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    // ---------------- Implementacion del método getTipoUsuario() ----------------
    @Override
    public String getTipoUsuario() {
        return "CLIENTE";
    }

}
