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
}

