package pt.ipp.estg.pp.pharmahold;

public class Users {
    private static int countId = 1;
    private int id;
    private String name;
    private String password;
    private int contact; // format  000 000 000
    private char userType; // c ou 1 = cliente | a ou 2 = admin | w ou 3 = colaborador

    public Users(String name, String password, int contact,char userType) {
        this.id = countId++;
        this.name = name;
        this.password = password;
        this.contact = contact;
        this.userType = userType;
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

    public char getUserType() {
        return userType;
    }
    public void setDecCountId() {
        this.countId = --countId;
    }

    public int setId(int id) {
        return this.id = id;
    }

    
}