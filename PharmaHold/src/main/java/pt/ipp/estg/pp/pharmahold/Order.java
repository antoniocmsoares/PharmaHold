package pt.ipp.estg.pp.pharmahold;

import java.util.ArrayList;
import pt.ipp.estg.pp.pharmahold.ENUMS.OrderState;

public class Order {

    private static int countId = 1;
    private int id;
    private OrderState state;
    private int[] creationDate = new int[3]; //emition date :D dd/mm/yy
    private int[] availableDate = new int[3]; //expire date :| dd/mm/yy
    private ArrayList<Product> productsList = new ArrayList<>();
    private static ArrayList<Order> orderList = new ArrayList<>();

    public Order(int[] creationDate, int[] availableDate) {
        this.id = countId++;
        this.creationDate = creationDate;
        this.availableDate = availableDate;
        orderList.add(this);
    }

    public void addProducts(Product produto) {
        productsList.add(produto);
    }

    public static int getCountId() {
        return countId;
    }

    public static void setCountId(int countId) {
        Order.countId = countId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[] getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(int[] creationDate) {
        this.creationDate = creationDate;
    }

    public int[] getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(int[] availableDate) {
        this.availableDate = availableDate;
    }

    public ArrayList<Product> getProductsList() {
        return productsList;
    }

    public static ArrayList<Order> getOrderList() {
        return orderList;
    }
    

    public static String listAllOrders() {
        String orders = "";
        for (Order order : orderList) {
            orders += "Order" + order.getId() + "\n";
            for (Product p : order.getProductsList()) {
                orders += "Product: " + p.toString() + "\n";
            }
        }
        return orders;
    }
}
