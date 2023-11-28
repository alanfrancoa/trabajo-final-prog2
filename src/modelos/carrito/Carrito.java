package modelos.carrito;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import interfaces.Usuario;
import modelos.articulos.Articulo;
import modelos.usuarios.Cliente;
import modelos.usuarios.Empleado;

public class Carrito {

    private Scanner sc = new Scanner(System.in);
    private ArrayList<Renglon> listaCompra;

    public Carrito() {
        this.listaCompra = new ArrayList<>();
    }

    public void agregar(Renglon nuevoRenglon) {
        listaCompra.add(nuevoRenglon);
    }

    public void verCarrito() {

        System.out.println("------------- Carrito de Compras -------------");

        if (listaCompra.isEmpty()) {
            System.out.println("---------------------");
            System.out.println("El carrito esta vacio");
            System.out.println("---------------------");
            return;
        }

        System.out.println("----------------------------------------------------");
        for (Renglon renglon : listaCompra) {
            Articulo producto = renglon.getProducto();
            int cantidad = renglon.getCantidad();
            System.out.println("CANTIDAD: " + cantidad);
            System.out.println("CODIGO: " + producto.getId_articulo());
            System.out.println("NOMBRE: " + producto.getNombre());
            System.out.println("PRECIO UNITARIO: " + producto.calcularPrecioFinal());
            System.out.println("PRECIO TOTAL: " + renglon.calcularPrecioTotal());
        }
        System.out.println("TOTAL A PAGAR: " + this.verMontoTotal());
        System.out.println("----------------------------------------------------");
    }

    public double verMontoTotal() {

        double total = 0;

        for (Renglon renglon : listaCompra) {
            total += renglon.calcularPrecioTotal();
        }

        return total;
    }

    public double verSaldo() {
        // Lógica para calcular el saldo total del carrito
        double saldoTotal = 0.0;
        for (Renglon renglon : listaCompra) {
            saldoTotal += renglon.calcularPrecioTotal();
        }
        return saldoTotal;
    }

    public void finalizarCompra(Cliente cliente, List<Usuario> listaUsuarios) {

        // Calcula el monto total de la compra
        double total = this.verMontoTotal();

        // Si el total es mayor a 12000 le hago un descuento del 15%
        if (total >= 12000) {
            total = total * 0.85;
        }

        System.out.println("-------------------------- FINALIZAR COMPRA --------------------------");
        System.out.println("MONTO TOTAL A PAGAR: " + total);
        System.out.println("SU SALDO ES DE: " + cliente.getSaldo());
        System.out.println("----------------------------------------------------------------------");

        System.out.println("Desea finalizar la compra?");
        System.out.println("1 - SI");
        System.out.println("2 - NO");
        int opcion = this.elegirOpcionFinalizarCompra();

        switch (opcion) {
            case 1:
                System.out.println("----------------------------- FACTURA DE COMPRA -----------------------------");

                // Muestra los detalles de cada artículo en el carrito
                for (Renglon renglon : listaCompra) {
                    Articulo articulo = renglon.getProducto();
                    int cantidad = renglon.getCantidad();

                    System.out.println("CANTIDAD: " + cantidad);
                    System.out.println("CODIGO: " + articulo.getId_articulo());
                    System.out.println("NOMBRE: " + articulo.getNombre());
                    System.out.println("PRECIO UNITARIO: " + articulo.calcularPrecioFinal());
                    System.out.println("RUBRO: " + articulo.getInfoRubro());
                    System.out.println("PRECIO TOTAL: " + renglon.calcularPrecioTotal());
                }

                System.out.println("------------------------------------------------------------------------------");

                // Verifica si el cliente tiene saldo suficiente para realizar la compra
                if (cliente.getSaldo() < total) {
                    System.out.println("--------------------------------------------------");
                    System.out.println("ERROR: Saldo insuficiente para realizar la compra.");
                    System.out.println("--------------------------------------------------");
                    return;
                }

                // Actualiza el stock del artículo en la lista de artículos
                for (Renglon renglon : listaCompra) {
                    Articulo articulo = renglon.getProducto();
                    int cantidad = renglon.getCantidad();
                    this.reducirStockArticulo(articulo, cantidad, listaUsuarios);
                }

                // Muestro un mensaje de la compra realizada y el nuevo saldo del cliente
                System.out.println("------------------------------------------------------------------------------");
                System.out.println("Compra realizada con éxito. ¡Gracias por su compra!");
                double clienteSaldoActual = cliente.getSaldo() - total;
                System.out.println("Su saldo actual es de: " + clienteSaldoActual);
                System.out.println("------------------------------------------------------------------------------");

                listaCompra.clear(); // Limpia el carrito después de finalizar la compra
                break;
            case 2:
                System.out.println("--------------------------------------------------");
                System.out.println("Ha CANCELADO la finalizacion de la compra.");
                System.out.println("--------------------------------------------------");
                break;

            default:
                System.out.println("--------------------------------------------------");
                System.out.println("ERROR: Elija una opcion valida.");
                System.out.println("--------------------------------------------------");
                break;
        }

    }

    private void reducirStockArticulo(Articulo articulo, int cantidad, List<Usuario> listaUsuarios) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario instanceof Empleado) {
                Empleado empleado = (Empleado) usuario;

                for (Articulo producto : empleado.getListaDeArticulos()) {
                    if (producto.getId_articulo() == articulo.getId_articulo()) {
                        int productoStockActual = producto.getStock();
                        producto.setStock(productoStockActual - cantidad);
                        break;
                    }
                }
            }
        }
    }

    private int elegirOpcionFinalizarCompra() {
        while (true) {
            try {
                int opcion;
                opcion = this.sc.nextInt();
                return opcion;
            } catch (InputMismatchException e) {
                this.sc.nextLine();
                System.out.println("---------------------------------");
                System.out.println("ERROR: Ingrese un valor numerico ");
                System.out.println("---------------------------------");
            }
        }
    }
}
