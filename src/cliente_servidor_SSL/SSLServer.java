/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente_servidor_SSL;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

/**
 *
 * @author DAM
 */
public class SSLServer {

    public static void main(String[] arg) throws IOException {
        SSLSocket clienteConectado = null;
        DataInputStream flujoEntrada = null; //FLUJO DE ENTRADA DE CLIENTE
        DataOutputStream flujoSalida = null; //FLUJO DE SALIDA AL CLIENTE

// Las propiedades se pueden especificar mediante código, o bien mediante
// argumentos de la JVM en la llamada a la aplicación
// System.setProperty("javax.net.ssl.keyStore", System.getProperty("user.dir") + "\\ClavesServidor");
// System.setProperty("javax.net.ssl.keyStorePassword", "12345678");
// Inicialización del ServerSocket SSL
        int puerto = 6000;
        SSLServerSocketFactory sfact = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        SSLServerSocket servidorSSL = (SSLServerSocket) sfact.createServerSocket(puerto);
        for (int i = 1; i < 5; i++) {
            System.out.println("Esperando al cliente " + i);
// Se espera la conexión de un cliente con accept
            clienteConectado = (SSLSocket) servidorSSL.accept();
// Trabajamos do DataInputStream y DataOutputStream para simplificar
// el código del ejemplo
            flujoEntrada = new DataInputStream(clienteConectado.getInputStream());
            flujoSalida = new DataOutputStream(clienteConectado.getOutputStream());
// El cliente envía un mensaje
            System.out.println("Recibiendo del CLIENTE: " + i + " \n\t" + flujoEntrada.readUTF());
// El Servidor responde con un saludo
            flujoSalida.writeUTF("Saludos al cliente del servidor");
        }
// CERRAR STREAMS Y SOCKETS
        flujoEntrada.close();
        flujoSalida.close();
        clienteConectado.close();
        servidorSSL.close();
    }

}
