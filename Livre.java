package Gestion_Bibliotheque;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Livre extends Ressource {
    public static Livre createFromDialog() {
        String id = JOptionPane.showInputDialog("ID du livre:");
        String titre = JOptionPane.showInputDialog("Titre du livre:");
        String auteur = JOptionPane.showInputDialog("Auteur du livre:");
        int annee = Integer.parseInt(JOptionPane.showInputDialog("Année de publication:"));
        return new Livre(id, titre, auteur, annee);
    }
    public Livre(String id, String titre, String auteur, int annee) {
        super(id, titre, auteur, annee);
    }
    @Override
    public String getType() { return "Livre"; }
}
