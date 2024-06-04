/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proveedores_algoritmos;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author DAM
 */
public class ProbarAlgoritmosJCA{
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java ProbarAlgoritmosJCA <algoritmo>");
            System.exit(1);
        }
        try {
            MessageDigest md = MessageDigest.getInstance(args[0]);
            System.out.println("Algoritmo: " + md.getAlgorithm());
            System.out.println("Proveedor: " + md.getProvider().getName());
            System.out.println("Info     : " + md.toString());
            System.out.println("Tamaño   : " + md.getDigestLength());
            System.out.println("Implement: " + md.getClass().getName());
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Algoritmo no disponible");
        }
    }
}
