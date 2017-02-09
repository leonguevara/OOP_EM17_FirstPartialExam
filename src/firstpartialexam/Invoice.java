/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstpartialexam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Set;

/**
 *
 * @author leonguevara
 */
public class Invoice {
    // =========================================================================
    // Attributes
    // =========================================================================
    
    // The invoice ID is an alphanumeric data, thus declaring it String
    private String id;
    // The customer is an instance of the Customer class
    private Customer customer;
    // The invoice amount is a double so we can handle cents 
    private double amount;
    // The invoice items are handled dynamically by an ArrayList
    private ArrayList<InvoiceItem> items;
    
    // =========================================================================
    // Methods
    // =========================================================================
    // Constructors
    // =========================================================================
    
    // This constructor gets the customer data separately, so we need to
    // create a new Customer instance to store the data.
    public Invoice(String id, int customerID, String name, double discount) {
        this.id = id;
        this.customer = new Customer(customerID, name, discount);
        
        // The invoice amount equals 0 at creation time.
        this.amount = 0;
        
        // The items list is initialised as an ArrayList of InvoiceItem
        this.items = new ArrayList<InvoiceItem>();
    }
    
    // This constructor receives an already created instance of the Customer
    // class, so we only store it in this invoice.
    public Invoice(String id, Customer customer) {
        this.id = id;
        this.customer = customer;
        
        // The invoice amount equals 0 at creation time.
        this.amount = 0;
        
        // The items list is initialised as an ArrayList of InvoiceItem
        this.items = new ArrayList<InvoiceItem>();
    }
    
    // Getters and Setters
    // ID getter
    public String getID(){
        return this.id;
    }
    
    // Customer getter. Returns an instance of the Customer class
    public Customer getCustomer() {
        return this.customer;
    }
    
    // Customer setter.  It receives an instance of the Customer class to
    // be stored in the invoice
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    // Amount getter
    public double getAmount() {
        return this.amount;
    }
    
    // This method gets the invoice total amount after discounting the
    // customer's entitled percentage discount.
    public double getAmountAfterDiscount() {
        double discount = this.amount * this.customer.getDiscount() / 100;
        return this.amount - discount;
    }
    
    // This method gets the invoice total amount (before any possible discount).
    // It iterates througouht the items ArrayList to accumulate each line total.
    public void recalculateAmount() {
        this.amount = 0;
        
        for(InvoiceItem tempItem : items) {
            this.amount += tempItem.getTotal();
        }
    }

    // This method retrieves the customer name
    public String getCustomerName() {
        return this.customer.getName();
    }
    
    // This method adds an item to the items ArrayList.  It receives the item
    // data separately, so we create a new InvoiceItem instance to add to the
    // ArrayList.  The method returns a boolean stating if the addition was
    // successful or not.
    public boolean addItem(String id, String description, int quantity,
            double unitPrice) {
        
        // If the item is not already in the list, we add it
        if (!findItem(id)) {
            this.items.add(new InvoiceItem(id, description, quantity,
                    unitPrice));
            return true;
        } else {
            return false;
        }
    }
    
    // This method adds an item to the items ArrayList.  It receives an instance
    // of the InvoiceItem, so we need not to create a new instance.  The method
    // returns a boolean stating if the addition was successful or not.
    public boolean addItem(InvoiceItem item) {
        
        // If the item is not already in the list, we add it
        if (!findItem(item.getID())) {
            this.items.add(item);
            return true;
        } else {
            return false;
        }
    }
    
    // This method removes an item of the items ArrayList.  It receives the item
    // id.  The method returns a boolean stating if the removal was successful
    // or not.
    public boolean removeItem(String id) {
        
        // If the item ID can be find in the ArrayList...
        if (findItem(id)) {
            
            // We use an iterator to locate the desired item
            ListIterator it = this.items.listIterator();
            while (it.hasNext()) {
                InvoiceItem temp = (InvoiceItem) it.next();
                
                // Once located, we remove the item from the ArrayList
                if (temp.getID() == id) {
                    it.remove();
                    return true;
                }
            }
            return true;
        } else {
            return false;
        }
    }
    
