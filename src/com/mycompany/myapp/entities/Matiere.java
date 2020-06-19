/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author bhk
 */
public class Matiere {
    private int id,coefficient,credit;
    private String name;

    public Matiere(int id, int coefficient, int credit, String name) {
        this.id = id;
        this.coefficient = coefficient;
        this.credit = credit;
        this.name = name;
    }

    public Matiere(int coefficient, int credit, String name) {
        this.coefficient = coefficient;
        this.credit = credit;
        this.name = name;
    }

    public Matiere(int coefficient, String name) {
        this.coefficient = coefficient;
        this.name = name;
    }

    public Matiere(int id) {
        this.id = id;
    }
    

    public Matiere() {
       }

    public int getId() {
        return id;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public int getCredit() {
        return credit;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  ", Name matiere=" + name +", coefficient=" + coefficient + ", credit=" + credit +"\n";
    }
    
   
}
