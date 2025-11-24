package pt.ipp.estg.pp.pharmahold;

import java.util.ArrayList;

public class Admin extends Users {

    public Admin(String name, String password, int contact) {
        super(name, password, contact);
    }

    @Override
    public boolean login(String uName, String uPassword) {

        for (int i = 0; i < admins.size(); i++) {
            Admin potentialUser = admins.get(i);
            if (potentialUser.getName().equals(uName)) {
                return true;
            }
        }
        return false;
    }
}
