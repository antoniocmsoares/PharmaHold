//Product class - contains CRUD linked to Users.
package pt.ipp.estg.pp.pharmahold;

import java.util.ArrayList;

import pt.ipp.estg.pp.pharmahold.ENUMS.ProductState;

public class Product {
    private int id;
    private String name;
    private float price;
    private int currentStock;
    private ProductState state;

    private boolean needPrescription;

    private static int countId = 1;

    static private ArrayList<Product> productsList = new ArrayList<>();

    public Product(String productName, float productPrice, int currentStock, boolean needPrescription) {
        this.id = countId++;
        this.name = productName;
        this.price = productPrice;
        this.needPrescription = needPrescription;
        this.currentStock = currentStock;
        this.state = ProductState.ACTIVE;

        productsList.add(this);
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

    public ProductState getState() {
        return state;
    }

    public void setState(ProductState state) {
        this.state = state;
    }

    public boolean getNeedPrescription() {
        return needPrescription;
    }

    public void setNeedPrescription(boolean needPrescription) {
        this.needPrescription = needPrescription;
    }

    public static int getCountId() {
        return countId;
    }

    public static void setCountId(int countId) {
        Product.countId = countId;
    }

    public static ArrayList<Product> getProductsList() {
        return productsList;
    }

    public static void setProductsList(ArrayList<Product> productsList) {
        Product.productsList = productsList;
    }

    // object factory (CREATE)
    public static Product addProduct(String name, float price, int currentStock, boolean needPrescription) {
        Product p = new Product(name, price, currentStock, needPrescription);
        return p;
    }

    // show all products (READ)
    public static String listAllProducts() {
        String prods = "";
        for (Product p : productsList) {
            if (productsList.indexOf(p) == 0) {
                prods += ("┌────────────────────────────\n│ Product name: " + p.getName() + "\n│ Reference: "
                        + productsList.indexOf(p) + "\n│ Price: " + p.getPrice()
                        + " EUR\n" + "│ QTY Available: " + p.getCurrentStock()
                        + "\n│ Prescription only?: " + p.getNeedPrescription() + "\n");
            } else {
                prods += ("├────────────────────────────\n│ Product name: " + p.getName() + "\n│ Reference: "
                        + productsList.indexOf(p) + "\n│ Price: " + p.getPrice()
                        + " EUR\n" + "│ QTY Available: " + p.getCurrentStock()
                        + "\n│ Prescription only?: " + p.getNeedPrescription() + "\n");
            }
        }
        prods += "└────────────────────────────\n";
        return prods;
    }

    public static Product getProductsById(int id) {
        for (int u = 0; u < productsList.size(); u++) {
            Product prod = productsList.get(u);
            if (prod.getId() == id) {
                return prod;
            }
        }
        return null;
    }

    public static Boolean isaProduct(int id) {
        for (Product product : productsList) {
            if (product.id == id) {
                return true;
            }
        }
        return false;
    }

    // delete product by index (DELETE)
    public static void rmvProductByIndex(int prod) {
        productsList.remove(prod);
    }

    // get product by index
    public static Product getProductsByIndex(int prod) {
        Product p = productsList.get(prod);
        return p;
    }

    public void displaySelf() {
        System.out.println("┌────────────────────────────\n│ Product name: " + this.getName() + "\n│ Reference: "
                + productsList.indexOf(this) + "\n│ Price: " + this.getPrice()
                + " EUR\n" + "│ QTY Available: " + this.getCurrentStock()
                + "\n│ Prescription only?: " + this.getNeedPrescription() + "\n└────────────────────────────");
    }
}
