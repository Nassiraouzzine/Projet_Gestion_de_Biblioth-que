package Gestion_Bibliotheque;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Adherent {
    private String id;
    private String nom;

    public Adherent(String id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public String getId() { return id; }
    public String getNom() { return nom; }
}
