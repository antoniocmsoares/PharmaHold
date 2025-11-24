package pt.ipp.estg.pp.pharmahold;

import static pt.ipp.estg.pp.pharmahold.Users.userList;

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

    public void addUserList(Client client) {

    }

    @Override
    public boolean login(String uName, String uPassword) {

        for (int i = 0; i < clients.size(); i++) {
            Client potentialUser = userList.get(i);
            if (potentialUser.getName().equals(uName)) {
                return true;
            }
        }
        return false;
    }
}
