/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstpartialexam;

/**
 *
 * @author León Felipe Guevara Chávez
 */
public class Customer {
    // Atributos
    private int id;
    private String name;
    private double discount;
    
    // Métodos
    // Constructor
    public Customer(int id, String name, double discount) {
        this.id = id;
        this.name = name;
        this.discount = discount;
    }
    
    // Getters and Setters
    public int getID() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public double getDiscount() {
        return this.discount;
    }
    
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    
    // toString
    @Override
    public String toString(){
        return this.name + " (" + this.id + ")";
    }
}
