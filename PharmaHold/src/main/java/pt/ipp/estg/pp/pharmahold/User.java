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
    
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public int getContact() {
        return contact;
    }

    public int setId(int id) {
        return this.id = id;
    }
}