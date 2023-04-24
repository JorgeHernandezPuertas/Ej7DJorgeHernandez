/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manipulaciónficheros;

import com.sun.jdi.PathSearchingVirtualMachine;
import java.awt.desktop.FilesEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 *
 * @author jorge
 */
public class ManipulacionFicheros {

    public static void main(String[] args) {
        // Creo la ruta del directorio donde voy a meter las copias
        String rutaDir = "./copias";

        // Creo las rutas de origen de los archivos txt que quiero copiar
        Path origenTurismos = Paths.get("./turismos.txt");
        Path origenDeportivos = Paths.get("./deportivos.txt");
        Path origenFurgonetas = Paths.get("./furgonetas.txt");

        // Creo las rutas de destino donde quiero copiar los archivos anteriores
        Path destinoTurismos = Paths.get("./copias/turismos.txt");
        Path destinoDeportivos = Paths.get("./copias/deportivos.txt");
        Path destinoFurgonetas = Paths.get("./copias/furgonetas.txt");

        // Creo el directorio copias
        System.out.println("Creo el directorio:");
        crearDirectorio(rutaDir);
        System.out.println("--------------------------------------------------");

        // Copio los ficheros a copias
        System.out.println("Copio los archivos dentro del directorio");
        copiarFichero(origenTurismos, destinoTurismos);
        copiarFichero(origenDeportivos, destinoDeportivos);
        copiarFichero(origenFurgonetas, destinoFurgonetas);
        System.out.println("--------------------------------------------------");

        // Muestro los ficheros contenidos en copias
        System.out.println("Muestro los ficheros contenidos en copias:");
        listarDirectorio(rutaDir);
        System.out.println("--------------------------------------------------");

        // Creo una lista de vehiculos a partir de los ficheros de copias
        System.out.println("Creo la lista de vehiculos.");
        List<Vehiculo> listaVehiculos = leerDirectorio(rutaDir);
        System.out.println("--------------------------------------------------");

        // Imprimo la lista de vehiculos
        System.out.println("Imprimo la lista de vehiculos:");
        listaVehiculos.forEach(v -> System.out.println(v.toString()));
        System.out.println("--------------------------------------------------");

        // Ordeno la lista por bastidor
        System.out.println("Ordeno la lista por bastidor.");
        listaVehiculos = listaVehiculos.stream()
                .sorted((v1, v2) -> v1.getBastidor().compareToIgnoreCase(v2.getBastidor()))
                .toList();
        System.out.println("--------------------------------------------------");

        // Imprimo la lista ordenada
        System.out.println("Imprimo la lista de vehiculos ordenada por bastidor:");
        listaVehiculos.forEach(v -> System.out.println(v.toString()));
        System.out.println("--------------------------------------------------");

        // Borro los ficheros originales
        System.out.println("Borro los .txt originales.");
        borrarFichero(origenFurgonetas);
        borrarFichero(origenDeportivos);
        borrarFichero(origenTurismos);
        System.out.println("--------------------------------------------------");
        
        // Muestro el contenido de la carpeta donde estaban los originales
        System.out.println("Muestro el contenido de la carpeta donde estaban los originales:");
        listarDirectorio(".");
        System.out.println("--------------------------------------------------");
        
        // Imprimo todas las matriculas ordenadas alfabeticamente de los coches grises distintos
        // (mis vehiculos no tienen matricula y no encuentro esas clases, asi que lo
        // ordenare por modelo)
        System.out.println("Imprimo los modelos de los coches grises por orden:");
        listaVehiculos.stream()
                .filter(v -> v.getColor().equalsIgnoreCase("gris"))
                .map(v -> v.getModelo())
                .distinct()
                .sorted()
                .forEach(m -> System.out.println(m));
        System.out.println("--------------------------------------------------");
        
        // Imprimo todas las marcas de coches distintas de los coches disponibles
        System.out.println("Marcas distintas de los coches disponibles: ");
        listaVehiculos.stream()
                .filter(v -> v.isDisponible())
                .map(v -> v.getMarca())
                .distinct()
                .forEach(v -> System.out.println(v));
        System.out.println("--------------------------------------------------");
        
        // Imprimo la cantidad de vehiculos citroen
        System.out.println("Cantidad de coches citroen:");
        long cuenta = listaVehiculos.stream()
                .filter(v -> v.getMarca().equalsIgnoreCase("citroen"))
                .count();
        System.out.println(cuenta);
        System.out.println("--------------------------------------------------");
        
        // Compruebo si hay algun peugeot negro en la lista
        System.out.println("Hay algún peugeot negro en la lista?");
        boolean hayPeugeot = listaVehiculos.stream()
                .anyMatch(v -> v.getMarca().equalsIgnoreCase("peugeot") 
                        && v.getColor().equalsIgnoreCase("negro"));
        if (hayPeugeot){
            System.out.println("Si hay algun Peugeot negro en la lista.");
        } else {
            System.out.println("No hay algun Peugeot negro en la lista");
        }
        System.out.println("--------------------------------------------------");
        
        // Obtengo una lista con todas las tarifas diferentes que se aplican a los vehiculos
        System.out.println("Creo una lista con las tarifas diferentes.");
        List<Double> listaTarifas = listaVehiculos.stream()
                .map(v -> v.getTarifa())
                .distinct()
                .toList();
        System.out.println("--------------------------------------------------");
        
        // Imprimo la lista
        System.out.println("Imprimo la lista de tarifas:");
        listaTarifas.forEach(t -> System.out.println("Tarifa: " + t));
        
    }