    // This method removes an item of the items ArrayList.  It receives an
    // instance of the InvoiceItem class.  The method returns a boolean stating
    // if the removal was successful or not.
    public boolean removeItem(InvoiceItem item) {
        
        // We call the other removeItem method, sending this item ID.
        return removeItem(item.getID());
    }
    
    // This method tries to update the description of an item within the items
    // array list
    public void updateItem(String id, String desc) {
        // If the items array is not empty, then we iterate the array
        if(!this.items.isEmpty()) {
            for(InvoiceItem tempItem : items) {
                // If the id could be found, update the description
                if (tempItem.getID() == id) {
                    tempItem.setDescription(desc);
                }
            }
        }
    }
    
    // This method tries to update the quantity of an item within the items
    // array list.  The method returns a boolean indicating if the update was
    // successful or not.
    public boolean updateItem(String id, int quantity) {
        // If the items array is not empty, then we iterate the array
        if(!this.items.isEmpty()) {
            for(InvoiceItem tempItem : items) {
                // If the id could be found and the current quantity is not
                // equal to the new quantity, then update the quantity
                if ((tempItem.getID() == id) 
                        && (tempItem.getQuantity() != quantity)) {
                    tempItem.setQuantity(quantity);
                    return true;
                }
            }
        }
        return false;
    }
    
    // This method tries to update the unit price of an item within the items
    // array list.  The method returns a boolean indicating if the update was
    // successful or not.
    public boolean updateItem(String id, double unitPrice) {
        // If the items array is not empty, then we iterate the array
        if(!this.items.isEmpty()) {
            for(InvoiceItem tempItem : items) {
                // If the id could be found and the current unit prices is not
                // equal to the new unit price, then update the unit price
                if ((tempItem.getID() == id)
                        && (tempItem.getUnitPrice() != unitPrice)) {
                    tempItem.setUnitPrice(unitPrice);
                    return true;
                }
            }
        }
        return false;
    }
    
    //This method tries to update the data of an item within the items array
    // list.  The method returns a boolean indicating if the update was
    // successful or not.
    public boolean updateItem(InvoiceItem item) {
        updateItem(item.getID(), item.getDescription());
        return (updateItem(item.getID(), item.getQuantity())
                || updateItem(item.getID(), item.getUnitPrice()));
    }
    
    // toString
    @Override
    public String toString(){
        String temp;
        
        // We start building the string with the invoice id and the customer
        // data
        temp = "Invoice[id=" + this.id + ",customer id=" +
                this.customer.getID() + ",customer name=" +
                this.customer.getName() + ",Items={";
        
        // We iterate throughout the items ArrayList to retreive the items data
        for(InvoiceItem tempItem : items) {
            temp += "Item[id=" + tempItem.getID() + ",description=" +
                    tempItem.getDescription() + ",quantity=" + 
                    tempItem.getQuantity() + ",unit price=" + 
                    tempItem.getUnitPrice() + ",line total=" +
                    tempItem.getTotal() + "],";
        }
        
        // We end the string with the invoice amounts
        temp += "},gross amount=" + this.amount + ",discount amount=" +
                this.amount * this.customer.getDiscount() / 100 +
                ",amount after discount=" + this.getAmountAfterDiscount() + "]";
        
        return temp;
    }
    
    // This method identifies if an item, given its ID, is already in the
    // items ArrayList.  The method returns a boolean indicating if the item
    // could be located or not.
    private boolean findItem(String id) {
        // If the items array is not empty, then we iterate the array
        if (!this.items.isEmpty()) {
            for(InvoiceItem tempItem : items) {
                // If the id could be found, returns true
                if (tempItem.getID() == id) {
                    return true;
                }
            }
        }
        return false;
    }
}
