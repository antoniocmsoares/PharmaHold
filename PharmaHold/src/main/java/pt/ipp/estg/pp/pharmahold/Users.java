package pt.ipp.estg.pp.pharmahold;

import java.util.ArrayList;

public abstract class Users {
    private static int countId = 1;
    private int id;
    private String name;
    private String password;
    private int contact; // format  000 000 000
    private boolean userState;
    private static ArrayList<Users> userList = new ArrayList<>();

    public Users(String name, String password, int contact) {
        this.id = countId++;
        this.name = name;
        this.password = password;
        this.contact = contact;
        this.userState = true;
        userList.add(this);
    }
    
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public static Users getUserById(int id) {
        for (int u = 0; u < userList.size(); u++) {
            Users usr = userList.get(u);
            if (usr.getId() == id) {
                return usr;
            }
        }
        return null;
    }

    public static void rmvUserById(int id) {
        for (int u = 0; u < userList.size(); u++) {
            Users usr = userList.get(u);
            if (usr.getId() == id) {
                userList.remove(id);
                usr.id = 0;
                --countId;
            }
        }
    }

    //usertypes 1- CLIENTE | 2- COLABORADOR | 3- ADMIN
    public abstract boolean login(String uName, String uPassword);
}