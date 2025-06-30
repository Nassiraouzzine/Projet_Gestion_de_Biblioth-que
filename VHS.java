ackage Gestion_Bibliotheque;

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

public class VHS extends Ressource {
    public static VHS createFromDialog() {
        String id = JOptionPane.showInputDialog("ID de la VHS:");
        String titre = JOptionPane.showInputDialog("Titre de la VHS:");
        String auteur = JOptionPane.showInputDialog("Réalisateur:");
        int annee = Integer.parseInt(JOptionPane.showInputDialog("Année de sortie:"));
        return new VHS(id, titre, auteur, annee);
    }
    public VHS(String id, String titre, String auteur, int annee) {
        super(id, titre, auteur, annee);
    }
    @Override
    public String getType() { return "VHS"; }
}
