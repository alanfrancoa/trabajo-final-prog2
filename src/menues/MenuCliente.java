package menues;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;

import modelos.carrito.Carrito;
import modelos.usuarios.Cliente;
import interfaces.Usuario;

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

    // Atributos
    private boolean continuar = true;
    private Scanner sc;
    private Cliente cliente;
    private List<Usuario> listaUsuarios;

    // Constructor
    public MenuCliente(Scanner sc, Cliente cliente, List<Usuario> listaUsuarios) {
        this.sc = sc;
        this.cliente = cliente;
        this.listaUsuarios = listaUsuarios;
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

        while (true) {
            int opcion;
            try {
                this.mostrarOpciones();
                opcion = this.sc.nextInt();
                return opcion;

            } catch (InputMismatchException e) {
                this.sc.nextLine();
                System.out.println("----------------------------------------------");
                System.out.println("ERROR: INGRESE UNA OPCIÓN NUMERICA");
                System.out.println("----------------------------------------------");
            }

        }

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

    // Funcion para salir del menu del cliente
    private void finalizar() {
        this.continuar = false;
        System.out.println("----------------------------------------------");
        System.out.println("HAS SALIDO DEL MENÚ DE CLIENTE");
        System.out.println("----------------------------------------------");
    }

    // Funcion para realizar una compra
    private void comprar() {
        // Lógica para realizar compras
        System.out.println("Has seleccionado la opción de COMPRAR.");
        // Aquí se implementa la lógica de compra utilizando el objeto cliente
    }

    private void verCarrito() {
        // Lógica para mostrar el carrito
        System.out.println("Has seleccionado la opción de VER CARRITO.");

    }

    // Lógica para ver el saldo del cliente
    private void verSaldo() {

        System.out.println("----------------------------------------------");
        System.out.println("Has seleccionado la opción de VER SALDO.");
        System.out.println("Saldo actual: " + cliente.getSaldo());
        System.out.println("----------------------------------------------");
        System.out.println("Elija una de las siguientes opciones: ");
        System.out.println("1- AGREGAR DINERO");
        System.out.println("2- RETIRAR DINERO");
        System.out.println("3- TRANSFERIR A OTRO CLIENTE");
        System.out.print("Opcion elegida: ");
        System.out.println("----------------------------------------------");

        int opcion = sc.nextInt();
        switch (opcion) {
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

    // Metodo que agrega dinero a la cuenta del cliente
    private void agregarDinero() {

        boolean continuar = true;

        while (continuar) {

            System.out.println("Ingrese la cantidad de dinero a agregar:");
            double cantidadIngresada = sc.nextDouble();

            if (cantidadIngresada < 0) {
                System.out.println("------------------------------------------------");
                System.out.println("ERROR! Por favor, elija una cantidad correcta.");
                System.out.println("-------------------------------------------------");
                return;
            }

            this.cliente.setSaldo(cliente.getSaldo() + cantidadIngresada);

            System.out.println(
                    "----------------------------------------------------------------------------------------");
            System.out.println(
                    "Se agregó " + cantidadIngresada + " al saldo actual. SALDO ACTUAL: " + cliente.getSaldo());
            System.out.println(
                    "----------------------------------------------------------------------------------------");

            continuar = false;
        }

    }

    // Metodo para retirar dinero de la cuenta bancaria del cliente
    private void retirarDinero() {

        // Ingreso un monto a retirar
        System.out.print("Ingrese la cantidad de dinero a retirar: ");
        double cantidadARetirar = sc.nextDouble();

        // Validaciones de la cantidad ingresada

        // Valido que la cantidad ingresada no sea negativa
        if (cantidadARetirar < 0) {
            System.out.println("------------------------------------------------");
            System.out.println("ERROR, ingrese una cantidad positiva");
            System.out.println("-------------------------------------------------");
            return;
        }

        // Valido si el cliente tiene saldo suficiente para retirar dicha cantidad
        // ingresada
        if (this.cliente.getSaldo() < cantidadARetirar) {
            System.out.println("------------------------------------------------");
            System.out.println("SALDO INSUFICIENTE para relaizar la operacion");
            System.out.println("-------------------------------------------------");
            return;
        }

        // Si el cliente posee saldo suficiente procedo con el retiro y a modificar el
        // saldo del cliente
        this.cliente.setSaldo(cliente.getSaldo() - cantidadARetirar);

        // Muestro por pantalla el saldo actual
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Se retiró " + cantidadARetirar + " del saldo actual. SALDO ACTUAL: " + cliente.getSaldo());
        System.out.println("----------------------------------------------------------------------------------------");

    }

    // Funcion para transferir de un cliente a otro
    private void transferirDinero() {

        // Ingreso el nombre del destinatario
        System.out.println("Ingrese el nombre del destinatario:");
        String nombreDestinatario = sc.next();

        // Verifico si exisnte un destinatario y guardo el resultado en una varaible de
        // tipo Cliente
        Cliente destinatario = this.validarExistenciaUsuario(nombreDestinatario);

        // Valido la existencia de dicho destinataruio

        // Si el destinatario es null, quiere decir que no existe, lanzo un mensaje de
        // error
        if (destinatario == null) {
            System.out.println("-------------------------------------------------------------");
            System.out.println("USUARIO INEXISTENTE, por favor vuelva a ingresar otro nombre");
            System.out.println("-------------------------------------------------------------");
            return;
        }

        // Si me devuelve un Cliente, procedo con la transferencia

        // Ingreso la cantidad a transferir
        System.out.print("Ingrese la cantidad a transferir:");
        double cantidadTransferir = sc.nextDouble();

        // Valido que la cantidad a transferir sea positiva

        // Si la el numero ingresado en negativo, lanzo un mensaje de error
        if (cantidadTransferir < 0) {
            System.out.println("------------------------------------------------");
            System.out.println("ERROR, ingrese una cantidad positiva");
            System.out.println("-------------------------------------------------");
            return;
        }

        // Valido que el cliente tenga saldo suficiente para realizar la transferencia

        // Si el saldo es menor a la cantidad a transferir lanzo un mensaje de error
        if (this.cliente.getSaldo() < cantidadTransferir) {
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("SALDO INSUFICIENTE para realizar la transferacia, por favor intentelo de nuevo");
            System.out.println("------------------------------------------------------------------------------");
        }

        // Caso contrario procedo con la transferencia

        // Disminuzco el saldo del cliente
        this.cliente.setSaldo(cliente.getSaldo() - cantidadTransferir);

        // Aumento el saldo del destinatario
        destinatario.setSaldo(destinatario.getSaldo() + cantidadTransferir);

        // Muestro mensajes de la transaccion una vez finalizada
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Transferencia exitosa: Le has enviado a " + destinatario.getNombreUsuario() + " la suma de "
                + cantidadTransferir);
        System.out.println("------------------------------------------------------------------------------");

    }

    private void finalizarCompra() {
        // Lógica para finalizar la compra

        System.out.println("Has seleccionado la opción de FINALIZAR COMPRA.");

    }

    private Cliente validarExistenciaUsuario(String nombreIngresado) {

        // Parto con una premisa booleana
        Cliente clienteEncontrado = null;

        // Recorro la lista de Usuarios
        for (Usuario usuario : listaUsuarios) {

            if (usuario instanceof Cliente) {
                if (usuario.getNombreUsuario().equals(nombreIngresado)) {
                    clienteEncontrado = (Cliente) usuario;
                    break;
                }
            }

        }

        // Devuelvo el valor final de la premisa
        return clienteEncontrado;
    }

}
