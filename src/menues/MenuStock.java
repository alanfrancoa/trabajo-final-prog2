package menues;

import java.util.ArrayList;
import java.util.Scanner;

import modelos.articulos.Articulo;
import modelos.articulos.Simple;
import modelos.articulos.Subsidiado;

public class MenuStock {

    // Atributos de la clase
    private boolean continuar = true;
    private Scanner sc;
    private ArrayList<Articulo> listaArticulos;

    // Constructor
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

    private void mostrarOpcionRubros() {
        System.out.println("---------------------------------------------");
        System.out.println("Elija un Rubro: ");
        System.out.println("A");
        System.out.println("B");
        System.out.println("C");
        System.out.println("---------------------------------------------");
        System.out.print("Por favor, elija uno de los rubros: ");
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

    private char elegirRubro() {

        boolean seguir = true;

        // Variable que va a guardar el resultado
        char rubroAsignado = ' ';

        // Hago el bucle
        while (seguir) {
            this.mostrarOpcionRubros();

            // Variable que guarda mi eleccion
            char rubroElegido = this.sc.next().toUpperCase().charAt(0);

            switch (rubroElegido) {
                case 'A':
                    rubroAsignado = 'A';
                    seguir = false;
                    break;
                case 'B':
                    rubroAsignado = 'B';
                    seguir = false;
                    break;
                case 'C':
                    rubroAsignado = 'C';
                    seguir = false;
                    break;

                default:
                    System.out.println("Opcion invalida. Por favor, ingrese una opcion valida");
                    break;
            }

        }

        return rubroAsignado;
    }

    private void finalizar() {
        this.continuar = false;
        System.out.println("----------------------------------------------");
        System.out.println("HAS SALIDO DEL MENÚ DE GESTIÓN DE STOCK");
        System.out.println("----------------------------------------------");
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

    // Lógica para crear un nuevo artículo y añadirlo a la lista de artículos
    private void crearArticulo() {

        // Creo el articulo ingresando los valores dados
        System.out.println("Has seleccionado la opción de CREAR ARTÍCULO.");

        // Primero ingreso un numero de ID
        System.out.print("Ingrese el numero de identificacion del articulo: ");
        int id = sc.nextInt();

        // Valido que el numero de ID ingresado no lo este ocupando un articulo
        boolean existeArticulo = this.existeArticulo(id);

        // Si existe entonces muestro un mensaje de Error
        if (existeArticulo) {
            System.out.println("-------------------------------------");
            System.out.println("ERROR! El id del articulo ya existe, por favor ingrese otro");
            System.out.println("-------------------------------------");
            return;
        }

        // Si no coincide, procedo con el registro del Articulo

        // Ingreso el nombre del articulo
        System.out.println("------------------------------");
        System.out.print("Ingrese nombre de articulo: ");
        String nombre = sc.next();

        // Ingreso el precio del Articulo
        System.out.println("------------------------------");
        System.out.print("Ingrese precio: ");
        double precio = sc.nextDouble();

        // Ingrese el stock del articulo
        System.out.println("------------------------------");
        System.out.print("Ingrese Stock: ");
        int stock = sc.nextInt();

        // Ingreso el rubro al que pertenece el articulo
        System.out.println("------------------------------");
        char rubroInput = this.elegirRubro();

        // Determino si el articulo que ingrese es subsidiado o no
        System.out.println("------------------------------");
        System.out.println("El producto es subsidiado? S/N");
        char subInput = sc.next().toUpperCase().charAt(0);

        switch (subInput) {
            case 'S':
                Subsidiado pSubsidiado = new Subsidiado(id, nombre, precio, stock, rubroInput);
                listaArticulos.add(pSubsidiado);
                break;
            case 'N':
                Simple pSimple = new Simple(id, nombre, precio, stock, rubroInput);
                listaArticulos.add(pSimple);
                break;
            default:
                System.out.println("-------------------------------------");
                System.out.println("ERROR! Por favor, elija una opcion correcta.");
                System.out.println("-------------------------------------");
                break;
        }

    }

    // Meotod para validar la existencia de un Articulo
    private boolean existeArticulo(int numeroIngresado) {
        for (Articulo articulo : listaArticulos) {
            if (articulo.getId_articulo() == numeroIngresado) {
                return true;
            }
        }
        return false; // Si no se encuentra el artículo, retorna falso
    }

    // Metodo que sirve para editar un Articulo
    private void editarArticulo() {
        // Lógica para editar un artículo existente en la lista
        System.out.println("Has seleccionado la opción de EDITAR ARTÍCULO.");
        // Implementa la lógica para editar un artículo existente en listaArticulos
    }

    // Metodo que sirve para eliminar un Articulo
    private void eliminarArticulo() {
        // Lógica para eliminar un artículo de la lista
        System.out.println("Has seleccionado la opción de ELIMINAR ARTÍCULO.");
        // Implementa la lógica para eliminar un artículo de listaArticulos
    }

}
