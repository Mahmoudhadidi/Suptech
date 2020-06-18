/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

/**
 *
 * @author Amenallah Lounis
 */
public class Evaluation {
    int id;
    int idEtd;
    String presence;
    String rendement;
    String discipline;
    String nomMatiere;
    int idEnseignant;

    public Evaluation() {
    }

    public Evaluation(int id, int idEtd, String presence, String rendement, String discipline, String nomMatiere, int idEnseignant) {
        this.id = id;
        this.idEtd = idEtd;
        this.presence = presence;
        this.rendement = rendement;
        this.discipline = discipline;
        this.nomMatiere = nomMatiere;
        this.idEnseignant = idEnseignant;
    }

    public Evaluation(int idEtd, String presence, String rendement, String discipline, String nomMatiere, int idEnseignant) {
        this.idEtd = idEtd;
        this.presence = presence;
        this.rendement = rendement;
        this.discipline = discipline;
        this.nomMatiere = nomMatiere;
        this.idEnseignant = idEnseignant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEtd() {
        return idEtd;
    }

    public void setIdEtd(int idEtd) {
        this.idEtd = idEtd;
    }

    public String getPresence() {
        return presence;
    }

    public void setPresence(String presence) {
        this.presence = presence;
    }

    public String getRendement() {
        return rendement;
    }

    public void setRendement(String rendement) {
        this.rendement = rendement;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

    public int getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(int idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

    @Override
    public String toString() {
        return "Evaluation{" + "id=" + id + ", idEtd=" + idEtd + ", presence=" + presence + ", rendement=" + rendement + ", discipline=" + discipline + ", nomMatiere=" + nomMatiere + ", idEnseignant=" + idEnseignant + '}';
    }
    
    
            
    
}
