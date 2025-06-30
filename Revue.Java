package Gestion_Bibliotheque;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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

//Revue.java
public class Revue extends Ressource {
 public static Revue createFromDialog() {
     String id = JOptionPane.showInputDialog("ID de la revue:");
     String titre = JOptionPane.showInputDialog("Titre de la revue:");
     String auteur = JOptionPane.showInputDialog("Éditeur:");
     int annee = Integer.parseInt(JOptionPane.showInputDialog("Année de publication:"));
     return new Revue(id, titre, auteur, annee);
 }
 public Revue(String id, String titre, String auteur, int annee) {
     super(id, titre, auteur, annee);
 }
 @Override
 public String getType() { return "Revue"; }
}
