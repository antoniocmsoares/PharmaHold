//Client class - contains Login and CRUD.
package pt.ipp.estg.pp.pharmahold;

import java.util.ArrayList;

import pt.ipp.estg.pp.pharmahold.ENUMS.OrderState;
import pt.ipp.estg.pp.pharmahold.ENUMS.ProductState;
import pt.ipp.estg.pp.pharmahold.ENUMS.UserState;

public class Client extends User {

    String address;

    private int totalPoints;
    private ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<Prescription> prescriptions = new ArrayList<>();
    private static ArrayList<Client> clients = new ArrayList<>();
    private UserState state;

    public Client(String name, String password, int contact, String address) {
        super(name, password, contact);
        this.address = address;
        this.totalPoints = 0;
        this.orders = new ArrayList<>();
        clients.add(this);
    }

    // GETTERS AND SETTERS
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

    public void addOrders(Order order) {
        orders.add(order);
    }

    public void rmvOrders(Order order) {
        orders.remove(order);
    }

    public void setState(UserState newState) {
        this.state = newState;
    }

    public UserState getState() {
        return this.state;
    }

    public static ArrayList<Client> getClients() {
        return clients;
    }

    public void addPrescription(Prescription presc) {
        this.prescriptions.add(presc);
    }

    public ArrayList<Prescription> getPrescriptionsList() {
        return prescriptions;
    }

