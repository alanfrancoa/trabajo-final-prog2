package menues;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;

import modelos.articulos.Articulo;
import modelos.carrito.Carrito;
import modelos.carrito.Renglon;
import modelos.usuarios.Cliente;
import modelos.usuarios.Empleado;
import interfaces.Usuario;

/*
 * SALDO:
 *  - Agregar dinero
 *  - Retirar dinero
 *  - Transferir a otro usuario (lista de Usarios de tipo cliente)
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
    private Carrito carrito;

    // Constructor
    public MenuCliente(Scanner sc, Cliente cliente, List<Usuario> listaUsuarios) {
        this.sc = sc;
        this.cliente = cliente;
        this.listaUsuarios = listaUsuarios;
        this.carrito = new Carrito();
    }

    // --------------------------- GENERICOS ---------------------------
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

    private void mostrarOpcionesSaldo() {
        System.out.println("------------------- MODULO SALDO -------------------");
        System.out.println("Elija una de las siguientes opciones: ");
        System.out.println("1- AGREGAR DINERO");
        System.out.println("2- RETIRAR DINERO");
        System.out.println("3- TRANSFERIR A OTRO CLIENTE");
        System.out.print("Opcion elegida: ");
        System.out.println("----------------------------------------------------");
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

    // --------------------------- MODULO COMPRA ---------------------------

    // Funcion para realizar una compra
    private void comprar() {
        // Lógica para realizar compras
        System.out.println("Has seleccionado la opción de COMPRAR.");

        this.verListaDeArticulos();

        int codigoArticulo = this.ingresarCodigo();

        while (codigoArticulo != 0) {

            // Busca el artículo en la lista de artículos disponibles
            Articulo articuloSeleccionado = buscarArticuloPorCodigo(codigoArticulo);

            if (articuloSeleccionado == null) {
                System.out.println("----------------------------------------------------------------------");
                System.out.println("ERROR: El articulo no existe, intente ingresar un articulo existente ");
                System.out.println("-----------------------------------------------------------------------");
                return;
            }

            System.out.println("Ingrese la cantidad que desea comprar:");
            int cantidadIngresada = sc.nextInt(); // HACER LA VALIDACION DE TIPO DE DATO

            // Verifica si hay suficiente stock
            if (articuloSeleccionado.getStock() < cantidadIngresada) {
                System.out.println("--------------------------------------------");
                System.out.println("ERROR: No hay suficiente stock disponible");
                System.out.println("-----------------------------------------");
                return;
            }

            // Agrega el artículo al carrito
            Renglon productoAAgregar = new Renglon(cantidadIngresada, articuloSeleccionado);

            this.carrito.agregar(productoAAgregar);

            System.out.println("--------------------------------------------");
            System.out.println("Producto agregado al carrito");
            System.out.println("--------------------------------------------");

            // Vuelvo a preguntar para seguir o para terminar
            System.out.println("Ingrese el código del siguiente artículo que desea comprar (0 para salir):");
            codigoArticulo = sc.nextInt();
        }

    }

    private void verCarrito() {

        System.out.println("Has seleccionado la opción de VER CARRITO.");
        carrito.verCarrito();

    }

    private void finalizarCompra() {
        System.out.println("Has seleccionado la opción de FINALIZAR COMPRA.");
        carrito.finalizarCompra(cliente, listaUsuarios);
    }

    // --------------------------- MODULO SALDO ---------------------------

    // Lógica para ver el saldo del cliente
    private void verSaldo() {

        System.out.println("------------------- SALDO ACTUAL -------------------");
        System.out.println("Has seleccionado la opción de VER SALDO.");
        System.out.println("SALDO ACTUAL: " + cliente.getSaldo());

        int opcion = this.elegirOpcionModuloSaldo();

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
            default:
                System.out.println("------------------------------------------------------------------------------");
                System.out.println("OPCION INCORRECTA. Elija una opcion valida.");
                System.out.println("------------------------------------------------------------------------------");
                break;
        }

    }

    // Metodo que agrega dinero a la cuenta del cliente
    private void agregarDinero() {

        double cantidadIngresada = this.ingresarSaldo();

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

    }

    // Metodo para retirar dinero de la cuenta bancaria del cliente
    private void retirarDinero() {

        // Ingreso un monto a retirar
        System.out.print("Ingrese la cantidad de dinero a retirar: ");
        double cantidadARetirar = this.ingresarSaldo();

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
            System.out.println("SALDO INSUFICIENTE para realizar la operacion");
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
        String nombreDestinatario = sc.next(); // VALIDAR QUE ESTO SEA UN STRING

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

        double cantidadTransferir = this.ingresarCantidad();

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

    // --------------------------- METODOS DEL MENU CLIENTE ---------------------------
   
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

    private double ingresarSaldo() {
        while (true) {
            double saldo;
            try {
                System.out.print("Ingrese la cantidad de dinero a agregar:");
                saldo = this.sc.nextDouble();
                return saldo;
            } catch (InputMismatchException e) {
                this.sc.nextLine();
                System.out.println("----------------------------------------------");
                System.out.println("ERROR: INGRESE UN VALOR NUMERICO");
                System.out.println("----------------------------------------------");
            }
        }
    }

    private int elegirOpcionModuloSaldo() {

        while (true) {
            int opcion;
            try {
                this.mostrarOpcionesSaldo();
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

    private int ingresarCodigo() {

        while (true) {
            int codigoIngresado;
            try {
                System.out.print("Ingrese el código del artículo que desea comprar (0 para salir):");
                codigoIngresado = this.sc.nextInt();
                return codigoIngresado;
            } catch (InputMismatchException e) {
                this.sc.nextLine();
                System.out.println("----------------------------------------------");
                System.out.println("ERROR: INGRESE UN VALOR NUMERICO");
                System.out.println("----------------------------------------------");
            }
        }

    }

    private void verListaDeArticulos() {
        for (Usuario usuario : listaUsuarios) {
            if (usuario instanceof Empleado) {
                List<Articulo> listaArticulos = ((Empleado) usuario).getListaDeArticulos();
                System.out.println("--------------------------------------------------------");
                for (Articulo articulo : listaArticulos) {
                    System.out.println("CODIGO: " + articulo.getId_articulo());
                    System.out.println("NOMBRE: " + articulo.getNombre());
                    System.out.println("PRECIO: " + articulo.calcularPrecioFinal());
                    System.out.println("STOCK: " + articulo.getStock());
                }
                System.out.println("--------------------------------------------------------");
            }
        }
    }

    private Articulo buscarArticuloPorCodigo(int codigoIngresado) {

        Articulo articuloEcontrado = null;

        // Lógica para buscar un artículo por su código
        for (Usuario usuario : listaUsuarios) {
            if (usuario instanceof Empleado) {
                // Obtener la lista de artículos del empleado
                List<Articulo> articulosDisponibles = ((Empleado) usuario).getListaDeArticulos();

                // Buscar el artículo por su código
                for (Articulo articulo : articulosDisponibles) {
                    if (articulo.getId_articulo() == codigoIngresado) {
                        articuloEcontrado = articulo;
                        break;
                    }
                }
            }
        }

        return articuloEcontrado;
    }

    private double ingresarCantidad() {
        while (true) {
            double cantidadIngresada;
            try {
                System.out.print("Ingrese la cantidad a transferir:");
                cantidadIngresada = this.sc.nextDouble();
                return cantidadIngresada;

            } catch (InputMismatchException e) {
                this.sc.nextLine();
                System.out.println("----------------------------------------------");
                System.out.println("ERROR: INGRESE UNA OPCIÓN NUMERICA");
                System.out.println("----------------------------------------------");
            }

        }
    }
}
