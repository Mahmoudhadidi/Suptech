/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suptech.Entity;

/**
 *
 * @author ADMIN
 */
public class DemandeClub {
    private int id;
    private int id_etudiant;
    private String nom_club;
    private String domaine;
    private String description;
    private String etat;

     public DemandeClub() {
    }

    public DemandeClub(int id, int id_etudiant, String nom_club, String domaine, String description, String etat) {
        this.id = id;
        this.id_etudiant = id_etudiant;
        this.nom_club = nom_club;
        this.domaine = domaine;
        this.description = description;
        this.etat = etat;     
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_etudiant() {
        return id_etudiant;
    }

    public void setId_etudiant(int id_etudiant) {
        this.id_etudiant = id_etudiant;
    }

    public String getNom_club() {
        return nom_club;
    }

    public void setNom_club(String nom_club) {
        this.nom_club = nom_club;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
    
}
