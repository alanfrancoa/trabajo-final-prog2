package menues;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import interfaces.Usuario;
import modelos.usuarios.Cliente;
import modelos.usuarios.Empleado;

public class MenuRegistroLogin {

    // Atributos
    List<Usuario> listaUsuarios;
    private Scanner sc;
    private boolean continuar = true;

    // Constructor
    public MenuRegistroLogin(List<Usuario> listaUsuarios, Scanner sc) {
        this.listaUsuarios = listaUsuarios;
        this.sc = sc;
    }

    // Genericos

    // Muestras las opciones de Login o Registro
    private void mostrarOpciones() {
        System.out.println("---------------------------------------------");
        System.out.println("BIENVENIDO AL SISTEMA DE ALTA DE USUARIO");
        System.out.println(" 1 - LOGIN");
        System.out.println(" 2 - REGISTRO");
        System.out.println("---------------------------------------------");
        System.out.print("Por favor, elija una de las siguientes opciones: ");
    }

    // Muestra las opciones de rol que puede elegir el usuario
    private void mostrarOpcionRoles() {
        System.out.println("---------------------------------------------");
        System.out.println("Elija un rol eligiendo una de las opciones numericas: ");
        System.out.println(" 1 - CLIENTE");
        System.out.println(" 2 - EMPLEADO");
        System.out.println("---------------------------------------------");
        System.out.print("Por favor, elija uno de los roles: ");
    }

    // Funcion para iniciar el programa
    public void iniciar() {
        while (continuar) {
            int opcion = this.elegirOpcion();
            this.realizarOpcion(opcion);
        }
    }

    // Funcion para elegir una opcion
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

    // Funcion que sirve para elegir el rol ya sea para el registro como para el

    private String elegirRol() {

        while (true) {
            int opcion;
            String rolAsignado = "";
            try {
                this.mostrarOpcionRoles();
                opcion = this.sc.nextInt();
                switch (opcion) {
                    case 1:
                        rolAsignado = "cliente";
                        break;
                    case 2:
                        rolAsignado = "empleado";
                        break;

                    default:
                        System.out.println("----------------------------------------------");
                        System.out.println("ERROR: INGRESE UNA OPCIÓN VALIDA");
                        System.out.println("----------------------------------------------");
                        break;
                }

                return rolAsignado;

            } catch (InputMismatchException e) {
                this.sc.nextLine();
                System.out.println("----------------------------------------------");
                System.out.println("ERROR: INGRESE UNA OPCIÓN NUMERICA");
                System.out.println("----------------------------------------------");
            }

        }

    }

