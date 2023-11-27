package modelos.carrito;

import java.util.ArrayList;
import java.util.List;

import interfaces.Usuario;
import modelos.articulos.Articulo;
import modelos.usuarios.Cliente;
import modelos.usuarios.Empleado;

public class Carrito {
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

        System.out.println("Resumen de la Compra:");

        // Muestra los detalles de cada artículo en el carrito
        for (Renglon renglon : listaCompra) {
            Articulo articulo = renglon.getProducto();
            int cantidad = renglon.getCantidad();

            System.out.println("CANTIDAD: " + cantidad);
            System.out.println("CODIGO: " + articulo.getId_articulo());
            System.out.println("NOMBRE: " + articulo.getNombre());
            System.out.println("PRECIO UNITARIO: " + articulo.calcularPrecioFinal());
            System.out.println("PRECIO TOTAL: " + renglon.calcularPrecioTotal());
            this.reducirStockArticulo(articulo, cantidad, listaUsuarios);
        }

        // Calcula el monto total de la compra
        double total = this.verMontoTotal();
        System.out.println("TOTAL A PAGAR: " + total);

        // Verifica si el cliente tiene saldo suficiente para realizar la compra
        if (cliente.getSaldo() < total) {
            System.out.println("Saldo insuficiente para completar la compra.");
            return;
        }
        // Resta el monto total del saldo del cliente
        double saldoActual = cliente.getSaldo();
        cliente.setSaldo(saldoActual - total);

        // Lógica adicional según tus necesidades, como reducir el stock de los
        // productos, etc.

        System.out.println("Compra realizada con éxito. ¡Gracias por su compra!");

        listaCompra.clear(); // Limpia el carrito después de finalizar la compra

    }

    private void reducirStockArticulo(Articulo articulo, int cantidad, List<Usuario> listaUsuarios){
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
}
