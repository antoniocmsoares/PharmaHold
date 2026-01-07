//User superclass, baseline for various user Types.
package pt.ipp.estg.pp.pharmahold;

import java.util.ArrayList;

public class User {
    private static int countId = 1;
    private int id;
    private String name;
    private String password;
    private int contact; // format  000 000 000
    private static ArrayList<User> users = new ArrayList<>();
    private String address;
    private int totalPoints;

    public User(String name, String password, int contact) {
        this.id = countId;
        this.name = name;
        this.password = password;
        this.contact = contact;
        users.add(this);
        countId++;
    }

    public static int getCountId() {
        return countId;
    }

    public static void setCountId(int countId) {
        User.countId = countId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public static void listAllUsers() {
        System.out.println("┌─ USERS ────────────────────────────────────────");
        String userType = "";

        for (User u : users) {
            if (u instanceof Client) {
                userType = "Client";
            } else if (u instanceof Admin) {
                userType = "Admin";
            } else {
                userType = "Undefined";
            }
            System.out.println("│ reference: " + users.indexOf(u) + " / user: " + u.getName() + " / id: " + u.getId() + " / userType: " + userType);
        System.out.println("├────────────────────────────────────────────────");
        }
        System.out.println("│ ALL USERS\n└────────────────────────────────────────────────");
    }

    public static User getUserByIndex(int index) {
        int i = 0;
        for (User u : users) {
            i++;
            if (i == index) {
                return u;
            }
        }
        return null;
    }
    
    public void displaySelf() {
        String userType = "";
        if (this instanceof Client) {
                userType = "Client";
            } else if (this instanceof Admin) {
                userType = "Admin";
            } else {
                userType = "Undefined";
            }
        System.out.print("┌────────────────────────────────────────────────\n│ reference: " + users.indexOf(this) + " / user: " + this.name + " / id: " + this.id + " / userType: " + userType + " / totalPoints: " + this.totalPoints + " / adress: " + this.address + " / contact: " + this.contact + "│\n" + "└────────────────────────────────────────────────");
    }

    // checks if User exists by username
    public static boolean userExists(String uName) {
        for (User user : users) {
            if (user.getName().equals(uName)) {
                System.out.println("Username already exists!");
                return true;
            }
        }
        return false;
    }
    
    public static void removeUserByIndex(int index) {
        int i = 0;
        users.remove(index);
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }
}
