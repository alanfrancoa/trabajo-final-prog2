## Trabajo final Programacion II: Java

CONSIGNA:
Realizar una aplicación de consola en Java, la misma debe contar con las siguientes funcionalidades.

 1. USUARIOS: 
Para utilizar el sistema, se deberá en primer lugar ingresar con un usuario el cual, cuanto mínimo, posea un nombre de usuario y una contraseña.
Los usuarios pueden ser empleado o cliente.
El sistema deberá permitir registrar usuarios.
Durante el registro, la calve debe ser solicitada dos veces y validar que coincida.
Para registrar un empleado, el sistema deberá pedir una clave extra, luego de ingresar los datos del mismo. La clave será “pepepiola123”. Esta clave solo sirve para registrar.
Los clientes solo pueden acceder a las funcionalidades 4 y 5.
 

2. ABM ARTÍCULOS: 
Se deberá poder cargar artículos, editarlos y eliminarlos. También ver un listado completo.

Los artículos deberán contener, cuanto mínimo, los siguientes datos: 
Código de Articulo: Identificador único de cada artículo (Se debe validar que no se repita)

Nombre/descripción: Un texto descriptivo del artículo.

Precio neto: El precio del artículo (Ya incluye IVA).

Stock: La cantidad en existencia del mismo. 

Rubro: A que rubro pertenece (Puede ser A, B o C).

Cada grupo debe elegir un nombre para cada categoría, pero deberá tener el tipo al final de su nombre. 
Ej: “Alimentos (A)”. (El nombre elegido no tiene que ser editable durante la ejecución del programa).

Adicionalmente, los artículos tienen que pertenecer a uno de estos tres tipos:
Subsidiados: Estos artículos deben tener un descuento en su precio final, dependiendo de a la categoría que pertenezcan. (A: 30%, B: 24%, C: 15%).
 Estos artículos se deben mostrar con la leyenda “(S)” al final de su nombre.

Por demanda: Estos artículos tienen un stock deseado, cuando se encuentran por arriba de este, deben aplicar un descuento basado en el porcentaje por el que se encuentran excedidos (Con un máximo de 50%).
Ej: Si el deseado es 80 y el stock es 100, 100 es el 125% de 80, por ende el descuento a aplicar es 25%.

Simples: No aplican ningún descuento.

3. STOCK: 
Se deberá poder ver y editar la cantidad de cada uno de los artículos cargados en el sistema.

4. MÓDULO DE SALDO: 
Cada usuario debe contar con una cantidad de saldo y debe poder realizar las siguientes tareas:
Agregar dinero a la cuenta.
Retirar dinero de la cuenta.
Transferir dinero a otro usuario.
5. CARRITO DE COMPRA: 
El sistema debe permitir agregar artículos a un carrito de compras.
Los mismos deberán ser agregados por código, para esto, se deberá mostrar el listado completo de artículos para saber cuál seleccionar.
Se deberá poder agregar más de una vez un artículo.
Se deberá poder ver el importe total de los artículos cargados en todo momento.
Si el total de la compra es mayor a $12000 se deberá aplicar un descuento del 15%.
Cuando el usuario desee finalizar, se deberá mostrar al usuario cuando se va a gastar y su saldo. En base a eso el mismo deberá confirmar la transacción.
Si el usuario no   tiene saldo suficiente, no podrá concretar la compra. (Se le debe mostrar un error).
Una vez finalizada la operación, se deberá mostrar por pantalla los artículos comprados, el subtotal, el importe total descontado y el total final.
Adicionalmente, se deberán descontar los artículos del stock y se debe reducir el saldo del usuario en base al total de la factura.
APARTADO TÉCNICO:
Se deberán guardar los datos en archivos.
El programa deberá contar con validaciones, no deberá permitir cargar datos ilógicos, ni romper cuando se ingresa un dato invalido.
El resultado final debe ser una aplicación de consola.

FECHA DE ENTREGA:
La entrega deberá realizarse el 27/11/2023 antes de las 23:00 Hs.