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
public class InvoiceItem {
    // Atributos
    private String id;
    private String description;
    private int quantity;
    private double unitPrice;
    
    // Métodos
    // Constructor
    public InvoiceItem(String id, String description, int quantity,
            double unitPrice) {
        this.id = id;
        this.description = description;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
    
    // Getters and Setters
    public String getID() {
        return this.id;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getQuantity() {
        return this.quantity;
    }
    
    public boolean setQuantity(int quantity) {
        // Si la nueva cantidad es diferente a la actual, se realiza el cambio
        // y se regresa verdadero; de lo contrario, no se hace cambio alguno y
        // se regresa falso.
        if (this.quantity != quantity) {
            this.quantity = quantity;
            return true;
        } else {
            return false;
        }
    }
    
    public double getUnitPrice() {
        return this.unitPrice;
    }
    
    public boolean setUnitPrice(double unitPrice) {
        // Si el nuevo precio unitario es diferente al actual, se realiza el
        // cambio y se regresa verdadero; de lo contrario, no se hace cambio
        // alguno y se regresa falso.
        if (this.unitPrice != unitPrice) {
            this.unitPrice = unitPrice;
            return true;
        } else {
            return false;
        }
    }
    
    // getTotal indica cuál es el total de la línea de acuerdo al número de
    // unidades y al precio unitario.
    public double getTotal() {
        return this.unitPrice * this.quantity;
    }
    
    // toString
    @Override
    public String toString() {
        return "InvoiceItem[id=" + this.id + ",description=" + this.description
                + ",quantity=" + this.quantity + ",unitPrice=" + this.unitPrice
                + "]";
    }
}
