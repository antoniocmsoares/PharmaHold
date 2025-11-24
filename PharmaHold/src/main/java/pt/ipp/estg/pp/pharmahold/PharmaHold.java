package pt.ipp.estg.pp.pharmahold;

import java.util.Scanner;

import pt.ipp.estg.pp.pharmahold.ENUMS.DoctorNames;
import pt.ipp.estg.pp.pharmahold.ENUMS.PrescriptionType;

public class PharmaHold {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //PH DB-------------------------------------

        // USERS
        Client cl1 = new Client("martini", "pass1", 919999999);
        Client cl2 = new Client("martini", "pass1", 919222222);
        Client cl3 = new Client("martini", "pass1", 919999999);

        // Prescriptions
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
        int userChoice = 1;
        int userID = 0;
        while (userChoice != 0) {
            //PH DB-------------------------------------
            if (userID != 0) {
                Users currUsr = Users.getUserById(userID);
                // para dar display das cenas do user atual....
            }
            Interface.newWindow();
            Interface.drawTitle("WELCOME TO PHARMAHOLD", 0);
            Interface.drawButtonList("def","LEAVE [0]", "LOGIN [1]", "SIGNIN [2]");
            Interface.drawInput(46);
            userChoice = input.nextInt();
            Interface.newWindow();
            if (userChoice == 0) break;
            Interface.drawTitle("CHOOSE THE USER TYPE",4);
            Interface.drawButtonList(" ","CLIENTE [1]", "COLABORADOR [2]", "ADMIN [3]");
            Interface.drawInput(49);
            int userType = input.nextInt();
            Interface.newWindow();
            input.nextLine();                                              //Efetuar Login
            Interface.drawCustomInput("Username", 49);
            String userName = input.nextLine();
            Interface.drawCustomInput("Password", 49);
            String pass = input.nextLine();
            System.out.println("---------------------------------");
            System.out.println("Loading...");
            System.out.println("---------------------------------");
            
        }
    }
} 
