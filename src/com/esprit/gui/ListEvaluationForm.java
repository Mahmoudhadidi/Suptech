/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.components.SpanLabel;

import com.codename1.demos.kitchen.KitchenSink;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.esprit.services.ServiceEvaluation;


/**
 *
 * @author bhk
 */
public class ListEvaluationForm extends Form{

    public ListEvaluationForm(Form previous) {
        setTitle("Liste d'evaluations");
        
        SpanLabel sp = new SpanLabel();
        sp.setText(ServiceEvaluation.getInstance().getAllTasks().toString());
        add(sp);
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }

   
    
    
}
