/*Client class - contains Login and CRUD of client.

Needs:

-
-
-

*/
package pt.ipp.estg.pp.pharmahold;

import java.util.ArrayList;

public class Client extends User {

    String address;

    private int totalPoints;
    private ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<Order> orderHistory = new ArrayList<>();
    private ArrayList<Prescription> prescriptions = new ArrayList<>();
    private static ArrayList<Client> clients = new ArrayList<>();

    public Client(String name, String password, int contact, char userType, String address) {
        super(name, password, contact, userType);
        this.address = address;
        this.totalPoints = 0;
        this.orders = new ArrayList<>();
        clients.add(this);
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
    public ArrayList<Order> getOrders() {
        return orders;
    }

    public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }

    public void addOrders(Order order) {
        orders.add(order);
        orderHistory.add(order);
    }

    public void rmvOrders(Order order) {
        orders.remove(order);
    }

    public static ArrayList<Client> getClients() {
        return clients;
    }

    public void addPrescription(Prescription presc) {
        this.prescriptions.add(presc);
    }

    public ArrayList<Prescription> getPrescriptionsList(Prescription presc) {
        return prescriptions;
    }

    public Prescription getPrescriptionById(int id) {
        for (Prescription p : prescriptions) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public void printPrescriptions() {
        System.out.println("- prescriptions ---------------------------------");
        for (Prescription p : prescriptions) {
            System.out.println("|_ id: " + p.getId() + " | doctor: " + p.getDoctorName() + " | type: " + p.getPrescriptionType());
        }
    }

    public String printOrders() {
        String res = "";
        System.out.println("- Orders ---------------------------------");
        for (Order ord : getOrders()) {
            res = (" id: " + ord.getId() + "\n" + " available Date: " + "\n" + ord.getAvailableDate()[0] + "/" + ord.getAvailableDate()[1] + "/" + ord.getAvailableDate()[2]
                    + " Creation Date: " + "\n" + ord.getCreationDate()[0] + "/" + ord.getCreationDate()[1] + "/" + ord.getCreationDate()[2] + "\n" + " Products: " + "\n" + ord.getProductsList());
        }
        return res;
    }

    public static Client getUserById(int id) {
        for (int u = 0; u < clients.size(); u++) {
            Client usr = clients.get(u);
            if (usr.getId() == id) {
                return usr;
            }
        }
        return null;
    }

    public static void rmvUserById(int id) {
        for (int u = 0; u < clients.size(); u++) {
            Client usr = clients.get(u);
            if (usr.getId() == id) {
                usr.setId(-999);
                clients.remove(id);
            }
        }
    }

    public static Client login(String uName, String uPassword, char userType) {
        for (Client client : clients) {
            if (client.getName().equals(uName) && client.getPassword().equals(uPassword)) {
                return client;
            }
        }
        return null;
    }
}
