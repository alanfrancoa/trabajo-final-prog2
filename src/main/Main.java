package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import interfaces.Usuario;
import menues.MenuPrincipal;

public class Main {
    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);
        List<Usuario> usuarios = new ArrayList<>();

       MenuPrincipal menu = new MenuPrincipal(sc, usuarios);

        menu.iniciar();

        sc.close();

    }
}
