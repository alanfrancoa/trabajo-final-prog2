package menues;

import java.util.Scanner;

import modelos.usuarios.Cliente;

/*
 * SALDO:
 *  - Agregar dinero
 *  - Retirar dinero
 *  - Transferir a otro usuario (lista de Usarios de tipo       cliente)
 * 
 * CARRITO DE COMPRA:
 *  - Agregar articulos (lista de articulos): estos deben ser agregados por codigo y se debe mostrar el listado completo de articulos
 *  - Se puede agregar mas de una vez
 *  - Se podra ver el importae total de articulos cargados en el momento
 *  - Si la compra es mayor a 12000 se aplicara un descuento del 15%
 *  - Cuando el usuario desee finalizar se le ebe mostrar el usuario cuanto se va a gastar y su saldo. Debe confirmar la transaccion
 * - Si el usuario no tiene saldo suficiente, no se podra concretar la compra
 * - Finalizada la operacion se debera mostrar por pantalla los articulos comprados, el subtotal, el importe descontado y el total final
 * - Se debera descontrar los articulos del stock y reducir el saldo del usuario en base a la factura
 * 
 */

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
