package Gestion_Bibliotheque;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
	public static void init() {
        try (Connection conn = dataconnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Table des ressources
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS ressources (
                    id TEXT PRIMARY KEY,
                    titre TEXT,
                    auteur TEXT,
                    annee INTEGER,
                    type TEXT,
                    disponible INTEGER
                )
            """);

            // Table des adhérents
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS adherents (
                    id TEXT PRIMARY KEY,
                    nom TEXT
                )
            """);

            // Table des emprunts
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS emprunts (
                    id_ressource TEXT,
                    id_adherent TEXT,
                    FOREIGN KEY(id_ressource) REFERENCES ressources(id),
                    FOREIGN KEY(id_adherent) REFERENCES adherents(id)
                )
            """);

            System.out.println("Tables créées ou déjà existantes.");
        } catch (SQLException e) {
            System.err.println("Erreur d'initialisation de la base : " + e.getMessage());
        }
    }
}
