/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proveedores_algoritmos;

import java.security.Provider;
import java.security.Security;
import java.util.Enumeration;

/**
 *
 * @author DAM
 */
public class InfoProveedoresJCA {
    
    public static void main(String[] args) {
        boolean listarProps = false;
        if (args.length > 0 && args[0].equals("-l")) {
            listarProps = true;
        }
        System.out.println("------------------------------------");
        System.out.println("Proveedores instalados en su sistema");
        System.out.println("------------------------------------");
        int i = 0;
        for (Provider proveedor: Security.getProviders()) {
            System.out.println("Núm. proveedor : " + (i + 1));
            System.out.println("Nombre         : " + proveedor.getName());
            System.out.println("Versión        : " + proveedor.getVersionStr());
            System.out.println("Información    :\n  " + proveedor.getInfo());
            System.out.println("Propiedades    :");
            if (listarProps) {
                Enumeration propiedades = proveedor.propertyNames();
                while (propiedades.hasMoreElements()) {
                    String clave = (String) propiedades.nextElement();
                    String valor = proveedor.getProperty(clave);
                    System.out.println("  " + clave + " = " + valor);
                }
            }
            System.out.println("------------------------------------");
        }
    }
}
