/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suptech.GUI;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.io.rest.Response;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.suptech.Entity.DemandeClub;
import com.suptech.Service.ServiceDemandeClub;
import java.util.Map;
import com.codename1.io.rest.Rest;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
//import com.sun.xml.internal.ws.api.message.saaj.SAAJFactory;

/**
 *
 * @author ADMIN
 */
public class ListeDemande {
    Form f;
    public ListeDemande(){
        Toolbar.setGlobalToolbar(true);
         f = new Form("Liste des DemandeClub", new BoxLayout(BoxLayout.Y_AXIS));
         
         //Search field
         Style s = UIManager.getInstance().getComponentStyle("Title");
         TextField searchField = new TextField("", "ToolBar Search"); // <1>
        searchField.getHintLabel().setUIID("Title");
        searchField.setUIID("Title");
        searchField.getAllStyles().setAlignment(Component.LEFT);
        f.getToolbar().setTitleComponent(searchField);
        FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);
        searchField.addDataChangeListener((i1, i2) -> { // <2>
            String t = searchField.getText();

            if(t.length() < 1) {
        for(Component cmp : f.getContentPane()) {
            cmp.setHidden(false);
            cmp.setVisible(true);
        }
    } else {
        t = t.toLowerCase();
        for(Component cmp : f.getContentPane()) {
            String val = null;
            if(cmp instanceof Label) {
                val = ((Label)cmp).getText();
            } else {
                if(cmp instanceof TextArea) {
                    val = ((TextArea)cmp).getText();
                } else {
                    val = (String)cmp.getPropertyValue("text");
                }
            }
            boolean show = val != null && val.toLowerCase().indexOf(t) > -1;
            cmp.setHidden(!show); // <3>
            cmp.setVisible(show);
        }
    }
    f.getContentPane().animateLayout(250);
});
         
         
         
         
         
         
         
         
         
         
         
         
         
         Toolbar tbh = f.getToolbar();
         ServiceDemandeClub service = new ServiceDemandeClub();
         for (DemandeClub a : service.RecupererDemandes()){
             Label nom = new Label(a.getNom_club());
            nom.getAllStyles().setFgColor(0x8c1010);
            Label description = new Label(a.getDescription());
            description.getAllStyles().setFgColor(0x8c1010);
             Label domaine = new Label(a.getDomaine());
            domaine.getAllStyles().setFgColor(0x8c1010);
            Label etat = new Label(a.getEtat());
            etat.getAllStyles().setFgColor(0x8c1010);
            
            Label xx = new Label("  ");
            Label x = new Label("  ");
            
            f.add(nom);
            f.add(description);
            f.add(domaine);
            f.add(etat);
            f.add(x);
            f.add(xx);
            
            
            
             
             
         }
       tbh.addMaterialCommandToSideMenu("Ajouter", FontImage.MATERIAL_BUILD, new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent evt) {
                              
                     AjouterDemande a= new AjouterDemande();
                     a.getF().show();
                     
                     
                 }
             }); 
        
    }
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
