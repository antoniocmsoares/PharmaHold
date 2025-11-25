package pt.ipp.estg.pp.pharmahold;

import java.util.ArrayList;

import java.util.Scanner;

import pt.ipp.estg.pp.pharmahold.ENUMS.DoctorNames;
import pt.ipp.estg.pp.pharmahold.ENUMS.PrescriptionType;

public class PharmaHold {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        //PH DB-------------------------------------

        // USERS
        Client cl1 = new Client("martini", "pass1", 919999999, 'c',"rua 1 numero 2");
        Client cl2 = new Client("martino", "pass2", 919222222, 'c',"rua 2 numero 3");
        Client cl3 = new Client("martini", "pass3", 919999999, 'c',"rua 3 numero 4");

        // Prescriptions1
        Prescription pres1 = new Prescription(new int[]{01, 01, 2026}, new int[]{02, 05, 2026},
                PrescriptionType.COMMON, DoctorNames.ANA_SANTOS);
        Prescription pres2 = new Prescription(new int[]{04, 02, 2026}, new int[]{12, 05, 2026},
                PrescriptionType.COMMON, DoctorNames.MARIA_CARVALHO);
        Prescription pres3 = new Prescription(new int[]{07, 02, 2026}, new int[]{22, 06, 2026},
                PrescriptionType.COMMON, DoctorNames.JOAO_PEREIRA);
        Prescription pres4 = new Prescription(new int[]{11, 01, 2026}, new int[]{12, 03, 2026},
                PrescriptionType.COMMON, DoctorNames.ANA_SANTOS);
        Prescription pres5 = new Prescription(new int[]{20, 02, 2026}, new int[]{25, 02, 2026},
                PrescriptionType.COMMON, DoctorNames.CARLOS_RODRIGUES);

        // PRODUCTS
        Products prod1 = new Products("brufen", 14.44f, 3, false);
        Products prod2 = new Products("benuron", 14.44f, 3, false);
        Products prod3 = new Products("ritalina", 14.44f, 3, false);
        Products prod4 = new Products("griponal", 14.44f, 3, false);

        // ORDERS

        Orders order1 = new Orders(new int[]{01, 03, 2026}, new int[]{01, 04, 2026});
        Orders.addProducts(prod1);
        Orders.addProducts(prod2);
        Orders order2 = new Orders(new int[]{05, 03, 2026}, new int[]{05, 04, 2026});
        Orders.addProducts(prod3);


        int userChoice = 1;
        int userID = 0;
        boolean isLoggedIn = false;
        Users loggedUser = null;



        //MARTINI TEST
        System.out.println(Orders.printAllOrders());

        while (userChoice != 0) {
            while (!isLoggedIn) { // logged in
                //PH DB-------------------------------------
                Interface.newWindow();
                Interface.drawTitle("WELCOME TO PHARMAHOLD", 0);
                Interface.drawButtonList("def", "LEAVE [0]", "LOGIN [1]", "SIGNIN [2]");
                userChoice = Interface.drawInput(46);
                Interface.newWindow();
                if (userChoice == 0) {
                    break;
                }
                Interface.drawTitle("CHOOSE THE USER TYPE", 4);
                Interface.drawButtonList(" ", "CLIENTE [1]", "COLABORADOR [2]", "ADMIN [3]");
                int userType = Interface.drawInput(49);
                Interface.newWindow();
                //Efetuar Login
                String userName = Interface.drawFormInput("Username", 49); //name
                String pass = Interface.drawFormInput("Password", 49); //pass
                System.out.println("---------------------------------");
                System.out.println("Loading...");
                System.out.println("---------------------------------");
                if (userType == 1) { // verifica login cliente
                    char utype = 'c';
                    ArrayList<Users> allUsers = Users.getAllUsers();
                    for (Users user : allUsers) {
                        if (user instanceof Client) {
                            //loggedUser = Client.loginUsers(userName, pass, utype);
                            System.out.println("pre-login");
                            if (loggedUser != null) {
                                System.out.println("Trying to login as Client...");
                                userID = loggedUser.getId();
                                System.out.println("Bem vindo " + loggedUser.getName() + " !"); // user logado
                                isLoggedIn = true;
                                break;
                            } else {
                                System.out.println("User não encontrado, voltar ao menu ou sair do programa? Voltar [1] | Sair [2]");
                                int goBack = Interface.drawInput(49);
                                if (goBack == 1) {
                                    break;
                                } else if (goBack == 2) {
                                    System.exit(0);
                                }
                            }
                        }
                    }

                }

                if (isLoggedIn && userChoice != 0) { // back
                    userChoice = 1;
                    Interface.drawTitle("WELCOME TO PHARMAHOLD", 0);
                    Interface.drawButtonList("def", "LOGOUT [0]", "PRODUCTS [1]", "ORDERS [2]", "ORDER HISTORY [3]", "PRESCRIPTIONS [4]");
                    userChoice = Interface.drawInput(46);
                }
            }
        }
    }
}
