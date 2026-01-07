// MAIN FILE || PHARMAHOLD ||
//┌─────────────────────────────────────────────────────────────────────────────────┐
//│        -                                                                        │
//│    -  /_\  -                                                                    │
//│   /_\ └─┘ /_\      ┌─────────────────────────────────────────────────────────┐  │
//│   └─┘ │ │ └─┘      │ HI THERE! THIS PROGRAM WAS POWERED BY TÓ AND MARTINI <3 │  │
//│   │ └_┘ └_┘ │      │ .───────────────────────────────────────────────────────┘  │
//│  ┌┘─  ___  ─└┐     │/                                                           │
//│  │ ─ / ─ \ ─ │   0/                                                             │
//│  │ ─ │ ─ │ ─ │  /│                                                              │
//│  └───┘ ─ └───┘  /'\                                       @ART MADE BY MARTINI  │
//└─────────────────────────────────────────────────────────────────────────────────┘
package pt.ipp.estg.pp.pharmahold;

import java.time.LocalDate;
import java.util.*;
import java.util.Scanner;

import pt.ipp.estg.pp.pharmahold.ENUMS.OrderState;
import pt.ipp.estg.pp.pharmahold.ENUMS.PrescriptionType;

public class PharmaHold {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        // USERS
        new Client("martini", "pass1", 123456789, "rua 1 numero 2");
        new Client("pass", "pass", 919999999, "rua 1 numero 2");
        new Client("andre", "pass2", 919222222, "rua 2 numero 3");

        // ADMIN
        new Admin("root", "root", 911111111);

        // PRESCRIPTIONS
        new Prescription(new int[] { 1, 1, 2026 }, new int[] { 2, 5, 2026 }, PrescriptionType.COMMON,
                "doctor1");
        new Prescription(new int[] { 4, 2, 2026 }, new int[] { 12, 5, 2026 }, PrescriptionType.COMMON,
                "doctor2");
        new Prescription(new int[] { 7, 2, 2026 }, new int[] { 22, 6, 2026 }, PrescriptionType.COMMON,
                "doctor3");

        // PRODUCTS
        new Product("brufen", 15.44f, 15, false);// 1
        new Product("benuron", 12.44f, 14, true);// 2
        new Product("ritalina", 11.44f, 12, false);// 3
        new Product("griponal", 14.44f, 10, true);// 4

        // ORDERS
        Order.createOrder(new int[] { 1, 3, 2026 }, new int[] { 1, 4, 2026 });
        Order.getOrderById(1).addProducts(Product.getProductsById(1), 1);
        Order.getOrderById(1).addProducts(Product.getProductsById(2), 1);

        Order.createOrder(new int[] { 5, 3, 2026 }, new int[] { 5, 4, 2026 });
        Order.getOrderById(2).addProducts(Product.getProductsById(4), 1);
        Order.getOrderById(2).addProducts(Product.getProductsById(1), 1);
        Order.getOrderById(2).addProducts(Product.getProductsById(2), 1);
        Order.getOrderById(2).addProducts(Product.getProductsById(3), 1);

        Order.createOrder(new int[] { 5, 3, 2026 }, new int[] { 5, 4, 2026 });
        Order.getOrderById(3).addProducts(Product.getProductsById(3), 3);
        Order.getOrderById(3).addProducts(Product.getProductsById(4), 2);

        Order.createOrder(new int[] { 5, 3, 2026 }, new int[] { 5, 4, 2026 });
        Order.getOrderById(4).addProducts(Product.getProductsById(1), 2);
        Order.getOrderById(4).addProducts(Product.getProductsById(4), 1);

        // Linking orders to clients
        Client.getUserById(1).addOrders(Order.getOrderById(1));
        Client.getUserById(1).addOrders(Order.getOrderById(2));
        Client.getUserById(2).addOrders(Order.getOrderById(3));
        Client.getUserById(3).addOrders(Order.getOrderById(4));

        Order.getOrderById(1).setState(OrderState.CANCELLED);

        // linking prescriptions to clients
        Client.getUserById(1).addPrescription(Prescription.getPrescriptionById(1));
        Client.getUserById(2).addPrescription(Prescription.getPrescriptionById(2));
        Client.getUserById(3).addPrescription(Prescription.getPrescriptionById(3));

        // LOCAL VARIABLES
        int userChoice = -999;
        int userType = 0;
        int option = -999;
        int option2 = -999;
        String name = null;
        boolean exists = false;
        boolean valid;
        Order currOrder = null;

        boolean isLoggedIn = false;
        Client loggedClient = null;
        Admin loggedAdmin = null;
        User loggedUser = null;

