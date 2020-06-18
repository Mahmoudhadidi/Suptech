/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.components.ImageViewer;
import com.codename1.demos.kitchen.KitchenSink;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.execute;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form{

Form current;
    public HomeForm() {
        current=this;
   
       
        setTitle("Interface enseignant");
        setLayout(BoxLayout.y());
         
        ImageViewer im = new ImageViewer();
       // im.setImage();
        add(new Label("choisir Votre option"));
    
        Button btnAddTask = new Button("Liste d'evaluations"); 
        btnAddTask.setUIID("InputAvatarImage");
        
        Button btnListTasks = new Button("Liste des Notes");
        btnListTasks.setUIID("GroupElementOnly");
        
        Button btnAjouter = new Button("Ajouter une Note");
        btnAjouter.setUIID("TouchCommand");
        
        Button btnAjouter1 = new Button("Ajouter une évaluation");
        btnAjouter1.setUIID("GroupElementOnly");
        
        Button btnpdf = new Button("Télécharger liste des notes PDF");
        btnpdf.setUIID("GroupElementOnly");
        
        Button btncsv = new Button("Télécharger liste d'évaluation EXCEL");
        btncsv.setUIID("RedCard");
        
        Button btntaches = new Button("Gestion des taches");
        btntaches.setUIID("GroupElementOnly");
         
         
         
          
        btntaches.addActionListener(e-> new HomeFormTaches(this).show());
        btnpdf.addActionListener(e->execute("http://localhost:8080/GestionEcole/web/app_dev.php/note/pdf"));
        btncsv.addActionListener(e->execute("http://localhost:8080/GestionEcole/web/app_dev.php/note/csv"));
        btnAjouter1.addActionListener(e-> new AddEvaluationForm(this).show());
        btnAjouter.addActionListener(e-> new AddNoteForm(this).show());
        btnAddTask.addActionListener(e-> new ListEvaluationForm(this).show());
        btnListTasks.addActionListener(e-> new ListNoteForm(this).show());
        addAll(btnAddTask,btnListTasks,btnAjouter,btnAjouter1,btnpdf,btncsv,btntaches);
        // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new KitchenSink());
         this.getToolbar().addMaterialCommandToSideMenu("Créer un compte", FontImage.MATERIAL_AC_UNIT, e-> new Register(this).show());
        this.getToolbar().addMaterialCommandToSideMenu("Déconnexion", FontImage.MATERIAL_AC_UNIT, e-> new Login(this).show());
      //   this.getToolbar().addMaterialCommandToSideMenu("Gestion des Notes & d'évaluations", FontImage.MATERIAL_AC_UNIT, e-> new HomeForm(this).show());
       //  this.getToolbar().addMaterialCommandToSideMenu("Gestion des Notes & d'évaluations", FontImage.MATERIAL_AC_UNIT, e-> new HomeForm(this).show());
      //  f.getToolbar().addMaterialCommandToSideMenu("Gestion des taches", FontImage.MATERIAL_BOOKMARK_BORDER, e-> new HomeFormTaches(currentForm).show());
//         f.getToolbar().addMaterialCommandToSideMenu("Consulter la liste des Notes", FontImage.MATERIAL_ADD_TO_QUEUE, e-> new ListNoteForm(currentForm).show());
//         f.getToolbar().addMaterialCommandToSideMenu("Consulter la listes des évaluations", FontImage.MATERIAL_ALBUM, e-> new ListEvaluationForm(currentForm).show());
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
         
 //  public HomeForm(){}
    
}
