package menues;

import java.util.Scanner;

import modelos.usuarios.Cliente;



public class MenuCliente {
    private boolean continuar = true;
    private Scanner sc;
    private Cliente cliente;


    public MenuCliente(Scanner sc, Cliente cliente) {
        this.sc = sc;
        this.cliente = cliente;
    }

    private void mostrarOpciones() {
        System.out.println("---------------------------------------------");
        System.out.println("MENÚ DE OPCIONES - CLIENTE");
        System.out.println(" 0 - SALIR");
        System.out.println(" 1 - COMPRAR");
        System.out.println(" 2 - VER CARRITO");
        System.out.println(" 3 - VER SALDO");
        System.out.println(" 4 - FINALIZAR COMPRA");
        System.out.println("---------------------------------------------");
        System.out.print("Por favor, elija una opción: ");
    }

    public void iniciar() {
        while (continuar) {
            int opcion = this.elegirOpcion();
            this.realizarOpcion(opcion);
        }
    }

    private int elegirOpcion() {
        this.mostrarOpciones();
        int opcion = this.sc.nextInt();
        return opcion;
    }

    private void finalizar() {
        this.continuar = false;
        System.out.println("----------------------------------------------");
        System.out.println("HAS SALIDO DEL MENÚ DE CLIENTE");
        System.out.println("----------------------------------------------");
    }

    private void comprar() {
        // Lógica para realizar compras
        System.out.println("Has seleccionado la opción de COMPRAR.");
        // Aquí se implementa la lógica de compra utilizando el objeto cliente
    }

    private void verCarrito() {
        // Lógica para mostrar el carrito
        System.out.println("Has seleccionado la opción de VER CARRITO.");
        
    }

    private void verSaldo() {
        // Lógica para ver el saldo del cliente
        System.out.println("Has seleccionado la opción de VER SALDO.");
        System.out.println("Saldo actual: " + cliente.getSaldo());
    }
    private void finalizarCompra() {
        // Lógica para finalizar la compra
        System.out.println("Has seleccionado la opción de FINALIZAR COMPRA.");
        
    }
    private void realizarOpcion(int opcion) {
        switch (opcion) {
            case 0:
                this.finalizar();
                break;
            case 1:
                this.comprar();
                break;
            case 2:
                this.verCarrito();
                break;
            case 3:
                this.verSaldo();
                break;
            case 4:
                this.finalizarCompra();
                break;
            default:
                System.out.println("----------------------------------------------");
                System.out.println("OPCIÓN INCORRECTA, INGRESE UNA OPCIÓN VÁLIDA");
                System.out.println("----------------------------------------------");
                break;
        }
    }
}
