package menues;

import java.util.Scanner;
import java.util.List;

import modelos.usuarios.Empleado;
import modelos.articulos.Articulo;
import modelos.articulos.Subsidiado;
import modelos.articulos.Simple;

/*
 * Menu empleados:
 * ABM ARTICULOS: crear, editar, eliminar articulo. 
 * Ver un listado completo de Articulos
 * STOCK: ver y editar la cantidad de cada uno de los articulos
 * 
 */


public class MenuEmpleado {
    private boolean continuar = true;
    private Scanner sc;
    private Empleado empleado;

    public MenuEmpleado(Scanner sc, Empleado empleado) {
        this.sc = sc;
        this.empleado = empleado;
    }

    private void mostrarOpciones() {
        System.out.println("---------------------------------------------");
        System.out.println("MENÚ DE OPCIONES - EMPLEADO");
        System.out.println(" 0 - SALIR");
        System.out.println(" 1 - MOSTRAR STOCK");
        System.out.println(" 2 - EDITAR STOCK");
        System.out.println(" 3 - MOSTRAR CANTIDAD DE UN PRODUCTO");
        System.out.println(" 4 - AGREGAR ARTICULO");
        System.out.println(" 5 - ELIMINAR ARTICULO");

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
        System.out.println("HAS SALIDO DEL MENÚ DE EMPLEADO");
        System.out.println("----------------------------------------------");
    }

    private void mostrarStock() {
        System.out.println("Has seleccionado la opción de MOSTRAR STOCK.");
        // Llamar al método de la clase Empleado para mostrar el stock
        // Acceder a la lista de artículos a través del Empleado
        List<Articulo> stockArticulos = empleado.getListaDeArticulos();

        // Mostrar los detalles de cada artículo en el stock
        if (stockArticulos!=null){
            for (Articulo articulo : stockArticulos) {
                System.out.println(articulo.toString());
            }
        } else {
            System.out.println("No hay artículos en el listado de stock.");
        }
    }


    private void editarStock() {
        System.out.println("Has seleccionado la opción de EDITAR STOCK.");
    
        System.out.print("Ingrese el ID del artículo: ");
        int idArticulo = sc.nextInt();
    
        Articulo articuloEncontrado = buscarArticuloPorId(idArticulo);
    
        if (articuloEncontrado != null) {
            System.out.println("Información del artículo encontrado:");
            System.out.println(articuloEncontrado.toString());
    
            System.out.print("¿Desea editar este artículo? (S/N): ");
            char opcion = sc.next().charAt(0);
    
            if (opcion == 'S' || opcion == 's') {
                // Capturar los nuevos valores para el artículo
                    System.out.println("Ingrese los nuevos valores para el artículo:");
        
                    System.out.print("Nuevo nombre: ");
                    String nuevoNombre = sc.next();
                    articuloEncontrado.setNombre(nuevoNombre);
        
                    System.out.print("Nuevo precio neto: ");
                    double nuevoPrecio = sc.nextDouble();
                    articuloEncontrado.setPrecio_neto(nuevoPrecio);
        
                    System.out.print("Nuevo stock: ");
                    int nuevoStock = sc.nextInt();
                    articuloEncontrado.setStock(nuevoStock);
        
                    System.out.print("Nuevo rubro: ");
                    char nuevoRubro = sc.next().charAt(0);
                    articuloEncontrado.setRubro(nuevoRubro);
        
                    System.out.println("Artículo actualizado exitosamente.");
            }
        } else {
            System.out.println("No se encontró ningún artículo con el ID ingresado.");
        }
    }

    private Articulo buscarArticuloPorId(int idProducto) {
        for (Articulo articulo : empleado.getListaDeArticulos()) {
            if (articulo.getId_articulo() == idProducto) {
                return articulo;
            }
        }
        return null; // Si no se encuentra, retorna null
    }
    
    private void mostrarCantidadProducto() {
        System.out.println("Has seleccionado la opción de MOSTRAR CANTIDAD DE UN PRODUCTO.");
    
        // Solicitar al usuario el ID del producto
        System.out.print("Ingrese el ID del producto: ");
        int idProducto = sc.nextInt();
    
        // Buscar el producto por su ID
        Articulo producto = buscarArticuloPorId(idProducto);
    
        // Muestra la informacion del articulo o informa si no se encuentra
        if (producto != null) {
            System.out.println(producto.toString());
        } else {
            System.out.println("El producto con ID " + idProducto + " no se encuentra en el inventario.");
        }
    }

