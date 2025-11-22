package pt.ipp.estg.pp.pharmahold;

import java.util.ArrayList;

public class Client extends Users {

    private int totalPoints;
    private ArrayList<Orders> orders;
    private ArrayList<Orders> orderHistory;
    private static ArrayList<Client> clients = new ArrayList<>();

    public Client(String name, String password, int contact) {
        super(name, password, contact);
        this.totalPoints = 0;
        this.orders = new ArrayList<Orders>();
    }

    // POINTS
    public int getTotalPoints() {
        return this.totalPoints;
    }

    public void setTotalPoints(int value) {
        this.totalPoints = value;
    }

    public void addPoints(int value) {
        this.totalPoints += value;
    }

    public void subPoints(int value) {
        this.totalPoints -= value;
    }

    // ORDERS
    public ArrayList<Orders> getOrders() {
        return orders;
    }

    public void addOrders(Orders order) {
        orders.add(order);
        orderHistory.add(order);
    }

    public void rmvOrders(Orders order) {
        orders.remove(order);
    }
    
    public boolean login(int type, String name, String password) {      //wtf tó, n sei o que queres com isto, resolve quando poderes
        if (type == 1) {
            return false;
        } 
        else {
            return true;
        }
    }   
}
