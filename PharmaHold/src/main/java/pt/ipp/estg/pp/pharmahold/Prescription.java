package pt.ipp.estg.pp.pharmahold;

import pt.ipp.estg.pp.pharmahold.ENUMS.*;
import java.util.ArrayList;

public class Prescription {
    private int id;
    private int[] emiDate = new int[3]; //emition date :D dd/mm/yy
    private int[] expDate = new int[3]; //expire date :| dd/mm/yy
    private PrescriptionType type; //tipo da receita, se é receita comum ou controlada...
    private DoctorsName doctor;
    static private ArrayList<Prescription> existentPrescriptions = new ArrayList<>(); //todas as existentes

    public Prescription(int id, PrescriptionType type, DoctorsName name) {
        this.id = id;
        this.type = type;
        this.doctor = name;
    }
    
    public static void newPrescription(int preId, PrescriptionType preType, DoctorsName preDoctor) {
        Prescription presc = new Prescription(preId, preType, preDoctor);
        existentPrescriptions.add(presc);
    }

    public static Prescription getPrescriptionById(int requestedId) {
        for (int i = 0; i < existentPrescriptions.size(); i++) {
            Prescription presc = existentPrescriptions.get(i);
            if (requestedId == existentPrescriptions.get(i).id) {
                return presc;
            }
        }
        return null;
    }

    public String toString() {
        return "** Requested Prescription **\n|_ id: " + id + "\n|_ type: " + type + "\n|_ doctor: " + doctor;
    }
}
