// MAIN FILE || PHARMAHOLD ||
package pt.ipp.estg.pp.pharmahold;

import java.util.InputMismatchException;
import java.util.Scanner;

import pt.ipp.estg.pp.pharmahold.ENUMS.PrescriptionType;

public class PharmaHold {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        // USERS
        new Client("martini", "pass1", 919999999, "rua 1 numero 2");
        new Client("pass", "pass", 919999999, "rua 1 numero 2");
        new Client("andre", "pass2", 919222222, "rua 2 numero 3");

        // ADMIN
        Admin adm1 = new Admin("root", "root", 911111111);

        // PRESCRIPTIONS
        Prescription.addPrescription(new int[] { 1, 1, 2026 }, new int[] { 2, 5, 2026 }, PrescriptionType.COMMON,
                "doctor1");
        Prescription.addPrescription(new int[] { 4, 2, 2026 }, new int[] { 12, 5, 2026 }, PrescriptionType.COMMON,
                "doctor2");
        Prescription.addPrescription(new int[] { 7, 2, 2026 }, new int[] { 22, 6, 2026 }, PrescriptionType.COMMON,
                "doctor3");

        // PRODUCTS
        Product prod1 = new Product("brufen", 14.44f, 15, false);
        Product prod2 = new Product("benuron", 14.44f, 14, true);
        Product prod3 = new Product("ritalina", 14.44f, 12, false);
        Product prod4 = new Product("griponal", 14.44f, 10, true);

        // ORDERS
        Order order1 = new Order(new int[] { 1, 3, 2026 }, new int[] { 1, 4, 2026 });
        order1.addProducts(prod1);
        order1.addProducts(prod2);

        Order order2 = new Order(new int[] { 5, 3, 2026 }, new int[] { 5, 4, 2026 });
        order2.addProducts(prod2);

        Order order3 = new Order(new int[] { 5, 3, 2026 }, new int[] { 5, 4, 2026 });
        order3.addProducts(prod3);
        order3.addProducts(prod2);

        Order order4 = new Order(new int[] { 5, 3, 2026 }, new int[] { 5, 4, 2026 });
        order4.addProducts(prod4);
        order4.addProducts(prod3);

        Client.getUserById(1).addOrders(order1);
        Client.getUserById(1).addOrders(order2);
        Client.getUserById(2).addOrders(order3);
        Client.getUserById(3).addOrders(order4);

        // LOCAL VARIABLES
        int userChoice = -99;
        int userType = 0;
        String name = null;
        boolean valid;

        boolean isLoggedIn = false;
        Client loggedClient = null;
        Admin loggedAdmin = null;
        User loggedUser = null;
        // begin interface, nothing prints until here AFTER " WHILEISLOGGEDIN" BEWARE

