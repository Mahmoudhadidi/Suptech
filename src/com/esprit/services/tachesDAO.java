/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.taches;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author sana
 */
public class tachesDAO {

    private Database db2;
    boolean created;

     public tachesDAO() {
    created =Database.exists("MyDB.db");
        try {
            db2 = Database.openOrCreate("MyDB.db");
            if (!created) {
            db2.execute("create table temp (id INTEGER PRIMARY KEY,dateTache text,tache text);");
            }
            System.out.println(created);
            System.out.println(db2);
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    public void ajouter(taches p) {

        try {
            System.out.println("tes");
//req="insert into temp (name,num) values (?,?);",  new String[]{""+p.getNom(), ""+p.getPrenom()});

            //db.execute("insert into temp (nom,prenom) values ('" + p.getNom() + "','" + p.getPrenom() + "')");

            db2.execute("insert into temp (dateTache,tache) values (?,?);",
                    new Object[]{p.getDateTache(), p.getTache()});
            System.out.println("ajouter");

        } catch (IOException ex) {
            System.out.println(ex);
        }

        
    }

    public ArrayList<taches> getPerson() throws IOException {
        Cursor c = db2.executeQuery("select * from temp");
        ArrayList<taches> list = new ArrayList();
        taches pers = null;
        while (c.next()) {
            Row r = c.getRow();
            int id = r.getInteger(0);
            String dateTache = r.getString(1);
            String tache = r.getString(2);
            pers = new taches(id, dateTache, tache);
            list.add(pers);
        }
        return list;

    }

    public Container getItems() throws IOException {
        Cursor c = db2.executeQuery("select * from temp");

        final Container rows = new ComponentGroup();
        rows.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        rows.setScrollable(true);
        while (c.next()) {
            Row r = c.getRow();
           
            String dateTache = r.getString(1);
            String tache = r.getString(2);
            CheckBox cb = new CheckBox(dateTache + " " + tache);
            //cb.putClientProperty("id", "" + id);
            rows.addComponent(cb);
        }
        return rows;

    }

}

