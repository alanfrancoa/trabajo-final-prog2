package menues;

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
        this.mostrarOpciones();
        int opcion = this.sc.nextInt();
        return opcion;
    }

    // Funcion que sirve para elegir el rol ya sea para el registro como para el
    // login
    private String elegirRol() {

        boolean seguir = true;

        String rolAsignado = "";

        while (seguir) {
            this.mostrarOpcionRoles();
            int opcion = this.sc.nextInt();

            if (opcion == 1) {
                rolAsignado = "cliente";
                seguir = false;
            } else if (opcion == 2) {
                rolAsignado = "empleado";
                seguir = false;
            } else {
                System.out.println("Opcion invalida. Por favor, ingrese una opcion valida");
            }
        }

        return rolAsignado;
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

        // Verifico si el nombre de usuario ingresado ya existe
        boolean existeUsuario = this.validarExistenciaUsuario(nombreIngresado);

        // Si el nombre del usuario existe lanzo un mensaje de error
        if (existeUsuario) {
            System.out.println("El usuario ya existe. Por favor, elija otro nombre de usuario.");
            return;
        }

        // Caso contrario procedo a seguir con el registro

        // Ingreso la clave
        System.out.print("Contraseña: ");
        String claveIngresada = this.sc.next();

        // Reingreso la clave
        System.out.print("Ingrese la contraseña nuevamente: ");
        String confirmarClave = this.sc.next();

        // Valido si ambas claves ingresadas son iguales
        boolean sonIguales = this.validarClaves(claveIngresada, confirmarClave);

        // Si las contraseñas ingresadas no coinciden lanzo un mensaje de rror
        if (!sonIguales) {
            System.out.println("Las contraseñas no coinciden. Intente nuevamente.");
            return;
        }

        // Caso contrario procedo a seguir con el registro

        // Elijo un rol
        String rolIngresado = this.elegirRol();

        // Si el rol que elegí para el usuario es EMPLEADO solicito la clave adicional y
        // lo registro

        if (rolIngresado.equals("empleado")) {
            System.out.print("Ingrese la clave de empleado: ");
            String claveEmpleado = this.sc.next();

            // Si la clave ingresada no es igual a la clave secreta para registrar el
            // empleado lanzo un mensaje de error
            if (!claveEmpleado.equals("pepepiola123")) {
                System.out.println("Clave de empleado incorrecta. Registro fallido.");
                return;
            }

            // Si la clave secreta es correcta creo el nuevo empleado
            Empleado nuevoEmpleado = new Empleado(nombreIngresado, claveIngresada);

            // Guardo ese nuevo empleado en la lista de Usuarios
            listaUsuarios.add(nuevoEmpleado);
        }

        // Si el rol que se le asigno es CLIENTE procedo a crear el nuevo cliente
        Cliente nuevoCliente = new Cliente(nombreIngresado, claveIngresada, 0);

        // Almaceno el nuevo cliente en la lista de clientes
        listaUsuarios.add(nuevoCliente);
        System.out.println("Registro exitoso. ¡Bienvenido!");

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
            System.out.println("El usuario no existe. Por favor, registrese o vuelve a intentarlo");
            return;
        }

        // Caso contrario procedo a seguir con el logueo
        System.out.print("Contraseña: ");
        String claveUsuario = this.sc.next();

        // Verifico que la contraseña sea correcta
        boolean validarClave = this.validarClave(claveUsuario);

        // Si la clave es incorrecta lanzo un mensaje de error
        if (!validarClave) {
            System.out.println("Contraseña incorrecta. Inicio de sesión fallido.");
            return;
        }

        System.out.println("Inicio de sesión exitoso. ¡Bienvenido!");

        // Obtengo el usuario mediante el nombre y la clave ingresada 
        Usuario usuarioEncontrado = this.getUsuario(nombreUsuario, claveUsuario);

        // Dependiendo del usuario encontrado si es Cliente o Empleado ejecuto el menu correspondiente
        if (usuarioEncontrado instanceof Cliente) {
            Cliente cliente = (Cliente) usuarioEncontrado;
            MenuCliente mCliente = new MenuCliente(sc, cliente);
            mCliente.iniciar();
        } else if (usuarioEncontrado instanceof Empleado) {
            MenuEmpleado mEmpleado = new MenuEmpleado(sc);
            mEmpleado.iniciar();
        }

        // MENU EMPLEADOS
        // ABM ARTICULOS (crear, editar, eliminar articulos)
        // STOCK (ver y editar la cantidad de articulos)
        // MENU CLIENTE
        // CARRITO (mucho texto fijarse en el campus)
        // SALDO (agregar dinero a la cuenta, retirar dinero, transferir a otro usuario)

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

    // Funcio que me devuelve un Usuario
    private Usuario getUsuario(String nombreUsuario, String claveUsuario) {
        Usuario usuarioEncontrado = null;

        for (Usuario usuario : listaUsuarios) {
            if (nombreUsuario.equals(usuario.getNombreUsuario()) && claveUsuario.equals(usuario.getClaveUsuario())) {
                usuarioEncontrado = usuario;
                break;
            }
        }

        return usuarioEncontrado;
    }

}
