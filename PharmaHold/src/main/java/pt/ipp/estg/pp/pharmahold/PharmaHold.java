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
        Prescription pres1 = new Prescription(new int[]{1, 1, 2026}, new int[]{2, 5, 2026},
                PrescriptionType.COMMON, "doctor1");
        Prescription pres2 = new Prescription(new int[]{4, 2, 2026}, new int[]{12, 5, 2026},
                PrescriptionType.COMMON, "doctor1");
        Prescription pres3 = new Prescription(new int[]{7, 2, 2026}, new int[]{22, 6, 2026},
                PrescriptionType.COMMON, "doctor1");

        // PRODUCTS
        Product prod1 = new Product("brufen", 14.44f, 15, false);
        Product prod2 = new Product("benuron", 14.44f, 14, true);
        Product prod3 = new Product("ritalina", 14.44f, 12, false);
        Product prod4 = new Product("griponal", 14.44f, 10, true);

        // ORDERS
        Order order1 = new Order(new int[]{1, 3, 2026}, new int[]{1, 4, 2026});
        order1.addProducts(prod1);
        order1.addProducts(prod2);

        Order order2 = new Order(new int[]{5, 3, 2026}, new int[]{5, 4, 2026});
        order2.addProducts(prod2);

        Order order3 = new Order(new int[]{5, 3, 2026}, new int[]{5, 4, 2026});
        order3.addProducts(prod3);
        order3.addProducts(prod2);

        Order order4 = new Order(new int[]{5, 3, 2026}, new int[]{5, 4, 2026});
        order4.addProducts(prod4);
        order4.addProducts(prod3);

        client1.addOrders(order1);
        client2.addOrders(order2);
        client3.addOrders(order3);
        client3.addOrders(order4);

        int userChoice = -99;

        boolean isLoggedIn = false;
        Client loggedClient = null;
        Admin loggedAdmin = null;
        Client loggedUser = null;
        //begin interface, nothing prints until here AFTER " WHILEISLOGGEDIN" BEWARE

        while (userChoice != 0) {
            if (!isLoggedIn) { // used to be while, in case of error
                Interface.newWindow();//NEW WINDOW

                //adm1.manageClient(2, 1);
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

                            loggedClient = Client.login(userName, pass);
                            if (loggedClient != null) {
                                System.out.println("Bem vindo " + loggedClient.getName() + " !");
                                isLoggedIn = true;
                                break;
                            } else {
                                loggedAdmin = Admin.login(userName, pass);
                                System.out.println("Bem vindo " + loggedAdmin.getName() + " !");
                                isLoggedIn = true;
                                break;
                            }

                            // Interface.drawTitle("CHOOSE THE USER TYPE", 4);
                            // Interface.drawButtonList(" ", "CLIENTE [1]", "COLABORADOR [2]", "ADMIN [3]");
                            // int userType = Interface.drawInput(49);
                            // if (userType == -99) {
                            //     userType = Interface.drawInput(49);
                            // }
                            // switch (userType) {
                            //     case 1: { // CLIENTE
                            //         Interface.newWindow();
                            //         Interface.drawFormInput("Username", 49);
                            //         String userName = input.nextLine();
                            //         Interface.drawFormInput("Password", 49);
                            //         String pass = input.nextLine();
                            //         System.out.println("---------------------------------");
                            //         System.out.println("Loading...");
                            //         System.out.println("---------------------------------");
                            //         loggedClient = Client.login(userName, pass);
                            //         if (loggedClient != null) {
                            //             System.out.println("Bem vindo " + loggedClient.getName() + " !");
                            //             isLoggedIn = true;
                            //             break;
                            //         } else {
                            //             System.out.println("User não encontrado, voltar ao menu ou sair do programa? Voltar [1] | Sair [2]");
                            //             int goBack = Interface.drawInput(49);
                            //             if (goBack == 2) {
                            //                 System.exit(0);
                            //             }
                            //         }
                            //     }
                            //     case 3: { // ADMIN
                            //         Interface.newWindow();
                            //         Interface.drawFormInput("Username", 49);
                            //         String userName = input.nextLine();
                            //         Interface.drawFormInput("Password", 49);
                            //         String pass = input.nextLine();
                            //         System.out.println("---------------------------------");
                            //         System.out.println("Loading...");
                            //         System.out.println("---------------------------------");
                            //         loggedAdmin = Admin.login(userName, pass, 'a');
                            //         System.out.println("Bem vindo " + loggedAdmin.getName() + " !");
                            //         isLoggedIn = true;
                            //         break;
                            //     }
                            //     default: {
                            //         System.out.println("Por favor inserir um dos numeros indicados acima!");
                            //         break;
                            //     }
                            // }
                        }
                        break;
                    }
                    case 2: {
                        //sign up
                        Interface.newWindow();
                        Interface.drawFormInput("Username", 49);
                        String userName = input.nextLine();
                        Interface.drawFormInput("Password", 49);
                        String pass = input.nextLine();
                        Interface.drawFormInput("Contact", 49);
                        int contact = input.nextInt();
                        Interface.drawFormInput("Address", 49);
                        input.nextLine();
                        String address = input.nextLine();

                        System.out.println("---------------------------------");
                        System.out.println("Loading...");
                        System.out.println("---------------------------------");

                        loggedClient = Client.login(userName, pass);

                        if (loggedClient != null) {
                            System.out.println("Bem vindo " + loggedClient.getName() + " !");
                            isLoggedIn = true;
                        } else {
                            System.out.println(
                                    "User não encontrado, voltar ao menu ou sair do programa? Voltar [1] | Sair [2]");
                            int goBack = Interface.drawInput(49);
                            if (goBack == 2) {
                                System.exit(0);
                            }
                        }
                        break;
                    }
                    default: {
                        System.out.println("Por favor inserir um dos numeros indicados acima!");
                        break;
                    }
                }
                //loggedmenu
                while (isLoggedIn && userChoice != 0) {

                    Interface.drawTitle("WELCOME TO PHARMAHOLD", 53);
                    Interface.drawButtonList("def", "LOGOUT [0]", "PRODUCTS [1]", "ORDERS [2]", "ORDER HISTORY [3]",
                            "PRESCRIPTIONS [4]");
                    userChoice = Interface.drawInput(75);

                    switch (userChoice) {
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
