package pt.ipp.estg.pp.pharmahold;

import java.util.ArrayList;

public class Admin extends Users {

    public Admin(String name, String password, int contact, char userType) {
        super(name, password, contact, userType);
    }

    @Override
    public Users login(String uName, String uPassword, char userType) {
        ArrayList<Users> userList = getAllUsers();
        if (userType == 'a') {
            for (int i = 0; i < userList.size(); i++) {
                Users potentialUser = userList.get(i);
                if (potentialUser.getName().equals(uName) && potentialUser.getPassword().equals(uPassword)) {
                    return potentialUser;
                }
            }
        }
        return null;
    }
}
