package menues;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

import interfaces.Usuario;
import modelos.articulos.Articulo;

public class MenuPrincipal {

    private boolean continuar = true;
    private Scanner sc;
    List<Usuario> listaUsuarios;
    ArrayList<Articulo> listaArticulo;

    public MenuPrincipal(Scanner sc, List<Usuario> listaUsuarios, ArrayList<Articulo> listaArticulo) {
        this.sc = sc;
        this.listaUsuarios = listaUsuarios;
        this.listaArticulo = listaArticulo;
    }

    // Genericos

    private void mostrarOpciones() {
        System.out.println("---------------------------------------------");
        System.out.println("BIENVENIDO A PURIN MARKET");
        System.out.println(" 0 - SALIR DEL PROGRAMA");
        System.out.println(" 1 - REGISTRO/LOGIN");
        System.out.println("---------------------------------------------");
        System.out.print("Por favor, elija una de las siguientes opciones: ");
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
        System.out.println("HAS SALIDO DEL PROGRAMA, QUE TENGAS UN BUEN DIA");
        System.out.println("----------------------------------------------");
    }

    private void realizarOpcion(int opcion) {
        switch (opcion) {
            case 0:
                this.finalizar();
                break;
            case 1:
                MenuRegistroLogin mRegistroLogin = new MenuRegistroLogin(listaUsuarios, sc, listaArticulo);
                mRegistroLogin.iniciar();
                break;
            default:
                System.out.println("----------------------------------------------");
                System.out.println("OPCION INCORRECTA, INGRESE UNA OPCION VALIDA");
                System.out.println("----------------------------------------------");
                break;
        }
    }
}
