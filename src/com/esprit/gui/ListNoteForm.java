/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.esprit.services.ServiceNotes;

/**
 *
 * @author bhk
 */
public class ListNoteForm extends Form{

    public ListNoteForm(Form previous) {
        setTitle("Liste des Notes");
        
        SpanLabel sp = new SpanLabel();
        sp.setText(ServiceNotes.getInstance().getAllTasks().toString());
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
    
}
