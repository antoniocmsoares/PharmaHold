// ADMIN CLASS
package pt.ipp.estg.pp.pharmahold;

import java.util.ArrayList;
import pt.ipp.estg.pp.pharmahold.ENUMS.ADMIN.*;

public class Admin extends User {

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

    public void manageClient(/*UserAction*/int action, int userId) { // the params include ACTION
        if (action == 2/*UserAction.REMOVE*/) {
            Client usr = Client.getUserById(userId);
            if (usr instanceof Client) { // checks if usr exists
                Client.rmvUserById(userId);

                for (int u = 0; u < Client.getClients().size(); u++) {
                    System.out.println(Client.getClients().size());
                    String name = Client.getClients().get(u).getName(); //:>
                    Interface.drawTitle(name, 0);
                }
            } 
            else {
                Interface.drawTitle("User not found",0);
            }
        }
    }
}
