/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funciones_resumen;



import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author DAM
 */
public class MessageDigestSHA {

    public static void main(String[] args) {
        
        String plaintext = "Esto es un texto plano.";
        try {
// Obtenemos un ENGINE que implementa el algoritmo especificado
// Se puede indicar cualquier algoritmo disponible en el sistema
// SHA-224, SHA-512, SHA-256, SHA3-224, ...
            MessageDigest m = MessageDigest.getInstance("SHA-256");
            
// Opcional - Reinicia el objeto para un nuevo uso
// Por si queremos poner este código en un bucle y procesar más
// de un mensaje
            m.reset();
// Realiza el resumen de los datos pasados por parámetro
// Si queremos procesar la información poco a poco,
// debemos ir llamando al método update para cada bloque de datos
            m.update(plaintext.getBytes());
// Completa el cálculo del valor del hash y devuelve el resumen
            byte[] digest = m.digest();
// Mensaje de resumen
            System.out.println("Resumen (raw data): " + new String(digest));
// Mensaje en formato hexadecimal
            System.out.println("Resumen (hex data): " + toHexadecimal(digest));
// Información del proceso
            System.out.println("=> Algoritmo: " + m.getAlgorithm() + ", Provider: " + m.getProvider().getName());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("No se ha encontrado la implementación del algoritmo MD5 en ningún Provider");
        }
    }

    static String toHexadecimal(byte[] hash) {
        String hex = "";
        for (int i = 0; i < hash.length; i++) {
            String h = Integer.toHexString(hash[i] & 0xFF);
            if (h.length() == 1) {
                hex += "0";
            }
            hex += h;
        }
        return hex.toUpperCase();
    }
}
