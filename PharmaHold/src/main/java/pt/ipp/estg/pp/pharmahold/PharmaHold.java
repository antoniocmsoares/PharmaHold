package pt.ipp.estg.pp.pharmahold;

import pt.ipp.estg.pp.pharmahold.ENUMS.DoctorNames;
import pt.ipp.estg.pp.pharmahold.ENUMS.PrescriptionType;

public class PharmaHold {

    public static void main(String[] args) {
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

        

        //PH DB-------------------------------------
        System.out.println("---------------------------------");

        // make a prescription request
        int requestedId = 3;
        Prescription test = Prescription.getPrescriptionById(requestedId);
        if (test != null) {
            System.out.println("_suc: Prescription added! " + test.getId());
            test.toString();
        } else {
            System.out.println("_err: The object Prescription with the id " + requestedId + " couldn't be found.");
        }
    }
}