    // Funcion que realiza las acciones de LOGIN o de REGISTRO dependiendo de lo que
    // elijamos
    private void realizarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                this.loginUsuario();
                break;
            case 2:
                this.registroUsuario();
                break;
            default:
                System.out.println("----------------------------------------------");
                System.out.println("OPCION INCORRECTA, INGRESE UNA OPCION VALIDA");
                System.out.println("----------------------------------------------");
                break;
        }
    }

    // Metodos del menu

    // Registro de usuarios

    /*
     * Esta funcion se va a encargar de la logica de>
     * - Registro del usuario
     * - Alta de usuario (EMPLEADO o CLIENTE)
     * - Guardar el usuario en la lista de usuarios
     */

    private void registroUsuario() {
        System.out.println("--------------------- REGISTRO ---------------------");

        // Primero comienzo ingresando los datos necesarios para registrar el nuevo
        // Usuario

        // Ingreso el nombre del usuario
        System.out.print("Nombre de usuario: ");
        String nombreIngresado = this.sc.next();

        // Validaciones con el nombre ingresado

        // Verifico si el valor ingresado es un String
        if (!validarString(nombreIngresado)) {
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("ERROR: El nombre de usuario debe contener solo letras. Intente nuevamente. ");
            System.out.println("------------------------------------------------------------------------------");
            return;
        }

        // Verifico si el nombre de usuario ingresado ya existe
        boolean existeUsuario = this.validarExistenciaUsuario(nombreIngresado);

        // Si el nombre del usuario existe lanzo un mensaje de error
        if (existeUsuario) {
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("ERROR: El nombre de Usuario ya existe. Por favor, elija otro nombre de usuario.");
            System.out.println("------------------------------------------------------------------------------");
            return;
        }

        // Si pasaron ambas validaciones sigo con el Registro

        // Ingreso la clave
        System.out.print("Contraseña: ");
        String claveIngresada = this.sc.next();

        // Reingreso la clave
        System.out.print("Ingrese la contraseña nuevamente: ");
        String confirmarClave = this.sc.next();

        // Valido si ambas claves ingresadas son iguales
        boolean sonIguales = this.validarClaves(claveIngresada, confirmarClave);

        if (!sonIguales) {
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("ERROR: Las constraseñas no coinciden, intente nuevamente.");
            System.out.println("------------------------------------------------------------------------------");
            return;
        }

        // Si paso la validacion, sigo con el registro del usuario

        // Elijo un rol
        String rolIngresado = this.elegirRol();

        // Si el valor de rolIngresado es "empleado" procedo con la verificacion extra

        if (rolIngresado.equals("empleado")) {
            System.out.print("Ingrese la clave de empleado: ");
            String claveEmpleado = this.sc.next();

            // Si la clave ingresada no es igual a la clave secreta para registrar el
            // empleado lanzo un mensaje de error
            if (!claveEmpleado.equals("pepepiola123")) {
                System.out.println("--------------------------------------------------------");
                System.out.println("ERROR: Clave incorrecta, registro de Empleado fallido");
                System.out.println("--------------------------------------------------------");
                return;
            }

            // Si la clave secreta es correcta creo el nuevo empleado
            Empleado nuevoEmpleado = new Empleado(nombreIngresado, claveIngresada);

            // Guardo ese nuevo empleado en la lista de Usuarios
            listaUsuarios.add(nuevoEmpleado);

            // Muestro un mesaje de registro exitoso
            System.out.println("--------------------------------------------------------");
            System.out.println("REGISTRO EXITOSO! Se ha registrado un nuevo Empleado");
            System.out.println("--------------------------------------------------------");

            return;
        }

        // Si el rol que se le asigno es CLIENTE procedo a crear el nuevo cliente
        Cliente nuevoCliente = new Cliente(nombreIngresado, claveIngresada, 0);

        // Almaceno el nuevo cliente en la lista de clientes
        listaUsuarios.add(nuevoCliente);
        System.out.println("--------------------------------------------------------");
        System.out.println("REGISTRO EXITOSO! Se ha registrado un nuevo Cliente");
        System.out.println("--------------------------------------------------------");
        return;

    }

    // Logueo de Usuarios

    /*
     * Esta funcion se va a encargar de la logica de:
     * - Logueo del usuario
     * - Acceso al menu de usuario dependendiendo del rol que se le asigno
     */

    private void loginUsuario() {

        System.out.println("--------------------- LOGIN ---------------------");

        // Ingreso el nombre de Usuario
        System.out.print("Nombre de usuario: ");
        String nombreUsuario = this.sc.next();

        // Valido que exista dicho usuario
        boolean existeUsuario = this.validarExistenciaUsuario(nombreUsuario);

        // Si el usuario no existe lanzo un mensaje de error
        if (!existeUsuario) {
            System.out.println("------------------------------------------------------------");
            System.out.println("ERROR: El usuario no existe, regístrese o inténtelo de nuevo");
            System.out.println("------------------------------------------------------------");
            return;
        }

        // Si paso la validacion procedo a seguir con el logueo

        // Ingreso la contraseña del usuario
        System.out.print("Contraseña: ");
        String claveUsuario = this.sc.next();

        // Valido que la contraseña sea la correcta
        boolean validarClave = this.validarClave(claveUsuario);

        // Si la clave es incorrecta lanzo un mensaje de error
        if (!validarClave) {
            System.out.println("------------------------------------------------------------");
            System.out.println("ERROR: Contraseña incorrecta, inicio de sesión fallido.");
            System.out.println("------------------------------------------------------------");
            return;
        }

        System.out.println("------------------------------------------------------------");
        System.out.println("INICIO DE SESIÓN EXITOSO! Bienvenidx!");
        System.out.println("------------------------------------------------------------");

        this.direccionarAUnMenu(nombreUsuario, claveUsuario);

        return;

    }

    // Verificar la existencia del Usuario

    /*
     * Esta funcion se va a encargar de:
     * - Validar la existencia del usuario
     * - Para que se valide su existencia recibe por parámetro:
     * - El nombre del usuario (String)
     */
    private boolean validarExistenciaUsuario(String nombreIngresado) {

        // Parto con una premisa booleana
        boolean existeUsuario = false;

        // Recorro la lista de Usuarios
        for (Usuario usuario : listaUsuarios) {

            // Por cada usuario obtengo su nombre
            if (usuario.getNombreUsuario().equals(nombreIngresado)) {
                // Si el nombre del usuario de turno es igual al nombreIngresado entonces la
                // premisa es TRUE
                existeUsuario = true;
                break;
            }
        }

        // Devuelvo el valor final de la premisa
        return existeUsuario;
    }

    // Validar igualdad de la contraseña reingresada
    /*
     * Esta funcion se va a encargar de:
     * - Validar que ambas claves en el registro sean identicas
     * - Para que se valide su existencia recibe por parámetro:
     * - la clave (String)
     * - la clave reingresada (String)
     */
    private boolean validarClaves(String clave, String claveReingresada) {

        // Parto con una premisa booleana
        boolean sonIguales = false;

        // Comparo las dos claves que recibi por parámetro
        if (clave.equals(claveReingresada)) {

            // Si son iguales entonces el valor de mi premisa será TURE
            sonIguales = true;
        }

        // Devuelvo el valor final de la premisa
        return sonIguales;
    }

    // Validar la contraseña
    /*
     * Esta funcion se va a encargar de:
     * - Validar que la clave ingresada sea identica a la clave del usuario
     * - Para que se valide su existencia recibe por parámetro:
     * - la clave reingresada (String)
     */
    private boolean validarClave(String claveIngresada) {

        // Parto con una premisa booleana
        boolean sonIguales = false;

        // Recorro la lista de Usuarios
        for (Usuario usuario : listaUsuarios) {
            // Si la claveIngresada es igual a la clave de algun usuario de turno
            if (claveIngresada.equals(usuario.getClaveUsuario())) {
                // Entonces la premisa es TRUE
                sonIguales = true;
            }
        }

        // Devuelvo el valor final de la premisa
        return sonIguales;
    }

    // Direccionar a un Menu
    /*
     * Esta funcion se va a encargar de:
     * - Dependiendo del Usuario determina que tipo de Usuario es (Cliente o Empleado)
     * - Dependiendo del tipo de Usuario me va a dirigir al menu específico
     * - La función recibe por parpámetro:
     * - El nombre del Usuario ya validado (String)
     * - La contraseña del Usuario ya validada (String)
     */
    private void direccionarAUnMenu(String nombreUsuario, String claveUsuario) {

        // Obtengo el usuario mediante el nombre y la clave ingresada
        Usuario usuarioEncontrado = this.getUsuario(nombreUsuario, claveUsuario);

        // Verifico si el usuarioEncontrado sea Cliente o Empleado

        if (usuarioEncontrado instanceof Cliente) {

            // Convierto al usuarioEncontrado en Cliente
            Cliente cliente = (Cliente) usuarioEncontrado;

            MenuCliente mCliente = new MenuCliente(sc, cliente, listaUsuarios);

            // EjecutO la funcion iniciar() de MenuCLiente
            mCliente.iniciar();

        } else if (usuarioEncontrado instanceof Empleado) {

            // Convierto al usuarioEncontrado en Empleado
            Empleado empleado = new Empleado(nombreUsuario, claveUsuario);

            // Instancio el menu de empleado que reciber el Scanner y el Empleado
            MenuEmpleado mEmpleado = new MenuEmpleado(sc, empleado);

            // Ejecuto la funcion iniciar() de Menu Empleado
            mEmpleado.iniciar();
        }
    }

    // Obtener un Usuario
    /*
     * Esta funcion se va a encargar de:
     * - Obtener un usuario 
     * - Para obtener un Usuario recibe por parámetro la siguiente información:
     * - El nombre de Usuario (String)
     * - La contraseña del Usuario (String)
     */
    private Usuario getUsuario(String nombreUsuario, String claveUsuario) {

        // Variable axuliar que guardara un valor
        Usuario usuarioEncontrado = null;

        // Recorro la listaUsuarios
        for (Usuario usuario : listaUsuarios) {

            // Por cada usuario valido que su nombre sea igual al nombre ingresado y que su clave sea igual a la clave ingresada
            if (nombreUsuario.equals(usuario.getNombreUsuario()) && claveUsuario.equals(usuario.getClaveUsuario())) {
                usuarioEncontrado = usuario;
                break;
            }
        }

        // Devuelvo el valor 
        return usuarioEncontrado;
    }

    // Validar String
    /*
     * Esta funcion se va a encargar de:
     * - Validar que el ingreso del nombre de Usuario sea un String
     * - Para que se valide su existencia recibe por parámetro:
     * - El nombre ingresado (String)
     */
    private boolean validarString(String nombreIngresado) {
        boolean esValido = nombreIngresado.matches("[a-zA-Z]+");
        return esValido;
    }

}
