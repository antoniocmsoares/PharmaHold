// MAIN FILE || PHARMAHOLD ||

package pt.ipp.estg.pp.pharmahold;

import java.util.Scanner;

import pt.ipp.estg.pp.pharmahold.ENUMS.PrescriptionType;

public class PharmaHold {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        // USERS
        Client client1 = new Client("martini", "pass1", 919999999, "rua 1 numero 2");
        Client client2 = new Client("andre", "pass2", 919222222, "rua 2 numero 3");
        Client client3 = new Client("marco", "pass3", 919999999, "rua 3 numero 4");

        // ADMIN
        Admin adm1 = new Admin("root", "root", 91111111);

        // PRESCRIPTIONS
        Prescription pres1 = new Prescription(new int[] { 1, 1, 2026 }, new int[] { 2, 5, 2026 },
                PrescriptionType.COMMON, "doctor1");
        Prescription pres2 = new Prescription(new int[] { 4, 2, 2026 }, new int[] { 12, 5, 2026 },
                PrescriptionType.COMMON, "doctor1");
        Prescription pres3 = new Prescription(new int[] { 7, 2, 2026 }, new int[] { 22, 6, 2026 },
                PrescriptionType.COMMON, "doctor1");

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

        client1.addOrders(order1);
        client2.addOrders(order2);
        client3.addOrders(order3);
        client3.addOrders(order4);

        // LOCAL VARIABLES
        int userChoice = -99;
        int userType = 0;
        String name = "";

        boolean isLoggedIn = false;
        Client loggedClient = null;
        Admin loggedAdmin = null;
<<<<<<< HEAD
        User loggedUser = null;
        //begin interface, nothing prints until here AFTER " WHILEISLOGGEDIN" BEWARE
=======
        // begin interface, nothing prints until here AFTER " WHILEISLOGGEDIN" BEWARE
>>>>>>> main

