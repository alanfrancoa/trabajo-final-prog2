package menues;


import java.util.List;
import java.util.Scanner;

import interfaces.Usuario;


public class MenuPrincipal {

    private boolean continuar = true;
    private Scanner sc;
    List<Usuario> listaUsuarios;

    public MenuPrincipal(Scanner sc, List<Usuario> listaUsuarios) {
        this.sc = sc;
        this.listaUsuarios = listaUsuarios;
    }

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
        this.mostrarOpciones();
        int opcion = this.sc.nextInt();
        return opcion;
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
                MenuRegistroLogin mRegistroLogin = new MenuRegistroLogin(listaUsuarios, sc);
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
