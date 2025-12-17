package pt.ipp.estg.pp.pharmahold;

import java.util.ArrayList;

import pt.ipp.estg.pp.pharmahold.ENUMS.ProductState;

public class Product {
    private int id;
    private String name;
    private float price;
    private int currentStock;
    private int minStock;
    private ProductState state;

    private boolean needPrescription;

    private static int countId = 1;

    static private ArrayList<Product> productsList = new ArrayList<>();

    public Product(String productName, float productPrice, int currentStock, boolean needPrescription) {
        this.id = countId++;
        this.name = productName;
        this.price = productPrice;
        this.minStock = 3;
        this.needPrescription = needPrescription;
        this.currentStock = currentStock;
        this.state = ProductState.ACTIVE;

        productsList.add(this);
    }

    // STATIC ---------------------------------------------------------------------------------------------------------------
    //GETTERS
    public static int getCountId() {
        return countId;
    }

    public static ArrayList<Product> getAllProducts() {
        return productsList;
    }

    //SETTERS
    public static void setCountId(int countId) {
        Product.countId = countId;
    }

    public static void setProductsList(ArrayList<Product> productsList) {
        Product.productsList = productsList;
    }

    //FUNCTIONS/METHODS
    public static String listAllProducts() {
        String prods = "";
        for (Product p : productsList) {
            prods += ("Product name: " + p.getProductName() + "\n" + "Price: " + p.getProductPrice() + " EUR\n" + "QTY Available: " + p.getCurrentStock() + "\n"
                    + "Prescription only?: " + p.isNeedPrescription() + "\n");
            prods += "------------------------\n";
        }
        return prods;
    }

    //NON-STATIC ---------------------------------------------------------------------------------------------------------
    //GETTERS
    public int getId() {
        return id;
    }

    public String getProductName() {
        return name;
    }

    public float getProductPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public int getMinStock() {
        return minStock;
    }

    //SETTERS
    public void setProductName(String productName) {
        this.name = productName;
    }

    public void setProductPrice(float productPrice) {
        this.price = productPrice;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

    public void setMinStock(int minStock) {
        this.minStock = minStock;
    }

    public void setNeedPrescription(boolean needPrescription) {
        this.needPrescription = needPrescription;
    }
    
    public void setState(ProductState newState) {
        this.state = newState;
    }

    //FUNCTIONS/METHODS
    public boolean isNeedPrescription() {
        return needPrescription;
    }
}
