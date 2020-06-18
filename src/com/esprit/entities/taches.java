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
public class taches {
    int id ;
   String  dateTache ;
   String tache;

    public taches(int id, String dateTache, String tache) {
        this.id = id;
        this.dateTache = dateTache;
        this.tache = tache;
    }

    public taches(String dateTache, String tache) {
        this.dateTache = dateTache;
        this.tache = tache;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateTache() {
        return dateTache;
    }

    public void setDateTache(String dateTache) {
        this.dateTache = dateTache;
    }

    public String getTache() {
        return tache;
    }

    public void setTache(String tache) {
        this.tache = tache;
    }

    @Override
    public String toString() {
        return "taches{" + "id=" + id + ", dateTache=" + dateTache + ", tache=" + tache + '}';
    }
   
   
    
}