    // gets a prescription by id
    public Prescription getPrescriptionById(int id) {
        for (Prescription p : prescriptions) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    // prints all prescriptions linked to user
    public String printPrescriptions() {
        String res = "";
        Interface.newWindow();
        Interface.drawTitle("YOUR PRESCRIPTIONS", 1);
        if (getPrescriptionsList().isEmpty()) {
            return "┌────────────────────────────────────────────────\nWARNING: NO PRESCRIPTIONS LINKED TO YOUR USER\n└────────────────────────────────────────────────";
        }
        for (Prescription p : prescriptions) {
            res += ("|_ id: " + p.getId() + " | doctor: " + p.getDocName() + " | type: " + p.getType());
        }
        return res;
    }

    // ORDER METHODS
    public ArrayList<Order> getAllActiveOrders() {
        ArrayList<Order> activeOrders = new ArrayList<>();
        for (Order order : getOrders()) {
            if (order.getState() != OrderState.CANCELLED || order.getState() != OrderState.DELIVERED) {
                activeOrders.add(order);
            }
        }
        return activeOrders;
    }

    // prints all orders linked to user
    public String printAllOrders() {
        String res = "";
        Interface.newWindow();
        if (getOrders().isEmpty() || getOrders() == null) {
            System.out.print("┌────────────────────────────────────────────────\nWARNING: NO ORDERS LINKED TO YOUR USER");
        }
        for (Order ord : getOrders()) {
            res += "\n┌────────────────────────────────────────────────\n│ number: " + getOrders().indexOf(ord)
                    + "\n│ Order Status: " + ord.getState() + "\n│ Available Date: " + ord.getAvailableDate()[0] + "/"
                    + ord.getAvailableDate()[1] + "/" + ord.getAvailableDate()[2] + "\n│ Creation Date: "
                    + ord.getCreationDate()[0] + "/" + ord.getCreationDate()[1] + "/" + ord.getCreationDate()[2]
                    + ord.listProductNames() + "\n├────────────────────────────────────────────────\n│ Total price: "
                    + ord.totalPrice();
        }
        res += "\n└────────────────────────────────────────────────";
        return res;
    }

    // prints orders
    public String printActiveOrders() {
        String res = "";
        Interface.newWindow();
        if (getOrders().isEmpty() || getOrders() == null) {
            System.out.print("┌────────────────────────────────────────────────\nWARNING: NO ORDERS LINKED TO YOUR USER");
        } else if (!anyOthers()) {
            System.out.print("┌────────────────────────────────────────────────\nWARNING: NO ORDERS LINKED TO YOUR USER");
        }
        for (Order ord : getOrders()) {
            if (ord.getState() != OrderState.CANCELLED) {
                if (ord.getState() != OrderState.DELIVERED) {
                    res += "\n┌────────────────────────────────────────────────\n│ number: " + getOrders().indexOf(ord)
                            + "\n│ Order Status: " + ord.getState() + "\n│ Available Date: " + ord.getAvailableDate()[0]
                            + "/" + ord.getAvailableDate()[1] + "/" + ord.getAvailableDate()[2] + "\n│ Creation Date: "
                            + ord.getCreationDate()[0] + "/" + ord.getCreationDate()[1] + "/" + ord.getCreationDate()[2]
                            + ord.listProductNames()
                            + "\n├────────────────────────────────────────────────\n│ Total price: " + ord.totalPrice();
                }
            }
        }
        res += "\n└────────────────────────────────────────────────";
        return res;
    }

    // cheks if there are orders without being cancelled or complete
    public Boolean anyOthers() {
        int instances = 0;
        for (Order ord : getOrders()) {
            if (ord.getState() != OrderState.CANCELLED && ord.getState() != OrderState.DELIVERED) {
                instances++;
            }
        }
        if (instances > 0) {
            return true;
        }
        return false;
    }

    // returns a order with the requested id
    public Order getOrderById(int id) {
        for (Order ord : this.orders) {
            if (ord.getId() == id) {
                return ord;
            }
        }
        return null;
    }

    // prints the content of a specific order´
    public void displayOrder(int id) {
        Order ord = getOrderById(id);
        Interface.drawTitle("ORDER NUMBER: " + ord.getId(), 2);
        System.out.print("\n┌─ PRODUCTS ─────────────────────────────────────");
        String res = "";
        int i = 0;
        for (Product prod : ord.getProductsList()) {
            i++;
            res += "\n» " + i + "st PRODUCT / state: " + prod.getState() + " / name: " + prod.getName() + " / price: "
                    + prod.getPrice();
        }
        res += "\n├────────────────────────────────────────────────\n│ Total price: " + ord.totalPrice();
        System.out.println(res);

        System.out.println("└────────────────────────────────────────────────");
    }

    // removes
    public void rmvOrderByIndex(int index) {
        if (Order.getOrderList().get(index) != null) {
            Order.getOrderList().get(index).setState(OrderState.CANCELLED);
        } else {
            System.out.println("ORDER NOT FOUND! [cooldown 2s]");
            Interface.wait(2);
        }

        // for (Order ord : orders) {
        // if (ord.getId() == id) {
        // ord.setState(OrderState.CANCELLED);
        // }
        // }
    }

    // returns user using id
    public static Client getUserById(int id) {
        for (int u = 0; u < clients.size(); u++) {
            Client usr = clients.get(u);
            if (usr.getId() == id) {
                return usr;
            }
        }
        return null;
    }

    // removes user using id
    public static void rmvUserById(int id) {
        for (int u = 0; u < clients.size(); u++) {
            Client usr = clients.get(u);
            if (usr.getId() == id) {
                clients.remove(id);
            }
        }
    }

    // user login
    public static Client login(String uName, String uPassword) {
        for (Client client : clients) {
            if (client.getName().equals(uName) && client.getPassword().equals(uPassword)) {
                return client;
            }
        }
        return null;
    }

    // checks if username already exists
    public static boolean userExists(String uName) {
        for (Client client : clients) {
            if (client.getName().equals(uName)) {
                System.out.println("Username already exists!");
                return true;
            }
        }
        return false;
    }

    // registers a new user
    public static Client register(String uName, String uPassword, int ucontact, String uAddress) {
        if (uName != null && uPassword != null) {
            return new Client(uName, uPassword, ucontact, uAddress);
        }
        return null;
    }
}
