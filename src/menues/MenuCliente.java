package menues;

import java.util.Scanner;

import interfaces.Usuario;

import java.util.List;

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
 * ❌Ver como se obtienen los descuentos(dtos por subsidio, producto demandando y dto en compra mayor a $12000) y mostrarlos por pantalla, al mostrar la compra.
 */

public class MenuCliente {
    private boolean continuar = true;
    private Scanner sc;
    private Cliente cliente;
    private List<Cliente> listaUsuarios;


    

    public MenuCliente(boolean continuar, Scanner sc, Cliente cliente, List<Cliente> listaUsuarios) {
        this.continuar = continuar;
        this.sc = sc;
        this.cliente = cliente;
        this.listaUsuarios = listaUsuarios;
    }

    public MenuCliente(Scanner sc2, Cliente cliente2, List<Usuario> listaUsuarios2) {
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
        System.out.println("1- AGREGAR DINERO");
        System.out.println("2- RETIRAR DINERO");
        System.out.println("3- TRANSFERIR A OTRO CLIENTE");
        int opcion = sc.nextInt();
        switch(opcion){
        case 1:
        this.agregarDinero();
        break;
        case 2:
        this.retirarDinero();
        break;
        case 3:
        this.transferirDinero();
        break;
        }

    }
    private void agregarDinero() {
        System.out.println("Ingrese la cantidad de dinero a agregar:");
        double cantidad = sc.nextDouble();
        cliente.setSaldo(cliente.getSaldo() + cantidad);
        System.out.println("Se agregó " + cantidad + " al saldo actual. Saldo actual: " + cliente.getSaldo());
    }
    
    private void retirarDinero() {
        System.out.println("Ingrese la cantidad de dinero a retirar:");
        double cantidad = sc.nextDouble();
        if (cliente.getSaldo() >= cantidad) {
            cliente.setSaldo(cliente.getSaldo() - cantidad);
            System.out.println("Se retiró " + cantidad + " del saldo actual. Saldo actual: " + cliente.getSaldo());
        } else {
            System.out.println("Saldo insuficiente para realizar la operación.");
        }
    }
    
    private void transferirDinero() {
        System.out.println("Ingrese el nombre del destinatario:");
        String nombreDestinatario = sc.next();
    
        Cliente destinatario = null;
        for (Cliente cliente : listaUsuarios) {
            if (cliente instanceof Cliente && cliente.getNombreUsuario().equalsIgnoreCase(nombreDestinatario)) {
                destinatario = (Cliente) cliente;
                break;
            }
        }
    
        if (destinatario != null) {
            System.out.println("Ingrese la cantidad a transferir:");
            double cantidadTransferir = sc.nextDouble();
    
            if (cliente.getSaldo() >= cantidadTransferir) {
                cliente.setSaldo(cliente.getSaldo() - cantidadTransferir);
                destinatario.setSaldo(destinatario.getSaldo() + cantidadTransferir);
                System.out.println("Transferencia exitosa a " + destinatario.getNombreUsuario());
                System.out.println("Nuevo saldo del remitente: " + cliente.getSaldo());
            } else {
                System.out.println("Saldo insuficiente para realizar la transferencia.");
            }
        } else {
            System.out.println("El destinatario no es un cliente válido.");
        }
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
