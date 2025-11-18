package pt.ipp.estg.pp.pharmahold;

import java.util.ArrayList;

import pt.ipp.estg.pp.pharmahold.ENUMS.UserTypes;

public class Users {
    private int id;
    private UserTypes type;
    private String name;
    private String passwowrd;
    private int contact; // format  000 000 000
    private ArrayList<Orders> orders;
    private int totalPoints;
}