package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import interfaces.Usuario;
import menues.MenuPrincipal;
import modelos.articulos.Articulo;
import modelos.articulos.Simple;


public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        List<Usuario> usuarios = new ArrayList<>();
        ArrayList<Articulo> listaArticulo = new ArrayList<>();

        Simple art1 = new Simple(1, "agua", 80, 100, 'A');
        Simple art2 = new Simple(2, "Cafe", 20, 800, 'C');
        Simple art3 = new Simple(3, "curitas", 100, 60, 'B');

        listaArticulo.add(art1);
        listaArticulo.add(art2);
        listaArticulo.add(art3);

        MenuPrincipal menu = new MenuPrincipal(sc, usuarios, listaArticulo);

        menu.iniciar();

        sc.close();

    }
}
