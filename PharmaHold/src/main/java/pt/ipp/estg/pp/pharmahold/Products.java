package pt.ipp.estg.pp.pharmahold;
import java.util.ArrayList;
public class Products {
    private int productId;
    private boolean productState;
    private String productName;
    private float productPrice;

    private int currentStock;
    private int minStock;

    private static ArrayList<Products> productsList = new ArrayList<>();

    public Products(int productId, boolean productState, String productName, float productPrice, int minStock) {
        this.productId = productId;
        this.productState = productState;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public static void addProducts(Products produto) {
        productsList.add(produto);
    }
}