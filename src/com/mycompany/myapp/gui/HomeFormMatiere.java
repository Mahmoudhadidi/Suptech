/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import static com.codename1.ui.CN.execute;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author bhk
 */
public class HomeFormMatiere extends Form{

    public HomeFormMatiere(Form previous) {
        
        setTitle("Home Mati�re");
        setLayout(BoxLayout.y());
        add(new Label("Choose an option"));
        Button btnAddMatiere = new Button("Ajouter Mati�re");
        Button btnListMatiere = new Button("List Mati�re");
        
     
        btnListMatiere.addActionListener(e-> new ListMatiereForm(this).show());
        btnAddMatiere.addActionListener(e-> new AddMatierekForm(this).show());
      addAll(btnAddMatiere,btnListMatiere);
     
        
        
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
    
}
