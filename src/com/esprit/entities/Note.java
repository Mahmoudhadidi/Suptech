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
public class Note {
    private int idNote;
    private float noteCc;
    private float noteDs;
    private float noteExamun;
    private float moyenne;
    private float net;
    private int cin;
    private String nomMatier;
    
    public Note(){
    }

    public Note(int idNote, float noteCc, float noteDs, float noteExamun, float moyenne, float net, int cin, String nomMatier) {
        this.idNote = idNote;
        this.noteCc = noteCc;
        this.noteDs = noteDs;
        this.noteExamun = noteExamun;
        this.moyenne = moyenne;
        this.net = net;
        this.cin = cin;
        this.nomMatier = nomMatier;
    }
    
     public Note(float noteCc, float noteDs, float noteExamun,  int cin, String nomMatier) {
       
        this.noteCc = noteCc;
        this.noteDs = noteDs;
        this.noteExamun = noteExamun;
        
        
        this.cin = cin;
        this.nomMatier = nomMatier;
    }

    public Note(float noteCc, float noteDs, float noteExamun, float moyenne, float net, int cin, String nomMatier) {
        this.noteCc = noteCc;
        this.noteDs = noteDs;
        this.noteExamun = noteExamun;
        this.moyenne = moyenne;
        this.net = net;
        this.cin = cin;
        this.nomMatier = nomMatier;
    }

    

    public int getIdNote() {
        return idNote;
    }

    public void setIdNote(int idNote) {
        this.idNote = idNote;
    }

    public float getNoteCc() {
        return noteCc;
    }

    public void setNoteCc(float noteCc) {
        this.noteCc = noteCc;
    }

    public float getNoteDs() {
        return noteDs;
    }

    public void setNoteDs(float noteDs) {
        this.noteDs = noteDs;
    }

    public float getNoteExamun() {
        return noteExamun;
    }

    public void setNoteExamun(float noteExamun) {
        this.noteExamun = noteExamun;
    }

    public float getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(float moyenne) {
        this.moyenne = moyenne;
    }

    public float getNet() {
        return net;
    }

    public void setNet(float net) {
        this.net = net;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getNomMatier() {
        return nomMatier;
    }

    public void setNomMatier(String nomMatier) {
        this.nomMatier = nomMatier;
    }

    @Override
    public String toString() {
        return "Note{" + "idNote=" + idNote + ", noteCc=" + noteCc + ", noteDs=" + noteDs + ", noteExamun=" + noteExamun + ", moyenne=" + moyenne + ", net=" + net + ", cin=" + cin + ", nomMatier=" + nomMatier + '}';
    }

   
    
    
    
    
    
}