    // Método para copiar un fichero y reemplazar si existe
    private static void copiarFichero(Path origen, Path destino) {
        try {
            Files.copy(origen, destino,
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            System.out.println("Ha habido un problema copiando los archivos");
            System.out.println(ioe.toString());
        }
    }

    // Método para crear un directorio si no existe ya
    private static void crearDirectorio(String rutaDir) {
        Path pathDir = Paths.get(rutaDir);
        File f = new File(rutaDir); // Creo el file para ver si existe
        if (!f.exists()) { // Controlo que el directorio no esté ya creado
            try {
                Files.createDirectory(pathDir);
            } catch (IOException ioe) {
                System.out.println("Ha habido un problema creando el directorio.");
                System.out.println(ioe.toString());
            }
        }
    }

    // Método para mostrar los ficheros de un directorio
    private static void listarDirectorio(String ruta) {
        File dir = new File(ruta);

        if (dir.exists()) {
            File[] archivos = dir.listFiles();
            for (File f : archivos) {
                System.out.println(f.getName());
            }
        } else {
            System.out.println("El directorio no existe.");
        }
    }

    // Método para leer los ficheros de copias y guardalos en una lista de vehiculos
    private static List<Vehiculo> leerDirectorio(String rutaDir) {
        List<Vehiculo> vehiculos = new ArrayList<>();
        File dir = new File(rutaDir);
        if (dir.exists()) {
            File[] archivos = dir.listFiles();
            for (File f : archivos) {
                vehiculos.addAll(leerFichero(f));
            }
        } else {
            System.out.println("El directorio no existe.");
        }

        return vehiculos;
    }

    // Método para leer un fichero y devolver una lista de vehiculos
    private static List<Vehiculo> leerFichero(File fichero) {
        List<Vehiculo> vehiculos = new ArrayList<>();
        String linea = "";
        String[] tokens;

        try ( Scanner flujo = new Scanner(new FileReader(fichero))) {
            while (flujo.hasNext()) {
                linea = flujo.nextLine();
                tokens = linea.split("( - )|(:)");
                vehiculos.add(crearVehiculo(tokens[0], tokens[1],
                        tokens[2], tokens[3], tokens[4],
                        Double.parseDouble(tokens[5]), Boolean.parseBoolean(tokens[6])));
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        return vehiculos;
    }

    // Método que decide que tipo de vehiculo crear en función del fichero
    private static Vehiculo crearVehiculo(String tipo, String bastidor,
            String marca, String modelo, String color, double tarifa, boolean disponible) {
        Vehiculo v = null;
        switch (tipo) {
            case "0" -> {
                v = new Turismo(bastidor, marca, modelo, color, tarifa, disponible);
            }
            case "1" -> {
                v = new Deportivo(bastidor, marca, modelo, color, tarifa, disponible);
            }
            case "2" -> {
                v = new Furgoneta(bastidor, marca, modelo, color, tarifa, disponible);
            }
        }
        return v;
    }

    // Método para borrar fichero
    private static void borrarFichero(Path ruta) {
        try {
            Files.delete(ruta);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

}
