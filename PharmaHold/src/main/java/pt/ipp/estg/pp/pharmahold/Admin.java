// ADMIN CLASS
package pt.ipp.estg.pp.pharmahold;

import java.util.ArrayList;

public class Admin extends User {
    private static ArrayList<Admin> adminList = new ArrayList<>();
    private static ArrayList<Worker> workerList = new ArrayList<>();
    private static ArrayList<Client> clientList = new ArrayList<>();
    private static ArrayList<Order> orderList = new ArrayList<>();
    private static ArrayList<Prescription> prescriptionList = new ArrayList<>();

    public Admin(String name, String password, int contact, char userType) {
        super(name, password, contact, userType);
        adminList.add(this);
    }

    public static Admin login(String uName, String uPassword, char userType) {
        for (Admin adm : adminList) {
            if (adm.getName().equals(uName) && adm.getPassword().equals(uPassword)) {
                return adm;
            }
        }
        return null;
    }

    // ADD TO ARRAYLIST THE NEW USERS
    public static void addAdmin(Admin newUsr) {
        adminList.add(newUsr);
        newUsr.setId(adminList.indexOf(newUsr));
    }

    public static void addWorker(Worker newUsr) {
        workerList.add(newUsr);
        newUsr.setId(workerList.indexOf(newUsr));
    }

    public static void addClient(Client newUsr) {
        clientList.add(newUsr);
        newUsr.setId(clientList.indexOf(newUsr));
    }

    public static void rmvAdmin(Admin trgUsr) {         // THIS WAY IT MAKES THE ARRAY + ID AVAIBLE, is it a good idea? or bad?
        adminList.remove(trgUsr);                       // "I ADMIT I THINK ITS QUICK BUT NOT AMAZING, SINCE IF FOR SOME REASON WE NEED HIM"
        trgUsr.setId(-999);                             // WE WONT BE ABLE TO CALL HIM, THIS IN MY HEAD... I MEAN I CANT THINK IN OTHER WAY
    }                                                   
                                                       

    // ADD TO ARRAYLIST THE NEW OBJECTS
    public static void addOrder(Order newOrd) {
        orderList.add(newOrd);
    }

    public static void addPresc(Prescription newPrc) {
        prescriptionList.add(newPrc);
    }

    public void manageClient(int action, int userId) { // the params include ACTION
        if (action == 2) {
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

