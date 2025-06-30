package Gestion_Bibliotheque;

public abstract class Utilisateur {
    protected String nom;
    protected String motDePasse;

    public Utilisateur(String nom, String motDePasse) {
        this.nom = nom;
        this.motDePasse = motDePasse;
    }

    public String getNom() { return nom; }
    public boolean verifierMotDePasse(String mdp) {
        return this.motDePasse.equals(mdp);
    }
}
