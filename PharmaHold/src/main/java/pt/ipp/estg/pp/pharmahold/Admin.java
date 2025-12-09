// ADMIN CLASS
package pt.ipp.estg.pp.pharmahold;

import java.util.ArrayList;

public class Admin extends Users {

    private static ArrayList<Admin> admins = new ArrayList<>();

    public Admin(String name, String password, int contact, char userType) {
        super(name, password, contact, userType);
        admins.add(this);
    }

    public static Admin login(String uName, String uPassword, char userType) {
        for (Admin adm : admins) {
            if (adm.getName().equals(uName) && adm.getPassword().equals(uPassword)) {
                return adm;
            }
        }
        return null;
    }
    
    public void manageClient(int action, Users user, String[] args) { // the params include ACTION
        if (action == 1) { 

        }
    }
}
