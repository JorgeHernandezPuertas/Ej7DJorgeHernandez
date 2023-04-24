/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vehiculos;

/**
 *
 * @author jorge
 */
public class Deportivo extends Vehiculo {

    // constructores
    public Deportivo() {
    }

    public Deportivo(String bastidor, String marca, String modelo, String color, int tarifa, boolean disponible) {
        super(bastidor, marca, modelo, color, tarifa, disponible);
    }

    // toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("1 - ").append(super.toString());
        return sb.toString();
    }
    
    
    
}
