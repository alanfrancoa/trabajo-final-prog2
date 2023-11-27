package menues;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import modelos.articulos.Articulo;
import modelos.articulos.Demandado;
import modelos.articulos.Simple;
import modelos.articulos.Subsidiado;
import modelos.usuarios.Empleado;

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
        System.out.println("BIENVENIDO " + this.empleado.getNombreUsuario());
        System.out.println("MENÚ DE OPCIONES - EMPLEADO");
        System.out.println(" 0 - SALIR");
        System.out.println(" 1 - MOSTRAR ARTICULOS");
        System.out.println(" 2 - CREAR ARTICULO");
        System.out.println(" 3 - EDITAR ARTICULO");
        System.out.println(" 4 - ELIMINAR ARTICULO");
        System.out.println(" 5 - MOSTRAR Y EDITAR STOCK DE UN PRODUCTO");
        System.out.println("---------------------------------------------");
        System.out.print("Por favor, elija una opción: ");
    }

    private void mostrarOpcinesTipoDeArticulo() {

        System.out.println("---------------------------------------------");
        System.out.println(" 1 - SUBSIDIADO");
        System.out.println(" 2 - DEMANDADO");
        System.out.println(" 3 - SIMPLE");
        System.out.println("---------------------------------------------");
        System.out.print("Por favor, elija una opción: ");
    }

    private void mostrarOpcionesEditar() {
        System.out.println("---------------------------------------------");
        System.out.println("¿Desea editar este artículo?: ");
        System.out.println("1 - SI");
        System.out.println("1 - NO");
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

    private void finalizar() {
        this.continuar = false;
        System.out.println("----------------------------------------------");
        System.out.println("HAS SALIDO DEL MENÚ DE EMPLEADO");
        System.out.println("----------------------------------------------");
    }

    private void realizarOpcion(int opcion) {

        switch (opcion) {
            case 1:
                this.verListadoArticulos();
                break;
            case 2:
                this.agregarArticulo();
                break;
            case 3:
                this.editarArticulo();
                break;
            case 4:
                this.eliminarArticulo();
                break;
            case 5:
                this.mostrarYEditarStock();
                break;
            case 0:
                this.finalizar();
                break;
            default:
                System.out.println("----------------------------------------------");
                System.out.println("ERROR: OPCION INVALIDA! ");
                System.out.println("----------------------------------------------");
                break;
        }
    }

    private void verListadoArticulos() {
        System.out.println("Has seleccionado la opción de VER LISTADO DE ARTICULOS.");

        // Acceder a la lista de artículos a través del Empleado
        List<Articulo> stockArticulos = empleado.getListaDeArticulos();

        if (stockArticulos == null) {
            System.out.println("----------------------------------------------");
            System.out.println("ERROR: NO HAY ARTICULOS EN LA LISTA");
            System.out.println("----------------------------------------------");
            return;
        }

        System.out.println("------------------- LISTA DE ARTICULOS -------------------");
        for (Articulo articulo : stockArticulos) {

            System.out.println(articulo.toString());

        }
        System.out.println("----------------------------------------------------------");
    }

    private void agregarArticulo() {
        System.out.println("----------------------------------------------------");
        System.out.println("Has seleccionado la opción de AGREGAR ARTICULO.");

        int idIngresado = this.ingresarIdArticulo();

        // Verifico si el articulo ya existe
        Articulo existeArticulo = this.validarExistenciaArticulo(idIngresado);

        if (existeArticulo != null) {
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("ERROR: El articulo ya existe. Intente ingresar otro articulo. ");
            System.out.println("------------------------------------------------------------------------------");
            return;
        }

        // Si pasa la validacion sigo con el registro del articulo
        System.out.println("--------------------------------------.");
        System.out.print("Ingrese el nombre del artículo: ");
        String nombreArticuloIngresado = this.sc.next();

        // Valido que sea un String el valor ingresado
        if (!this.validarString(nombreArticuloIngresado)) {
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("ERROR: El nombre del articulo debe contener solo letras. Intente nuevamente. ");
            System.out.println("------------------------------------------------------------------------------");
            return;
        }

        // Valido que no se repita el nombre ingresado
        boolean articuloRepetido = this.validarNombreArticuloRepetido(nombreArticuloIngresado);

        if (articuloRepetido) {
            System.out.println("----------------------------------------------");
            System.out.println("ERROR: Ya existe un articulo con el nombre ingresado. ");
            System.out.println("----------------------------------------------");
            return;
        }
        // Si paso la validacion sigo con el registro del Articulo

        System.out.println("--------------------------------------.");
        double precioNetoIngresado = this.ingresarPrecioNeto();
        System.out.println("--------------------------------------.");
        System.out.print("Ingrese el stock del articulo: ");
        int stockIngresado = this.ingresarStock();
        System.out.println("--------------------------------------.");
        char rubroIngresado = this.ingresarRubro();

        if (rubroIngresado != 'A' && rubroIngresado != 'B' && rubroIngresado != 'C') {
            System.out.println("----------------------------------------------");
            System.out.println("ERROR: ELIJA UN RUBRO VALIDO ");
            System.out.println("----------------------------------------------");
            return;
        }

        System.out.println("--------------------------------------.");
        int tipoIngresado = this.ingresarTipoDeArticulo();

        Articulo nuevoArticulo;

        switch (tipoIngresado) {
            case 1:
                nuevoArticulo = new Subsidiado(idIngresado, nombreArticuloIngresado, precioNetoIngresado, tipoIngresado,
                        rubroIngresado);
                break;
            case 2:
                System.out.print("Ingrese el stock deseado para el artículo Por Demanda: ");
                int stockDeseado = this.ingresarStock();
                nuevoArticulo = new Demandado(idIngresado, nombreArticuloIngresado, precioNetoIngresado, tipoIngresado,
                        rubroIngresado, stockDeseado);
                break;
            case 3:
                nuevoArticulo = new Simple(idIngresado, nombreArticuloIngresado, precioNetoIngresado, stockIngresado,
                        rubroIngresado);
                break;
            default:
                System.out.println("Tipo de artículo no válido. Se creará como Simple por defecto.");
                nuevoArticulo = new Subsidiado(idIngresado, nombreArticuloIngresado, precioNetoIngresado, tipoIngresado,
                        rubroIngresado);
        }

        empleado.agregarArticulo(nuevoArticulo);

        System.out.println("----------------------------------------------");
        System.out.println("ARTICULO CREADO Y AGREGADO EXITOSAMENTE !!!! ");
        System.out.println("----------------------------------------------");
    }

    private void eliminarArticulo() {
        System.out.println("----------------------------------------------------");
        System.out.println("Has seleccionado la opción de ELIMINAR UN ARTÍCULO.");

        this.verListadoArticulos();

        // Solicitar al usuario el ID del producto a eliminar

        int idArticulo = this.ingresarIdArticulo();

        // Buscar el artículo por su ID
        Articulo articuloEncontrado = this.validarExistenciaArticulo(idArticulo);

        // Si se encuentra el artículo, eliminarlo de la lista
        if (articuloEncontrado == null) {
            System.out.println("----------------------------------------------");
            System.out.println("ERROR: El artículo con ID " + idArticulo + " no se encuentra en el inventario.");
            System.out.println("----------------------------------------------");
            return;
        }

        empleado.eliminarArticulo(idArticulo);

        System.out.println("----------------------------------------------");
        System.out.println("ARTICULO ELIMINADO CORRECTAMENTE ");
        System.out.println("----------------------------------------------");

    }

    private void editarArticulo() {
        System.out.println("----------------------------------------------------");
        System.out.println("Has seleccionado la opción de EDITAR UN ARTICULO.");

        this.verListadoArticulos();

        // Solicitar al usuario el ID del producto a eliminar

        int idArticulo = this.ingresarIdArticulo();

        // Buscar el artículo por su ID
        Articulo articuloEncontrado = this.validarExistenciaArticulo(idArticulo);

        // Si se encuentra el artículo, eliminarlo de la lista
        if (articuloEncontrado == null) {
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("ERROR: El artículo con ID " + idArticulo + " no se encuentra en el inventario.");
            System.out.println("----------------------------------------------------------------------------");
            return;
        }

        System.out.println("------------------------- ARTICULO ENCONTRADO -------------------------");
        articuloEncontrado.toString();
        System.out.println("-----------------------------------------------------------------------");

        int opcion = this.ingresarOpcionEditar();

        switch (opcion) {
            case 1:
                // Capturar los nuevos valores para el artículo
                System.out.println("Ingrese los nuevos valores para el artículo");

                System.out.print("Nuevo nombre: ");
                String nuevoNombre = sc.next();
                articuloEncontrado.setNombre(nuevoNombre);

                // Valido que sea un String el valor ingresado
                if (!this.validarString(nuevoNombre)) {
                    System.out
                            .println("------------------------------------------------------------------------------");
                    System.out.println("ERROR: El nombre del articulo debe contener solo letras. Intente nuevamente. ");
                    System.out
                            .println("------------------------------------------------------------------------------");
                    return;
                }

                // Valido que no se repita el nombre ingresado
                boolean articuloRepetido = this.validarNombreArticuloRepetido(nuevoNombre);

                if (articuloRepetido) {
                    System.out.println("----------------------------------------------");
                    System.out.println("ERROR: Ya existe un articulo con el nombre ingresado. ");
                    System.out.println("----------------------------------------------");
                    return;
                }

                System.out.print("Nuevo precio neto: ");
                double nuevoPrecio = this.ingresarPrecioNeto();
                articuloEncontrado.setPrecio_neto(nuevoPrecio);

                System.out.print("Nuevo rubro: ");
                char nuevoRubro = this.ingresarRubro();
                articuloEncontrado.setRubro(nuevoRubro);

                System.out.println("-------------------------------------------");
                System.out.println("ARTICULO ACTUALIZADO CORRECTAMENTE");
                System.out.println("-------------------------------------------");
                break;
            case 2:
                System.out.println("-------------------------------------------");
                System.out.println("HA CANCELADO LA ACTUALIZACION DEL ARTICULO");
                System.out.println("-------------------------------------------");
                break;

            default:
                System.out.println("----------------------------------------------------------------------------");
                System.out.println("ERROR: Opcion invalida, elija una opcion correcta.");
                System.out.println("----------------------------------------------------------------------------");
                break;
        }
    }

    private void mostrarYEditarStock() {
        System.out.println("Has seleccionado la opción de EDITAR STOCK.");

        this.verListadoArticulos();

        // Solicitar al usuario el ID del producto a eliminar

        int idArticulo = this.ingresarIdArticulo();

        // Buscar el artículo por su ID
        Articulo articuloEncontrado = this.validarExistenciaArticulo(idArticulo);

        // validar la existencia del Articulo encontrado
        if (articuloEncontrado == null) {
            System.out.println("----------------------------------------------");
            System.out.println("ERROR: El artículo con ID " + idArticulo + " no se encuentra en el inventario.");
            System.out.println("----------------------------------------------");
            return;
        }

        System.out.println("------------------------- ARTICULO ENCONTRADO -------------------------");
        articuloEncontrado.toString();
        System.out.println("-----------------------------------------------------------------------");

        System.out.print("Ingrese una cantidad para agregar al stock: ");
        int cantidadIngresada = this.ingresarStock();

        if (cantidadIngresada < 0) {
            System.out.println("----------------------------------------------");
            System.out.println("ERROR: INGRESE UNA CANTIDAD MAYOR A CERO");
            System.out.println("----------------------------------------------");
            return;
        }

        int stockArticuloActual = articuloEncontrado.getStock();
        articuloEncontrado.setStock(stockArticuloActual + cantidadIngresada);

        System.out.println("----------------------------------------------");
        System.out.println("STOCK EDITADO CORRECTAMENTE");
        articuloEncontrado.toString();
        System.out.println("----------------------------------------------");

    }

    // Metodos del Menu

    private Articulo validarExistenciaArticulo(int idIngresado) {

        // Parto con una premisa booleana
        Articulo existeArticulo = null;

        // Recorro la lista de Usuarios
        for (Articulo articulo : empleado.getListaDeArticulos()) {
            if (articulo.getId_articulo() == idIngresado) {
                existeArticulo = articulo;
                break;
            }
        }

        // Devuelvo el valor final de la premisa
        return existeArticulo;
    }

    private boolean validarNombreArticuloRepetido(String nombreIngresado) {

        // Parto con una premisa booleana
        boolean existeArticulo = false;

        // Recorro la lista de Usuarios
        for (Articulo articulo : empleado.getListaDeArticulos()) {
            if (articulo.getNombre().equalsIgnoreCase(nombreIngresado)) {
                existeArticulo = true;
                break;
            }
        }

        // Devuelvo el valor final de la premisa
        return existeArticulo;
    }

    private boolean validarString(String nombreIngresado) {
        boolean esValido = nombreIngresado.matches("[a-zA-Z]+");
        return esValido;
    }

    private int ingresarIdArticulo() {
        while (true) {
            int id_articulo;
            try {
                System.out.println("Ingrese un codigo de identificacion del Articulo: ");
                id_articulo = this.sc.nextInt();
                return id_articulo;
            } catch (InputMismatchException e) {
                this.sc.nextLine();
                System.out.println("----------------------------------------------");
                System.out.println("ERROR: INGRESE UN VALOR NUMERICO");
                System.out.println("----------------------------------------------");
            }
        }
    }

    private double ingresarPrecioNeto() {
        while (true) {
            double precioNeto = 0;
            try {
                System.out.print("Ingrese el precio neto del artículo: ");
                precioNeto = this.sc.nextDouble();
                return precioNeto;
            } catch (NumberFormatException e) {
                this.sc.nextLine();
                System.out.println("----------------------------------------------");
                System.out.println("ERROR: INGRESE UN VALOR NUMERICO");
                System.out.println("----------------------------------------------");
            }
        }
    }

    private int ingresarStock() {
        while (true) {
            int stock;
            try {
                stock = this.sc.nextInt();
                return stock;
            } catch (NumberFormatException e) {
                this.sc.nextLine();
                System.out.println("----------------------------------------------");
                System.out.println("ERROR: INGRESE UN VALOR NUMERICO");
                System.out.println("----------------------------------------------");
            }
        }
    }

    private char ingresarRubro() {
        while (true) {
            char rubro;
            try {
                System.out.print("Ingrese el rubro del artículo (A, B, C): ");
                rubro = this.sc.next().toUpperCase().charAt(0);
                return rubro;
            } catch (InputMismatchException e) {
                this.sc.nextLine();
                System.out.println("----------------------------------------------");
                System.out.println("ERROR: INGRESE UN LETRA");
                System.out.println("----------------------------------------------");
            }
        }
    }

    private int ingresarTipoDeArticulo() {
        while (true) {
            int opcion;
            try {
                this.mostrarOpcinesTipoDeArticulo();
                opcion = this.sc.nextInt();
                return opcion;
            } catch (NumberFormatException e) {
                this.sc.nextLine();
                System.out.println("----------------------------------------------");
                System.out.println("ERROR: INGRESE UN VALOR NUMERICO");
                System.out.println("----------------------------------------------");
            }
        }
    }

    private int ingresarOpcionEditar() {
        while (true) {
            int opcion;
            try {
                this.mostrarOpcionesEditar();
                opcion = this.sc.nextInt();
                return opcion;
            } catch (InputMismatchException e) {
                this.sc.nextLine();
                System.out.println("----------------------------------------------");
                System.out.println("ERROR: INGRESE UN VALOR NUMERICO");
                System.out.println("----------------------------------------------");
            }
        }
    }

}
