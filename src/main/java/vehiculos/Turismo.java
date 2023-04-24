/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vehiculos;

/**
 *
 * @author jorge
 */
public class Turismo extends Vehiculo {

    // constructores
    public Turismo() {
    }

    public Turismo(String bastidor, String marca, String modelo, String color, int tarifa) {
        super(bastidor, marca, modelo, color, tarifa);
    }
    
    // toString 
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("0 - ").append(super.toString());
        return sb.toString();
    }
    
    
}