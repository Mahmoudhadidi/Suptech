
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.ui.Button;

import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Note;
import com.esprit.entities.taches;
import com.esprit.services.ServiceNotes;
import com.esprit.services.tachesDAO;


/**
 *
 * @author bhk
 */
public class ajoutertache extends Form{

    public ajoutertache(Form previous) {
        setTitle("Ajouter une tache");
        setLayout(BoxLayout.y());
        
        TextField tfNoteCc = new TextField("","Date tache");
        TextField tfNoteDs= new TextField("", "Créer votre tache");
        
        Button btnValider = new Button("Ajouter");
        btnValider.setUIID("RedCard");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNoteCc.getText().length()==0)||(tfNoteDs.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", "OK","Cancel");
                    
                else
                {
                    try {
                         taches p1 = new taches(tfNoteCc.getText(), tfNoteDs.getText());
            tachesDAO pdao = new tachesDAO();
            pdao.ajouter(p1);
                            Dialog.show("Success","Note ajoutée","OK","Cancel");
                        
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Try again","OK","Cancel");
                    }
                    tfNoteCc.setText("");
                    tfNoteDs.setText("");
                   
                    
                }
                
                
            }
        });
        
        addAll(tfNoteCc,tfNoteDs,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}
