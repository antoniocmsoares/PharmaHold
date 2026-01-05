// MAIN FILE || PHARMAHOLD ||
package pt.ipp.estg.pp.pharmahold;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import pt.ipp.estg.pp.pharmahold.ENUMS.OrderState;
import pt.ipp.estg.pp.pharmahold.ENUMS.PrescriptionType;

public class PharmaHold {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        // USERS
        new Client("martini", "pass1", 919999999, "rua 1 numero 2");
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
        new Product("brufen", 14.44f, 15, false);
        new Product("benuron", 14.44f, 14, true);
        new Product("ritalina", 14.44f, 12, false);
        new Product("griponal", 14.44f, 10, true);

        // ORDERS
        new Order(new int[] { 1, 3, 2026 }, new int[] { 1, 4, 2026 });
        Order.getOrderById(1).addProducts(Product.getProductsById(1), 1);
        Order.getOrderById(1).addProducts(Product.getProductsById(2), 2);

        new Order(new int[] { 5, 3, 2026 }, new int[] { 5, 4, 2026 });
        Order.getOrderById(2).addProducts(Product.getProductsById(2), 2);

        new Order(new int[] { 5, 3, 2026 }, new int[] { 5, 4, 2026 });
        Order.getOrderById(3).addProducts(Product.getProductsById(3), 3);
        Order.getOrderById(3).addProducts(Product.getProductsById(4), 2);

        new Order(new int[] { 5, 3, 2026 }, new int[] { 5, 4, 2026 });
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

        boolean isLoggedIn = false;
        Client loggedClient = null;
        Admin loggedAdmin = null;
        User loggedUser = null;
        // begin interface, nothing prints until here AFTER " WHILEISLOGGEDIN" BEWARE

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
                                System.out.println("Welcome " + userName + " !");
                                isLoggedIn = true;
                                break;
                            } else {
                                loggedUser = Admin.login(userName, pass);
                                if (loggedUser instanceof Admin) {
                                    loggedAdmin = (Admin) loggedUser;
                                    System.out.println("Welcome ADMIN " + loggedUser.getName() + " !");
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
            // logged menu
            Interface.newWindow();

            while (isLoggedIn && loggedUser != null) {
                Interface.drawTitle("WELCOME TO PHARMAHOLD", 53);
                Interface.drawButtonList("def", "LOGOUT [0]", "PRODUCTS [1]", "ORDERS [2]", "ORDER HISTORY [3]",
                        "PRESCRIPTIONS [4]");
                userChoice = Interface.drawInput(75);

                switch (userChoice) {
                    case 0: {
                        Interface.newWindow();
                        userChoice = -999;
                        userType = -999;
                        isLoggedIn = false;
                        loggedAdmin = null;
                        loggedClient = null;
                        break;
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
                        } catch (NullPointerException e) {
                            System.out.println("No orders linked to your user.");
                        }
                        // client order mgnt
                        Interface.drawButtonList(" ", "BACK [0]", "CREATE [1]", "REMOVE [2]", "EDIT [3]");
                        userChoice = Interface.drawInput(0);
                        switch (userChoice) {
                            case 0:

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

                                loggedClient.addOrders(new Order(creationDate, availableDate));
                                break;
                            case 2:
                                Interface.newWindow();
                                Interface.drawTitle("REMOVE ORDER", 0);
                                // if (loggedClient != null || loggedUser instanceof Admin) {
                                // if (loggedClient.getOrders().isEmpty()) {
                                // System.out.println("No orders linked to your user.");
                                // } else {
                                // System.out.println(loggedClient.printActiveOrders());
                                // }
                                // }
                                try {
                                    System.out.println(loggedClient.printActiveOrders());
                                } catch (NullPointerException e) {
                                    System.out.println("No orders linked to your user.");
                                }
                                System.out.println("INSERT THE ORDER NUMBER [this action will cancel the order]: ");
                                option = Interface.drawInput(0);

                                try {
                                    loggedClient.rmvOrderById(option);
                                } catch (NullPointerException e) {
                                    System.out.println("ORDER NOT FOUND! [cooldown 2s]");
                                }

                                Interface.wait(2);
                                break;
                            case 3: {
                                Interface.newWindow();
                                Interface.drawTitle("EDIT ORDER", 0);
                                try {
                                    System.out.println(loggedClient.printActiveOrders());
                                } catch (NullPointerException e) {
                                    System.out.println("No orders linked to your user.");
                                }
                                System.out.println(
                                        "INSERT THE ORDER NUMBER [this action will allow you to edit the order]: ");
                                option = Interface.drawInput(0);

                                Interface.newWindow();
                                do {
                                    try {
                                        loggedClient.getOrderById(option);
                                        loggedClient.displayOrder(option);
                                        exists = true;
                                    } catch (NullPointerException e) {
                                        System.out.println("ORDER NOT FOUND [cooldown 2s]");
                                        Interface.wait(2);
                                        System.out.println(loggedClient.printActiveOrders());
                                        exists = false;
                                    }
                                    
                                    
                                    if (exists) {
                                        Interface.drawButtonList(" ", "BACK [0]", "ADD PRODCUT [1]", "RMV PRODUCT [2]");
                                        option2 = Interface.drawInput(0);
                                        switch (option2) {
                                            case 0: {
                                                break;
                                            }
                                            case 1: {
                                                System.out.println("INSERT THE ID OF THE PRODUCT TO ADD IT IN THIS ORDER");
                                                int op = Interface.drawInput(0);
                                                System.out.println("INSERT THE QUANTITY OF THE PRODUCT YOU WISH");
                                                int qt = Interface.drawInput(0);
                                                try {
                                                    loggedClient.getOrderById(option).addProducts(Product.getProductsById(op), qt);
                                                } catch (NullPointerException e) {
                                                    System.out.println("NO SUTCH PRODUCT HAS THE ID «" + op + "»");
                                                }
                                                break;
                                            }
                                            case 2: {
                                                System.out.println(
                                                        "INSERT THE ID OF THE PRODUCT TO REMOVE IT FROM THE ORDER");
                                                break;
                                            }
                                            default: {
                                                System.out.println("INSERT A VALID OPTION");
                                                Interface.wait(2);
                                                break;
                                            }
                                        }
                                    } else {
                                        option = Interface.drawInput(0);
                                    }
                                } while (option2 != 0);

                                // try {
                                // loggedClient.rmvOrderById(option);
                                // } catch (NullPointerException e) {
                                // System.out.println("ORDER CANCELLED FAILED! [cooldown 2s]");
                                // }
                                // try {
                                // if (Order.getOrderById(option).getState() == OrderState.CANCELLED) {
                                // System.out.println("ORDER CANCELLED WITH SUCCESS! [cooldown 2s]");
                                // }
                                // } catch (NullPointerException e) {
                                // System.out.println("ORDER NOT FOUND! [cooldown 2s]");
                                // }

                                // try {
                                // Thread.sleep(2000);
                                // } catch (InterruptedException e) {
                                // System.out.println(e);
                                // Thread.currentThread().interrupt();
                                // }
                                break;
                            }
                            default:
                                break;
                        }
                        break;
                    }
                    case 3: {
                        Interface.newWindow();
                        Interface.drawTitle("YOUR ORDER HISTORY", 0);
                        try {
                            System.out.println(loggedClient.printAllOrders());
                        } catch (NullPointerException e) {
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
                        } catch (NullPointerException e) {
                            System.out.println("No prescriptions linked to your user.");
                        }
                        System.out.println("Press ENTER to go back.");
                        input.nextLine();
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
