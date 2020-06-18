/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.demos.kitchen.SalesDemo;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.execute;
import static com.codename1.ui.CN.isNativeShareSupported;
import static com.codename1.ui.CN.share;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;


/**
 *
 * @author bhk
 */
public class HomeFormTaches extends Form{


    public HomeFormTaches(Form previous) {
       
        setTitle("Interface enseignant");
        setLayout(BoxLayout.y());
         
        
        add(new Label("choisir Votre option"));
        add(new Label("","background.jpg"));
        Button btnAddTask = new Button("Ajouter une tache");
        btnAddTask.setUIID("RedCard");
        Button btnListTasks = new Button("Consulter la liste des taches");
        btnListTasks.setUIID("GroupElementOnly");
       
        
        
        btnAddTask.addActionListener(e-> new ajoutertache(this).show());
        btnListTasks.addActionListener(e-> new consulterTache(this).show());
        addAll(btnAddTask,btnListTasks);
       //  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new HomeForm(this));
     //   this.getToolbar().addMaterialCommandToSideMenu("Gestion des Notes & d'évaluations", FontImage.MATERIAL_AC_UNIT, e-> new HomeForm(this).show());
      //   this.getToolbar().addMaterialCommandToSideMenu("Gestion des Notes & d'évaluations", FontImage.MATERIAL_AC_UNIT, e-> new HomeForm(this).show());
      //  f.getToolbar().addMaterialCommandToSideMenu("Gestion des taches", FontImage.MATERIAL_BOOKMARK_BORDER, e-> new HomeFormTaches(currentForm).show());
//         f.getToolbar().addMaterialCommandToSideMenu("Consulter la liste des Notes", FontImage.MATERIAL_ADD_TO_QUEUE, e-> new ListNoteForm(currentForm).show());
//         f.getToolbar().addMaterialCommandToSideMenu("Consulter la listes des évaluations", FontImage.MATERIAL_ALBUM, e-> new ListEvaluationForm(currentForm).show());
        this.getToolbar().addMaterialCommandToSideMenu("Gestion des Notes & d'évaluations", FontImage.MATERIAL_AC_UNIT, e-> new HomeForm().show());
        this.getToolbar().addMaterialCommandToSideMenu("CodenameOne.com", 
                FontImage.MATERIAL_WEB, e -> execute("https://www.codenameone.com/"));
        this.getToolbar().addMaterialCommandToSideMenu("Getting Started", FontImage.MATERIAL_WEB, e -> execute("https://www.codenameone.com/"));
        this.getToolbar().addMaterialCommandToSideMenu("Developer Guide", FontImage.MATERIAL_WEB, e -> execute("https://www.codenameone.com/files/developer-guide.pdf"));
        this.getToolbar().addMaterialCommandToSideMenu("JavaDoc (Reference)", FontImage.MATERIAL_WEB, e -> execute("https://www.codenameone.com/javadoc/"));
        this.getToolbar().addMaterialCommandToSideMenu("Source Code", FontImage.MATERIAL_WEB, e -> execute("https://github.com/codenameone/KitchenSink"));
      
        this.getToolbar().addMaterialCommandToSideMenu("About", 
                FontImage.MATERIAL_INFO, e -> {
                    Dialog.show("About", "KitchenSink provides an overview of the core Codename One capaiblities. "
                            + "Codename One allows Java developers to create native mobile applications that work everywhere!", "OK", null);
                });
        
    }

   
    
}
