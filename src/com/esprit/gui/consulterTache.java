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
import com.esprit.entities.taches;
import com.esprit.services.ServiceEvaluation;
import com.esprit.services.tachesDAO;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author bhk
 */
public class consulterTache extends Form{

    public consulterTache(Form previous) {
        setTitle("Liste d'evaluations");
         tachesDAO pdao = new tachesDAO();
       
            try {
                this.add(pdao.getItems());
                System.out.println(pdao.getPerson());
            } catch (IOException ex) {
                System.out.println(ex);
            }
       this.show();
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }

   
    
    
}
