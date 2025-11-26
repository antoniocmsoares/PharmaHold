package pt.ipp.estg.pp.pharmahold;

import java.util.ArrayList;

import pt.ipp.estg.pp.pharmahold.ENUMS.DoctorNames;
import pt.ipp.estg.pp.pharmahold.ENUMS.PrescriptionType;

public class Prescription {
    private int id;
    private int[] emiDate = new int[3]; //emition date :D dd/mm/yy
    private int[] expDate = new int[3]; //expire date :| dd/mm/yy
    private PrescriptionType type; //tipo da receita, se é receita comum ou controlada...
    private String doctor;
    private int idCliente;

    static private ArrayList<Prescription> existingPrescriptions = new ArrayList<>(); //todas as existentes
    private static int countID = 1;

    public Prescription(int[] emiDate, int[] expDate,PrescriptionType type, DoctorNames name) {
        this.id = countID++;
        this.emiDate = emiDate;
        this.expDate = expDate;
        this.type = type;

        switch(name){
            case ARTUR_SOUSA:
            this.doctor = "Artur Sousa";
            break;
            case MARIA_CARVALHO:
            this.doctor = "Maria Carvalho";
            break;
            case JOAO_PEREIRA:
            this.doctor = "Joao Pereira";
            break;
            case ANA_SANTOS:
            this.doctor = "Ana Santos";
            break;
            case CARLOS_RODRIGUES:
            this.doctor = "Carlos Rodrigues";
            break;
        }
        existingPrescriptions.add(this);
    }

    public static Prescription getPrescriptionById(int requestedId) {
        for (int i = 0; i < existingPrescriptions.size(); i++) {
            Prescription presc = existingPrescriptions.get(i);
            if (requestedId == existingPrescriptions.get(i).id) {
                return presc;
            }
        }
        return null;
    }

    public int getId(){
        return id;
    }

    public String toString() {
       return "** Requested Prescription **\n|_ id: " + id + "\n|_ type: " + type + "\n|_ doctor: " + doctor + "\n|_ emission date: " + emiDate[0] + "/" + emiDate[1] + "/" +emiDate[2] + "\n|_ expiration date: " + expDate[0] + "/" + expDate[1] + "/" +expDate[2];
    }

    public String getDoctorName() {
        return this.doctor;
    }
    public PrescriptionType getPrescriptionType() {
        return this.type;
    }
}
