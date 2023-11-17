package modelos.usuarios;

public class Cliente extends Usuario{
    
    // Atributos propios de la clase Cliente
    private double saldo;
    
    // Constructor de Cliente
    public Cliente(String nombre, String clave, String rol, double saldo){
        super(nombre, clave, rol);
        this.saldo = saldo;
    }

    // Getter y Setter
    public double getSaldo() {
        return this.saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    
    
}
