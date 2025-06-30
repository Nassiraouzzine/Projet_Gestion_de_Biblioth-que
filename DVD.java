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

public class DVD extends Ressource {
    public static DVD createFromDialog() {
        String id = JOptionPane.showInputDialog("ID du DVD:");
        String titre = JOptionPane.showInputDialog("Titre du DVD:");
        String auteur = JOptionPane.showInputDialog("Réalisateur:");
        int annee = Integer.parseInt(JOptionPane.showInputDialog("Année de sortie:"));
        return new DVD(id, titre, auteur, annee);
    }
    public DVD(String id, String titre, String auteur, int annee) {
        super(id, titre, auteur, annee);
    }
    @Override
    public String getType() { return "DVD"; }
}
