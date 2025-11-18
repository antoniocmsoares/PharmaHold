package pt.ipp.estg.pp.pharmahold;

import pt.ipp.estg.pp.pharmahold.ENUMS.DoctorNames;
import pt.ipp.estg.pp.pharmahold.ENUMS.PrescriptionType;

public class PharmaHold {
    public static Prescription pres1 = new Prescription(new int[] {01,01,2026},new int[] {01,01,2026},PrescriptionType.COMMON, DoctorNames.ANA_SANTOS);

    public static void main(String[] args) {
        System.out.println("=== PROGRAM DEBUG OUTPUT ==="); //só para orientação
        System.out.println(pres1);
        System.out.println("---------------------------------");

        int requestedId = 0;
        Prescription test = Prescription.getPrescriptionById(requestedId);
        if (test != null) {
            System.out.println("_suc: Prescription founded!");
            test.toString();
        }
        else System.out.println("_err: The object Prescription with the id " + requestedId + " could'nt be found.");
    }
}
