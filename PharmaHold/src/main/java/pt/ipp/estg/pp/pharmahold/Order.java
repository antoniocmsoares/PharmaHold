//Order class - contains CRUD linked to Users.
package pt.ipp.estg.pp.pharmahold;

import java.util.ArrayList;

import pt.ipp.estg.pp.pharmahold.ENUMS.OrderState;

public class Order {

    private static int countId = 1;
    private int id;
    private OrderState state = OrderState.DRAFT;
    private int[] creationDate = new int[3]; // emition date :D dd/mm/yy
    private int[] availableDate = new int[3]; // expire date :| dd/mm/yy
    private ArrayList<Product> productsList = new ArrayList<>();
    private static ArrayList<Order> orderList = new ArrayList<>();

    public Order(int[] creationDate, int[] availableDate) {
        this.id = countId++;
        this.creationDate = creationDate;
        this.availableDate = availableDate;
        orderList.add(this);
    }

    // getters
    public static int getCountId() {
        return countId;
    }

    public int getId() {
        return id;
    }


    public OrderState getState() {
        return state;
    }

    public int[] getCreationDate() {
        return creationDate;
    }

    public int[] getAvailableDate() {
        return availableDate;
    }
    // setters

    public void setState(OrderState state) {
        this.state = state;
    }

    public static void setOrderList(ArrayList<Order> orderList) {
        Order.orderList = orderList;
    }

    public ArrayList<Product> getProductsList() {
        return productsList;
    }

    public static ArrayList<Order> getOrderList() {
        return orderList;
    }

    public ArrayList<Product> setProductsList(ArrayList<Product> newList) {
        productsList = newList;
        return productsList;
    }

    public void addProducts(Product produto, int qty) {
        for (int i = 0; i < qty; i++) {
            productsList.add(produto);
        }
    }

    public void rmvProducts(Product produto) {
        productsList.remove(produto);
    }

    public static Order getOrderById(int id) {
        for (int u = 0; u < orderList.size(); u++) {
            Order order = orderList.get(u);
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
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

    public String listProductNames() {
        String result = "";
        int i = 0;
        for (Product p : productsList) {
            i += 1;
            try {
            result += "\n» " + i + "º PRODUCT / name: " + p.getName() + " / id: " + p.getId();
            } catch (NullPointerException e) {
            }
        }
        return result;
    }

    public float totalPrice(){
        float total = 0f;
        for (Product p : productsList){
                total += p.getPrice();
        }
        return total;
    }

    public void orderCheckOut(){
        int orderId = this.getId();
        System.out.println("CHECKOUT COMPLETE! DELIVERY STATUS UPDATES WILL BE NOTIFIED! [Cooldown 2s]");
        Interface.wait(2);
        for (OrderState newState : OrderState.values()) {
            switch (newState) {
                case DRAFT, CANCELLED, PROCESSING: {
                    continue;
                }

                default: {
                    this.setState(newState);
                    System.out.println("STATUS UPDATE: ORDER # "+ orderId + " IN STATE: "+ newState +"!");
                    Interface.wait(2);
                    break;
                }
            }
        }
        
    }

    
}