    private void agregarArticulo() {
        System.out.println("Has seleccionado la opción de AGREGAR ARTÍCULO.");
    
        // Solicitar los detalles del nuevo artículo al usuario
        System.out.print("Ingrese el ID del nuevo artículo: ");
        int idNuevoArticulo = sc.nextInt();
    
        // Verificar si el ID del artículo ya existe
        if (buscarArticuloPorId(idNuevoArticulo) != null) {
            System.out.println("Ya existe un artículo con el ID ingresado.");
            return; // Sale de la función si el ID ya existe
        }
    
        System.out.print("Ingrese el nombre del nuevo artículo: ");
        String nombreNuevoArticulo = sc.next();
    
        // Verificar si el nombre del artículo ya existe
        for (Articulo articulo : empleado.getListaDeArticulos()) {
            if (articulo.getNombre().equalsIgnoreCase(nombreNuevoArticulo)) {
                System.out.println("Ya existe un artículo con el nombre ingresado.");
                return; // Sale de la función si el nombre ya existe
            }
        }
    
        System.out.print("Ingrese el precio neto del nuevo artículo: ");
        double precioNuevoArticulo = sc.nextDouble();
    
        System.out.print("Ingrese el stock inicial del nuevo artículo: ");
        int stockNuevoArticulo = sc.nextInt();
    
        System.out.print("Ingrese el rubro del nuevo artículo: A/B/C");
        char rubroNuevoArticulo = sc.next().charAt(0);
    
        System.out.print("¿El artículo es subsidiado? (S/N): ");
        char esSubsidiado = sc.next().charAt(0);
        boolean esSubsidiadoBool = (esSubsidiado == 'S' || esSubsidiado == 's');
    
        // Crear un nuevo artículo subsidiado o simple según la respuesta del usuario
        Articulo nuevoArticulo;
        if (esSubsidiadoBool) {
            nuevoArticulo = new Subsidiado(idNuevoArticulo, nombreNuevoArticulo, precioNuevoArticulo, stockNuevoArticulo, rubroNuevoArticulo);
        } else {
            nuevoArticulo = new Simple(idNuevoArticulo, nombreNuevoArticulo, precioNuevoArticulo, stockNuevoArticulo, rubroNuevoArticulo);
        }
    
        // Agregar el nuevo artículo a la lista de artículos del empleado
        empleado.agregarArticulo(nuevoArticulo);
    
        System.out.println("El nuevo artículo ha sido agregado correctamente.");
    }

    private void eliminarArticuloPorId() {
        System.out.println("Has seleccionado la opción de ELIMINAR UN ARTÍCULO POR SU ID.");
    
        // Solicitar al usuario el ID del producto a eliminar
        System.out.print("Ingrese el ID del artículo a eliminar: ");
        int idArticulo = sc.nextInt();
    
        // Buscar el artículo por su ID
        Articulo articuloEncontrado = buscarArticuloPorId(idArticulo);
    
        // Si se encuentra el artículo, eliminarlo de la lista
        if (articuloEncontrado != null) {
            empleado.eliminarArticulo(idArticulo);
            System.out.println("El artículo ha sido eliminado correctamente.");
        } else {
            System.out.println("El artículo con ID " + idArticulo + " no se encuentra en el inventario.");
        }
    }

    private void realizarOpcion(int opcion) {
        switch (opcion) {
            case 0:
                this.finalizar();
                break;
            case 1:
                this.mostrarStock();
                break;
            case 2:
                this.editarStock();
                break;
            case 3:
                this.mostrarCantidadProducto();
                break;
            case 4:
                this.agregarArticulo();
                break;
            case 5:
                this.eliminarArticuloPorId();
                break;
            default:
                System.out.println("----------------------------------------------");
                System.out.println("OPCIÓN INCORRECTA, INGRESE UNA OPCIÓN VÁLIDA");
                System.out.println("----------------------------------------------");
                break;
        }
    }
}