        int adminOp = -999;
        // begin interface, nothing prints until here AFTER " WHILEISLOGGEDIN" BEWARE

            
            System.out.println(Order.getOrderById(1).listProductNames());
        while (userChoice != 0) {
            if (!isLoggedIn) {
                Interface.newWindow();
                name = "WELCOME TO PHARMAHOLD";
                Interface.drawTitle("WELCOME TO PHARMAHOLD", 0);
                Interface.drawButtonList("def", "LEAVE [0]", "LOGIN [1]", "SIGNUP [2]");
                userChoice = Interface.drawInput(46);

                switch (userChoice) {
                    case 0: {
                        break;
                    }
                    case 1: {
                        while (!isLoggedIn) {
                            Interface.newWindow();
                            Interface.drawFormInput("Username", 49);
                            String userName = input.nextLine();
                            Interface.drawFormInput("Password", 49);
                            String pass = input.nextLine();

                            loggedUser = Client.login(userName, pass);
                            if (loggedUser instanceof Client) {
                                loggedClient = (Client) loggedUser;
                                userName = loggedClient.getName();
                                isLoggedIn = true;
                                break;
                            } else {
                                loggedUser = Admin.login(userName, pass);
                                if (loggedUser instanceof Admin) {
                                    loggedAdmin = (Admin) loggedUser;
                                    isLoggedIn = true;
                                    break;
                                } else {
                                    Interface.newWindow();
                                    System.out.println(
                                            "Your username or password are incorrect, in case you don't have an account you should SIGN UP.\nCooldown of 2s per try");
                                    Interface.wait(2);
                                    break;
                                }
                            }
                        }
                        break;
                    }

                    case 2: {
                        // sign up
                        Interface.newWindow();
                        Interface.drawFormInput("Username", 49);
                        String userName = input.nextLine();
                        Interface.drawFormInput("Password", 49);
                        String pass = input.nextLine();

                        while (pass.length() < 3) {
                            System.out.println("THE PASSWORD MUST HAVE +3 CHARS");
                            Interface.drawFormInput("New Password", 49);
                            pass = input.nextLine();
                        }

                        Interface.drawFormInput("Contact", 49);
                        int contact = Interface.readInt();
                        Interface.drawFormInput("Address", 49);
                        String address = input.nextLine();

                        if (Client.userExists(userName)) {
                            System.out.println("Registration failed.");
                            break;
                        } else {
                            Client usr = Client.register(userName, pass, contact, address);
                            System.out.println(userName);

                            System.out.println("Successfully registered!");
                            loggedUser = usr;
                            loggedClient = usr;
                            isLoggedIn = true;
                            break;
                        }
                    }

                    default: {
                        System.out.println("Please use the numbers displayed on the buttons [cooldown of 1s]");
                        Interface.wait(2);
                        break;
                    }
                }
            }

            if (loggedUser instanceof Client) {

                // logged client menu
                Interface.newWindow();

                while (isLoggedIn && loggedUser != null) {
                    Interface.drawTitle("WELCOME TO PHARMAHOLD", 70);
                    Interface.drawButtonList("def", "LOGOUT [0]", "PRODUCTS [1]", "ORDERS [2]", "ORDER HISTORY [3]",
                            "PRESCRIPTIONS [4]", "PROFILE [5]");
                    userChoice = Interface.drawInput(116);

                    switch (userChoice) {
                        case 0: {
                            Interface.newWindow();
                            userChoice = -999;
                            userType = -999;
                            isLoggedIn = false;
                            loggedAdmin = null;
                            loggedClient = null;
                            System.out.println("Thank you for using PharmaHold!");
                            Interface.wait(2);
                        }
                        case 1: {
                            Interface.newWindow();
                            Interface.drawTitle("PRODUCTS LIST", 0);
                            System.out.println(Product.listAllProducts());
                            System.out.println("Press ENTER to go back.");
                            input.nextLine();
                            break;
                        }
                        case 2: {
                            Interface.newWindow();
                            try {
                                System.out.println(loggedClient.printActiveOrders());
                            } catch (Exception e) {
                                System.out.println("No orders linked to your user.");
                            }
                            // client order mgnt
                            Interface.drawButtonList(" ", "BACK [0]", "CREATE [1]", "REMOVE [2]", "EDIT [3]",
                                    "CHECKOUT [4]");
                            userChoice = Interface.drawInput(0);
                            switch (userChoice) {
                                case 0:
                                    userChoice = -999;
                                    break;
                                case 1:
                                    Interface.newWindow();
                                    Interface.drawTitle("CREATE ORDER", 0);

                                    // COOL STUFF THAT I FOUND OUT year//month/day so i made it day//month//year
                                    LocalDate currentDate = LocalDate.now();
                                    int[] creationDate = { // CREATION DATE
                                            currentDate.getDayOfMonth(),
                                            currentDate.getMonthValue(),
                                            currentDate.getYear()
                                    };

                                    LocalDate cooldownDate = currentDate.plusDays(30); // AVAILABLE DATE (+30 days)
                                    int[] availableDate = { // CREATION DATE
                                            cooldownDate.getDayOfMonth(),
                                            cooldownDate.getMonthValue(),
                                            cooldownDate.getYear()
                                    };

                                    loggedClient.addOrders(Order.createOrder(creationDate, availableDate));
                                    System.out.println("ORDER CREATED! [Cooldown 2s]");
                                    Interface.wait(2);
                                    userChoice = -999;
                                    break;
                                case 2:
                                    if (loggedClient.anyOthers()) {
                                        Interface.newWindow();
                                        Interface.drawTitle("REMOVE ORDER", 0);
                                        try {
                                            loggedClient.printActiveOrders();
                                        } catch (Exception e) {
                                            System.out.println("No orders linked to your user.");
                                        }
                                        System.out.println(
                                                "INSERT THE ORDER NUMBER [this action will cancel the order]: ");
                                        option = Interface.drawInput(0);

                                        try {
                                            loggedClient.rmvOrderByIndex(option);
                                        } catch (Exception e) {
                                            System.out.println("ORDER NOT FOUND! [cooldown 2s]");
                                            Interface.wait(2);
                                        }

                                        Interface.wait(2);
                                    } else {
                                        Interface.newWindow();
                                        System.out.println(
                                                "this option is not avaible, seems like you have no orders linked to your account.");
                                        Interface.wait(2);
                                        Interface.newWindow();
                                    }
                                    userChoice = -999;
                                    break;
                                case 3: {
                                    if (loggedClient.anyOthers()) {
                                        Interface.newWindow();
                                        Interface.drawTitle("EDIT ORDER", 0);
                                        try {
                                            System.out.println(loggedClient.printActiveOrders());
                                        } catch (Exception e) {
                                            System.out.println("No orders linked to your user.");
                                        }
                                        System.out.println(
                                                "INSERT THE ORDER NUMBER [this action will allow you to edit the order]: ");
                                        option = Interface.drawInput(0);
                                        Interface.newWindow();
                                        do {
                                            try {
                                                loggedClient.getOrderbyIndex(option); // testa?
                                                loggedClient.displayOrder(option);
                                                exists = true;
                                            } catch (Exception e) { // TA TOP A EXCEPTION
                                                System.out.println("ORDER NOT FOUND [cooldown 2s]");
                                                Interface.wait(2);
                                                exists = false;
                                            }

                                            if (exists) {
                                                currOrder = loggedClient.getOrderbyIndex(option);
                                                boolean productExists = false;
                                                Interface.drawButtonList(" ", "BACK [0]", "ADD PRODUCT [1]",
                                                        "RMV PRODUCT [2]");
                                                option2 = Interface.drawInput(0);
                                                switch (option2) {
                                                    case 0: {
                                                        break;
                                                    }
                                                    case 1: {
                                                        System.out.println(
                                                                "INSERT THE ID OF THE PRODUCT TO ADD IT IN THIS ORDER");
                                                        int opAdd = Interface.drawInput(0);
                                                        System.out
                                                                .println("INSERT THE QUANTITY OF THE PRODUCT YOU WISH");
                                                        int qt = Interface.drawInput(0);

                                                        Product currProd = null;

                                                        try {
                                                            // currProd = Product.getProductsById(opAdd);
                                                            productExists = Product.isaProduct(opAdd);
                                                        } catch (Exception e) {
                                                            System.out.println("PRODUCT ID NOT FOUND! [Cooldown 2s]");
                                                            Interface.wait(2);
                                                        }

                                                        if (productExists) {
                                                            currOrder.addProducts(currProd, qt);
                                                            System.out.println(
                                                                    qt + " PRODUCT ID " + opAdd
                                                                            + " ADDED! [Cooldown 2s]");
                                                            Interface.wait(2);
                                                            break;
                                                        } else {
                                                            System.out.println("PRODUCT ID NOT FOUND! [Cooldown 2s]");
                                                            Interface.wait(2);
                                                            break;
                                                        }
                                                    }
                                                    case 2: {
                                                        System.out.println(
                                                                "INSERT THE ID OF THE PRODUCT TO REMOVE IT FROM THE ORDER");
                                                        int opRmv = Interface.drawInput(0);
                                                        Product currProd = null;
                                                        try {
                                                            currOrder.getProductsList().indexOf(opRmv);
                                                            // Product.getProductsById(opRmv);
                                                        } catch (Exception e) {
                                                            productExists = false;
                                                        }

                                                        if (productExists) {
                                                            currProd = currOrder.getProductsByIndex(opRmv);
                                                            currOrder.rmvProducts(currProd);
                                                            System.out.println(
                                                                    "1 PRODUCT ID " + opRmv
                                                                            + " Removed! [Cooldown 2s]");
                                                            Interface.wait(2);
                                                            break;
                                                        } else {
                                                            System.out.println("PRODUCT ID NOT FOUND! [Cooldown 2s]");
                                                            Interface.wait(2);
                                                            break;
                                                        }
                                                    }
                                                    default: {
                                                        System.out.println("INSERT A VALID OPTION");
                                                        Interface.wait(2);
                                                        break;
                                                    }
                                                }
                                            } else {
                                                System.out.println(loggedClient.printActiveOrders());
                                                option = Interface.drawInput(0);
                                            }
                                        } while (option2 != 0);
                                    } else {
                                        Interface.newWindow();
                                        System.out.println(
                                                "this option is not avaible, seems like you have no orders linked to your account.");
                                        Interface.wait(2);
                                        Interface.newWindow();
                                    }
                                    userChoice = -999;
                                    break;
                                }

                                case 4: {
                                    if (loggedClient.anyOthers()) {
                                        Interface.newWindow();
                                        Interface.drawTitle("ORDER CHECKOUT", 0);
                                        int opCheckout = -999;
                                        try {
                                            System.out.println(loggedClient.printActiveOrders());
                                        } catch (Exception e) {
                                            System.out.println("No orders linked to your user.");
                                        }
                                        System.out.println(
                                                "INSERT THE ORDER NUMBER [This action will perform the checkout]: ");
                                        opCheckout = Interface.drawInput(0);
                                        Interface.newWindow();
                                        do {
                                            try {
                                                loggedClient.getOrderbyIndex(opCheckout);
                                                loggedClient.displayOrder(opCheckout);
                                                exists = true;
                                            } catch (Exception e) {
                                                System.out.println("ORDER NOT FOUND [cooldown 2s]");
                                                Interface.wait(2);
                                                exists = false;
                                            }
                                            if (exists) {
                                                Interface.drawButtonList(" ", "BACK [0]", "CONFIRM CHECKOUT [1]");
                                                option2 = Interface.drawInput(0);
                                                switch (option2) {
                                                    case 0: {
                                                        break;
                                                    }
                                                    case 1: {
                                                        loggedClient.getOrderbyIndex(opCheckout).orderCheckOut(opCheckout);
                                                        loggedClient.getOrderbyIndex(opCheckout).totalPoints(loggedClient);
                                                        break;
                                                    }
                                                    default: {
                                                        System.out.println("INSERT A VALID OPTION");
                                                        Interface.wait(2);
                                                        break;
                                                    }
                                                }
                                            } else {
                                                System.out.println(loggedClient.printActiveOrders());
                                                option = Interface.drawInput(0);
                                            }
                                            break;
                                        } while (option2 != 0);
                                    } else {
                                        Interface.newWindow();
                                        System.out.println(
                                                "THIS OPTION IS NOT AVAIBLE MOST LIKELY YOU HAVE NO ORDERR [cooldown of 2s]");
                                        Interface.wait(2);
                                        Interface.newWindow();
                                    }
                                    userChoice = -999;
                                }

                                default: {
                                    if (userChoice != -999) {
                                        System.out.println(
                                                "PLEASE USE THE NUMBERS DISPLAYED ON THE BUTTONS [cooldown of 2s]");
                                        Interface.wait(2);
                                    }
                                    break;
                                }
                            }
                            break;
                        }
                        case 3: {
                            Interface.newWindow();
                            Interface.drawTitle("YOUR ORDER HISTORY", 0);
                            try {
                                System.out.println(loggedClient.printAllOrders());
                            } catch (Exception e) {
                                System.out.println("No orders linked to your user.");
                            }

                            System.out.println("Press ENTER to go back.");
                            input.nextLine();
                            break;
                        }
                        case 4: {
                            Interface.newWindow();
                            Interface.drawTitle("YOUR PRESCRIPTIONS", 0);
                            try {
                                System.out.println(loggedClient.printPrescriptions());
                            } catch (Exception e) {
                                System.out.println("No prescriptions linked to your user.");
                            }
                            System.out.println("Press ENTER to go back.");
                            input.nextLine();
                            break;
                        }
                        case 5: {
                            Interface.drawTitle("YOUR PROFILE", 0);
                            Interface.drawPerfil(loggedClient);
                            System.out.println("CHOSSE THE DATA TO EDIT");
                            Interface.drawButtonList(" ", "BACK [0]", "NAME [1]", "PHONE [2]", "ADDRESS [3]",
                                    "PASSWORD [4]");
                            int dataType = Interface.readInt();

                            switch (dataType) {
                                case 0:
                                    break;
                                case 1: {
                                    Interface.drawTitle("NAME CHANGE", 0);
                                    String newName = null;
                                    Boolean fits = false;
                                    do {
                                        Interface.drawFormInput("new name", 100);
                                        newName = input.nextLine();
                                        if (newName != "" && !User.userExists(newName)) {
                                            loggedClient.setName(newName);
                                            fits = true;
                                        } else {
                                            fits = false;
                                        }
                                    } while (fits != true);
                                    break;
                                }
                                case 2: {
                                    Interface.drawTitle("PHONE CHANGE", 0);
                                    int newPhone = 0;
                                    Boolean fits = false;

                                    do {
                                        Interface.drawFormInput("new phone", 100);
                                        newPhone = Interface.readInt();

                                        if (newPhone > 0 && newPhone != loggedClient.getContact()) {
                                            if (newPhone <= 100000000) {
                                                System.out.println("Please provide a valid contact of 9 digits");
                                                Interface.wait(2);
                                                break;
                                            }
                                            try {
                                                loggedClient.setContact(newPhone);
                                            } catch (Exception e) {
                                                System.out.println("Error updating the information, please try again.");
                                                Interface.wait(2);
                                                break;
                                            }
                                            fits = true;
                                            System.out.println("Contact successfully updated!");
                                        }
                                    } while (fits != true);
                                    Interface.wait(2);
                                    break;
                                }
                                case 3: {
                                    Interface.drawTitle("ADDRESS CHANGE", 0);
                                    String newAddress = null;
                                    Boolean fits = false;
                                    do {
                                        Interface.drawFormInput("new address", 100);
                                        newAddress = input.nextLine();
                                        if (newAddress != "" && newAddress != loggedClient.getAddress()) {
                                            loggedClient.setAddress(newAddress);
                                            fits = true;
                                        } else {
                                            fits = false;
                                        }
                                    } while (fits != true);
                                    break;
                                }
                                case 4: {
                                    Interface.drawTitle("PASSWORD CHANGE", 0);
                                    String newPass = null;
                                    String currPass = null;
                                    Boolean fits = false;
                                    do {
                                        Interface.drawFormInput("current password", 100);
                                        currPass = input.nextLine();
                                        if (currPass.equals(loggedClient.getPassword())) { // <_problem here retorna
                                                                                           // como se n fosse...
                                            Interface.drawFormInput("new password", 100);
                                            newPass = input.nextLine();
                                            if (newPass.length() > 3) {
                                                if (newPass != loggedClient.getPassword()) {
                                                    loggedClient.setPassword(newPass);
                                                    fits = true;
                                                } else {
                                                    System.out.println("You must choose a diferent password");
                                                    fits = false;
                                                }
                                            } else {
                                                System.out.println("Password must have more than 3 characters");
                                                fits = false;
                                            }
                                        } else {
                                            System.out.println("Wrong password");
                                        }

                                    } while (fits != true);
                                    break;
                                }
                                default:
                                    break;
                            }
                            break;
                        }
                        default: {
                            System.out.println("PLEASE USE THE NUMBERS DISPLAYED ON THE BUTTONS [cooldown of 2s]");
                            Interface.wait(2);
                            break;
                        }
                    }
                }
            } else if (loggedUser instanceof Admin) {
                while (isLoggedIn && loggedUser != null) {
                    Interface.drawTitle("ADMIN PHARMAHOLD ACCESS", 57);
                    Interface.drawButtonList("def", "LOGOUT [0]", "PRODUCT MGMT [1]", "ORDERS MGMT[2]",
                            "USER MGMT [3]");
                    adminOp = Interface.drawInput(116);

                    switch (adminOp) {
                        case 0: {
                            Interface.newWindow();
                            userChoice = -999;
                            userType = -999;
                            isLoggedIn = false;
                            loggedAdmin = null;
                            adminOp = -999;
                            System.out.println("Thank you for using PharmaHold!");
                            Interface.wait(2);
                            break;
                        }
                        case 1: {
                            int prod = 0;
                            Interface.newWindow();
                            Interface.drawTitle("PRODUCT MANAGEMENT", 0);
                            System.out.println(Product.listAllProducts());
                            Interface.drawButtonList("def", "LEAVE [0]", "ADD PRODUCT [1]", "REMOVE PRODUCT [2]",
                                    "EDIT PRODUCT [3]");
                            adminOp = Interface.drawInput(116);

                            switch (adminOp) {
                                case 0: {
                                    System.out.println("THANK YOU FOR USING PHARMAHOLD! [Cooldown 2s]");
                                    Interface.wait(2);
                                    break;
                                }
                                case 1: {
                                    Interface.newWindow();
                                    boolean needsP = false;
                                    Interface.drawTitle("ADD PRODUCT", 0);
                                    Interface.drawFormInput("Name", 49);
                                    String pName = input.nextLine();
                                    Interface.drawFormInput("Price", 49);
                                    float pPrice = input.nextFloat();
                                    Interface.drawFormInput("Stock", 49);
                                    int stock = Interface.readInt();
                                    Interface.drawFormInput("Needs prescription? Y/N", 49);
                                    Interface.drawButtonList("def", "No [0]", "Yes [1]");
                                    int prescBool = Interface.readInt();

                                    if (prescBool == 1) {
                                        needsP = true;
                                    }

                                    try {
                                        Product.addProduct(name, pPrice, stock, needsP);
                                    } catch (Exception e) {
                                        System.out.println("FALIED TO ADD PRODUCT! [Cooldown 2s]");
                                        Interface.wait(2);
                                        break;
                                    }

                                    System.out.println("PRODUCT ADDED SUCCESSFULLY! [Cooldown 2s]");
                                    Interface.wait(2);
                                    break;
                                }
                                case 2: {
                                    Interface.newWindow();
                                    Interface.drawTitle("REMOVE PRODUCT", 0);
                                    System.out.println(Product.listAllProducts());
                                    System.out.println("INSERT THE PRODUCT REFERENCE TO REMOVE IT: ");
                                    prod = Interface.readInt();

                                    try {
                                        Product.rmvProductByIndex(prod);
                                        System.out.println("PRODUCT REMOVED SUCESSFULLY! [Cooldown 2s]");
                                        Interface.wait(2);
                                    } catch (Exception e) {
                                        System.out.println("PRODUCT ID NOT FOUND! [Cooldown 2s]");
                                        Interface.wait(2);
                                    }
                                    break;
                                }
                                case 3: {
                                    Interface.newWindow();
                                    Interface.drawTitle("EDIT PRODUCT", 0);
                                    System.out.println(Product.listAllProducts());
                                    System.out.println("INSERT THE PRODUCT REFERENCE TO EDIT: ");
                                    int prodIndex = Interface.readInt();
                                    Product prodEdit = Product.getProductsByIndex(prodIndex);

                                    prodEdit.displaySelf();
                                        Interface.drawButtonList("def", "LEAVE [0]", "NAME [1]",
                                            "PRICE [2]", "STOCK [3]", "NEEDS PRESCRIPTION [4]");
                                    

                                        int opEdit = Interface.readInt();
                                        switch (opEdit) {

                                            case 1: {
                                                Interface.drawFormInput("NEW NAME", 49);
                                                String pName = input.nextLine();
                                                
                                                try {
                                                    prodEdit.setName(pName);
                                                } catch (Exception e) {
                                                    System.out.println("SOMETHING HAPPENED! PLEASE TRY AGAIN!");
                                                    Interface.wait(2);
                                                    break;
                                                };
                                                System.out.println("NAME CHANGED SUCCESSFULLY!");
                                                Interface.wait(2);
                                                break;
                                            }

                                            case 2: {
                                                Interface.drawFormInput("NEW PRICE", 49);
                                                try {
                                                    float pPrice = input.nextFloat();
                                                    input.nextLine();

                                                    prodEdit.setPrice(pPrice);
                                                } catch (Exception e) {
                                                    System.out.println("SOMETHING HAPPENED! PLEASE TRY USING A COMMA (,) !");
                                                    Interface.wait(2);
                                                    break;
                                                }
                                                ;
                                                System.out.println("PRICE CHANGED SUCCESSFULLY!");
                                                Interface.wait(2);
                                                break;
                                            }

                                            case 3: {
                                                Interface.drawFormInput("NEW STOCK", 49);
                                                int stock = Interface.readInt();

                                                try {
                                                    prodEdit.setCurrentStock(stock);
                                                } catch (Exception e) {
                                                    System.out.println("SOMETHING HAPPENED! PLEASE TRY AGAIN!");
                                                    Interface.wait(2);
                                                    break;
                                                };
                                                Interface.wait(2);
                                                break;
                                            }

                                            case 4: {
                                                Interface.drawFormInput("NEEDS PRESCRIPTION? Y/N", 49);
                                                Interface.drawButtonList("def", "No [0]", "Yes [1]");
                                                int prescBool = Interface.readInt();
                                                boolean needsP = false;

                                                if (prescBool == 1) {
                                                    needsP = true;
                                                }
                                                
                                                try {
                                                    prodEdit.setNeedPrescription(needsP);
                                                } catch (Exception e) {
                                                    System.out.println("SOMETHING HAPPENED! PLEASE TRY AGAIN!");
                                                    Interface.wait(2);
                                                    break;
                                                };
                                                System.out.println("NAME CHANGED SUCCESSFULLY!");
                                                Interface.wait(2);
                                                break;
                                            }

                                            case 0: {
                                                break;
                                            }

                                            default: {
                                                System.out.println(
                                                        "Please use the numbers displayed on the buttons [cooldown of 1s]");
                                                Interface.wait(2);
                                                break;
                                            }
                                        }

                                    break;
                                }
                                default: {
                                    System.out.println(
                                            "Please use the numbers displayed on the buttons [cooldown of 1s]");
                                    Interface.wait(2);
                                    break;
                                }
                            }

                            break;
                        }

                        case 2: {
                            Interface.newWindow();
                            try {
                                System.out.println(Order.printAllOrders());
                            } catch (Exception e) {
                                System.out.println("NO ORDERS FOUND.");
                            }
                            Interface.drawButtonList(" ", "BACK [0]", "CREATE [1]", "REMOVE [2]", "EDIT [3]",
                                    "CHECKOUT [4]");
                            int ordChoice = Interface.drawInput(0);
                            int oIndex = -1;
                            int oIndex2 = -1;
                            switch (ordChoice) {
                                case 0:{
                                    ordChoice = -999;
                                    break;
                                }
                                case 1:{
                                    Interface.newWindow();
                                    Interface.drawTitle("CREATE ORDER", 0);

                                    // day//month//year
                                    LocalDate currentDate = LocalDate.now();
                                    int[] creationDate = { // CREATION DATE
                                            currentDate.getDayOfMonth(),
                                            currentDate.getMonthValue(),
                                            currentDate.getYear()
                                    };

                                    LocalDate cooldownDate = currentDate.plusDays(30); // AVAILABLE DATE (+30 days)
                                    int[] availableDate = { // CREATION DATE
                                            cooldownDate.getDayOfMonth(),
                                            cooldownDate.getMonthValue(),
                                            cooldownDate.getYear()
                                    };

                                    Order.createOrder(creationDate, availableDate);
                                    System.out.println("ORDER CREATED! [Cooldown 2s]");
                                    Interface.wait(2);
                                    ordChoice = -999;
                                    break;
                                    }
                                case 2:{
                                    
                                    try {
                                        Interface.newWindow();
                                        Interface.drawTitle("REMOVE ORDER", 0);
                                        try {
                                            System.out.println(Order.printAllOrders());
                                        } catch (Exception e) {
                                            System.out.println("No orders found.");
                                        }
                                        System.out.println(
                                                "INSERT THE ORDER NUMBER [this action will cancel the order]: ");
                                        oIndex = Interface.drawInput(0);

                                        try {
                                            Order.rmvOrderByIndex(oIndex);
                                        } catch (Exception e) {
                                            System.out.println("ORDER NOT FOUND! [cooldown 2s]");
                                            Interface.wait(2);
                                        }

                                        Interface.wait(2);
                                    } catch (Exception e) {
                                        Interface.newWindow();
                                        System.out.println(
                                                "this option is not avaible, seems like you have no orders.");
                                        Interface.wait(2);
                                        Interface.newWindow();
                                    }
                                    ordChoice = -999;
                                    break;
                                    }
                                case 3: {
                                        Interface.newWindow();
                                        Interface.drawTitle("EDIT ORDER", 0);
                                        try {
                                            System.out.println(Order.printAllOrders());
                                        } catch (Exception e) {
                                            System.out.println("No orders linked to your user.");
                                        }
                                        System.out.println(
                                                "INSERT THE ORDER NUMBER [this action will allow you to edit the order]: ");
                                        oIndex = Interface.drawInput(0);
                                        Interface.newWindow();
                                        do {
                                            try {
                                                Order.getOrderByIndex(oIndex);
                                                Order.ListOrderByIndex(oIndex);
                                                exists = true;
                                            } catch (Exception e) {
                                                System.out.println("ORDER NOT FOUND [cooldown 2s]");
                                                Interface.wait(2);
                                                exists = false;
                                            }

                                            if (exists) {
                                                Order currOrd = Order.getOrderByIndex(oIndex);
                                                boolean productExists = false;
                                                Interface.drawButtonList(" ", "BACK [0]", "ADD PRODUCT [1]",
                                                        "RMV PRODUCT [2]");
                                                oIndex2 = Interface.drawInput(0);
                                                switch (oIndex2) {
                                                    case 0: {
                                                        break;
                                                    }
                                                    case 1: {
                                                        System.out.println(
                                                                "INSERT THE ID OF THE PRODUCT TO ADD IT IN THIS ORDER");
                                                        int opAdd = Interface.drawInput(0);
                                                        System.out
                                                                .println("INSERT THE QUANTITY OF THE PRODUCT YOU WISH");
                                                        int qt = Interface.drawInput(0);

                                                        Product currProd = null;

                                                        try {
                                                            productExists = Product.isaProduct(opAdd);
                                                        } catch (Exception e) {
                                                            System.out.println("PRODUCT ID NOT FOUND! [Cooldown 2s]");
                                                            Interface.wait(2);
                                                        }

                                                        if (productExists) {
                                                            currOrd.addProducts(currProd, qt);
                                                            System.out.println(
                                                                    qt + " PRODUCT ID " + opAdd
                                                                            + " ADDED! [Cooldown 2s]");
                                                            Interface.wait(2);
                                                            break;
                                                        } else {
                                                            System.out.println("PRODUCT ID NOT FOUND! [Cooldown 2s]");
                                                            Interface.wait(2);
                                                            break;
                                                        }
                                                    }
                                                    case 2: {
                                                        System.out.println(
                                                                "INSERT THE ID OF THE PRODUCT TO REMOVE IT FROM THE ORDER");
                                                        int opRmv = Interface.drawInput(0);
                                                        Product currProd = null;
                                                        try {
                                                            currOrd.getProductsList().indexOf(opRmv);
                                                            // Product.getProductsById(opRmv);
                                                        } catch (Exception e) {
                                                            productExists = false;
                                                        }

                                                        if (productExists) {
                                                            currProd = currOrd.getProductsByIndex(opRmv);
                                                            currOrd.rmvProducts(currProd);
                                                            System.out.println(
                                                                    "1 PRODUCT ID " + opRmv
                                                                            + " Removed! [Cooldown 2s]");
                                                            Interface.wait(2);
                                                            break;
                                                        } else {
                                                            System.out.println("PRODUCT ID NOT FOUND! [Cooldown 2s]");
                                                            Interface.wait(2);
                                                            break;
                                                        }
                                                    }
                                                    default: {
                                                        System.out.println("INSERT A VALID OPTION");
                                                        Interface.wait(2);
                                                        break;
                                                    }
                                                }
                                            } else {
                                                System.out.println(Order.printAllOrders());
                                                oIndex = Interface.drawInput(0);
                                            }
                                        } while (oIndex2 != 0);

                                        Interface.newWindow();
                                        System.out.println(
                                                "this option is not avaible, seems like you have no orders linked to your account.");
                                        Interface.wait(2);
                                        Interface.newWindow();
                                    userChoice = -999;
                                    break;
                                }

                                case 4: {
                                        Interface.newWindow();
                                        Interface.drawTitle("ORDER CHECKOUT", 0);
                                        int opCheckout = -999;
                                        try {
                                            System.out.println(Order.printAllOrders());
                                        } catch (Exception e) {
                                            System.out.println("No orders linked to your user.");
                                        }
                                        System.out.println(
                                                "INSERT THE ORDER NUMBER [This action will perform the checkout]: ");
                                        opCheckout = Interface.drawInput(0);
                                        Interface.newWindow();
                                        do {
                                            try {
                                                Order.getOrderByIndex(opCheckout);
                                                Order.ListOrderByIndex(opCheckout);
                                                exists = true;
                                            } catch (Exception e) {
                                                System.out.println("ORDER NOT FOUND [cooldown 2s]");
                                                Interface.wait(2);
                                                exists = false;
                                            }
                                            if (exists) {
                                                Interface.drawButtonList(" ", "BACK [0]", "CONFIRM CHECKOUT [1]");
                                                oIndex2 = Interface.drawInput(0);
                                                switch (oIndex2) {
                                                    case 0: {
                                                        break;
                                                    }
                                                    case 1: {
                                                        Order.getOrderByIndex(opCheckout)
                                                                .orderCheckOut(opCheckout);
                                                        break;
                                                    }
                                                    default: {
                                                        System.out.println("INSERT A VALID OPTION");
                                                        Interface.wait(2);
                                                        break;
                                                    }
                                                }
                                            } else {
                                                System.out.println(Order.printAllOrders());
                                                option = Interface.drawInput(0);
                                            }
                                            break;
                                        } while (option2 != 0);
                                    userChoice = -999;
                                }
                                default: {
                                    if (userChoice != -999) {
                                        System.out.println(
                                                "PLEASE USE THE NUMBERS DISPLAYED ON THE BUTTONS [cooldown of 2s]");
                                        Interface.wait(2);
                                    }
                                    break;
                                }
                            }
                        }

                        case 3: {
                            int prod = 0;
                            Interface.newWindow();
                            Interface.drawTitle("USER MANAGEMENT", 0);
                            User.listAllUsers();
                            Interface.drawButtonList("def", "LEAVE [0]", "ADD USER [1]", "REMOVE USER [2]",
                                    "EDIT USER [3]");
                            adminOp = Interface.drawInput(116);

                            switch (adminOp) {
                                case 0: {
                                    System.out.println("THANK YOU FOR USING PHARMAHOLD! [Cooldown 2s]");
                                    Interface.wait(2);
                                    break;
                                }
                                case 1: {
                                    Interface.newWindow();
                                    Interface.drawTitle("ADD USER", 0);
                                    String uName = "";
                                    do {
                                        Interface.drawFormInput("Name", 49);
                                        uName = input.nextLine();
                                        if (User.userExists(uName)) {
                                            System.out.println("Username already exists, try again.");
                                        }
                                    } while (User.userExists(uName) && uName != "");
                                    Interface.drawFormInput("Password", 49);
                                    float uPass = input.nextFloat();
                                    Interface.drawFormInput("Contact", 49);
                                    int uContact = Interface.readInt();

                                    try {
                                        new User(name, uName, uContact);
                                    } catch (Exception e) {
                                        System.out.println("USER FAILED TO BE CREATED!");
                                        break;
                                    }
                                    System.out.println("USER WAS CREATED! [Cooldown 2s]");
                                    Interface.wait(2);
                                    break;
                                }
                                case 2: {
                                    Interface.newWindow();
                                    Interface.drawTitle("REMOVE USER", 0);
                                    User.listAllUsers();
                                    System.out.println("INSERT THE USER REFERENCE TO REMOVE IT: ");
                                    int u = Interface.readInt();

                                    try {
                                        User.removeUserByIndex(u);
                                        System.out.println("USER REMOVED SUCESSFULLY! [Cooldown 2s]");
                                        Interface.wait(2);
                                    } catch (Exception e) {
                                        System.out.println("USER ID NOT FOUND! [Cooldown 2s]");
                                        Interface.wait(2);
                                    }
                                    break;
                                }
                                case 3: {
                                    Interface.newWindow();
                                    Interface.drawTitle("EDIT USER", 0);
                                    System.out.println(Product.listAllProducts());
                                    System.out.println("INSERT THE USER REFERENCE TO EDIT: ");
                                    int userIndex = Interface.readInt();
                                    User userEdit = User.getUserByIndex(userIndex);

                                    if (userEdit instanceof Client) {
                                        userEdit = (Client) userEdit;
                                    } else {
                                        userEdit = (Admin) userEdit;
                                    }

                                    if (userEdit != null) {
                                        userEdit.displaySelf();
                                    }
                                        Interface.drawButtonList("def", "LEAVE [0]", "NAME [1]",
                                            "PASSWORD [2]", "CONTACT [3]", "ADDRESS [4]", "POINTS [5]");
                                    

                                        int opEdit = Interface.readInt();
                                        switch (opEdit) {

                                            case 1: {
                                                Interface.drawFormInput("NEW NAME", 49);
                                                String pName = input.nextLine();
                                                
                                                try {
                                                    userEdit.setName(pName);
                                                } catch (Exception e) {
                                                    System.out.println("SOMETHING HAPPENED! PLEASE TRY AGAIN!");
                                                    Interface.wait(2);
                                                    break;
                                                };
                                                System.out.println("USERNAME CHANGED SUCCESSFULLY!");
                                                Interface.wait(2);
                                                break;
                                            }

                                            case 2: {
                                                Interface.drawFormInput("NEW PASSWORD", 49);
                                                try {
                                                    String uPass = input.nextLine();
                                                    input.nextLine();

                                                    userEdit.setPassword(uPass);
                                                } catch (Exception e) {
                                                    System.out.println("SOMETHING HAPPENED! PLEASE TRY USING A COMMA (,) !");
                                                    Interface.wait(2);
                                                    break;
                                                }
                                                ;
                                                System.out.println("PASSWORD CHANGED SUCCESSFULLY!");
                                                Interface.wait(2);
                                                break;
                                            }

                                            case 3: {
                                                Interface.drawFormInput("NEW CONTACT", 49);
                                                int uContact = Interface.readInt();

                                                try {
                                                    userEdit.setContact(uContact);
                                                } catch (Exception e) {
                                                    System.out.println("SOMETHING HAPPENED! PLEASE TRY AGAIN!");
                                                    Interface.wait(2);
                                                    break;
                                                };
                                                System.out.println("CONTACT CHANGED SUCCESSFULLY!");
                                                Interface.wait(2);
                                                break;
                                            }

                                            case 4: {
                                                Interface.drawFormInput("NEW ADDRESS", 49);
                                                Interface.drawInput(49);
                                                String uAddress = input.nextLine();

                                                try {
                                                    userEdit.setAddress(uAddress);
                                                } catch (Exception e) {
                                                    System.out.println("SOMETHING HAPPENED! PLEASE TRY AGAIN!");
                                                    Interface.wait(2);
                                                    break;
                                                };
                                                System.out.println("ADDRESS CHANGED SUCCESSFULLY!");
                                                Interface.wait(2);
                                                break;
                                            }
                                            case 5: {
                                                Interface.drawFormInput("NEW POINTS", 49);
                                                Interface.drawInput(49);
                                                int uPoints = input.nextInt();

                                                try {
                                                    userEdit.setTotalPoints(uPoints);
                                                } catch (Exception e) {
                                                    System.out.println("SOMETHING HAPPENED! PLEASE TRY AGAIN!");
                                                    Interface.wait(2);
                                                    break;
                                                };
                                                System.out.println("POINTS CHANGED SUCCESSFULLY!");
                                                Interface.wait(2);
                                                break;
                                            }
                                            case 0: {
                                                break;
                                            }

                                            default: {
                                                System.out.println(
                                                        "Please use the numbers displayed on the buttons [cooldown of 1s]");
                                                Interface.wait(2);
                                                break;
                                            }
                                        }

                                    break;
                                }
                                default: {
                                    System.out.println(
                                            "Please use the numbers displayed on the buttons [cooldown of 1s]");
                                    Interface.wait(2);
                                    break;
                                }
                            }

                            break;
                        }

                        default: {
                            System.out.println("Please use the numbers displayed on the buttons [cooldown of 1s]");
                            Interface.wait(2);
                            break;
                        }
                
                    }
                }
            }
        }
    }
}

