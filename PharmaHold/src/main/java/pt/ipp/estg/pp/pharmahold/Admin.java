//Admin class - contains Login and CRUD.
package pt.ipp.estg.pp.pharmahold;

import java.util.ArrayList;

public class Admin extends User {

    public static ArrayList<Admin> adminList = new ArrayList<>();

    public Admin(String name, String password, int contact) {
        super(name, password, contact);
        adminList.add(this);
    }

    public static Admin login(String uName, String uPassword) {
        for (Admin adm : adminList) {
            if (adm.getName().equals(uName) && adm.getPassword().equals(uPassword)) {
                return adm;
            }
        }
        return null;
    }

    // adds admin (Create)
    public static Admin addAdmin(String name, String password, int contact) {
        Admin a = new Admin(name, password, contact);
        return a;
    }

    // returns admin by id (READ)
    public static Admin getAdminById(int id) {
        for (Admin adm : adminList) {
            if (adm.getId() == id) {
                return adm;
            }
        }
        return null;
    }

    // removes admin by id (DELETE)
    public static boolean removeAdminById(int id) {
        Admin adm = getAdminById(id);
        if (adm != null) {
            adminList.remove(adm);
            return true;
        }
        return false;
    }
}

