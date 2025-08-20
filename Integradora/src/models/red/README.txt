Proyecto: Escáner de Red y Validación de Dispositivos

Este proyecto de Java es una herramienta de escaneo de red que utiliza el comando arp -a para detectar dispositivos
conectados localmente. Su función principal es validar si las direcciones IP de los dispositivos detectados están 
registradas y autorizadas en una base de datos MySQL.

Características principales
Escaneo de red: Utiliza el protocolo ARP para identificar dispositivos con direcciones IP y MAC en la red local.

Validación de IPs: Compara cada IP detectada con una lista de IPs autorizadas almacenadas en una base de datos.

Manejo de errores: Muestra un mensaje de error claro en la consola si detecta una IP que no coincide con la base de datos, 
lo que permite identificar rápidamente dispositivos no autorizados.

Conexión a base de datos: Se conecta a una base de datos MySQL para obtener la lista de IPs autorizadas.

Requisitos
Para ejecutar este proyecto, necesitas lo siguiente:

Java Development Kit (JDK) 8 o superior.

MySQL Server en funcionamiento.

El conector mysql-connector-j-9.3.0.jar en el classpath de tu proyecto.

Permisos de administrador o privilegios para ejecutar el comando arp -a en el sistema operativo.

Configuración de la Base de Datos
El código espera encontrar una base de datos con las siguientes especificaciones:

Nombre de la base de datos: Sdg_ip_mac

Tabla: direccionamiento

Columnas: Una columna llamada Direccion IP que almacene las direcciones IP autorizadas.

Asegúrate de que tu tabla direccionamiento esté configurada de la siguiente manera:

// Codigo SQL

CREATE TABLE `direccionamiento` (
    `ID Dispositivo` INT NOT NULL AUTO_INCREMENT,
    `Direccion IP` VARCHAR(45) NOT NULL,
    `Direccion MAC` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`ID Dispositivo`)
);

Claro, aquí tienes un archivo de texto (README.md) para tu proyecto. Un README bien escrito es crucial para que otros desarrolladores entiendan el propósito del código, cómo funciona, y cómo pueden configurarlo.

README.md
Proyecto: Escáner de Red y Validación de Dispositivos
Este proyecto de Java es una herramienta de escaneo de red que utiliza el comando arp -a para detectar dispositivos conectados localmente. Su función principal es validar si las direcciones IP de los dispositivos detectados están registradas y autorizadas en una base de datos MySQL.

Características principales
Escaneo de red: Utiliza el protocolo ARP para identificar dispositivos con direcciones IP y MAC en la red local.

Validación de IPs: Compara cada IP detectada con una lista de IPs autorizadas almacenadas en una base de datos.

Manejo de errores: Muestra un mensaje de error claro en la consola si detecta una IP que no coincide con la base de datos, lo que permite identificar rápidamente dispositivos no autorizados.

Conexión a base de datos: Se conecta a una base de datos MySQL para obtener la lista de IPs autorizadas.

Requisitos
Para ejecutar este proyecto, necesitas lo siguiente:

Java Development Kit (JDK) 8 o superior.

MySQL Server en funcionamiento.

El conector mysql-connector-j-9.3.0.jar en el classpath de tu proyecto.

Permisos de administrador o privilegios para ejecutar el comando arp -a en el sistema operativo.

Configuración de la Base de Datos
El código espera encontrar una base de datos con las siguientes especificaciones:

Nombre de la base de datos: Sdg_ip_mac

Tabla: direccionamiento

Columnas: Una columna llamada Direccion IP que almacene las direcciones IP autorizadas.

Asegúrate de que tu tabla direccionamiento esté configurada de la siguiente manera:

SQL

CREATE TABLE `direccionamiento` (
    `ID Dispositivo` INT NOT NULL AUTO_INCREMENT,
    `Direccion IP` VARCHAR(45) NOT NULL,
    `Direccion MAC` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`ID Dispositivo`)
);

