package pt.ipp.estg.pp.pharmahold;

import java.util.ArrayList;

public class Products {

    private int productId;
    private String productName;
    private float productPrice;

    private int currentStock;
    private int minStock;

    private boolean needPrescription;

    private static int countId = 1;

    static private ArrayList<Products> productsList = new ArrayList<>();

    public Products(String productName, float productPrice, int minStock, boolean needPrescription) {
        this.productId = countId++;
        this.productName = productName;
        this.productPrice = productPrice;
        this.minStock = minStock;
        this.needPrescription = needPrescription;

        productsList.add(this);

    }
}
