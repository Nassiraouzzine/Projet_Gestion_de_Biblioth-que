package Gestion_Bibliotheque;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Emprunt {
    private Ressource ressource;
    private Adherent adherent;

    public Emprunt(Ressource ressource, Adherent adherent) {
        this.ressource = ressource;
        this.adherent = adherent;
    }

    public Ressource getRessource() { return ressource; }
    public Adherent getAdherent() { return adherent; }
}
