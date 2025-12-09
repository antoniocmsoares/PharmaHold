package pt.ipp.estg.pp.pharmahold;

import java.util.Scanner;

import pt.ipp.estg.pp.pharmahold.ENUMS.DoctorNames;
import pt.ipp.estg.pp.pharmahold.ENUMS.PrescriptionType;

public class PharmaHold {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        // ADMIN
        Admin adm1 = new Admin("root", "root", 91111111, 'a');

        // USERS
        Client client1 = new Client("martini", "pass1", 919999999, 'c', "rua 1 numero 2");
        Client client2 = new Client("martino", "pass2", 919222222, 'c', "rua 2 numero 3");
        Client client3 = new Client("1", "1", 919999999, 'c', "rua 3 numero 4");

        // PRESCRIPTIONS
        Prescription pres1 = new Prescription(new int[] { 1, 1, 2026 }, new int[] { 2, 5, 2026 },
                PrescriptionType.COMMON, DoctorNames.ANA_SANTOS);
        Prescription pres2 = new Prescription(new int[] { 4, 2, 2026 }, new int[] { 12, 5, 2026 },
                PrescriptionType.COMMON, DoctorNames.MARIA_CARVALHO);
        Prescription pres3 = new Prescription(new int[] { 7, 2, 2026 }, new int[] { 22, 6, 2026 },
                PrescriptionType.COMMON, DoctorNames.JOAO_PEREIRA);

        // PRODUCTS
        Products prod1 = new Products("brufen", 14.44f, 15, false);
        Products prod2 = new Products("benuron", 14.44f, 14, true);
        Products prod3 = new Products("ritalina", 14.44f, 12, false);
        Products prod4 = new Products("griponal", 14.44f, 10, true);

        // ORDERS
        Orders order1 = new Orders(new int[] { 1, 3, 2026 }, new int[] { 1, 4, 2026 });
        order1.addProducts(prod1);
        order1.addProducts(prod2);

        Orders order2 = new Orders(new int[] { 5, 3, 2026 }, new int[] { 5, 4, 2026 });
        order2.addProducts(prod2);

        Orders order3 = new Orders(new int[] { 5, 3, 2026 }, new int[] { 5, 4, 2026 });
        order3.addProducts(prod3);
        order3.addProducts(prod2);

        Orders order4 = new Orders(new int[] { 5, 3, 2026 }, new int[] { 5, 4, 2026 });
        order4.addProducts(prod4);
        order4.addProducts(prod3);

        client1.addOrders(order1);
        client2.addOrders(order2);
        client3.addOrders(order3);
        client3.addOrders(order4);

        int userChoice = 1;
        boolean isLoggedIn = false;
        Client loggedClient = null;
        Admin loggedAdmin = null;
        Client loggedUser = null;

        while (userChoice != 0) {
            if (!isLoggedIn) { // used to be while, in case of error
                Interface.newWindow();
                Interface.drawTitle("WELCOME TO PHARMAHOLD", 0);
                Interface.drawButtonList("def", "LEAVE [0]", "LOGIN [1]", "SIGNUP [2]");
                userChoice = Interface.drawInput(46);

                if (userChoice == 0) {
                    break;
                } else if (userChoice == 1) {

                    Interface.newWindow();
                    Interface.drawTitle("CHOOSE THE USER TYPE", 4);
                    Interface.drawButtonList(" ", "CLIENTE [1]", "COLABORADOR [2]", "ADMIN [3]");
                    int userType = Interface.drawInput(49);

                    Interface.newWindow();
                    Interface.drawFormInput("Username", 49);
                    String userName = input.nextLine();
                    Interface.drawFormInput("Password", 49);
                    String pass = input.nextLine();

                    System.out.println("---------------------------------");
                    System.out.println("Loading...");
                    System.out.println("---------------------------------");

                    if (userType == 1) { // CLIENTE
                        loggedClient = Client.login(userName, pass, 'c');
                        System.out.println(loggedClient);

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
                    } else if (userType == 3) { // ADMIN
                        loggedAdmin = Admin.login(userName, pass, 'a');
                        System.out.println("Bem vindo " + loggedAdmin.getName() + " !");
                        isLoggedIn = true;
                    }

                    while (isLoggedIn && userChoice != 0) {
                        Interface.drawTitle("WELCOME TO PHARMAHOLD", 53);
                        Interface.drawButtonList("def", "LOGOUT [0]", "PRODUCTS [1]", "ORDERS [2]", "ORDER HISTORY [3]",
                                "PRESCRIPTIONS [4]");
                        userChoice = Interface.drawInput(75);

                        switch (userChoice) {
                            case 1:
                                Interface.newWindow();
                                Interface.drawTitle("PRODUCTS LIST", 0);
                                System.out.println(Products.listAllProducts());
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
                } else {
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

                    if (userName != null && pass != null) {
                        Client signClient = new Client(userName, pass, contact, 'c', address);
                        loggedClient = Client.login(userName, pass, 'c');

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
                    }
                }
            }
        }
    }
}
