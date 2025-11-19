package pt.ipp.estg.pp.pharmahold;

import java.util.ArrayList;
import pt.ipp.estg.pp.pharmahold.ENUMS.OrderStates;

public class Orders {
    private int id;
    private OrderStates state;
    private int[] emiDate = new int[3]; //emition date :D dd/mm/yy
    private int[] expDate = new int[3]; //expire date :| dd/mm/yy
    private static ArrayList<Products> productsList = new ArrayList<>();

    public static void addProducts(Products produto) {
        productsList.add(produto);
    }
}

