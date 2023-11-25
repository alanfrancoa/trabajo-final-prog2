package menues;

import java.util.Scanner;

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
        System.out.println("MENÚ DE OPCIONES - EMPLEADO");
        System.out.println(" 0 - SALIR");
        System.out.println(" 1 - MOSTRAR STOCK");
        System.out.println(" 2 - EDITAR STOCK");
        System.out.println(" 3 - MOSTRAR CANTIDAD DE UN PRODUCTO");
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
        // Lógica para mostrar el stock
        System.out.println("Has seleccionado la opción de MOSTRAR STOCK.");

    }

    private void editarStock() {
        // Lógica para editar el stock ya sea agregar, editar o eliminar.
        System.out.println("Has seleccionado la opción de EDITAR STOCK.");

    }

    private void mostrarCantidadProducto() {
        // Lógica para mostrar cantidad de un producto
        System.out.println("Has seleccionado la opción de MOSTRAR CANTIDAD DE UN PRODUCTO.");

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
            default:
                System.out.println("----------------------------------------------");
                System.out.println("OPCIÓN INCORRECTA, INGRESE UNA OPCIÓN VÁLIDA");
                System.out.println("----------------------------------------------");
                break;
        }
    }
}
