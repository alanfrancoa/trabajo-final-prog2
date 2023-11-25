package menues;

import java.util.ArrayList;
import java.util.Scanner;

import modelos.articulos.Articulo;
import modelos.articulos.Simple;
import modelos.articulos.Subsidiado;

public class MenuStock {
    private boolean continuar = true;
    private Scanner sc;
    private ArrayList<Articulo> listaArticulos;

    public MenuStock(Scanner sc, ArrayList<Articulo> listaArticulos) {
        this.sc = sc;
        this.listaArticulos = listaArticulos;
    }

    private void mostrarOpciones() {
        System.out.println("---------------------------------------------");
        System.out.println("GESTIÓN DE STOCK");
        System.out.println(" 0 - SALIR");
        System.out.println(" 1 - CREAR ARTÍCULO");
        System.out.println(" 2 - EDITAR ARTÍCULO");
        System.out.println(" 3 - ELIMINAR ARTÍCULO");
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
        System.out.println("HAS SALIDO DEL MENÚ DE GESTIÓN DE STOCK");
        System.out.println("----------------------------------------------");
    }

    private void crearArticulo() {
        // Lógica para crear un nuevo artículo y añadirlo a la lista de artículos
        System.out.println("Has seleccionado la opción de CREAR ARTÍCULO.");
        System.out.print("Ingrese nombre de articulo: ");
        String nombre = sc.next();
        if (existeArticulo(nombre)){
            System.out.println("Articulo existente.");
        } else {
            System.out.print("Ingrese ID: ");
            int id = sc.nextInt();
            System.out.println("");
            System.out.print("Ingrese precio: ");
            double precio = sc.nextDouble();
            System.out.println("");
            System.out.print("Ingrese Stock: ");
            int stock = sc.nextInt();
            System.out.println("");
            System.out.println("A que rubro pertenece? A/B/C");
            String rubroInput = sc.next().toUpperCase();
            char rubro = rubroInput.charAt(0);
            System.out.println("");
            System.out.println("El producto es subsidiado? S/N");
            String subInput = sc.next();
            if (subInput.equalsIgnoreCase("S")){
                Subsidiado producto = new Subsidiado(id, nombre, precio, stock, rubro);
                listaArticulos.add(producto);
            } else if (subInput.equalsIgnoreCase("N")){
                Simple producto = new Simple(rubro, nombre, precio, stock, rubro);
                listaArticulos.add(producto);
            }
        }
        

    }

    private boolean existeArticulo(String nombre){
        for (Articulo articulo : listaArticulos) {
            if (articulo.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false; // Si no se encuentra el artículo, retorna falso
    }

    private void editarArticulo() {
        // Lógica para editar un artículo existente en la lista
        System.out.println("Has seleccionado la opción de EDITAR ARTÍCULO.");
        // Implementa la lógica para editar un artículo existente en listaArticulos
    }

    private void eliminarArticulo() {
        // Lógica para eliminar un artículo de la lista
        System.out.println("Has seleccionado la opción de ELIMINAR ARTÍCULO.");
        // Implementa la lógica para eliminar un artículo de listaArticulos
    }

    private void realizarOpcion(int opcion) {
        switch (opcion) {
            case 0:
                this.finalizar();
                break;
            case 1:
                this.crearArticulo();
                break;
            case 2:
                this.editarArticulo();
                break;
            case 3:
                this.eliminarArticulo();
                break;
            default:
                System.out.println("----------------------------------------------");
                System.out.println("OPCIÓN INCORRECTA, INGRESE UNA OPCIÓN VÁLIDA");
                System.out.println("----------------------------------------------");
                break;
        }
    }
}