        while (userChoice != 0) {
            if (!isLoggedIn) { // used to be while, in case of error
                Interface.newWindow();// NEW WINDOW

                switch (userType) {
                    case 0: {
                        name = "WELCOME TO PHARMAHOLD";
                        Interface.drawTitle("WELCOME TO PHARMAHOLD", 0);
                        Interface.drawButtonList("def", "LEAVE [0]", "LOGIN [1]", "SIGNUP [2]");
                        userChoice = Interface.drawInput(46);
                        break;
                    }
                    case 1: {
                        name = "WELCOME TO PHARMAHOLD" + loggedClient.getName();
                        Interface.drawTitle("WELCOME TO PHARMAHOLD" + name, 0);
                        Interface.drawButtonList("def", "LEAVE [0]", "LOGIN [1]", "SIGNUP [2]");
                        userChoice = Interface.drawInput(46);
                        break;
                    }
                    case 2: {
                        name = "WELCOME TO PHARMAHOLD" + loggedAdmin.getName();
                        Interface.drawTitle("WELCOME TO PHARMAHOLD" + name, 0);
                        Interface.drawButtonList("def", "LEAVE [0]", "LOGIN [1]", "SIGNUP [2]");
                        userChoice = Interface.drawInput(46);
                        break;
                    }
                }

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

<<<<<<< HEAD
                            loggedUser = Client.login(userName, pass);
                            
                            //checks if user is Client or Admin
                            if (loggedUser instanceof Client) {
                                System.out.println("Bem vindo " + loggedUser.getName() + " !");
=======
                            loggedClient = Client.login(userName, pass);
                            if (loggedClient != null) {
                                userName = loggedClient.getName();
                                System.out.println("Welcome " + userName + " !");
>>>>>>> main
                                isLoggedIn = true;
                                userType = 1;
                                break;
                            } else {
<<<<<<< HEAD
                                loggedUser = Admin.login(userName, pass);
                                if (loggedUser instanceof Client){
                                    System.out.println("Bem vindo " + loggedUser.getName() + " !");
                                    isLoggedIn = true;
                                    break;
                                }else{
                                    System.out.println(
                                            "User não encontrado, voltar ao menu ou sair do programa? Voltar [1] | Sair [2]");
                                    int goBack = Interface.drawInput(49);
                                    if (goBack == 2) {
                                        System.exit(0);
=======
                                loggedAdmin = Admin.login(userName, pass);
                                if (loggedAdmin != null) {
                                    userName = loggedClient.getName();
                                    System.out.println("Welcome " + userName + " !");
                                    isLoggedIn = true;
                                    userType = 255;
                                    break;
                                } else {
                                    Interface.newWindow();
                                    System.out.println(
                                            "Your account or password are incorret, in case you dont have an account you should do SIGN UP.");
                                    Interface.drawButtonList("def", "LEAVE[0]", "LOG IN[1]", "SIGN UP[2]");
                                    userChoice = input.nextInt();
                                    input.nextLine(); // CLEANING BUFFER SINCE SCANNER IS A S**T
                                    switch (userChoice) {
                                        case 0:
                                            return;
                                        case 1:
                                            break;
                                        case 2:
                                            break;
                                        default:
                                            System.out.print("YOU NEED TO RESPOND WITH THE GIVEN OPTIONS");
                                            break;
>>>>>>> main
                                    }
                                }
                            }

                        }
                        break;
                    }
                    case 2: {
                        // sign up
                        Interface.newWindow();
                        Interface.drawFormInput("Username", 49);
                        String userName = Interface.readString();
                        Interface.drawFormInput("Password", 49);
                        String pass = Interface.readString();
                        Interface.drawFormInput("Contact", 49);
                        int contact = Interface.readInt();
                        Interface.drawFormInput("Address", 49);
                        input.nextLine();
                        String address = Interface.readString();

                        System.out.println("---------------------------------");
                        System.out.println("Loading...");
                        System.out.println("---------------------------------");

                        loggedClient = Client.login(userName, pass);
                        isLoggedIn = true;
                        
                        break;
                    }
                    default: {
                        System.out.println("Por favor inserir um dos numeros indicados acima!");
                        break;
                    }
                }
                // loggedmenu
                Interface.newWindow();
                while (isLoggedIn && userChoice != 0) {

                    Interface.drawTitle("WELCOME TO PHARMAHOLD", 53);
                    Interface.drawButtonList("def", "LOGOUT [0]", "PRODUCTS [1]", "ORDERS [2]", "ORDER HISTORY [3]",
                            "PRESCRIPTIONS [4]");
                    userChoice = Interface.drawInput(75);

                    switch (userChoice) {
                        case 0:
                            name = "WELCOME TO PHARMAHOLD";
                            Interface.drawTitle("WELCOME TO PHARMAHOLD", 0);
                            Interface.drawButtonList("def", "LEAVE [0]", "LOGIN [1]", "SIGNUP [2]");
                            userChoice = Interface.drawInput(46);
                            userType = -999;
                            isLoggedIn = false;
                            loggedAdmin = null;
                            loggedClient = null;
                            break;
                        case 1:
                            Interface.newWindow();
                            Interface.drawTitle("PRODUCTS LIST", 0);
                            System.out.println(Product.listAllProducts());
                            System.out.println("Press ENTER to go back.");
                            input.nextLine();
                            break;
                        case 2:
                            Interface.newWindow();
                            Interface.drawTitle("YOUR ORDERS", 0);
                            System.out.println(loggedClient.printOrders());
                            System.out.println("Press ENTER to go back.");
                            input.nextLine();
                            break;
                        case 3:
                            Interface.newWindow();
                            Interface.drawTitle("YOUR ORDER HISTORY", 0);
                            System.out.println(loggedClient.getOrderHistory());
                            System.out.println("Press ENTER to go back.");
                            input.nextLine();
                            break;
                        case 4:
                            Interface.newWindow();
                            Interface.drawTitle("YOUR PRESCRIPTIONS", 0);
                            // System.out.println(loggedClient.getAllProducts());
                            System.out.println("Press ENTER to go back.");
                            input.nextLine();
                            break;
                        default:
                            System.exit(0);
                            break;
                    }
                }
            }
        }
    }
}
