package pt.ipp.estg.pp.pharmahold;

public class Admin extends Users {
    public Admin(String name, String password, int contact) {
        super(name, password, contact);
    }

    public boolean login(int type, String name, String password) {
        if (type == 1) {
            return false;
        } 
        else {
            return true;
        }
    }    
}
