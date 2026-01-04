//User superclass, baseline for various user Types.
package pt.ipp.estg.pp.pharmahold;

public class User {
    private static int countId = 1;
    private int id;
    private String name;
    private String password;
    private int contact; // format  000 000 000

    public User(String name, String password, int contact) {
        this.id = countId;
        this.name = name;
        this.password = password;
        this.contact = contact;
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
    
}
