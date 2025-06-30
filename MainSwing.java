// MainSwing.java
package Gestion_Bibliotheque;

import javax.swing.*;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainSwing {
    private static JTable resourceTable;

    public static void main(String[] args) {
        DatabaseInitializer.init();
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gestion Bibliothèque");
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(createTabbedPane());
            frame.setVisible(true);
        });
    }

    private static JTabbedPane createTabbedPane() {
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Ressources", createResourcePanel());
        tabs.addTab("Adhérents", createAdherentPanel());
        tabs.addTab("Emprunts", createEmpruntPanel());
        return tabs;
    }
  


    private static JPanel createResourcePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        String[] columns = {"ID", "Titre", "Auteur", "Année", "Type", "Statut"};
        List<Ressource> data = dataconnection.getAllRessources();
        String[][] tableData = new String[data.size()][6];

        for (int i = 0; i < data.size(); i++) {
            Ressource r = data.get(i);
            tableData[i][0] = r.getId();
            tableData[i][1] = r.getTitre();
            tableData[i][2] = r.getAuteur();
            tableData[i][3] = String.valueOf(r.getAnnee());
            tableData[i][4] = r.getType();
            tableData[i][5] = r.isDisponible() ? "Disponible" : "Emprunté";
        }

        resourceTable = new JTable(tableData, columns);
        JScrollPane scrollPane = new JScrollPane(resourceTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttons = new JPanel();

        JButton add = new JButton("Ajouter Ressource");
        add.addActionListener(e -> {
            String[] types = {"Livre", "DVD", "CD", "Revue", "VHS"};
            String type = (String) JOptionPane.showInputDialog(null, "Choisir le type de ressource:", "Type", JOptionPane.QUESTION_MESSAGE, null, types, types[0]);
            if (type == null) return;
            Ressource r = null;
            switch (type) {
                case "Livre": r = Livre.createFromDialog(); break;
                case "DVD": r = DVD.createFromDialog(); break;
                case "CD": r = CD.createFromDialog(); break;
                case "Revue": r = Revue.createFromDialog(); break;
                case "VHS": r = VHS.createFromDialog(); break;
            }
            if (r != null) {
                dataconnection.saveRessource(r);
                refreshResourcePanel();
                JOptionPane.showMessageDialog(null, type + " ajouté !");
            }
        });

        JButton supprimer = new JButton("Supprimer");
        supprimer.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("ID à supprimer :");
            Ressource r = dataconnection.getRessourceById(id);
            if (r != null) {
                dataconnection.deleteRessourceById(id);
                refreshResourcePanel();
                JOptionPane.showMessageDialog(null, "Supprimé avec succès.");
            }
        });

        JButton modifier = new JButton("Modifier");
        modifier.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("ID de la ressource à modifier :");
            Ressource r = dataconnection.getRessourceById(id);
            if (r != null) {
                String nouveauTitre = JOptionPane.showInputDialog("Nouveau titre :", r.getTitre());
                String nouvelAuteur = JOptionPane.showInputDialog("Nouvel auteur :", r.getAuteur());
                int nouvelleAnnee = Integer.parseInt(JOptionPane.showInputDialog("Nouvelle année :", r.getAnnee()));
                r.setTitre(nouveauTitre);
                r.setAuteur(nouvelAuteur);
                r.setAnnee(nouvelleAnnee);
                dataconnection.updateRessource(r);
                refreshResourcePanel();
            }
        });

        buttons.add(add);
        buttons.add(supprimer);
        buttons.add(modifier);
        panel.add(buttons, BorderLayout.SOUTH);

        return panel;
    }

    public static void refreshResourcePanel() {
        List<Ressource> data = dataconnection.getAllRessources();
        DefaultTableModel model = new DefaultTableModel(
            new Object[][] {}, new String[] {"ID", "Titre", "Auteur", "Année", "Type", "Statut"});
        for (Ressource r : data) {
            model.addRow(new Object[] {
                r.getId(), r.getTitre(), r.getAuteur(),
                r.getAnnee(), r.getType(),
                r.isDisponible() ? "Disponible" : "Emprunté"
            });
        }
        resourceTable.setModel(model);
    }


    private static JPanel createEmpruntPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Gestion des emprunts à implémenter"));
        return panel;
    }
    private static JTable adherentTable;

    private static JPanel createAdherentPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Colonnes
        String[] columns = {"ID", "Nom"};
        List<Adherent> data = dataconnection.getAllAdherents();
        String[][] tableData = new String[data.size()][2];
        for (int i = 0; i < data.size(); i++) {
            Adherent a = data.get(i);
            tableData[i][0] = a.getId();
            tableData[i][1] = a.getNom();
        }

        // Table
        adherentTable = new JTable(tableData, columns);
        JScrollPane scroll = new JScrollPane(adherentTable);
        panel.add(scroll, BorderLayout.CENTER);

        // Boutons
        JPanel buttons = new JPanel();

        JButton add = new JButton("Ajouter");
        add.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("ID de l'adhérent :");
            String nom = JOptionPane.showInputDialog("Nom de l'adhérent :");
            if (id != null && nom != null && !id.isEmpty() && !nom.isEmpty()) {
                dataconnection.saveAdherent(new Adherent(id, nom));
                refreshAdherentPanel();
            }
        });

        JButton delete = new JButton("Supprimer");
        delete.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("ID à supprimer :");
            if (id != null && !id.isEmpty()) {
                dataconnection.deleteAdherentById(id);
                refreshAdherentPanel();
            }
        });

        buttons.add(add);
        buttons.add(delete);
        panel.add(buttons, BorderLayout.SOUTH);

        return panel;
    }

    private static void refreshAdherentPanel() {
        List<Adherent> data = dataconnection.getAllAdherents();
        String[][] tableData = new String[data.size()][2];
        for (int i = 0; i < data.size(); i++) {
            tableData[i][0] = data.get(i).getId();
            tableData[i][1] = data.get(i).getNom();
        }
        adherentTable.setModel(new javax.swing.table.DefaultTableModel(tableData, new String[]{"ID", "Nom"}));
    }

}
