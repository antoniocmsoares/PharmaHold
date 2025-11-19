package pt.ipp.estg.pp.pharmahold;

public class Users {
    private static int countId = 1;
    private int id;
    private String name;
    private String password;
    private int contact; // format  000 000 000

    public Users(String name, String password, int contact) {
        this.id = countId++;
        this.name = name;
        this.password = password;
        this.contact = contact;
    }
}