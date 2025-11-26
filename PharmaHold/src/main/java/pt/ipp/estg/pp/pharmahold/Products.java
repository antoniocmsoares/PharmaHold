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

    public Products(String productName, float productPrice, int currentStock, boolean needPrescription) {
        this.productId = countId++;
        this.productName = productName;
        this.productPrice = productPrice;
        this.minStock = 3;
        this.needPrescription = needPrescription;
        this.currentStock = currentStock;

        productsList.add(this);

    }

    public static String listAllProducts() {
        String prods = "";
            for (Products p : productsList) {
                prods += ("Product name: " + p.getProductName() + "\n" + "Price: " + p.getProductPrice() + " EUR\n" + "QTY Available: " + p.getCurrentStock() + "\n"
                        + "Prescription only?: " + p.isNeedPrescription() + "\n");
                prods += "------------------------\n";
        }
        return prods;
    }

    public static ArrayList<Products> getAllProducts() {
        return productsList;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

    public int getMinStock() {
        return minStock;
    }

    public void setMinStock(int minStock) {
        this.minStock = minStock;
    }

    public boolean isNeedPrescription() {
        return needPrescription;
    }

    public void setNeedPrescription(boolean needPrescription) {
        this.needPrescription = needPrescription;
    }

    public static int getCountId() {
        return countId;
    }

    public static void setCountId(int countId) {
        Products.countId = countId;
    }

    public static void setProductsList(ArrayList<Products> productsList) {
        Products.productsList = productsList;
    }

    
}
