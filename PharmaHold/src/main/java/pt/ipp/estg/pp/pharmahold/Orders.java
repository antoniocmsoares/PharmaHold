package pt.ipp.estg.pp.pharmahold;

import java.util.ArrayList;
import pt.ipp.estg.pp.pharmahold.ENUMS.OrderStates;

public class Orders {

    private static int countId = 1;
    private int id;
    private OrderStates state;
    private int[] creationDate = new int[3]; //emition date :D dd/mm/yy
    private int[] availableDate = new int[3]; //expire date :| dd/mm/yy
    private static ArrayList<Products> productsList = new ArrayList<>();
    private static ArrayList<Orders> orderList = new ArrayList<>();

    public Orders(int[] creationDate, int[] availableDate) {
        this.id = countId++;
        this.creationDate = creationDate;
        this.availableDate = availableDate;
        orderList.add(this);
    }

    public static void addProducts(Products produto) {
        productsList.add(produto);
    }

    public static int getCountId() {
        return countId;
    }

    public static void setCountId(int countId) {
        Orders.countId = countId;
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

    public static ArrayList<Products> getProductsList() {
        return productsList;
    }

    public static ArrayList<Orders> getOrderList() {
        return orderList;
    }

    public static String showAllOrders() {
        String txt = ("- orders -----------------------------------------\n");

        for (Orders order : orderList) {
            txt = txt + "|_" + order.getAvailableDate();
        }

        return txt;
    }

    public static String printAllOrders() {
        String txt = "";
        for (Orders order : orderList) {
            txt += "|_ " + order.getId() + " index\n";
            for (Products p : order.getProductsList()) {
                txt += "|__ product: " + p.toString() + "\n";
            }
        }
        return txt;
    }
}
