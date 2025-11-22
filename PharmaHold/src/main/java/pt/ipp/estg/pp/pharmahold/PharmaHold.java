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
            System.out.println("\n\n\n\n\n\n\n\n");                 //talvez fazer uma class que desenhe a ui, invez de ser hardcoded
            System.out.println("+--------------------------------------------+");
            System.out.println("|           WELCOME TO PHARMAHOLD            |");
            System.out.println("+--------------------------------------------+");
            System.out.println("+-----------+\t+-----------+\t+------------+");
            System.out.println("| LEAVE [0] |\t| LOGIN [1] |\t| SIGNIN [2] |");
            System.out.println("+-----------+\t+-----------+\t+------------+");
            System.out.print("\n\n\n\n\n----------------------------------------------\n| YOUR CHOICE: ");
            userChoice = input.nextInt();
            if (userChoice == 0) break;
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\t     CHOOSE THE USER TYPE");
            System.out.println("---------------------------------------------");
            System.out.println("| CLIENTE [1] | COLABORADOR [2] | ADMIN [3] |");
            System.out.println("---------------------------------------------");
            int userType = input.nextInt();
            input.nextLine();
            System.out.println("---------------------------------");
            if (userType == 1) {                                                //Efetuar Login
                System.out.println("---------------------------------");
                System.out.println("Username:");
                String userName = input.nextLine();
                System.out.println("---------------------------------");
                System.out.println("Password:");
                String pass = input.nextLine();
                System.out.println("---------------------------------");
                System.out.println("Loading...");
                System.out.println("---------------------------------");
            }
        }
        // while (userChoice != 0) {
        //     //PH DB-------------------------------------
        //     System.out.println("\n\n\n\n\n\n\n\n");
        //     System.out.println("_.~----------------------------------------~._");
        //     System.out.println("|           WELCOME TO PHARMAHOLD            |");
        //     System.out.println("----------------------------------------------\n");
        //     System.out.println("-------------\t-------------\t--------------");
        //     System.out.println("| LEAVE [0] |\t| LOGIN [1] |\t| SIGNIN [2] |");
        //     System.out.println("-------------\t-------------\t--------------");
        //     System.out.print("\n----------------------------------------------\n| YOUR CHOICE: ");
        //     userChoice = input.nextInt();
        //     if (userChoice == 0) break;
        //     System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\t     CHOOSE THE USER TYPE");
        //     System.out.println("---------------------------------------------");
        //     System.out.println("| CLIENTE [1] | COLABORADOR [2] | ADMIN [3] |");
        //     System.out.println("---------------------------------------------");
        //     int userType = input.nextInt();
        //     input.nextLine();
        //     System.out.println("---------------------------------");
        //     if (userType == 1) {                                                //Efetuar Login
        //         System.out.println("---------------------------------");
        //         System.out.println("Username:");
        //         String userName = input.nextLine();
        //         System.out.println("---------------------------------");
        //         System.out.println("Password:");
        //         String pass = input.nextLine();
        //         System.out.println("---------------------------------");
        //         System.out.println("Loading...");
        //         System.out.println("---------------------------------");
        //     }
        // }
    }
} 
