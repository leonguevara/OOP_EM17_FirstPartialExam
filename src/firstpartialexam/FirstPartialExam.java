/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstpartialexam;

/**
 *
 * @author leonguevara
 */
public class FirstPartialExam {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Customer myCustomer;
        InvoiceItem myItem = new InvoiceItem("AP001","Manzanas",5,2.5);
        Invoice myInvoice;
        
        myCustomer = new Customer(1,"León",50);
        System.out.println(myCustomer.toString());
        System.out.println("Discount = " + myCustomer.getDiscount());
        System.out.println(myItem.toString());
        
        myInvoice = new Invoice("A000000001", myCustomer);
        System.out.println("Amount = " + myInvoice.getAmount());
        if (myInvoice.addItem(myItem)) {
            System.out.println("Added " + myItem.getDescription());
            myInvoice.recalculateAmount();
        }
        System.out.println("Amount = " + myInvoice.getAmount());
        System.out.println(myInvoice.toString());
    }
    
}
