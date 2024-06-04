/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package encriptacion_simetrica;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author DAM
 */
public class SecretKeyEncrypt {

    public static void main(String[] args) {
        SecretKey claveSecreta = null;
        try {
//Generamos clave secreta
// Podemos crear una nueva clave
            claveSecreta = getNewKey();
// O bien usar una clave guardada en algún almacén, fichero, etc.
            claveSecreta = getKeyFromData();
            System.out.println("Clave usada: " + claveSecreta.getFormat());
//Se define el objeto Cipher (Algoritmo/modo/relleno)
            Cipher c = Cipher.getInstance("DESede"); // AES/ECB/PKCS5Padding
// Configuramos el modo de CIFRADO
            c.init(Cipher.ENCRYPT_MODE, claveSecreta);
// Aquí leemos la información que queremos cifrar
// Puede ser una cadena o leerla de un archivo
            byte[] textoPlano = "Texto que queremos cifrar para la prueba".getBytes();
// Si queremos ir cifrando poco a poco, vamos haciendo llamadas
// al método update
// c.update(textoPlano);
// Se realiza el proceso final de cifrado de la información
            byte[] textoCifrado = c.doFinal(textoPlano);
            System.out.println("Texto cifrado con clave secreta (raw):\n" + new String(textoCifrado));
            System.out.println("Texto cifrado con clave secreta (hex):\n" + toHexadecimal(textoCifrado));
// El proceso de descifrado es equivalente
// Cambiamos el modo de ENCRYPT a DECRYPT
// Usamos la misma clave
// Pasamos el texto cifrado para obtener el original
            c.init(Cipher.DECRYPT_MODE, claveSecreta);
            byte[] textoOriginal = c.doFinal(textoCifrado);
//Leemos bloques de bytes del fichero y lo vamos escribiendo ya cifrado en el fichero de salida
            System.out.println("Texto descifrado:\n" + new String(textoOriginal));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static SecretKey getNewKey() throws InvalidKeySpecException, NoSuchAlgorithmException {
        KeyGenerator kg = KeyGenerator.getInstance("DESede");
        kg.init(112);
        SecretKey clave = kg.generateKey();
        return clave;
    }

    static SecretKey getNewRandomKey() throws InvalidKeySpecException, NoSuchAlgorithmException {
// Clave obtenida usando un generador de número aleatorios seguro
        KeyGenerator genClaves = KeyGenerator.getInstance("DESede");
// Utilizamos un algoritmo de generación de aleatorios
        SecureRandom srand = SecureRandom.getInstance("SHA1PRNG");
        genClaves.init(srand);
        SecretKey clave = genClaves.generateKey();
        System.out.println("Formato de clave: " + clave.getFormat());
        /*
SecretKeyFactory keySpecFactory = SecretKeyFactory.getInstance("DESede");
DESedeKeySpec keySpec = (DESedeKeySpec) keySpecFactory.getKeySpec(clave, DESedeKeySpec.class);
byte[] valorClave = keySpec.getKey();
         */
        return clave;
    }

    static SecretKey getKeyFromData() throws InvalidKeySpecException, NoSuchAlgorithmException {
// La clave se puede obtener desde un fichero o cualquier otra fuente
        byte valorClave[] = "12345678123456781234567812345678".getBytes();
        SecretKeySpec keySpec = new SecretKeySpec(valorClave, "DESede");
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        SecretKey clave = keyFactory.generateSecret(keySpec);
        return clave;
    }

    static Key getKeyFromData2() throws InvalidKeySpecException, NoSuchAlgorithmException {
// La clave se puede obtener desde un fichero o cualquier otra fuente
        byte valorClave[] = "12345678123456781234567812345678".getBytes();
        Key clave = new SecretKeySpec(valorClave, "AES");
        return clave;
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
