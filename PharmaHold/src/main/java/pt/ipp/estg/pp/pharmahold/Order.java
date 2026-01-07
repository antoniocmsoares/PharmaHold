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

    // CREATE
    public static Order createOrder(int[] creationDate, int[] availableDate) {
        Order o = new Order(creationDate, availableDate);
        return o;
    }

    // get product by index
    public static Order getOrderByIndex(int prod) {
        Order o = orderList.get(prod);
        return o;
    }

    public static void ListOrderByIndex(int index) {
        Order ord = getOrderByIndex(index);
        Interface.drawTitle("ORDER NUMBER: " + ord.getId(), 2);
        System.out.print("\n┌─ PRODUCTS ─────────────────────────────────────");
        String res = "";
        int i = 0;
        for (Product prod : ord.getProductsList()) {
            i++;
            res += "\n» " + i + "st PRODUCT / state: " + prod.getState() + " / name: " + prod.getName() + " / price: "
                    + prod.getPrice();
        }
        res += "\n├────────────────────────────────────────────────\n│ Total price: " + ord.totalPrice();
        System.out.println(res);

        System.out.println("└────────────────────────────────────────────────");
    }


    // delete order by index (DELETE)
    public static void rmvOrderByIndex(int prod) {
        orderList.remove(prod);
    }

    public void addProducts(Product produto, int qty) {
        for (int i = 0; i < qty; i++) {
            this.productsList.add(produto);
        }
    }

    public void rmvProducts(Product produto) {
        this.productsList.remove(produto);
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

    // prints all orders
    public static String printAllOrders() {
        String res = "";
        Interface.newWindow();
        if (orderList.isEmpty() || orderList == null) {
            System.out
                    .print("┌────────────────────────────────────────────────\nWARNING: NO ORDERS FOUND");
        }
        for (Order ord : orderList) {
            res += "\n┌────────────────────────────────────────────────\n│ number: " + (orderList.indexOf(ord))
                    + "\n│ Order Status: " + ord.state + "\n│ Available Date: " + ord.availableDate[0] + "/"
                    + ord.availableDate[1] + "/" + ord.availableDate[2] + "\n│ Creation Date: "
                    + ord.creationDate[0] + "/" + ord.creationDate[1] + "/" + ord.creationDate[2]
                    + ord.listProductNames() + "\n├────────────────────────────────────────────────\n│ Total price: "
                    + ord.totalPrice();
        }
        res += "\n└────────────────────────────────────────────────";
        return res;
    }

    public String listProductNames() {
        String result = "";
        int i = 0;
        for (Product p : productsList) {
            i += 1;
            try {
            result += "\n» " + i + "st PRODUCT / state: " + p.getState() + " / name: " + p.getName() + " / price: " + p.getPrice();
            } catch (Exception e) {
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

    public float totalPoints(Client client) {
        int newPoints = client.getTotalPoints();
        float aux;

        for (Product p : productsList) {
            if (p.getNeedPrescription()) {
                aux = p.getPrice();
                newPoints += aux;
            } else {
                aux = Math.round(p.getPrice() * client.getBonus());
                newPoints += aux;
            }
        }
        client.setTotalPoints(newPoints);
        return newPoints;
    }

    public void orderCheckOut(int ordIndex){
        System.out.println("CHECKOUT COMPLETE! DELIVERY STATUS UPDATES WILL BE NOTIFIED! [Cooldown 2s]");
        Interface.wait(2);
        for (OrderState newState : OrderState.values()) {
            switch (newState) {
                case DRAFT, CANCELLED, PROCESSING: {
                    continue;
                }

                default: {
                    System.out.println("STATUS UPDATE: ORDER # "+ ordIndex + " IN STATE: "+ newState +"!"+ "\n");
                    this.state = newState;
                    Interface.wait(2);
                    break;
                }
            }
        }
    }

    public Product getProductsByIndex(int index) {
        return productsList.get(index);
    }
}
