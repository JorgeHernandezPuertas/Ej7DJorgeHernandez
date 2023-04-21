/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manipulaciónficheros;

import com.sun.jdi.PathSearchingVirtualMachine;
import java.awt.desktop.FilesEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author jorge
 */
public class ManipulacionFicheros {

    public static void main(String[] args) {
        // Creo la ruta del directorio donde voy a meter las copias
        String rutaDir = "./copias";
        Path pathDir = Paths.get(rutaDir);

        // Creo las rutas de origen de los archivos txt que quiero copiar
        Path origenTurismos = Paths.get("./turismos.txt");
        Path origenDeportivos = Paths.get("./deportivos.txt");
        Path origenFurgonetas = Paths.get("./furgonetas.txt");

        // Creo las rutas de destino donde quiero copiar los archivos anteriores
        Path destinoTurismos = Paths.get("./copias/turismos.txt");
        Path destinoDeportivos = Paths.get("./copias/deportivos.txt");
        Path destinoFurgonetas = Paths.get("./copias/furgonetas.txt");

        // Creo el directorio copias
        File f = new File(rutaDir); // Creo el file para ver si existe
        if (!f.exists()) { // Controlo que el directorio no esté ya creado
            try {
                Files.createDirectory(pathDir);
            } catch (IOException ioe) {
                System.out.println("Ha habido un problema creando el directorio.");
                System.out.println(ioe.toString());
            }
        }
        
        // Copio los ficheros a copias
        try {
            Files.copy(origenTurismos, destinoTurismos, 
                    StandardCopyOption.REPLACE_EXISTING);
            Files.copy(origenDeportivos, destinoDeportivos, 
                    StandardCopyOption.REPLACE_EXISTING);
            Files.copy(origenFurgonetas, destinoFurgonetas, 
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe){
            System.out.println("Ha habido un problema copiando los archivos");
            System.out.println(ioe.toString());
        }
        
    }

}