        while (userChoice != 0) {
<<<<<<< HEAD
            if (!isLoggedIn) { // used to be while, in case of error
                //switch (userType) {
                    //case 0: {
                        name = "WELCOME TO PHARMAHOLD";
                        Interface.drawTitle("WELCOME TO PHARMAHOLD", 0);
                        Interface.drawButtonList("def", "LEAVE [0]", "LOGIN [1]", "SIGNUP [2]");
                        userChoice = Interface.drawInput(46);
                        //break;
                    /* }
                    case 1: {
                        name = loggedClient.getName();
                        Interface.drawTitle("WELCOME TO PHARMAHOLD" + name, 0);
                        Interface.drawButtonList("def", "LEAVE [0]", "LOGIN [1]", "SIGNUP [2]");
                        userChoice = Interface.drawInput(46);
                        break;
                    }
                    case 2: {
                        name = loggedAdmin.getName();
                        Interface.drawTitle("WELCOME TO PHARMAHOLD" + name, 0);
                        Interface.drawButtonList("def", "LEAVE [0]", "LOGIN [1]", "SIGNUP [2]");
                        userChoice = Interface.drawInput(46);
                        break;
                    }
                }*/
=======
            if (!isLoggedIn) {
                Interface.newWindow();
                name = "WELCOME TO PHARMAHOLD";
                Interface.drawTitle("WELCOME TO PHARMAHOLD", 0);
                Interface.drawButtonList("def", "LEAVE [0]", "LOGIN [1]", "SIGNUP [2]");
                userChoice = Interface.drawInput(46);
>>>>>>> 4df13e8 (GIMER)

                switch (userChoice) {
                    case 0: {
                        break;
                    }
                    case 1: {
                        while (!isLoggedIn) {
<<<<<<< HEAD
                            
=======
                            Interface.newWindow();
                            System.out.println("THIS IS THE LOG-IN FORM");
>>>>>>> 4df13e8 (GIMER)
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
<<<<<<< HEAD
                                    System.out.println("Your username or password are incorrect, in case you don't have an account you should SIGN UP.");
                                    /*  Interface.drawButtonList("def", "LEAVE[0]", "LOG IN[1]", "SIGN UP[2]");
                                    userChoice = input.nextInt();
                                    input.nextLine(); // CLEANING BUFFER
                                    switch (userChoice) {
                                        case 0:
                                            return;
                                        case 1:
                                            break;
                                        case 2:
                                            break;
                                        default:
                                            System.out.print("Please select a valid option.");
                                        
                                    } */
=======
                                    System.out.println(
                                            "Your username or password are incorrect, in case you don't have an account you should SIGN UP.\nCooldown of 2s per try");
                                    try {
                                        Thread.sleep(2000);
                                    } catch (InterruptedException e) {
                                        Thread.currentThread().interrupt();
                                    }
>>>>>>> 4df13e8 (GIMER)
                                    break;
                                }
                            }
                        }
                        break;
                    }

                    case 2: {
<<<<<<< HEAD
                        // sign up
                        Interface.newWindow();
                        Interface.drawFormInput("Username", 49);
                        String userName = input.nextLine();
                        Interface.drawFormInput("Password", 49);
                        String pass = input.nextLine();
                        Interface.drawFormInput("Contact", 49);
                        int contact = Interface.readInt();
                        Interface.drawFormInput("Address", 49);
                        String address = input.nextLine();

                        if (Client.userExists(userName)){
                            System.out.println("Registration failed.");
                            break;
                        }else{

                            Client.register(userName, pass, contact, address);
                            System.out.println(userName);

                            System.out.println("Successfully registered!");

                            System.out.println("---------------------------------");
                            System.out.println("Loading...");
                            System.out.println("---------------------------------");
                            break;
=======
                        while (!isLoggedIn) {
                            // sign up
                            Interface.newWindow();
                            System.out.println("THIS IS THE SIGN-IN FORM");
                            Interface.drawFormInput("Username", 49);
                            String userName = input.nextLine();
                            Interface.drawFormInput("Password", 49);
                            String pass = input.nextLine();
                            int contact = 0;
                            do {
                                try {
                                    Interface.drawFormInput("Contact", 49);
                                    contact = input.nextInt();
                                    valid = true;
                                } catch (InputMismatchException e) {
                                    System.out.println("REALLY?? A PHONE NUMBER WITH TEXT??? CONGRATS DUMB ASS!"); // JOKES
                                                                                                                   // HAHA
                                    input.nextLine();
                                    valid = false;
                                }
                            } while (!valid);

                            input.nextLine();
                            Interface.drawFormInput("Address", 49);
                            String address = input.nextLine();
                            loggedUser = Client.register(userName, pass, contact, address);

                            // loggedUser = Client.login(userName, pass);
                            // loggedClient = (Client) loggedUser;
                            isLoggedIn = true;
>>>>>>> 4df13e8 (GIMER)
                        }
                    }

                    default: {
                        try {
                            System.out.println("Please use the numbers displayed on the buttons [cooldown of 1s]");
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            System.out.println(e);
                            Thread.currentThread().interrupt();
                        }
                        break;
                    }
                }
            }
                // logged menu
                //Interface.newWindow();
                System.out.println(loggedUser);
                System.out.println(loggedClient);
                System.out.println(loggedAdmin);

                while (isLoggedIn && userChoice != 0 && loggedUser != null) {

                    Interface.drawTitle("WELCOME TO PHARMAHOLD", 53);
                    Interface.drawButtonList("def", "LOGOUT [0]", "PRODUCTS [1]", "ORDERS [2]", "ORDER HISTORY [3]", "PRESCRIPTIONS [4]");
<<<<<<< HEAD
                    userChoice = Interface.drawInput(75);

                    switch (userChoice) {
                         case 0: {
                        //     name = "WELCOME TO PHARMAHOLD";
                        //     Interface.drawTitle("WELCOME TO PHARMAHOLD", 0);
                        //     Interface.drawButtonList("def", "LEAVE [0]", "LOGIN [1]", "SIGNUP [2]");
                        //     userChoice = Interface.drawInput(46);
                             userType = -999;
                             isLoggedIn = false;
                             loggedAdmin = null;
                             loggedClient = null;
                             break;
                         }
=======
                    do {
                        userChoice = Interface.drawInput(75);
                    } while (userChoice < 0 && userChoice < 4);

                    switch (userChoice) {
                        case 0: {
                            name = "WELCOME TO PHARMAHOLD";
                            Interface.drawTitle("WELCOME TO PHARMAHOLD", 0);
                            Interface.drawButtonList("def", "LEAVE [0]", "LOGIN [1]", "SIGNUP [2]");
                            userChoice = Interface.drawInput(46);
                            userType = -999;
                            isLoggedIn = false;
                            loggedAdmin = null;
                            loggedClient = null;
                            break;
                        }
>>>>>>> 4df13e8 (GIMER)
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
<<<<<<< HEAD
                            Interface.drawTitle("YOUR ORDERS", 0);
                            if (loggedClient != null || loggedUser instanceof Admin) {
                                if (loggedClient.getOrders().isEmpty()) {
                                    System.out.println("No orders linked to your user.");
                                } else {
                                    System.out.println(loggedClient.printOrders());
                                }
=======
                            if (loggedUser instanceof Client) {
                                loggedClient = (Client) loggedUser; // problem solved, loggedClient was null... so i defined it
                                System.out.println(loggedClient.printOrders());
>>>>>>> 4df13e8 (GIMER)
                            }
                            // new options
                            Interface.drawButtonList(" ", "BACK [0]", "CREATE [1]", "REMOVE [2]", "EDIT [3]");
                            userChoice = Interface.drawInput(0);
                            switch (userChoice) {
                                case 0:
                                    break;
                                case 1:
                                    System.out.println("CREATE ORDER");
                                case 2:

                                default:
                                    break;
                            }
                            break;
                        }
                        case 3: {
                            Interface.newWindow();
                            Interface.drawTitle("YOUR ORDER HISTORY", 0);
                            System.out.println(loggedClient.getOrderHistory());
                            System.out.println("Press ENTER to go back.");
                            input.nextLine();
                            break;
                        }
                        case 4: {
                            Interface.newWindow();
                            Interface.drawTitle("YOUR PRESCRIPTIONS", 0);
                            // System.out.println(loggedClient.getAllProducts());
                            System.out.println("Press ENTER to go back.");
                            input.nextLine();
                            break;
                        }
                        default: {
                            System.exit(0);
                            break;
                        }
                    }
                }
            
        }
    }
}
