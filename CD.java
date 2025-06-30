package Gestion_Bibliotheque;

import java.sql.*;

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
import java.util.ArrayList;
import java.util.List;

public class CD extends Ressource {
    public static CD createFromDialog() {
        String id = JOptionPane.showInputDialog("ID du CD:");
        String titre = JOptionPane.showInputDialog("Titre du CD:");
        String auteur = JOptionPane.showInputDialog("Artiste:");
        int annee = Integer.parseInt(JOptionPane.showInputDialog("Année de sortie:"));
        return new CD(id, titre, auteur, annee);
    }
    public CD(String id, String titre, String auteur, int annee) {
        super(id, titre, auteur, annee);
    }
    @Override
    public String getType() { return "CD"; }
}