Configuración del Código
Antes de compilar y ejecutar, debes actualizar las credenciales de la base de datos en el archivo ARPScanner.java.

Abre el archivo models/red/ARPScanner.java.

Busca el método obtenerIpsDeBD().

Reemplaza los valores de url, usuario y contraseña con tus propios datos:

// CODIGO JAVA

private Set<String> obtenerIpsDeBD() {
        Set<String> ips = new HashSet<>();
        String url = "jdbc:mysql://localhost:3306/Sdg_ip_mac"; 
        String usuario = "tu_usuario"; 
        String contraseña = "tu_contraseña"; 
        String sql = "SELECT `Direccion IP` FROM direccionamiento"; 
        // ... (resto del código)
    }

Claro, aquí tienes un archivo de texto (README.md) para tu proyecto. Un README bien escrito es crucial para que otros desarrolladores entiendan el propósito del código, cómo funciona, y cómo pueden configurarlo.

README.md
Proyecto: Escáner de Red y Validación de Dispositivos
Este proyecto de Java es una herramienta de escaneo de red que utiliza el comando arp -a para detectar dispositivos conectados localmente. Su función principal es validar si las direcciones IP de los dispositivos detectados están registradas y autorizadas en una base de datos MySQL.

Características principales
Escaneo de red: Utiliza el protocolo ARP para identificar dispositivos con direcciones IP y MAC en la red local.

Validación de IPs: Compara cada IP detectada con una lista de IPs autorizadas almacenadas en una base de datos.

Manejo de errores: Muestra un mensaje de error claro en la consola si detecta una IP que no coincide con la base de datos, lo que permite identificar rápidamente dispositivos no autorizados.

Conexión a base de datos: Se conecta a una base de datos MySQL para obtener la lista de IPs autorizadas.

Requisitos
Para ejecutar este proyecto, necesitas lo siguiente:

Java Development Kit (JDK) 8 o superior.

MySQL Server en funcionamiento.

El conector mysql-connector-j-9.3.0.jar en el classpath de tu proyecto.

Permisos de administrador o privilegios para ejecutar el comando arp -a en el sistema operativo.

Configuración de la Base de Datos
El código espera encontrar una base de datos con las siguientes especificaciones:

Nombre de la base de datos: Sdg_ip_mac

Tabla: direccionamiento

Columnas: Una columna llamada Direccion IP que almacene las direcciones IP autorizadas.

Asegúrate de que tu tabla direccionamiento esté configurada de la siguiente manera:

SQL

CREATE TABLE `direccionamiento` (
    `ID Dispositivo` INT NOT NULL AUTO_INCREMENT,
    `Direccion IP` VARCHAR(45) NOT NULL,
    `Direccion MAC` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`ID Dispositivo`)
);
Configuración del Código
Antes de compilar y ejecutar, debes actualizar las credenciales de la base de datos en el archivo ARPScanner.java.

Abre el archivo models/red/ARPScanner.java.

Busca el método obtenerIpsDeBD().

Reemplaza los valores de url, usuario y contraseña con tus propios datos:

Java

    private Set<String> obtenerIpsDeBD() {
        Set<String> ips = new HashSet<>();
        String url = "jdbc:mysql://localhost:3306/Sdg_ip_mac"; 
        String usuario = "tu_usuario"; 
        String contraseña = "tu_contraseña"; 
        String sql = "SELECT `Direccion IP` FROM direccionamiento"; 
        // ... (resto del código)
    }


Ejecución
Para ejecutar el proyecto:

Añade el archivo mysql-connector-j-9.3.0.jar a tu proyecto.

Compila los archivos Java.

Ejecuta la clase ARPScanner.

java -cp ".;mysql-connector-j-9.3.0.jar" models.red.ARPScanner

Al ejecutar, el programa imprimirá en la consola la lista de dispositivos detectados y mostrará un mensaje de error si
encuentra una IP no registrada.
