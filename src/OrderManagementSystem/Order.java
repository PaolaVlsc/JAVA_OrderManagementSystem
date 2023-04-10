/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OrderManagementSystem;

/**
 *
 * @author Velasco
 */
public class Order {

    private String appID;
    private String orderID;
    private String orderDate;
    private String clientName;
    private String itemName;
    private int unitsCount;
    private double netItemPrice;
    private double taxPercent;
    
    // constructor_1
    public Order(){
         
     }
    
    // constructor_2 with arguments
    public Order(String appID, String orderID, String orderDate, String clientName, String itemName, int unitsCount, double netItemPrc, double taxPercent) {
        this.appID = appID;
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.clientName = clientName.trim();
        this.itemName = itemName;
        this.unitsCount = unitsCount;
        this.netItemPrice = netItemPrc;
        this.taxPercent = taxPercent;
    }

    // getters & setters of class instance members 
    public String getAppID() {
        return appID;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getClientName() {
        return clientName;
    }

    public String getItemName() {
        return itemName;
    }

    public int getUnitsCount() {
        return unitsCount;
    }

    public double getNetItemPrice() {
        return netItemPrice;
    }

    public double getTaxPercent() {
        return taxPercent;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setUnitsCount(int unitsCount) {
        this.unitsCount = unitsCount;
    }

    public void setNetItemPrice(double netItemPrice) {
        this.netItemPrice = netItemPrice;
    }

    public void setTaxPercent(double taxPercent) {
        this.taxPercent = taxPercent;
    }

    // Method used to display the values on the main JFrame ( on TextArea ) 
    @Override
    public String toString() {
        return orderID + "\t\t"
                + orderDate + "\t\t"
                + clientName + "\t\t"
                + itemName + "\t\t"
                + unitsCount + "\t\t"
                + netItemPrice + "$\t\t"
                + taxPercent + "%";
    }
 
    // Method used to save on a csv file
    public String formatEntryOnSave() {
        return appID + ";"
                + orderID + ";"
                + orderDate + ";"
                + clientName + ";"
                + itemName + ";"
                + unitsCount + ";"
                + netItemPrice + ";"
                + taxPercent;
    }

    // Method: calculate the total price of each order - entry
    public double totalOrderPrice() {
 

        return (taxPercent * (netItemPrice * unitsCount) + (netItemPrice * unitsCount));

    }

    // Method: calculate the net price of each order - entry 
    public double totalOrderNetPrice() {
 
        return (netItemPrice * unitsCount);

    }
}
