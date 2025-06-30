package Gestion_Bibliotheque;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dataconnection {
    private static final String URL = "jdbc:sqlite:bibliotheque.db";
    private static Connection connection;

    public static ArrayList<Adherent> adherents = new ArrayList<>();
    public static ArrayList<Emprunt> emprunts = new ArrayList<>();
    public static void saveRessource(Ressource r) {
        String sql = "INSERT INTO ressources (id, titre, auteur, annee, type, disponible) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, r.getId());
            ps.setString(2, r.getTitre());
            ps.setString(3, r.getAuteur());
            ps.setInt(4, r.getAnnee());
            ps.setString(5, r.getType());
            ps.setBoolean(6, r.isDisponible());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur saveRessource: " + e.getMessage());
        }
    }


    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur de chargement du driver SQLite: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL);
        }
        return connection;
    }

    public static List<Ressource> getAllRessources() {
        List<Ressource> list = new ArrayList<>();
        String sql = "SELECT * FROM ressources";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String id = rs.getString("id");
                String titre = rs.getString("titre");
                String auteur = rs.getString("auteur");
                int annee = rs.getInt("annee");
                String type = rs.getString("type");
                boolean dispo = rs.getBoolean("disponible");

                Ressource r = null;
                switch (type) {
                    case "Livre": r = new Livre(id, titre, auteur, annee); break;
                    case "DVD": r = new DVD(id, titre, auteur, annee); break;
                    case "CD": r = new CD(id, titre, auteur, annee); break;
                    case "Revue": r = new Revue(id, titre, auteur, annee); break;
                    case "VHS": r = new VHS(id, titre, auteur, annee); break;
                }
                if (r != null) r.setDisponible(dispo);
                list.add(r);
            }
        } catch (Exception e) {
            System.err.println("Erreur getAllRessources: " + e.getMessage());
        }
        return list;
    }

    public static Ressource getRessourceById(String id) {
        String sql = "SELECT * FROM ressources WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String titre = rs.getString("titre");
                String auteur = rs.getString("auteur");
                int annee = rs.getInt("annee");
                String type = rs.getString("type");
                boolean dispo = rs.getBoolean("disponible");
                Ressource r = null;
                switch (type) {
                    case "Livre": r = new Livre(id, titre, auteur, annee); break;
                    case "DVD": r = new DVD(id, titre, auteur, annee); break;
                    case "CD": r = new CD(id, titre, auteur, annee); break;
                    case "Revue": r = new Revue(id, titre, auteur, annee); break;
                    case "VHS": r = new VHS(id, titre, auteur, annee); break;
                }
                if (r != null) r.setDisponible(dispo);
                return r;
            }
        } catch (Exception e) {
            System.err.println("Erreur getRessourceById: " + e.getMessage());
        }
        return null;
    }

    public static void deleteRessourceById(String id) {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM ressources WHERE id = ?")) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("Erreur deleteRessourceById: " + e.getMessage());
        }
    }

    public static void updateRessource(Ressource r) {
        String sql = "UPDATE ressources SET titre = ?, auteur = ?, annee = ?, disponible = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, r.getTitre());
            ps.setString(2, r.getAuteur());
            ps.setInt(3, r.getAnnee());
            ps.setBoolean(4, r.isDisponible());
            ps.setString(5, r.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("Erreur updateRessource: " + e.getMessage());
        }
    }

    public static void markAsAvailable(String id) {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE ressources SET disponible = 1 WHERE id = ?")) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("Erreur markAsAvailable: " + e.getMessage());
        }
    }
 // Ajout d'un adhérent dans la base
    public static void saveAdherent(Adherent a) {
        String sql = "INSERT INTO adherents (id, nom) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, a.getId());
            ps.setString(2, a.getNom());
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("Erreur saveAdherent: " + e.getMessage());
        }
    }

    // Suppression d’un adhérent
    public static void deleteAdherentById(String id) {
        String sql = "DELETE FROM adherents WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("Erreur deleteAdherentById: " + e.getMessage());
        }
    }

    // Récupération de tous les adhérents
    public static List<Adherent> getAllAdherents() {
        List<Adherent> list = new ArrayList<>();
        String sql = "SELECT * FROM adherents";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Adherent(rs.getString("id"), rs.getString("nom")));
            }
        } catch (Exception e) {
            System.err.println("Erreur getAllAdherents: " + e.getMessage());
        }
        return list;
    }

}
