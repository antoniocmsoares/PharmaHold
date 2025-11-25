package pt.ipp.estg.pp.pharmahold;

import java.util.ArrayList;

public class Client extends Users {
    String address;

    private int totalPoints;
    private ArrayList<Orders> orders;
    private ArrayList<Orders> orderHistory;

    public Client(String name, String password, int contact, char userType, String address) {
        super(name, password, contact, userType);
        this.address = address;
        this.totalPoints = 0;
        this.orders = new ArrayList<Orders>();
    }
    // ADDRESS
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    @Override
    public Users login(String uName, String uPassword, char userType) {
        ArrayList<Users> userList = getAllUsers();
        for (Users potentialUser : userList) {
            if (potentialUser.getName().equals(uName)) {
                if (potentialUser.getPassword().equals(uPassword)) {
                    return potentialUser;
                }
            }
        }
        return null;
    }
}
