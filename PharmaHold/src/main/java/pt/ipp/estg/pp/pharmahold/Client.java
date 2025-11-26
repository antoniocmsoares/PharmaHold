package pt.ipp.estg.pp.pharmahold;

import java.util.ArrayList;

public class Client extends Users {

    String address;

    private int totalPoints;
    private ArrayList<Orders> orders = new ArrayList<>();
    private ArrayList<Orders> orderHistory = new ArrayList<>();
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
    public ArrayList<Orders> getOrders() {
        return orders;
    }

    public ArrayList<Orders> getOrderHistory() {
        return orderHistory;
    }

    public void addOrders(Orders order) {
        orders.add(order);
        orderHistory.add(order);
    }

    public void rmvOrders(Orders order) {
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
        for (Orders ord : getOrders()) {
            res = (" id: " + ord.getId() + "\n" + " available Date: " + "\n" + ord.getAvailableDate()[0] + "/" + ord.getAvailableDate()[1] + "/" + ord.getAvailableDate()[2]
                    + " Creation Date: " + "\n" + ord.getCreationDate()[0] + "/" + ord.getCreationDate()[1] + "/" + ord.getCreationDate()[2] + "\n" + " Products: " + "\n" + ord.getProductsList());
        }
        return res;
    }

    public static Users getUserById(int id) {
        for (int u = 0; u < clients.size(); u++) {
            Users usr = clients.get(u);
            if (usr.getId() == id) {
                return usr;
            }
        }
        return null;
    }

    public static void rmvUserById(int id) {
        for (int u = 0; u < clients.size(); u++) {
            Users usr = clients.get(u);
            if (usr.getId() == id) {
                clients.remove(id);
                usr.setId(0);
                usr.setDecCountId();
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
